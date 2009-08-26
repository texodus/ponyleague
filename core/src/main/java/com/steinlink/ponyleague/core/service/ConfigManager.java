package com.steinlink.ponyleague.core.service;

import com.steinlink.service.GenericManager;

import java.util.List;
import java.util.Set;

import com.steinlink.ponyleague.core.model.Config;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 30, 2007
 * Time: 1:36:44 AM
 * To change this template use File | Settings | File Templates.
 */
public interface ConfigManager extends GenericManager<Config, String> {

    public String getConfig(String key);

}
