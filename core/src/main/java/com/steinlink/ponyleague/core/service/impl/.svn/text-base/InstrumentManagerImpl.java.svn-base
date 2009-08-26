package com.steinlink.ponyleague.core.service.impl;

import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.service.InstrumentManager;
import com.steinlink.ponyleague.core.dao.InstrumentDao;
import com.steinlink.service.impl.GenericManagerImpl;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 8, 2007
 * Time: 7:05:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class InstrumentManagerImpl extends GenericManagerImpl<Instrument, Long> implements InstrumentManager {

    private InstrumentDao instrumentDao;


    public InstrumentManagerImpl(InstrumentDao dao) {
        super(dao);
        this.instrumentDao = dao;
    }

    public List<Instrument> findByMidi(int value) {
        return instrumentDao.findByMidi(value); 
    }

    public Instrument findByName(String s) {
        return instrumentDao.findByName(s);
    }

    public void removeAll() {
        instrumentDao.removeAll();
    }
}
