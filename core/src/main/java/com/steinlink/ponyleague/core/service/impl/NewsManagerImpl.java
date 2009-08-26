package com.steinlink.ponyleague.core.service.impl;

import com.steinlink.ponyleague.core.dao.NewsDao;
import com.steinlink.ponyleague.core.model.News;
import com.steinlink.ponyleague.core.service.NewsManager;

import java.util.List;

import com.steinlink.service.impl.GenericManagerImpl;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Feb 3, 2007
 * Time: 1:19:41 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewsManagerImpl extends GenericManagerImpl<News, Long> implements NewsManager {
   private NewsDao dao;

       public NewsManagerImpl(NewsDao dao) {
        super(dao);
        this.dao = dao;
    }

    public List getMostRecent() {
        return dao.getMostRecent();
    }

} 
