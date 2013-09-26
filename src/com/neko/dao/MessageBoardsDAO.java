package com.neko.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.neko.bean.Finances;
import com.neko.bean.MessageBoards;

/**
 * A data access object (DAO) providing persistence and search support for MessageBoard entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the desired type of transaction control.
 * 
 * @see MessageBoards.MessageBoard
 * @author MyEclipse Persistence Tools
 */

@Repository
public class MessageBoardsDAO extends HibernateDaoSupport
{
    private static final Logger log = LoggerFactory.getLogger(MessageBoardsDAO.class);

    protected void initDao()
    {
        // do nothing
    }

    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
    
    public void save(MessageBoards transientInstance)
    {
        log.debug("saving MessageBoards instance");
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

    public void delete(MessageBoards persistentInstance)
    {
        log.debug("deleting MessageBoards instance");
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

    public MessageBoards findById(java.lang.Integer id)
    {
        log.debug("getting MessageBoards instance with id: " + id);
        try
        {
            MessageBoards instance = (MessageBoards) getHibernateTemplate().get("com.neko.bean.MessageBoards", id);
            return instance;
        }
        catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }

    public List findByExample(MessageBoards instance)
    {
        log.debug("finding MessageBoards instance by example");
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
        log.debug("finding MessageBoards instance with property: " + propertyName + ", value: " + value);
        try
        {
            String queryString = "from MessageBoards as model where model." + propertyName + "= ?";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString, value);
        }
        catch (RuntimeException re)
        {
            log.error("find by property name failed", re);
            throw re;
        }
    }
    
    public List findByCriteria(DetachedCriteria criteria)
    { 
        getHibernateTemplate().setCacheQueries(true);
        return getHibernateTemplate().findByCriteria(criteria);
    }
    
    public List<MessageBoards> findListByPager(final int firstResult,final int maxResults, DetachedCriteria criteria)
    {
        List<MessageBoards> list = new ArrayList<MessageBoards>();
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
    
    public List findAll()
    {
        log.debug("finding all MessageBoards instances");
        try
        {
            String queryString = "from MessageBoards";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString);
        }
        catch (RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }

    public MessageBoards merge(MessageBoards detachedInstance)
    {
        log.debug("merging MessageBoards instance");
        try
        {
            MessageBoards result = (MessageBoards) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        }
        catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(MessageBoards instance)
    {
        log.debug("attaching dirty MessageBoards instance");
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

    public void attachClean(MessageBoards instance)
    {
        log.debug("attaching clean MessageBoards instance");
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

    public static MessageBoardsDAO getFromApplicationContext(ApplicationContext ctx)
    {
        return (MessageBoardsDAO) ctx.getBean("MessageBoardsDAO");
    }
}