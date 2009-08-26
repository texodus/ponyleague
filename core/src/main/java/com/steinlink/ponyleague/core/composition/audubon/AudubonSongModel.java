package com.steinlink.ponyleague.core.composition.audubon;

import com.steinlink.ponyleague.core.song.SongModel;
import com.steinlink.ponyleague.core.service.InstrumentManager;
import com.steinlink.ponyleague.core.service.GeneManager;
import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.model.Gene;

import java.util.List;
import java.util.LinkedList;

import org.springframework.context.ApplicationContext;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 27, 2007
 * Time: 3:58:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class AudubonSongModel extends SongModel {

    public AudubonSongModel(SongModel model) {
        super(model); 
    }

    public List<AudubonChromosome> generateChromosomes(ApplicationContext ctx) {

        List<AudubonChromosome> chromosomes = new LinkedList<AudubonChromosome>();

        int idGen = -1;

        InstrumentManager instrumentManager = (InstrumentManager)ctx.getBean("instrumentManager");
        GeneManager geneManager = (GeneManager)ctx.getBean("geneManager");

        //determine which nodes need gene for which instruments
        for (int i = 0; i < nodes.size(); i ++) {
            if (!nodes.get(i).getInstrument().equals(" - null - ")) {

                String instrument = nodes.get(i).getInstrument();

                // TODO fix this!
                try {
                    Instrument q =  instrumentManager.get(Long.parseLong(instrument));
                    nodes.get(i).addInstrument(q);
                    int width = nodes.get(i).getDivWidth();

                    int j = i - 1;
                    while (j >= 0) {
                        if (nodes.get(j).getDivWidth() < width) {
                            nodes.get(j).addInstrument(q);
                            width = nodes.get(j).getDivWidth();
                        }
                        j --;
                    }
                } catch (Exception e) {
                    // Do Nothing;   this was a null node!
                }
            }
        }

        // get a gene for every node that needs one
        for (int i = 0; i < nodes.size(); i++) {
            if (nodes.get(i).getInstruments().size() > 0) {

                try {

                    List<Instrument> ins = nodes.get(i).getInstruments();
                    String com = nodes.get(i).getComplexity();

                    Gene gene = geneManager.findRandomGene(ins, com, style);
                    
                    if (gene == null) {
                    	System.out.println("THIS SUCKS");
                    }
                    
                    gene.setNodeID(idGen ++);
                    nodes.get(i).setGene(gene);

                } catch (Exception e) {
                    System.out.println("ERROR" + e.getMessage()) ;
                }
            }

        }

        // now build chromosomes for each instrument
        for (int i = 0; i < nodes.size(); i ++) {
            if (!nodes.get(i).getInstrument().equals(" - null - ")) {

                int width = nodes.get(i).getDivWidth();

                AudubonChromosome chromosome = new AudubonChromosome();
                chromosome.addReverseGene(nodes.get(i).getGene());
                chromosome.setInstrument(instrumentManager.get(Long.parseLong(nodes.get(i).getInstrument())));

                int j = i - 1;
                while (j >= 0) {
                    if (nodes.get(j).getDivWidth() < width) {
                        chromosome.addReverseGene(nodes.get(j).getGene());
                        width = nodes.get(j).getDivWidth();
                    }
                    j --;
                }
                chromosomes.add(chromosome);
            }
        }

        return chromosomes;
    }

}
