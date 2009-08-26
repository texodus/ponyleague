package com.steinlink.ponyleague.core.dao;

import com.steinlink.dao.GenericDao;
import com.steinlink.ponyleague.core.model.Style;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:40:16 PM
 * To change this template use File | Settings | File Templates.
 */
public interface StyleDao extends GenericDao<Style, Long> {
    public void removeAll();
}
