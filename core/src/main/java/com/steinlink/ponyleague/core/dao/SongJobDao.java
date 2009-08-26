package com.steinlink.ponyleague.core.dao;

import com.steinlink.ponyleague.core.model.Job;
import com.steinlink.ponyleague.core.model.SongJob;
import com.steinlink.dao.GenericDao;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:30:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SongJobDao extends GenericDao<SongJob, Long> {
    public SongJob findTopByStatusAndIncrement(String status);    
}
