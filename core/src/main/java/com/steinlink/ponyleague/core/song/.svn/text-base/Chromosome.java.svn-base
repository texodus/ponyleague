package com.steinlink.ponyleague.core.song;

import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.ponyleague.core.model.Instrument;

import java.util.List;
import java.util.LinkedList;
import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 18, 2007
 * Time: 4:07:18 PM
 * To change this template use File | Settings | File Templates.
 */
public abstract class Chromosome implements Serializable {

    private List<Gene> genes = new LinkedList<Gene>();
    private Instrument instrument;
    private boolean enabled = true;

    public ChromosomeIterator iterator() {
        return new ChromosomeIterator(this);
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public int getGeneLength() {
        return genes.size();    
    }

    public Gene removeGene(int i) {
        return genes.remove(i);
    }

    public Gene getGene(int i) {
        return genes.get(i);
    }

    public Note getNoteFromGene(int geneIndex) {
        return genes.get(geneIndex).getNote(geneIndex);
    }

    public void addGene(Gene gene) {
        genes.add(gene);
    }

    public void addReverseGene(Gene gene) {
        genes.add(0, gene);
    }
}
