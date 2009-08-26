package com.steinlink.ponyleague.core.composition.audubon;

import com.steinlink.ponyleague.core.song.Note;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 27, 2007
 * Time: 3:59:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class AudubonNote extends Note {

    private int parent = 0;
    private long floatRhythm = -1;

    public AudubonNote(Note note) {
        super();

        this.setRhythm(note.getRhythm());
        this.setPitch(note.getPitch());
        this.setVelocity(note.getVelocity());
    }

    public AudubonNote() {
        super();
    }

    public int getParentID() {
        return parent;
    }

    public void setParentID(int parent) {
        this.parent = parent;
    }

    public Note clone() {

        AudubonNote note = new AudubonNote();
        note.setPitch(this.getPitch());
        note.setRhythm(this.getRhythm());
        note.setVelocity(this.getVelocity());
        note.setParentID(parent);
        
        return note;
    }

    public long getLongRhythm() {

        if (floatRhythm == -1) {
            floatRhythm = this.getRhythm();
        }

        return floatRhythm;
    }

    public void setLongRhythm(long x) {
        floatRhythm = x;
    }
}
