package com.steinlink.ponyleague.core.composition;
                                                                                            
import com.steinlink.ponyleague.core.CompositionEngineAdapter;
import com.steinlink.ponyleague.core.composition.audubon.*;
import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.ponyleague.core.song.SongModel;

import javax.sound.midi.*;
import javax.swing.tree.*;
import java.util.*;

import org.springframework.context.ApplicationContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 15, 2007
 * Time: 4:16:55 PM
 * To change this template use File | Settings | File Templates.
 */
public class AudubonEngineAdapter implements CompositionEngineAdapter {

    protected final Log log = LogFactory.getLog(getClass());

    private List<TreePath> tracks;
    private ApplicationContext ctx;

    private int ticksPerMeter = 1024;

    /**
     * Since the audubon engine is legacy code, quite a bit of work needs to be done in order
     * to utilize it as a composition engine for SongMonkey.  The complexity lies in converting
     * a SongObject into a grammar and treepath list.
     * @param songModel
     * @return
     */
    public Sequence compose(SongModel songModel, ApplicationContext c) throws Exception {

        ctx = c;
        List<AudubonVoice> voices = buildVoicesUp(buildVoicesDown(new AudubonSongModel(songModel)));

        return processVoices(voices);
    }

    /**
     * Harmonize a list of voices
     * @param voices
     * @return
     */
    public List<AudubonVoice> buildVoicesUp(List<AudubonVoice> voices) throws Exception {

        log.debug("Building up phase");

        // determine the maximum depth of the tracks
        int deepestLevel = findDeepestLevel(voices);
        AudubonMerge merge = new AudubonMerge();

        while (deepestLevel > 1) {

            // make a list deepestTracks of all tracks at this depth
            List<AudubonVoice> deepestTracks = getTracksAtLevel(deepestLevel, voices);
            log.debug("Found " + deepestTracks.size() + " voices at the deepest level " + deepestLevel);

            while (!deepestTracks.isEmpty()) {

                LinkedList<AudubonVoice> deepestTracksToMerge = new LinkedList<AudubonVoice>();

                // remove the first track from this list, put it into a new list toMergeTracks
                deepestTracksToMerge.add(deepestTracks.remove(0));

                // find any other tracks in deepestTracks that have the same parent as the first track in toMergeTracks, and
                // move these tracks to toMergeTracks as well
                if (deepestTracks.size() > 0) {
                    for (int i = 0; i < deepestTracks.size(); i ++) {

                        if (deepestTracks.get(i).getParentNodeID() == deepestTracksToMerge.get(0).getParentNodeID()) {
                            deepestTracksToMerge.add(deepestTracks.remove(i));
                            i --;
                        }
                    }
                }

                log.debug("Group has " + deepestTracksToMerge.size() + " voices");

                // perform a merge on the SongObject object for toMergeTracks
                try {
                    merge.doMerge(deepestTracksToMerge);
                } catch (Exception e) {
                    log.error("Failed to merge voices.");
                    throw e;
                }
            }

            // every track on this level should have been processed, and deepestLevel - 1 should have the next tracks
            // that need processing
            deepestLevel --;
        }

        // tear-down
        // Compress the dynamic range of the durations;
        //
        // First, get the longest note in the song
        long longestNote = 0;
        for (int i = 0; i < voices.size(); i ++) {
            for (int j = 0; j < voices.get(i).getStackNotes().size(); j ++) {
                if (voices.get(i).getStackNote(j).getLongRhythm() > longestNote) {
                    longestNote = voices.get(i).getStackNote(j).getLongRhythm();
                }
            }
        }

        // now we know the longest note played, modulate the song
        for (int i = 0; i < voices.size(); i ++) {
            for (int j = 0; j < voices.get(i).getStackNotes().size(); j ++) {

                AudubonNote note = (AudubonNote)voices.get(i).getStackNotes().get(j);

                // scale the notes so no note is longer than ppq * 16
                note.setLongRhythm((note.getLongRhythm() * ticksPerMeter) / longestNote);
            }
        }

        return voices;
    }

