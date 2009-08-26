package com.steinlink.ponyleague.core.model;

import com.steinlink.model.BaseObject;


import java.util.Date;
import javax.persistence.*;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Jan 31, 2007
 * Time: 9:16:25 PM
 * To change this template use File | Settings | File Templates.
 */
@Entity
public class News extends BaseObject {

    private long id;
    private String subject;
    private String text;
    private Date createDate;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) 
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Column(name="subject", length=50)
    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Column(name="text", length=50000)
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
        
    public String toString() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean equals(Object object) {
        return (id == ((News)object).getId());  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int hashCode() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }
}


