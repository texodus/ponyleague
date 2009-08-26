package com.steinlink.ponyleague.core.service.impl;

import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.ponyleague.core.service.GeneManager;
import com.steinlink.ponyleague.core.dao.GeneDao;
import com.steinlink.service.impl.GenericManagerImpl;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:24:06 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneManagerImpl extends GenericManagerImpl<Gene, Long> implements GeneManager {

    private GeneDao geneDao;


    public GeneManagerImpl(GeneDao dao) {
        super(dao);
        this.geneDao = dao;
    }

    public List<Gene> find(List<Instrument> value) {
        return geneDao.find(value);
    }

    public List<Gene> find(List<Instrument> lists, String complexity, String style) {
        return geneDao.find(lists, complexity, style);
    }

    public Gene findRandomGene(List<Instrument> instruments, String complexity, String style) {
        return geneDao.findRandomGene(instruments, complexity, style);
    }

    public void removeAll() {
        geneDao.removeAll();
    }
}
