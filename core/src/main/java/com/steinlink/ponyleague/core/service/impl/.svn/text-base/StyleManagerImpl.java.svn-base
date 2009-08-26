package com.steinlink.ponyleague.core.service.impl;

import com.steinlink.ponyleague.core.model.Style;
import com.steinlink.ponyleague.core.service.StyleManager;
import com.steinlink.ponyleague.core.dao.StyleDao;
import com.steinlink.service.impl.GenericManagerImpl;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:43:12 PM
 * To change this template use File | Settings | File Templates.
 */
public class StyleManagerImpl extends GenericManagerImpl<Style, Long> implements StyleManager {

    private StyleDao songJobDao;


    public StyleManagerImpl(StyleDao dao) {
        super(dao);
        this.songJobDao = dao;
    }

    public void removeAll() {
        songJobDao.removeAll();
    }
}