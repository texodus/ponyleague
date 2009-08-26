package com.steinlink.ponyleague.core.util;

import com.steinlink.service.GenericManager;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.ServletContext;

import com.steinlink.ponyleague.core.service.ConfigManager;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Aug 19, 2007
 * Time: 6:08:59 PM
 * To change this template use File | Settings | File Templates.
 */
public class Database {

    public static Object getManager(String name, ServletContext context) {

        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        return ctx.getBean(name);
    }

}
