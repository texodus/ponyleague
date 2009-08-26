package com.steinlink.ponyleague.core.model;

import com.steinlink.model.BaseObject;

import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: May 29, 2007
 * Time: 10:22:10 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Config extends BaseObject {

    private String key;
    private String value;

    public static final String USER_STORAGE = "USER_STORAGE";
    public static final String IS_DEBUG = "IS_DEBUG";

    @Id
    public String getId() {
        return key;
    }

    public void setId(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean equals(Object o) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int hashCode() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
