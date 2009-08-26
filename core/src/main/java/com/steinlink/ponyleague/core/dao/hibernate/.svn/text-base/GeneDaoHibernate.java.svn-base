package com.steinlink.ponyleague.core.dao.hibernate;

import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.model.Gene;
import com.steinlink.ponyleague.core.dao.GeneDao;
import com.steinlink.dao.hibernate.GenericDaoHibernate;

import java.util.*;
import java.lang.reflect.Array;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 9:32:57 PM
 * To change this template use File | Settings | File Templates.
 */
public class GeneDaoHibernate  extends GenericDaoHibernate<Gene, Long> implements GeneDao {

    public GeneDaoHibernate() {
        super(Gene.class);
    }

    /**
     * Find a list of genes that are applicable to a set of instruments
     * @param instruments
     * @return
     */
    public List<Gene> find(List<Instrument> instruments) {

        if (instruments == null || instruments.size() < 1) return new LinkedList<Gene>();
        return getHibernateTemplate().find("from Gene g where " + getParamList(instruments), instruments.toArray());
    }

    /**
     * Find an applicable gene
     * @param instruments
     * @param complexity
     * @param style
     * @return
     */
    public List<Gene> find(List<Instrument> instruments, String complexity, String style) {

        if (instruments == null || instruments.size() < 1) return new LinkedList<Gene>();

        ArrayList list = new ArrayList(instruments);
        list.add(complexity);
        list.add(style);

        return getHibernateTemplate().find("from Gene g where " + getParamList(instruments) + " and g.complexity = ? and g.style.title = ?", list.toArray());
    }

    public void removeAll() {
        getHibernateTemplate().deleteAll(getHibernateTemplate().find("from Gene where 1=1"));
    }

    /**
     * Find a random gene for a list of instruments
     * @param instruments
     * @param complexity
     * @param style
     * @return
     */
    public Gene findRandomGene(List<Instrument> instruments, String complexity, String style) {

        List<Gene> genes = find(instruments, complexity, style);
        return genes.get((new Random()).nextInt(genes.size()));
    }

    /**
     * Generate a list of HQL parameters
     * @param instruments
     * @return
     */
    private String getParamList(List<Instrument> instruments) {

        int i = 0;
        String params = "";

        while (i < instruments.size() - 1) {
            params += "? in elements(g.instruments) and ";
            i ++;
        }

        return params += "? in elements(g.instruments)";
    }
}