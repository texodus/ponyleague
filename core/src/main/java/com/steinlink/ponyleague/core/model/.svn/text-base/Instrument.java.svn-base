package com.steinlink.ponyleague.core.model;

import com.steinlink.model.BaseObject;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.*;
import java.lang.reflect.Array;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 8, 2007
 * Time: 6:24:21 AM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Instrument extends BaseObject implements Comparable<Instrument> {

    private long id;
    private String name;
    private int midiValue;
    private int lowerBound;
    private int upperbound;
    private int velocityOffset;

    public int getLowerbound() {
        return lowerBound;
    }

    public void setLowerbound(int lowerBound) {
        this.lowerBound = lowerBound;
    }

    public int getUpperbound() {
        return upperbound;
    }

    public void setUpperbound(int upperbound) {
        this.upperbound = upperbound;
    }

    public int getVelocityOffset() {
        return velocityOffset;
    }

    public void setVelocityOffset(int velocityOffset) {
        this.velocityOffset = velocityOffset;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMidiValue() {
        return midiValue;
    }

    public void setMidiValue(int midiValue) {
        this.midiValue = midiValue;
    }

    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean equals(Object o) {
        return ((Instrument)o).getId() == id;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int hashCode() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int compareTo(Instrument instrument) {
        if (instrument.getId() < id) return -1;
        else if (instrument.getId() > id) return 1;
        else return 0;
    }

    public static String generateKey(Set<Instrument> instruments) {

        List<Long> list = new LinkedList<Long>();
        Object[] newList = (Object[])instruments.toArray();

        for (int i = 0; i < Array.getLength(newList); i ++) {
            list.add(((Instrument)newList[i]).getId());
        }

        return generateIdsKey(list);
    }

    /**
     * Keys are needed for instrument lsits so the engine can make quick lookups for chromosomes
     * @param list
     * @return
     */
    public static String generateIdsKey(List<Long> list) {

        Collections.sort(list);

        String key = "";
        for(Iterator i = list.iterator(); i.hasNext();) {

            String currentInstrument = String.valueOf(i.next());

            // remove duplicates from the key
            if (!key.contains(currentInstrument)) {
                key += currentInstrument + ":";
            }
        }

        return key;
    }
}
