package com.neko.dao;
// default package

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

import com.neko.bean.FinanceTypes;

/**
 * A data access object (DAO) providing persistence and search support for
 * FinanceTypes entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see .FinanceTypes
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FinanceTypesDAO extends HibernateDaoSupport
{
    private static final Logger log = LoggerFactory.getLogger(FinanceTypesDAO.class);
    // property constants
    public static final String NAME = "name";
    public static final String TYPE = "type";
    
    protected void initDao()
    {
        // do nothing
    }
    
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
    
    public void save(FinanceTypes transientInstance)
    {
        log.debug("saving FinanceTypes instance");
        try
        {
            getHibernateTemplate().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re)
        {
            log.error("save failed", re);
            throw re;
        }
    }
    
    public void delete(FinanceTypes persistentInstance)
    {
        log.debug("deleting FinanceTypes instance");
        try
        {
            getHibernateTemplate().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re)
        {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public FinanceTypes findById(java.lang.Integer id)
    {
        log.debug("getting FinanceTypes instance with id: " + id);
        try
        {
            FinanceTypes instance = (FinanceTypes) getHibernateTemplate().get("com.neko.bean.FinanceTypes", id);
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List findByExample(FinanceTypes instance)
    {
        log.debug("finding FinanceTypes instance by example");
        try
        {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List findByProperty(String propertyName, Object value)
    {
        log.debug("finding FinanceTypes instance with property: " + propertyName + ", value: " + value);
        try
        {
            String queryString = "from FinanceTypes as model where model." + propertyName + "= ?";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re)
        {
            log.error("find by property name failed", re);
            throw re;
        }
    }
    
    public List findByName(Object name)
    {
        return findByProperty(NAME, name);
    }
    
    public List findByType(Object type)
    {
        return findByProperty(TYPE, type);
    }
    
    public List findAll()
    {
        log.debug("finding all FinanceTypes instances");
        try
        {
            String queryString = "from FinanceTypes";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public FinanceTypes merge(FinanceTypes detachedInstance)
    {
        log.debug("merging FinanceTypes instance");
        try
        {
            FinanceTypes result = (FinanceTypes) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public void attachDirty(FinanceTypes instance)
    {
        log.debug("attaching dirty FinanceTypes instance");
        try
        {
            getHibernateTemplate().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re)
        {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(FinanceTypes instance)
    {
        log.debug("attaching clean FinanceTypes instance");
        try
        {
            getHibernateTemplate().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re)
        {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public static FinanceTypesDAO getFromApplicationContext(ApplicationContext ctx)
    {
        return (FinanceTypesDAO) ctx.getBean("FinanceTypesDAO");
    }
}