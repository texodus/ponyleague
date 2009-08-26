package com.steinlink.ponyleague.core.dao.hibernate;

import com.steinlink.ponyleague.core.model.Style;
import com.steinlink.ponyleague.core.dao.StyleDao;
import com.steinlink.dao.hibernate.GenericDaoHibernate;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:41:15 PM
 * To change this template use File | Settings | File Templates.
 */
public class StyleDaoHibernate  extends GenericDaoHibernate<Style, Long> implements StyleDao {

    public void removeAll() {
        getHibernateTemplate().deleteAll(getHibernateTemplate().find("from Style where 1=1"));
    }

    public StyleDaoHibernate() {
        super(Style.class);
    }
}