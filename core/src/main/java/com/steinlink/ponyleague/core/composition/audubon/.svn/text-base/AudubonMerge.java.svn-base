package com.steinlink.ponyleague.core.composition.audubon;

import com.steinlink.ponyleague.common.util.MathUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 28, 2007
 * Time: 1:58:47 AM
 * To change this template use File | Settings | File Templates.
 */
public class AudubonMerge {

    protected final Log log = LogFactory.getLog(getClass());

    public void doMerge(List<AudubonVoice> voices) throws ClassCastException {
        
		log.debug("Starting merge.");

        mergeRhythm(voices);
        mergePitch(voices);
		mergeVelocity(voices);
		mergeMetadata(voices);

        log.debug("Finished merge successfully.");
    }

	/**
	 * RHYTHMIC HARMONIZATION
	 * for each parent-related subgroup of the tracks to be merged, get the total absolute
	 * length of the subgroup, and then multiply the durations of each note by (length of longest
	 * parent-related subgroup)/(length of current parent-related subgroup)
	 */
	private void mergeRhythm(List<AudubonVoice> voices) {

        log.debug("Merging rhythm.");

        // for every node in the parent track, perform a seperate merge calculation
		for(int i = 0; i < voices.get(0).getParentSize(); i ++) {

            List<Long> trackSegmentSizes = getTrackSegmentSizes(voices, i);

            // iterate through each track that needs to be merged
			for (int k = 0; k < voices.size(); k ++) {

                log.debug("Group " + i + " is " + trackSegmentSizes.get(k) + " ticks long.");
                long modulus = 0;

                try {
                    modulus = MathUtil.lcm(trackSegmentSizes)/trackSegmentSizes.get(k);
                } catch (Exception e) {
                    log.error("Modulus calculation failed; setting modulus = 1");
                    modulus = 1;
                }

                log.debug("Calculated lcm/segmentlength of " + modulus);

                // iterate through each note in the current track
				for (int j = 0; j < voices.get(k).getStackNotes().size(); j ++) {

					// if this note is derived from the note of the parent track we are currently looking at (i) ...
					if (voices.get(k).getStackNote(j).getParentID() == i) {

						// multiply the duration of this note by the lcm/size of the current track
						voices.get(k).getStackNote(j).setLongRhythm(voices.get(k).getStackNote(j).getLongRhythm()* modulus);
					}
				}
			}

			List noteSizes = getNoteSizes(voices, i);

            // par the size down as much as possible
			long tracksGcd = MathUtil.gcd(noteSizes);
            /*
            log.debug("Trimming segment with gcd " + tracksGcd);

            while ((tracksGcd > 1)) {

				for (int k = 0; k < voices.size(); k++) {
					for (int j = 0; j < voices.get(k).getStackNotes().size(); j ++) {
						if (voices.get(k).getStackNote(j).getParentID() == i) {
							voices.get(k).getStackNote(j).setLongRhythm(
                                    voices.get(k).getStackNote(j).getLongRhythm()  / tracksGcd
							);
                        }
					}
                }

                noteSizes = getNoteSizes(voices, i);
				tracksGcd = MathUtil.gcd(noteSizes);
			}            */

            // sanity check
            List<Long> lengths = getTrackSegmentSizes(voices, i);
            Long first = lengths.get(0);
            for (int k = 1; k < lengths.size(); k ++) {
                if (lengths.get(k) != first) {
                    log.error("Segment lengths do not match!");
                }
            }


            for (int k = 0; k < voices.size(); k ++) {
                voices.get(k).logVoiceLong();
            }
        }
    }

    /**
     * Calculate a list of the float duration sums of each 
     * @param voices
     * @param parentID
     * @return
     */
    private static List<Long> getTrackSegmentSizes(List<AudubonVoice> voices, int parentID) {

        long sum = 0;
        List<Long> sums = new LinkedList<Long>();

        for (Iterator i = voices.iterator(); i.hasNext();) {
            AudubonVoice voice = (AudubonVoice)i.next();

            for (int x = parentID; x < voice.getStackNotes().size(); x ++) {
                if (voice.getStackNote(x).getParentID() == parentID) {
                    sum += voice.getStackNote(x).getLongRhythm();
                }
            }

            sums.add(sum);
            sum = 0;
        }

        return sums;
    }

