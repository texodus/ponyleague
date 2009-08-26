package com.steinlink.ponyleague.core.service;

import com.steinlink.ponyleague.core.model.Instrument;
import com.steinlink.service.GenericManager;

import java.util.List;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 8, 2007
 * Time: 7:02:09 AM
 * To change this template use File | Settings | File Templates.
 */
public interface InstrumentManager extends GenericManager<Instrument, Long> {
    public List<Instrument> findByMidi(int value);
    public Instrument findByName(String s);
    public void removeAll();
}