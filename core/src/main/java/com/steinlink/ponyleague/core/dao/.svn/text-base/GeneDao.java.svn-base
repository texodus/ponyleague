package com.steinlink.ponyleague.core.dao;

import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.dao.GenericDao;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 9:25:56 PM
 * To change this template use File | Settings | File Templates.
 */
public interface GeneDao extends GenericDao<Gene, Long> {

    public List<Gene> find(List<Instrument> lists);
    public List<Gene> find(List<Instrument> lists, String complexity, String Style);
    public Gene findRandomGene(List<Instrument> instruments, String complexity, String style);
    public void removeAll();
}
