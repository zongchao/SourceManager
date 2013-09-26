package com.neko.service.impl;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neko.bean.ContactTypes;
import com.neko.bean.SourceTypes;
import com.neko.bean.Users;
import com.neko.dao.ContactTypesDAO;
import com.neko.dao.ContactsDAO;
import com.neko.dao.SourceTypesDAO;
import com.neko.service.interfaces.ContactTypeServiceInterface;;

@Service("contactTypeService")
public class ContactTypeServiceHibernateImpl implements ContactTypeServiceInterface
{
    public static Logger log = Logger.getLogger(ContactTypeServiceHibernateImpl.class.getName());
    
    @Resource(name = "contactTypesDAO")
    ContactTypesDAO contactTypesDAO;

    @Override
    public Collection<ContactTypes> getContactTypeList()
    {
        log.info("获取联系人类型列表");
      return contactTypesDAO.findAll();
    }

    @Override
    public ContactTypes findContactTypeById(Integer id)
    {
        // TODO Auto-generated method stub
        return null;
    }

    public ContactTypesDAO getContactTypesDAO()
    {
        return contactTypesDAO;
    }

    public void setContactTypesDAO(ContactTypesDAO contactTypesDAO)
    {
        this.contactTypesDAO = contactTypesDAO;
    }

}
