package com.steinlink.ponyleague.core.service.impl;

import com.steinlink.service.impl.GenericManagerImpl;
import com.steinlink.ponyleague.core.dao.ConfigDao;
import com.steinlink.ponyleague.core.model.Config;
import com.steinlink.ponyleague.core.service.ConfigManager;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 30, 2007
 * Time: 1:37:43 AM
 * To change this template use File | Settings | File Templates.
 */
public class ConfigManagerImpl extends GenericManagerImpl<Config, String> implements ConfigManager {

    private ConfigDao configDao;

    public ConfigManagerImpl(ConfigDao dao) {
        super(dao);
        this.configDao = dao;
    }

    public String getConfig(String key) {
        return configDao.getConfig(key);
    }
}