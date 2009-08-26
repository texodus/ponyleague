package com.steinlink.ponyleague.core.dao;

import com.steinlink.dao.GenericDao;

import java.util.List;
import java.util.Set;

import com.steinlink.ponyleague.core.model.Config;
import com.steinlink.ponyleague.core.model.Gene;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 30, 2007
 * Time: 1:31:17 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigDao extends GenericDao<Config, String> {

    public String getConfig(String key);
}
