package com.neko.dao;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.neko.bean.Attachments;

/**
 * A data access object (DAO) providing persistence and search support for Attachments entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see hibernate.Attachments
 * @author MyEclipse Persistence Tools
 */
@Repository
public class AttachmentsDAO extends HibernateDaoSupport
{
    private static final Logger log = LoggerFactory.getLogger(AttachmentsDAO.class);

    protected void initDao()
    {
        // do nothing
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
    
    public void save(Attachments transientInstance)
    {
        log.debug("saving Attachments instance");
        try
        {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        }
        catch (RuntimeException re)
        {
            log.error("save failed", re);
            throw re;
        }
    }

    public void delete(Attachments persistentInstance)
    {
        log.debug("deleting Attachments instance");
        try
        {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        }
        catch (RuntimeException re)
        {
            log.error("delete failed", re);
            throw re;
        }
    }

    public Attachments findById(java.lang.Integer id)
    {
        log.debug("getting Attachments instance with id: " + id);
        try
        {
            Attachments instance = (Attachments) getHibernateTemplate().get("com.neko.bean.Attachments", id);
            return instance;
        }
        catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(Attachments instance)
    {
        log.debug("finding Attachments instance by example");
        try
        {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        }
        catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }

    public List findByProperty(String propertyName, Object value)
    {
        log.debug("finding Attachments instance with property: " + propertyName + ", value: " + value);
        try
        {
            String queryString = "from Attachments as model where model." + propertyName + "= ?";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString, value);
        }
        catch (RuntimeException re)
        {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findAll()
    {
        log.debug("finding all Attachments instances");
        try
        {
            String queryString = "from Attachments";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString);
        }
        catch (RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    public Attachments merge(Attachments detachedInstance)
    {
        log.debug("merging Attachments instance");
        try
        {
            Attachments result = (Attachments) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Attachments instance)
    {
        log.debug("attaching dirty Attachments instance");
        try
        {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        }
        catch (RuntimeException re)
        {
            log.error("attach failed", re);
            throw re;
        }
    }

    public void attachClean(Attachments instance)
    {
        log.debug("attaching clean Attachments instance");
        try
        {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        }
        catch (RuntimeException re)
        {
            log.error("attach failed", re);
            throw re;
        }
    }

    public static AttachmentsDAO getFromApplicationContext(ApplicationContext ctx)
    {
        return (AttachmentsDAO) ctx.getBean("AttachmentsDAO");
    }
}