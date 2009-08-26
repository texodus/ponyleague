package com.steinlink.ponyleague.core.composition.audubon;

import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.song.Note;
import com.steinlink.ponyleague.core.song.Voice;

import java.util.*;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 27, 2007
 * Time: 3:59:52 PM
 * AudubonVoice is a special subclass of Voice that provides transaction history through a spinal
 * object, voiceStack.  A new voice need only call constructor, but to add new voices to the same
 * spine, the addVoice method must be used.  If the voice is empty, then a call to getNotes will
 * return a single null note, never a null list
 */
public class AudubonVoice extends Voice {

    protected final Log log = LogFactory.getLog(getClass());
    
    private List<AudubonVoice> voiceStack;
    private int nodeID;

    public int getStackSize() {
        return voiceStack.size();
    }

    public AudubonVoice(Instrument ins) {
        super();

        AudubonNote note = new AudubonNote();

        note.setPitch(1);
        note.setRhythm(1);
        note.setVelocity(1);

        getNotes().add(note);
        instrument = ins;
        nodeID = 0;
        
        // add itself to the stack
        voiceStack = new LinkedList<AudubonVoice>();
        voiceStack.add(this);
    }

    protected AudubonVoice(List<AudubonVoice> stack) {
        super();

        // add itself to the stack
        voiceStack = stack;
        voiceStack.add(this);
    }

    public void process(Gene gene) {

        AudubonVoice voice = new AudubonVoice(voiceStack);
        voice.internalProcess(voiceStack.get(voiceStack.size() - 2), gene);
    }

    /**
     * Method does the actual replacement operations on the voice from the replacement rules of a
     * supplied gene
     * @param parentVoice
     * @param gene
     */
    protected void internalProcess(AudubonVoice parentVoice, Gene gene) {

        nodeID = gene.getNodeID();

        Set<Integer> seed = new HashSet<Integer>();
        for (int j = 1; j < gene.getComplexityIterations(); j ++) {
            seed.add(Note.getRandomPitch());
        }
        
        for (int i = 0; i < parentVoice.getNotes().size(); i ++) {

            AudubonNote note = (AudubonNote)parentVoice.getNotes().get(i);
            if (seed.contains(note.getPitch()) || (voiceStack.size() < 3))  {
                replace(i, gene, note);
            } else {

                AudubonNote newNote = (AudubonNote)note.clone();
                newNote.setParentID(i);
                getNotes().add(newNote);
            }
        }
    }

    private void replace(int x, Gene gene, AudubonNote parentNote) {

        for (int i = 0; i < gene.getNotes().length; i ++) {

            AudubonNote newNote = new AudubonNote(gene.getNotes()[i]);
            newNote.setParentID(x);
            newNote.setPitch(newNote.getPitch() + parentNote.getPitch());
            getNotes().add(newNote);
        }
    }

    public AudubonVoice clone() {
        AudubonVoice voice = new AudubonVoice(instrument);

        for (Iterator i = getNotes().iterator(); i.hasNext();) {
            AudubonNote note = (AudubonNote)i.next();
            voice.getNotes().add(note.clone());
        }

        voice.setNodeID(nodeID);

        return voice;
    }

    public String parentToString() {

        String s = "";
        int lastParent = 0;
        for (Iterator i = voiceStack.get(voiceStack.size() - 1).getNotes().iterator(); i.hasNext();) {

            AudubonNote note = (AudubonNote)i.next();
            if (note.getParentID() != lastParent) {
                s += " - ";
                lastParent ++;
            }

            s += ((note.getParentID() < 10) ? " " + Integer.toString(note.getParentID()) : note.getParentID());
        }

        return s;
    }

    public String pitchToString() {

         String s = "";
         int lastParent = 0;
         for (Iterator i = voiceStack.get(voiceStack.size() - 1).getNotes().iterator(); i.hasNext();) {

             AudubonNote note = (AudubonNote)i.next();
             if (note.getParentID() != lastParent) {
                 s += " - ";
                 lastParent ++;
             }

             s += ((note.getPitch() < 10) ? " " + Integer.toString(note.getPitch()) : note.getPitch());
         }

         return s;
     }


    public String rhythmToString() {

        String s = "";
        int lastParent = 0;
        for (Iterator i = voiceStack.get(voiceStack.size() - 1).getNotes().iterator(); i.hasNext();) {

            AudubonNote note = (AudubonNote)i.next();
            if (note.getParentID() != lastParent) {
                s += " - ";
                lastParent ++;
            }

            s += " " + Integer.toString(note.getRhythm());
        }

        return s;
    }

    public String longRhythmToString() {

        String s = "";
        int lastParent = 0;
        for (Iterator i = voiceStack.get(voiceStack.size() - 1).getNotes().iterator(); i.hasNext();) {

            AudubonNote note = (AudubonNote)i.next();
            if (note.getParentID() != lastParent) {
                s += " - ";
                lastParent ++;
            }

            s += " " + Long.toString(note.getLongRhythm());
        }

        return s;
    }

    /**
     * return note at x from voice at top of stack
     * @param x
     * @return
     */
    public AudubonNote getStackNote(int x) {
        return (AudubonNote)voiceStack.get(voiceStack.size() - 1).getNotes().get(x);
    }

    public int getPitch(int i) {
        return getStackNote(i).getPitch();
    }

    public int getVelocity(int i) {
        return getStackNote(i).getVelocity();
    }

    public void pop() {
        voiceStack.remove(voiceStack.size() - 2);
    }

    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int i) {
        nodeID = i;
    }

    public List<Note> getStackNotes() {
        return voiceStack.get(voiceStack.size() - 1).getNotes();
    }

    public AudubonNote getParentNote(int parentID) {
        return (AudubonNote)voiceStack.get(voiceStack.size() - 2).getNotes().get(parentID);
    }

    public int getParentSize() {
        return voiceStack.get(voiceStack.size() - 2).getNotes().size();
    }

    public int getParentNodeID() {
        return voiceStack.get(voiceStack.size() - 2).nodeID;
    }

    public void logVoice() {
        log.debug("Parent row : " + this.parentToString());
        log.debug("Rhythm row : " + this.rhythmToString());
        log.debug("Pitch row  : " + this.pitchToString());
    }

    public void logVoiceLong() {
        log.debug("Parent row : " + this.parentToString());
        log.debug("Rhythm row : " + this.longRhythmToString());
        log.debug("Pitch row  : " + this.pitchToString());
    }
}
