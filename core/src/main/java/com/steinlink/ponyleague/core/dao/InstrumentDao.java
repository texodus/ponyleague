package com.steinlink.ponyleague.core.dao;

import com.steinlink.dao.GenericDao;
import java.util.List;
import java.util.Set;

import com.steinlink.ponyleague.core.model.Instrument;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 8, 2007
 * Time: 6:51:55 AM
 * To change this template use File | Settings | File Templates.
 */
public interface InstrumentDao extends GenericDao<Instrument, Long> {
    public List<Instrument> findByMidi(int midi);
    public Instrument findByName(String s);
    public void removeAll();
}