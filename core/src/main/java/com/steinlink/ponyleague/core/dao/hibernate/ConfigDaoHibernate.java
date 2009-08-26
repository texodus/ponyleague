package com.steinlink.ponyleague.core.dao.hibernate;

import com.steinlink.dao.hibernate.GenericDaoHibernate;

import com.steinlink.ponyleague.core.model.Config;
import com.steinlink.ponyleague.core.dao.ConfigDao;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 30, 2007
 * Time: 1:32:42 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigDaoHibernate  extends GenericDaoHibernate<Config, String> implements ConfigDao {

    public ConfigDaoHibernate() {
        super(Config.class);
    }

    public String getConfig(String key) {
        try {
            String temp = ((Config)getHibernateTemplate().find("from Config where Id = ?", key).get(0)).getValue();
            return temp;
        } catch (Exception e) {
            return null;
        }
    }
}