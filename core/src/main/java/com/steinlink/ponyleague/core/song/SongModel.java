package com.steinlink.ponyleague.core.song;


import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.ponyleague.core.service.InstrumentManager;
import com.steinlink.ponyleague.core.service.GeneManager;

import java.util.List;
import java.util.LinkedList;
import java.io.Serializable;

import org.springframework.context.ApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 15, 2007
 * Time: 4:14:25 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongModel implements Serializable {

    protected List<GrammarNode> nodes;
    protected String style;
    private int ticksPerMeter = 1000;

    public SongModel() {}

    public SongModel(SongModel model) {
        this.nodes = model.nodes;
        this.style = model.style;
    }

    public static GrammarNode createGrammarNode(List<GrammarNode> list) {
        return new SongModel.GrammarNode(list);
    }

    public List<GrammarNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<GrammarNode> nodes) {
        this.nodes = nodes;
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }



    /**
     * This is an individual unit of the grammar tree
     */
    public static class GrammarNode implements Serializable {

        private List<GrammarNode> list;
        private int indent;
        private int index;
        private List<Instrument> instruments = new LinkedList<Instrument>();
        private String instrument;
        private String complexity;
        private Gene gene;

        public Gene getGene() {
            return gene;
        }

        public void setGene(Gene gene) {
            this.gene = gene;
        }

        public GrammarNode(List<GrammarNode> form) {
            list = form;
        }

        public void addInstrument(Instrument i) {
            instruments.add(i);
        }

        public List<Instrument> getInstruments() {
            return instruments;
        }

        public int getDivWidth() {
            return indent;
        }

        public void setDivWidth(int x) {
            indent = x;
        }

        public String getInstrument() {
            return instrument;
        }

        public String getComplexity() {
            return complexity;
        }

        public void setInstrument(String s) {
            instrument = s;
        }

        public void setComplexity(String s) {
            complexity = s;
        }

        public void add() {

            index = list.indexOf(this) + 1;
            list.add(index, new GrammarNode(list));
            list.get(index).setDivWidth(indent + 75);
        }

        public void delete() {
            int x = getDivWidth();
            int y = list.indexOf(this);

            list.remove(y);
            try {
                while (list.get(y).getDivWidth() > x) {
                    list.remove(y);
                }
            } catch (IndexOutOfBoundsException e) {

            }
        }

        public boolean getDrawDelete() {
            if (getDivWidth() == 0) return false; else return true;
        }
    }

}
