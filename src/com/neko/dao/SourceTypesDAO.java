package com.neko.dao;

import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.neko.bean.SourceTypes;

/**
 * A data access object (DAO) providing persistence and search support for SourceTypes entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see com.neko.bean.SourceTypes
 * @author MyEclipse Persistence Tools
 */
@Repository
public class SourceTypesDAO extends HibernateDaoSupport
{
    private static final Logger log = LoggerFactory.getLogger(SourceTypesDAO.class);

    // property constants
    public static final String NAME = "name";

    protected void initDao()
    {
        // do nothing
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
    
    public void save(SourceTypes transientInstance)
    {
        log.debug("saving SourceTypes instance");
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

    public void delete(SourceTypes persistentInstance)
    {
        log.debug("deleting SourceTypes instance");
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

    public SourceTypes findById(java.lang.Integer id)
    {
        log.debug("getting SourceTypes instance with id: " + id);
        try
        {
            SourceTypes instance = (SourceTypes) getHibernateTemplate().get("com.neko.bean.SourceTypes", id);
            return instance;
        }
        catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(SourceTypes instance)
    {
        log.debug("finding SourceTypes instance by example");
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
        log.debug("finding SourceTypes instance with property: " + propertyName + ", value: " + value);
        try
        {
            String queryString = "from SourceTypes as model where model." + propertyName + "= ?";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString, value);
        }
        catch (RuntimeException re)
        {
            log.error("find by property name failed", re);
            throw re;
        }
    }

    public List findByName(Object name)
    {
        return findByProperty(NAME, name);
    }

    public List findAll()
    {
        log.debug("finding all SourceTypes instances");
        try
        {
            String queryString = "from SourceTypes";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString);
        }
        catch (RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    public SourceTypes merge(SourceTypes detachedInstance)
    {
        log.debug("merging SourceTypes instance");
        try
        {
            SourceTypes result = (SourceTypes) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(SourceTypes instance)
    {
        log.debug("attaching dirty SourceTypes instance");
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

    public void attachClean(SourceTypes instance)
    {
        log.debug("attaching clean SourceTypes instance");
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

    public static SourceTypesDAO getFromApplicationContext(ApplicationContext ctx)
    {
        return (SourceTypesDAO) ctx.getBean("SourceTypesDAO");
    }
}