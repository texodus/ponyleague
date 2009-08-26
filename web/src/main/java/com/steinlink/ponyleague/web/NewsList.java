package com.steinlink.ponyleague.web;

import com.steinlink.webapp.action.BasePage;

import java.util.List;
import java.io.Serializable;

import com.steinlink.ponyleague.core.service.NewsManager;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Jan 31, 2007
 * Time: 9:41:16 PM
 * To change this template use File | Settings | File Templates.
 */

public class NewsList extends BasePage implements Serializable {
    private NewsManager manager;

    public void setNewsManager(NewsManager manager) {
         this.manager = manager;
    }

    public NewsList() {
       // setSortColumn("id"); // sets the default sort column

    }

    public List getNews() {
        return manager.getMostRecent();
    }
}

