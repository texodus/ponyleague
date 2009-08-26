package com.steinlink.ponyleague.core.song;

import com.steinlink.ponyleague.core.model.Gene;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 27, 2007
 * Time: 3:40:23 PM
 * To change this template use File | Settings | File Templates.
 */
public class ChromosomeIterator implements Iterator {

    private Chromosome chrome;
    private int cursor;


    public ChromosomeIterator(Chromosome c) {
        chrome = c;
        cursor = 0;
    }

    public boolean hasNext() {
        return (chrome.getGeneLength() > cursor) ? true : false;
    }

    public Gene next() {
        cursor ++;
        return chrome.getGene(cursor - 1);
    }

    public void remove() {
        chrome.removeGene(cursor - 1);    
    }
}
