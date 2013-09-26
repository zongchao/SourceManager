package com.neko.service.impl;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.XPath;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.neko.bean.Users;
import com.neko.service.interfaces.UserServiceInterface;
import com.neko.xml.XMLUtil;

public class UserServiceXmlImpl 
{
    static final String USERSXML = "com/neko/xml/Users.xml";
    XMLUtil xmlUtil = new XMLUtil();
    public static Logger log = Logger.getLogger(UserServiceXmlImpl.class.getName());
    
    public Boolean addUser(Users user)
    {
        log.info("添加用户");
        Boolean isAdded = false;
        Document doc;
        try
        {
            doc = XMLUtil.read(this.getClass().getClassLoader().getResource(USERSXML).getPath());
            Element element = XMLUtil.getRootElement(doc);
            element = element.addElement("user");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            element.setAttributeValue("id", user.getId().toString());
            element.addElement("username").setText(user.getUsername());
            element.addElement("password").setText(user.getPassword());
            element.addElement("userType").setText(user.getUserType().toString());
            element.addElement("createTime").setText(user.getUsername());
            element.addElement("username").setText(user.getUsername());
            element.addElement("username").setText(user.getUsername());
            System.out.println(this.getClass().getClassLoader().getResource(USERSXML).getPath());
            xmlUtil.writerXML(doc,this.getClass().getClassLoader().getResource(USERSXML).getPath());
        }
        catch (DocumentException e)
        {
            log.error("添加用户发生异常DocumentException");
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            log.error("添加用户发生异常MalformedURLException");
            e.printStackTrace();
        }
        return isAdded;
    }

    public Boolean delUser(Integer id)
    {
        log.info("添加用户");
        Boolean isDeleted = false;
        Document doc;
        try
        {
            doc = XMLUtil.read(this.getClass().getClassLoader().getResource(USERSXML).getPath());
            Element element = (Element) doc.selectSingleNode("//user[@id = "+ id +"]");
            doc.remove(element);
            xmlUtil.writerXML(doc,this.getClass().getClassLoader().getResource(USERSXML).getPath());
        }
        catch (DocumentException e)
        {
            log.error("添加用户发生异常DocumentException");
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            log.error("添加用户发生异常MalformedURLException");
            e.printStackTrace();
        }
        return isDeleted;
    }

    public Users findUserById(Integer id)
    {
        log.info("通过ID查找用户");
        Users user = null;
        try
        {
            Document doc = XMLUtil.read(this.getClass().getClassLoader().getResource(USERSXML).getPath());
            Element element = (Element) doc.selectSingleNode("//user[@id = "+ id +"]");
            if(element != null)
            {
                user = new Users(id, 
                        XMLUtil.getElementText(element, "username"), 
                        XMLUtil.getElementText(element, "password"), 
                        Integer.parseInt(XMLUtil.getElementText(element, "userType")),
                        parseDate(XMLUtil.getElementText(element, "createTime")), 
                        parseDate(XMLUtil.getElementText(element, "lastLoginTime")), 
                        Integer.parseInt(XMLUtil.getElementText(element, "isDelete")));
            }
        }
        catch (DocumentException e)
        {
            log.error("通过ID查找用户发生异常DocumentException");
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            log.error("通过ID查找用户发生异常MalformedURLException");
            e.printStackTrace();
        }
        return user;
    }
    
    
    public Users findUserByUserInfo(String username, String password)
    {
        log.info("通过用户名密码查找用户");
        Users user = null;
        try
        {
            Document doc = xmlUtil.read(this.getClass().getClassLoader().getResource(USERSXML).getPath());
            Element element = (Element) doc.selectSingleNode("//user[username = '"+ username +"' and password = '"+ password +"']");
            if(element != null)
            {
                user = new Users(Integer.parseInt(element.attributeValue("id")), 
                        username, 
                        password, 
                        Integer.parseInt(xmlUtil.getElementText(element, "userType")),
                        parseDate(xmlUtil.getElementText(element, "createTime")), 
                        parseDate(xmlUtil.getElementText(element, "lastLoginTime")), 
                        Integer.parseInt(xmlUtil.getElementText(element, "isDelete")));
            }
        }
        catch (DocumentException e)
        {
            log.error("通过用户名密码查找用户发生异常DocumentException");
            e.printStackTrace();
        }
        catch (NumberFormatException e)
        {
            log.error("通过用户名密码查找用户发生异常NumberFormatException");
            e.printStackTrace();
        }
        catch (MalformedURLException e)
        {
            log.error("通过用户名密码查找用户发生异常MalformedURLException");
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return user;
    }


    public Boolean modifyUser(Users user)
    {
        // TODO Auto-generated method stub
        return null;
    }

    private Date parseDate(String source)
    {
        Date date = null;
        if(source != null && !source.equals(""))
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try
            {
                date = sdf.parse(source);
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
        return date;
    }
    
    @Test
    public void findUserByUserInfoTester() throws DocumentException 
    {
        Users user =  findUserByUserInfo("admin","admin");
        System.out.println(user);
        Assert.assertNotNull(user);
    }
    
    @Test
    public void findUserByIdTester()
    {
        Users user =  findUserById(1);
        System.out.println(user);
        Assert.assertNotNull(user);
    }

    @Test
    public void addUserTester()
    {
        Users user = findUserById(1);
        addUser(user);
    }

    public Collection<Users> getUserList()
    {
        // TODO Auto-generated method stub
        return null;
    }

    public Boolean isUsernameExist(String username)
    {
        // TODO Auto-generated method stub
        return null;
    }
}
