package com.steinlink.ponyleague.core.model;

import com.steinlink.model.BaseObject;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 6:25:52 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class Job extends BaseObject {

    private long id;
    private String title;
    private String status;
    private String fileKey;
    private int percentCompleted;

    private static String STATUS_NEW = "New";
    private static String STATUS_PROCESSING = "Processing";
    private static String STATUS_PROCESSED = "Processed";
    private static String STATUS_ERROR = "Error";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPercentCompleted() {
        return percentCompleted;
    }

    public void setPercentCompleted(int percentCompleted) {
        this.percentCompleted = percentCompleted;
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
