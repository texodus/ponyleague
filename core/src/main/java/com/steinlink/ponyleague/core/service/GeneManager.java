package com.steinlink.ponyleague.core.service;

import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.service.GenericManager;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:22:55 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GeneManager extends GenericManager<Gene, Long> {
    public List<Gene> find(List<Instrument> instruments);
    public List<Gene> find(List<Instrument> lists, String complexity, String Style);
    public Gene findRandomGene(List<Instrument> instruments, String complexity, String style);
    public void removeAll();
}