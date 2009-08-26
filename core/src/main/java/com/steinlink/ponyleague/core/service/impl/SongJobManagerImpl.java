package com.steinlink.ponyleague.core.service.impl;

import com.steinlink.ponyleague.core.model.SongJob;
import com.steinlink.ponyleague.core.service.SongJobManager;
import com.steinlink.ponyleague.core.dao.SongJobDao;
import com.steinlink.service.impl.GenericManagerImpl;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:37:31 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongJobManagerImpl extends GenericManagerImpl<SongJob, Long> implements SongJobManager {

    private SongJobDao songJobDao;


    public SongJobManagerImpl(SongJobDao dao) {
        super(dao);
        this.songJobDao = dao;
    }

    public SongJob findTopByStatusAndIncrement(String status) {
        return songJobDao.findTopByStatusAndIncrement(status);
    }
}