package com.steinlink.ponyleague.core.service;

import com.steinlink.ponyleague.core.model.SongJob;
import com.steinlink.service.GenericManager;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:36:43 PM
 * To change this template use File | Settings | File Templates.
 */
public interface SongJobManager extends GenericManager<SongJob, Long> {
    public SongJob findTopByStatusAndIncrement(String status);
}