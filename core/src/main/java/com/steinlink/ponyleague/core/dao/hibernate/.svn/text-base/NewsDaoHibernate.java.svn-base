package com.steinlink.ponyleague.core.dao.hibernate;

import com.steinlink.dao.hibernate.GenericDaoHibernate;

import java.util.List;

import com.steinlink.ponyleague.core.dao.NewsDao;
import com.steinlink.ponyleague.core.model.News;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Feb 2, 2007
 * Time: 11:07:43 PM
 * To change this template use File | Settings | File Templates.
 */
public class NewsDaoHibernate extends GenericDaoHibernate<News, Long> implements NewsDao {

    public NewsDaoHibernate() {
        super(News.class);
    }

    public List<News> getMostRecent() {
        return getHibernateTemplate().find("from com.steinlink.ponyleague.core.model.News order by CreateDate desc");
    }
}