    /**
     * Generate a list of voices from a songmodel
     * @param song
     * @return
     */
    private List<AudubonVoice> buildVoicesDown(AudubonSongModel song) throws Exception {

        log.debug("Building down phase");

        List<AudubonVoice> voices = new LinkedList<AudubonVoice>();
        List<AudubonChromosome> chromes = song.generateChromosomes(ctx);

        // parse through each chromosome and calculate voices for each
        for (Iterator i = chromes.iterator(); i.hasNext();) {

            AudubonChromosome chrome = (AudubonChromosome)i.next();
            AudubonVoice voice = new AudubonVoice(chrome.getInstrument());

            log.debug("Creating new voice, " + chrome.getInstrument().getName() + " of " + chrome.getGeneLength() + " nodes.");
  
            for (Iterator j = chrome.iterator(); j.hasNext();) {

                Gene gene = (Gene)j.next();

                log.debug("Processing gene " + gene.toString());

                try {
                    voice.process(gene);
                } catch (Exception e) {
                    log.error("Processing gene failed.");
                    throw e;
                }

                log.debug("Proessing gene successful");
                voice.logVoice();
            }

            voices.add(voice);
            log.debug("Successfully created voice.");
        }

        return voices;
    }

    /**
     * Get the voice with the largest stack
     * @param voices
     * @return
     */
    private int findDeepestLevel(List<AudubonVoice> voices) {

        int deepestLevel = 0;

        for (Iterator i = voices.iterator(); i.hasNext();) {
            AudubonVoice voice = (AudubonVoice)i.next();

            if (voice.getStackSize() > deepestLevel) {
                deepestLevel = voice.getStackSize();
            }
        }

        return deepestLevel;
    }

    /**
     * Returns all voices in a set that are at a specified height
     * @param level
     * @param voices
     * @return
     */
    private List<AudubonVoice> getTracksAtLevel(int level, List<AudubonVoice> voices) {

        List<AudubonVoice> newVoices = new LinkedList<AudubonVoice>();

        for (Iterator i = voices.iterator(); i.hasNext();) {
            AudubonVoice voice = (AudubonVoice)i.next();
            if (voice.getStackSize() == level) {
                newVoices.add(voice);
            }
        }

        return newVoices;
    }

    /**
     * Build a midi sequence from a set of voices
     * @param voices
     * @return
     */
    private Sequence processVoices(List<AudubonVoice> voices) {

        Sequence newSequence;
        MidiEvent event;
        ShortMessage message;
        int i = 0, p = 0;
        long index;


        try {

            newSequence = new Sequence(Sequence.PPQ, ticksPerMeter/4);

            for(int l = 0; l < voices.size(); l ++) {

                // create a new midi message setting the instrument
                Track currentTrack = newSequence.createTrack();
                AudubonVoice currentVoice = voices.get(l);

                index = 0;

                // set the instrument for the track
                message = new ShortMessage();
                message.setMessage(192, currentVoice.getInstrument().getMidiValue(), 120);
                event = new MidiEvent(message, index);
                currentTrack.add(event);

                // iterate over every note in the current voice
                for(int j = 0; j < currentVoice.getStackNotes().size(); j ++) {

                    // normalize and bound pitch and velocity
                    if (currentVoice.getPitch(j) >= 0) {
                        while (currentVoice.getPitch(j) < currentVoice.getInstrument().getLowerbound()) {
                            currentVoice.getStackNote(j).setPitch(currentVoice.getPitch(j) + 12);
                        }

                        while (currentVoice.getPitch(j) > currentVoice.getInstrument().getUpperbound()) {
                            currentVoice.getStackNote(j).setPitch(currentVoice.getPitch(j) - 12);
                        }

                    }

                    currentVoice.getStackNote(j).setVelocity(currentVoice.getStackNote(j).getVelocity() + currentVoice.getInstrument().getVelocityOffset());

                    // check to see if this note is muted
                    if (currentVoice.getPitch(j) == 0) {
                        index += (Math.abs(currentVoice.getStackNote(j).getLongRhythm()));
                    } else {
                        // write the start of the note
                        message = new ShortMessage();
                        message.setMessage(
                                ShortMessage.NOTE_ON,
                                Math.abs(currentVoice.getPitch(j)),
                                Math.abs(currentVoice.getVelocity(j))
                        );

                        MidiEvent startEvent = new MidiEvent(message, index);
                        index += (Math.abs(currentVoice.getStackNote(j).getLongRhythm()));

                        // write the end of the note
                        message = new ShortMessage();
                        message.setMessage(
                                ShortMessage.NOTE_OFF,
                                Math.abs(currentVoice.getPitch(j)),
                                Math.abs(currentVoice.getVelocity(j))
                        );

                        MidiEvent endEvent = new MidiEvent(message, index);

                        // write the notes to the track
                        currentTrack.add(startEvent);
                        currentTrack.add(endEvent);
                    }
                    p ++;
                }
            }
        } catch (InvalidMidiDataException e) {
            return null;
        }

        return newSequence;
    }

}
