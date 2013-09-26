package com.neko.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.dom4j.util.UserDataAttribute;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.neko.bean.Users;
import com.neko.dao.UsersDAO;
import com.neko.hibernate.HibernateSessionFactory;
import com.neko.service.interfaces.UserServiceInterface;
import com.neko.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.sun.corba.se.impl.protocol.giopmsgheaders.Message;
import com.sun.net.httpserver.HttpContext;

@Service("userService")
public class UserServiceHibernateImpl implements UserServiceInterface
{
    public static Logger log = Logger.getLogger(UserServiceHibernateImpl.class.getName());
    
    @Resource( name = "usersDAO")
    private UsersDAO usersDAO;
    
    @Override
    public Boolean addUser(Users user)
    {
        log.info("开始保存用户信息");
        Boolean flag = false;
        try
        {
            user.setPassword(StringUtil.getMd5String(user.getPassword()));
            user.setCreateTime(new Date());
            user.setIsDelete(0);
            user.setLastLoginTime(new Date());
            user.setUserType(1);
            usersDAO.save(user);
            flag = true;
            log.info("保存用户信息完成");
        }
        catch (Exception e)
        {
            log.info("保存用户信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean delUser(Integer id)
    {
        log.info("删除用户");
        Boolean flag = false;
        try
        {
            Users user = findUserById(id);
            usersDAO.delete(user);
            flag = true;
            log.info("删除用户完成");
        }
        catch (Exception e)
        {
            log.info("删除用户失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Users findUserById(Integer id)
    {
        log.info("通过id获取用户信息");
        return usersDAO.findById(id);
    }

    @Override
    public Users findUserByUserInfo(String username, String password)
    {
        log.info("通过用户名密码获取用户信息");
        DetachedCriteria dCriteria = DetachedCriteria.forClass(Users.class);
        dCriteria.add(Restrictions.eq("username", username));
        dCriteria.add(Restrictions.eq("password", StringUtil.getMd5String(password)));
        Users user = null;
        try
        {
            user = (Users) usersDAO.getHibernateTemplate().findByCriteria(dCriteria).get(0);
            Map<String, Object> session = ActionContext.getContext().getSession();
            session.put("user", user);
            log.info("通过用户名密码获取用户信息成功");
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            log.info("通过用户名密码获取用户信息失败");
            log.error(e.getMessage());
        }
        
        return user;
    }

    @Override
    public Collection<Users> getUserList()
    {
        log.info("获取用户列表");
        return usersDAO.findAll();
    }

    @Override
    public Boolean isUsernameExist(String username)
    {
        log.info("判断用户名是否存在");
        Boolean isExist = false;
        if(usersDAO.findByUsername(username).size() > 0)
        {
            isExist = true;
        }
        return isExist;
    }
    
    @Override
    public Boolean modUser(Users user_temp)
    {
        log.info("修改用户信息");
        Boolean flag = false;
        try
        {
            Users user = findUserById(user_temp.getId());
            // 如果密码没用进行过修改，不再进行md5加密
            if(!user.getPassword().equals(user_temp.getPassword()))
            {
                user.setPassword(StringUtil.getMd5String(user_temp.getPassword()));
            }
            user.setUsername(user_temp.getUsername());
            user.setLastLoginTime(user_temp.getLastLoginTime());
            usersDAO.merge(user);
            flag = true;
            log.info("修改用户信息完成");
        }
        catch (Exception e)
        {
            log.info("修改用户信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public UsersDAO getUsersDAO()
    {
        return usersDAO;
    }

    public void setUsersDAO(UsersDAO usersDAO)
    {
        this.usersDAO = usersDAO;
    }

    @Override
    public Boolean forbidOrActivateUser(Integer id)
    {
        log.info("禁用或启用用户");
        Boolean flag = false;
        try
        {
            Users user = findUserById(id);
            if(user.getIsDelete() == 0)
            {
                user.setIsDelete(1);
            }
            else 
            {
                user.setIsDelete(0);
            }
            usersDAO.merge(user);
            flag = true;
            log.info("禁用或启用用户完成");
        }
        catch (Exception e)
        {
            log.info("禁用或启用用户失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }
}
