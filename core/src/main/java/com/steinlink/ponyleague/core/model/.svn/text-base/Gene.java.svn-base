package com.steinlink.ponyleague.core.model;

import com.steinlink.model.BaseObject;
import org.hibernate.annotations.CollectionOfElements;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.lang.reflect.Array;

import com.steinlink.ponyleague.core.song.Note;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 6:59:01 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Gene extends BaseObject {

    private Style style;
    private Long id = null;
    private Set<Instrument> instruments;
    private String complexity;
    private Note[] notes;
    private int points;
    private int votes;
    private int nodeID;
  
    @Transient
    public Note getNote(int index) {
        return notes[index % Array.getLength(notes)];
    }

    @Transient
    public List<Integer> getPitches() {
        List<Integer> pitches = new LinkedList<Integer>();
        for (int i = 0; i < Array.getLength(notes); i ++) {
            pitches.add(notes[i].getPitch());
        }
        return pitches;
    }

    @Transient
    public List<Integer> getRhythms() {
        List<Integer> pitches = new LinkedList<Integer>();
        for (int i = 0; i < Array.getLength(notes); i ++) {
            pitches.add(notes[i].getRhythm());
        }
        return pitches;
    }

    @Transient
    public List<Integer> getVelocities() {
        List<Integer> pitches = new LinkedList<Integer>();
        for (int i = 0; i < Array.getLength(notes); i ++) {
            pitches.add(notes[i].getVelocity());
        }
        return pitches;
    }

    /**
     *
     * @return
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    public void setId(Long str) {
        id = str;    
    }

    @ManyToOne
    @JoinColumn(name="StyleId")
    public Style getStyle() {
        return style;
    }

    public void setStyle(Style styleId) {
        this.style = styleId;
    }

    @Lob
    public Note[] getNotes() {
        return notes;
    }

    public void setNotes(Note[] notes) {
        this.notes = notes;
    }

    @ManyToMany(
        targetEntity=com.steinlink.ponyleague.core.model.Instrument.class,
        cascade={CascadeType.PERSIST, CascadeType.MERGE}
    )
    @JoinTable(
        name="GeneInstruments",
        joinColumns={@JoinColumn(name="GeneId")},
        inverseJoinColumns={@JoinColumn(name="InstrumentId")}
    )
    public Set<Instrument> getInstruments() {

        return instruments;
    }

    public void setInstruments(Set<Instrument> instrumentIds) {
        this.instruments = instrumentIds;
    }

    public String getComplexity() {
        return complexity;
    }

    public void setComplexity(String complexity) {
        this.complexity = complexity;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    public String toString() {

        String s = "";

        for (int i = 0; i < Array.getLength(notes); i++) {
            s += notes[i].getRhythm();
        }

        return s;
    }

    public boolean equals(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int hashCode() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Transient
    public int getComplexityIterations() {

        // TODO unify complexity model
        return 9;
    }
    @Transient
    public int getNodeID() {
        return nodeID;
    }

    public void setNodeID(int i) {
        nodeID = i;
    }

    
}
