package com.steinlink.ponyleague.core.service.impl;

import com.steinlink.ponyleague.core.model.Job;
import com.steinlink.ponyleague.core.service.JobManager;
import com.steinlink.ponyleague.core.dao.JobDao;
import com.steinlink.service.impl.GenericManagerImpl;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:33:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class JobManagerImpl extends GenericManagerImpl<Job, Long> implements JobManager {

    private JobDao jobDao;


    public JobManagerImpl(JobDao dao) {
        super(dao);
        this.jobDao = dao;
    }
}