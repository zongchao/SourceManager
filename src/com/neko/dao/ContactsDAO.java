package com.neko.dao;
// default package

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.neko.bean.Contacts;
import com.neko.bean.Sources;

/**
 * A data access object (DAO) providing persistence and search support for
 * Contacts entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Contacts
 * @author MyEclipse Persistence Tools
 */
@Repository
public class ContactsDAO extends HibernateDaoSupport
{
    private static final Logger log = LoggerFactory
            .getLogger(ContactsDAO.class);
    // property constants
    public static final String NAME = "name";
    public static final String EMAIL = "email";
    public static final String ADDRESS = "address";
    public static final String MOBILE_PHONE = "mobilePhone";
    public static final String TELEPHONE = "telephone";
    public static final String OTHER_INFO = "otherInfo";
    public static final String DESCRIPTION = "description";
    
    protected void initDao()
    {
        // do nothing
    }
    
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
    
    public void save(Contacts transientInstance)
    {
        log.debug("saving Contacts instance");
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
    
    public void delete(Contacts persistentInstance)
    {
        log.debug("deleting Contacts instance");
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
    
    public Contacts findById(java.lang.Integer id)
    {
        log.debug("getting Contacts instance with id: " + id);
        try
        {
            Contacts instance = (Contacts) getHibernateTemplate().get(
                    "com.neko.bean.Contacts", id);
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List findByExample(Contacts instance)
    {
        log.debug("finding Contacts instance by example");
        try
        {
            List results = getHibernateTemplate().findByExample(instance);
            log.debug("find by example successful, result size: "
                    + results.size());
            return results;
        } catch (RuntimeException re)
        {
            log.error("find by example failed", re);
            throw re;
        }
    }
    
    public List findByProperty(String propertyName, Object value)
    {
        log.debug("finding Contacts instance with property: " + propertyName
                + ", value: " + value);
        try
        {
            String queryString = "from Contacts as model where model."
                    + propertyName + "= ?";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString, value);
        } catch (RuntimeException re)
        {
            log.error("find by property name failed", re);
            throw re;
        }
    }
    
    public List<Contacts> findListByPager(final int firstResult,final int maxResults, DetachedCriteria criteria)
    {
        List<Contacts> list = new ArrayList<Contacts>();
        try
        {
            Criteria c = criteria.getExecutableCriteria(getHibernateTemplate().getSessionFactory().openSession());
            list = c.setFirstResult(firstResult).setMaxResults(maxResults).setCacheable(true).list();
        }
        catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
        return list;
    }
    
    public List findByCriteria(DetachedCriteria criteria)
    { 
        getHibernateTemplate().setCacheQueries(true);
        return getHibernateTemplate().findByCriteria(criteria);
    }
    
    public List findByName(Object name)
    {
        return findByProperty(NAME, name);
    }
    
    public List findByEmail(Object email)
    {
        return findByProperty(EMAIL, email);
    }
    
    public List findByAddress(Object address)
    {
        return findByProperty(ADDRESS, address);
    }
    
    public List findByMobilePhone(Object mobilePhone)
    {
        return findByProperty(MOBILE_PHONE, mobilePhone);
    }
    
    public List findByTelephone(Object telephone)
    {
        return findByProperty(TELEPHONE, telephone);
    }
    
    public List findByOtherInfo(Object otherInfo)
    {
        return findByProperty(OTHER_INFO, otherInfo);
    }
    
    public List findByDescription(Object description)
    {
        return findByProperty(DESCRIPTION, description);
    }
    
    public List findAll()
    {
        log.debug("finding all Contacts instances");
        try
        {
            String queryString = "from Contacts";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString);
        } catch (RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public Contacts merge(Contacts detachedInstance)
    {
        log.debug("merging Contacts instance");
        try
        {
            Contacts result = (Contacts) getHibernateTemplate().merge(
                    detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Contacts instance)
    {
        log.debug("attaching dirty Contacts instance");
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
    
    public void attachClean(Contacts instance)
    {
        log.debug("attaching clean Contacts instance");
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
    
    public static ContactsDAO getFromApplicationContext(ApplicationContext ctx)
    {
        return (ContactsDAO) ctx.getBean("ContactsDAO");
    }
}