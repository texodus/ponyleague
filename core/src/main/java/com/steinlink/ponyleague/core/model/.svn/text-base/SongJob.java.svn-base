package com.steinlink.ponyleague.core.model;

import com.steinlink.model.BaseObject;
import com.steinlink.model.User;

import javax.persistence.*;
import javax.sound.midi.Sequence;
import javax.sound.midi.MidiSystem;

import com.steinlink.ponyleague.core.song.SongModel;

import java.io.BufferedOutputStream;
import java.io.InputStream;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 6:58:33 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class SongJob extends BaseObject {

    private long id;
    private User username;
    private SongModel songModel;
    private int percentCompleted;
    private String status;
    private String fileName;
    private Sequence midi = null;

    public static String STATUS_NEW = "NEW";
    public static String STATUS_OPEN = "OPEN";
    public static String STATUS_PROCESSING = "PROCESSING";
    public static final String STATUS_DONE = "DONE";
    public static final String STATUS_ERROR = "ERROR";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name="UserId")
    public User getUser() {
        return username;
    }

    public void setUser(User username) {
        this.username = username;
    }

    @Lob
    public SongModel getSong() {
        return songModel;
    }

    public void setSong(SongModel genes) {
        this.songModel = genes;
    }

    @Lob
    public byte[] getMidi() {

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        try {
            MidiSystem.write(midi,1,stream);
        }  catch (Exception e) {
            // DO Nothing!
        }
        return stream.toByteArray();
    }

    public void setMidi(byte[] s) {
        ByteArrayInputStream stream = new ByteArrayInputStream(s);

        try {
            midi = MidiSystem.getSequence(stream);
        } catch (Exception e) {
            // Do Nothing
        }

    }

    public void setMidi(Sequence s) {
        midi = s;
    }

    public int getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(int percentCompleted) {
        this.percentCompleted = percentCompleted;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean equals(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int hashCode() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