    /**
     * Get a list of the sizes of each note in the track under the specified parent
     * @param parent
     * @return a list of the sizes of each note under the given parent node
     */
    private static LinkedList<Long> getNoteSizes(List<AudubonVoice> voices, int parent) {

        LinkedList<Long> temp = new LinkedList<Long>();
        for (int k = 0; k < voices.size(); k ++) {
            for (int l = 0; l < voices.get(k).getStackNotes().size(); l++) {

                if ((voices.get(k).getStackNote(l).getParentID() == parent)
                        && !temp.contains(voices.get(k).getStackNote(l).getLongRhythm())) {

                    temp.add(voices.get(k).getStackNote(l).getLongRhythm());
                }
            }
        }
        return temp;
    }


    /**
	 * PITCH HARMONIZATION
	 * for the heaviest track, do nothing;  for every other track, treat the the previous track as a modulus
	 */
	private void  mergePitch(List<AudubonVoice> voices) {

        log.debug("Merging pitch.");

        if (voices.size() > 1) {

            // for each parent related subgroup ...
			for (int j = 0; j < voices.get(0).getParentSize(); j ++) {
				// for each track - skip the first, anchor
				for (int k = 1; k < voices.size(); k ++) {
					// for each note in the same parent related subgroup
					for (int l = 0; l < voices.get(k).size(); l ++) {

						// if this is an element of the parent related subgroup under scrutiny ...
						long currentGroupOffset = 0;
						if ((voices.get(k).getStackNote(l).getParentID() == j)) {

							// determine which note to harmonize against by counting the absolute durations
							int index = 0;
							while (voices.get(k).getStackNote(index).getLongRhythm() < currentGroupOffset) {
								index ++;
							}

							currentGroupOffset += voices.get(k).getStackNote(l).getRhythm();

							// write the modulus to the voice
							voices.get(k).getStackNote(l).setPitch(
                                    voices.get(k).getStackNote(l).getPitch()
                                    + voices.get(k - 1).getStackNote(index).getPitch()
                            );
						}
					}
                }
			}
		}

        for (int i = 0; i < voices.size(); i ++) {
            voices.get(i).logVoiceLong();
        }
    }

	/**
	 * VELOCITY HARMONIZATION
	 * TODO I'm not sure how to do this best yet;  copy pitch for now, it will give a nice distribution that
	 * guarantees one voice will usually dominate and preserve a relatively constant volume,
	 * disregarding nulls
	 */
	private static void mergeVelocity(List<AudubonVoice> voices) {

		if (voices.size() > 1) {
			// for each parent related subgroup ...
			for (int j = 0; j < voices.get(0).getParentSize(); j ++) {
				// for each track - skip the first, anchor
				for (int k = 1; k < voices.size(); k ++) {
					// for each note in the same parent related subgroup
					for (int l = 0; l < voices.get(k).size(); l ++) {

						// if this is an elemnt of the parent related subgroup under scrutiny ...
						long currentGroupOffset = 0;
						if ((voices.get(k).getStackNote(l).getParentID() == j)) {

							// determine which note to harmonize against by counting the absolute durations
							int index = 0;

							while (voices.get(k).getStackNote(index).getLongRhythm() < currentGroupOffset) {
								index ++;
							}

							currentGroupOffset += voices.get(k).getStackNote(l).getRhythm();

							voices.get(k).getStackNote(l).setVelocity(
                                    voices.get(k).getStackNote(l).getVelocity()
                                    + voices.get(k - 1).getStackNote(index).getVelocity()
							);

						}
					}
				}
			}
		}
	}

	/**
	 * METADATA HARMONIZATION
	 * Set each parent element to the parent element of its parent node, then fix the treepaths
	 * in the songObject object to point to their respective parents.
	 */
	private static void mergeMetadata(List<AudubonVoice> voices) {

		for (int i = 0; i < voices.size(); i ++) {
			for (int k = 0; k < voices.get(i).getStackNotes().size(); k ++) {
				try {
					voices.get(i).getStackNote(k).setParentID(
                            voices.get(i).getParentNote(
                                    voices.get(i).getStackNote(k).getParentID()
                            ).getParentID()
					);
				} catch(IndexOutOfBoundsException e) {

				}
			}
		}

		// tear-down;
		// make each treepath point to it's parent unless it is the root node

		for (int i = 0; i < voices.size(); i++) {
			if (voices.get(i).getStackSize() != 0) {
			    voices.get(i).pop();
			}
		}
	}
}
