package com.steinlink.ponyleague.core.song;

import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.ponyleague.core.model.Instrument;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 27, 2007
 * Time: 3:21:47 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Voice implements Cloneable{

    private List<Note> notes;
    protected Instrument instrument;
    
    
    public abstract void process(Gene gene);

    public Voice() {
        notes = new LinkedList<Note>();
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public int getPitch(int i) {
        return notes.get(i).getPitch();
    }

    public int getVelocity(int i) {
        return notes.get(i).getPitch();
    }

    public int size() {
        return notes.size();
    }


    protected List<Note> getNotes() {
        return notes;
    }

    public abstract Voice clone();

}
