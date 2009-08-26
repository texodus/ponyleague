package com.steinlink.ponyleague.core.dao.hibernate;

import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.ponyleague.core.dao.InstrumentDao;
import com.steinlink.dao.hibernate.GenericDaoHibernate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 8, 2007
 * Time: 6:56:02 AM
 * To change this template use File | Settings | File Templates.
 */
public class InstrumentDaoHibernate  extends GenericDaoHibernate<Instrument, Long> implements InstrumentDao {

    public InstrumentDaoHibernate() { 
        super(Instrument.class);
    }

    public List<Instrument> findByMidi(int value) {
        return getHibernateTemplate().find("from Instrument where midiValue=?", value);
    }

    public void removeAll() {
        getHibernateTemplate().deleteAll(getHibernateTemplate().find("from Instrument where 1=1"));
    }

    public Instrument findByName(String s) {
        return (Instrument)getHibernateTemplate().find("from Instrument where name like ?", s).get(0);
    }
}