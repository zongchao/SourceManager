package com.neko.dao;
// default package

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.Type;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.neko.bean.Finances;
import com.neko.bean.Sources;

/**
 * A data access object (DAO) providing persistence and search support for
 * Finances entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see .Finances
 * @author MyEclipse Persistence Tools
 */
@Repository
public class FinancesDAO extends HibernateDaoSupport
{
    private static final Logger log = LoggerFactory.getLogger(FinancesDAO.class);
    // property constants
    public static final String MONEY = "money";
    public static final String NOTE = "note";
    
    protected void initDao()
    {
        // do nothing
    }
    
    @Resource(name = "sessionFactory")
    public void setSuperSessionFactory(SessionFactory sessionFactory)
    {
        super.setSessionFactory(sessionFactory);
    }
    
    public void save(Finances transientInstance)
    {
        log.debug("saving Finances instance");
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
    
    public void delete(Finances persistentInstance)
    {
        log.debug("deleting Finances instance");
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
    
    public Finances findById(java.lang.Integer id)
    {
        log.debug("getting Finances instance with id: " + id);
        try
        {
            Finances instance = (Finances) getHibernateTemplate().get("com.neko.bean.Finances", id);
            return instance;
        } catch (RuntimeException re)
        {
            log.error("get failed", re);
            throw re;
        }
    }
    
    public List findByExample(Finances instance)
    {
        log.debug("finding Finances instance by example");
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
        log.debug("finding Finances instance with property: " + propertyName + ", value: " + value);
        try
        {
            String queryString = "from Finances as model where model." + propertyName + "= ?";
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
    
    public List<Finances> findListByPager(final int firstResult,final int maxResults, DetachedCriteria criteria)
    {
        List<Finances> list = new ArrayList<Finances>();
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
    
    public List getStatistic(int userId, Date startDate, Date endDate)
    {
        List list = new ArrayList();
        Session session = getHibernateTemplate().getSessionFactory().openSession();
        list = session.createCriteria(Finances.class,"f")
                .createCriteria("financeTypes","ft")
                .createCriteria("f.users","u")
                .add(Restrictions.eq("u.id", userId))
                .add(Restrictions.between("f.happenTime", startDate,endDate))
                .setProjection(Projections.projectionList()
                            .add(Projections.sum("f.money"))
                            .add(Projections.sqlGroupProjection(
                                    "CONVERT(varchar(256),year(happenTime)) + '-' + CONVERT(varchar(256),MONTH(happenTime)) as x"
                                    , "CONVERT(varchar(256),year(happenTime)) + '-' + CONVERT(varchar(256),MONTH(happenTime))"
                                    , new String[]{"x"}
                                    , new Type[]{Hibernate.STRING}))
                            .add(Projections.groupProperty("ft.type"))
                ).setCacheable(true).list();
        return list;
    }
    
    public List findByMoney(Object money)
    {
        return findByProperty(MONEY, money);
    }
    
    public List findByNote(Object note)
    {
        return findByProperty(NOTE, note);
    }
    
    public List findAll()
    {
        log.debug("finding all Finances instances");
        try
        {
            String queryString = "from Finances";
            getHibernateTemplate().setCacheQueries(true);
            return getHibernateTemplate().find(queryString);
        }
        catch (RuntimeException re)
        {
            log.error("find all failed", re);
            throw re;
        }
    }
    
    public Finances merge(Finances detachedInstance)
    {
        log.debug("merging Finances instance");
        try
        {
            Finances result = (Finances) getHibernateTemplate().merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re)
        {
            log.error("merge failed", re);
            throw re;
        }
    }
    
    public void attachDirty(Finances instance)
    {
        log.debug("attaching dirty Finances instance");
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
    
    public void attachClean(Finances instance)
    {
        log.debug("attaching clean Finances instance");
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
    
    public static FinancesDAO getFromApplicationContext(ApplicationContext ctx)
    {
        return (FinancesDAO) ctx.getBean("FinancesDAO");
    }
}