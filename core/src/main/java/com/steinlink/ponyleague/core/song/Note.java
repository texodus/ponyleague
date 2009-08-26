package com.steinlink.ponyleague.core.song;

import java.util.Random;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 18, 2007
 * Time: 4:11:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class Note implements Serializable, Comparable<Note>, Cloneable {

    private int pitch;
    private int velocity;
    private int rhythm;

    public int compareTo(Note note) {
        if (Integer.parseInt(note.getId()) < Integer.parseInt(getId())) return -1;
        if (Integer.parseInt(note.getId()) > Integer.parseInt(getId())) return 1;
        else return 0;
    }

    public Note clone() {

        Note note = new Note();
        note.setPitch(this.getPitch());
        note.setRhythm(this.getRhythm());
        note.setVelocity(this.getVelocity());

        return note;
    }

    public String getId() {
        return "" + pitch + "" + velocity + "" + rhythm;
    }
    
    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public int getRhythm() {
        return rhythm;
    }

    public void setRhythm(int rhythm) {
        this.rhythm = rhythm;
    }

    public static int getRandomPitch() {
        return (new Random()).nextInt(13);
    }
}
