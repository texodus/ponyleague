package com.steinlink.ponyleague.core.dao.hibernate;

import com.steinlink.ponyleague.core.model.SongJob;
import com.steinlink.ponyleague.core.dao.SongJobDao;
import com.steinlink.dao.hibernate.GenericDaoHibernate;
import org.springframework.orm.hibernate3.HibernateTemplate;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: steinlink
 * Date: Apr 12, 2007
 * Time: 11:31:50 PM
 * To change this template use File | Settings | File Templates.
 */
public class SongJobDaoHibernate  extends GenericDaoHibernate<SongJob, Long> implements SongJobDao {

    public SongJobDaoHibernate() {
        super(SongJob.class);
    }

    public SongJob findTopByStatusAndIncrement(String status) {
        try{
            HibernateTemplate template = getHibernateTemplate();
            List list = template.find("from SongJob where Status=? order by Id", status);
            SongJob job = (SongJob)list.get(0);

            job.setStatus(SongJob.STATUS_PROCESSING);
            getHibernateTemplate().update(job);
            return job;

        } catch(Exception e) {
            return null;
        }
    }
}