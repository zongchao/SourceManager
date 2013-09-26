package com.neko.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.neko.bean.Contacts;
import com.neko.bean.Finances;
import com.neko.bean.Sources;
import com.neko.dao.ContactsDAO;
import com.neko.page.bean.Pager;
import com.neko.page.service.PagerService;
import com.neko.service.interfaces.ContactServiceInterface;
import com.neko.util.ExcelUtil;
import com.neko.util.StringUtil;

@Service("contactService")
public class ContactServiceHibernateImpl implements ContactServiceInterface
{
    public static Logger log = Logger.getLogger(ContactServiceHibernateImpl.class.getName());
    
    @Resource(name = "contactsDAO")
    private ContactsDAO contactsDAO;

    @Override
    public Collection<Contacts> getContactList(int userId)
    {
        return contactsDAO.findByProperty("users.id", userId);
    }

    @Override
    public Pager getContactPager(int userId, int pageNo, int pageSize,String condition)
    {
      log.info("获取通讯录列表第"+ pageNo + "页,每页" + pageSize + "条" );
      
      DetachedCriteria criteria = getCriteria(userId, condition);
      int totalCount = contactsDAO.findByCriteria(criteria).size();

      Pager pager = new Pager();
      if(totalCount >= 1)
      {
          int startIndex = Pager.getStartOfPage(pageNo, pageSize);
          List<Contacts> list = contactsDAO.findListByPager(startIndex, pageSize, criteria);
          pager = new Pager(pageNo, totalCount, pageSize, list);
          PagerService pagerService = new PagerService(pager);
          
          StringBuffer clickUrl = new StringBuffer("contactList.action?");
          if(!StringUtil.IsNullOrWhiteSpace(condition))
          {
              clickUrl.append("&condition=");
              clickUrl.append(condition);
          }
          pager.setPageHtml(pagerService.paging(clickUrl.toString()));
      }
      return pager;
    }

    private DetachedCriteria getCriteria(int userId, String condition)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Contacts.class,"c");
        criteria.createCriteria("users","u")
        .add(Restrictions.eq("u.id", userId));
        if(!StringUtil.IsNullOrWhiteSpace(condition))
        {
            criteria.add(Restrictions.like("name", "%"+ condition +"%"));
        }
        return criteria;
    }
    
    @Override
    public Boolean addContact(Contacts contact)
    {
        log.info("开始保存联系人信息");
        Boolean flag = false;
        try
        {
            contact.setOtherInfo("");
            contact.setCreateTime(new Date());
            contact.setLastModifyTime(new Date());
            contactsDAO.save(contact);
            flag = true;
            log.info("保存资源信息完成");
        }
        catch (Exception e)
        {
            log.info("保存资源信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean modContact(Contacts contact_temp)
    {
        log.info("开始修改联系人信息");
        Boolean flag = false;
        try
        {
            Contacts contact = findContactById(contact_temp.getId());
            contact.setAddress(contact_temp.getAddress());
            contact.setAttachments(contact_temp.getAttachments());
            contact.setDescription(contact_temp.getDescription());
            contact.setEmail(contact_temp.getEmail());
            contact.setMobilePhone(contact_temp.getMobilePhone());
            contact.setTelephone(contact_temp.getTelephone());
            contact.setName(contact_temp.getName());
            contact.setOtherInfo(contact_temp.getOtherInfo());
            contact.setContactTypes(contact_temp.getContactTypes());
            contact.setLastModifyTime(new Date());
            contactsDAO.merge(contact);
            flag = true;
            log.info("修改联系人信息完成");
        }
        catch (Exception e)
        {
            log.info("修改联系人信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean delContact(Integer id)
    {
        log.info("开始删除id="+ id +"的联系人");
        Boolean flag = false;
        try
        {
            Contacts contact = findContactById(id);
            contactsDAO.delete(contact);
            flag = true;
        }
        catch (Exception e)
        {
            flag = false;
            log.info("删除联系人失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Contacts findContactById(Integer id)
    {
        log.info("通过id获取联系人信息");
        Contacts contact = contactsDAO.findById(id);
        return contact;
    }
    
    public ContactsDAO getContactsDAO()
    {
        return contactsDAO;
    }

    public void setContactsDAO(ContactsDAO contactsDAO)
    {
        this.contactsDAO = contactsDAO;
    }

    @Override
    public File generateExcel(String realPath,int userId)
    {
        File file = new File(realPath + "/" + RandomStringUtils.randomAlphanumeric(10) + ".xls");
        WritableWorkbook workbook = null;
        try
        {
            workbook = ExcelUtil.createWorkbook(file);
            WritableSheet sheet = ExcelUtil.createSheet(workbook, "通讯录");
            generateExcelHead(sheet);
            List<Contacts> contactList = new ArrayList<Contacts>();
            contactList = (List<Contacts>) getContactList(userId);
            generateExcelValue(sheet,contactList);
            workbook.write();
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } 
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                workbook.close();
            }
            catch (WriteException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        return file;
    }
    
    /**
     * 生成excel表头
     * @param sheet 工作簿
     */
    private void generateExcelHead(WritableSheet sheet)
    {
        ExcelUtil.addCell(sheet, 0, 0, "ID", 0);
        ExcelUtil.addCell(sheet, 1, 0, "姓名", 0);
        ExcelUtil.addCell(sheet, 2, 0, "关系", 0);
        ExcelUtil.addCell(sheet, 3, 0, "电子邮箱", 0);
        ExcelUtil.addCell(sheet, 4, 0, "住址", 0);
        ExcelUtil.addCell(sheet, 5, 0, "手机", 0);
        ExcelUtil.addCell(sheet, 6, 0, "宅电", 0);
        ExcelUtil.addCell(sheet, 7, 0, "描述", 0);
    }
    
    /**
     * 生成excel内容
     * @param sheet 工作簿
     * @param contactList 数据列表
     */
    private void generateExcelValue(WritableSheet sheet,List<Contacts> contactList)
    {
        int i = 1;
        for (Contacts contacts : contactList)
        {
            ExcelUtil.addCell(sheet, 0, i, contacts.getId(), 1);
            ExcelUtil.addCell(sheet, 1, i, contacts.getName(), 0);
            ExcelUtil.addCell(sheet, 2, i, contacts.getContactTypes().getName(), 0);
            ExcelUtil.addCell(sheet, 3, i, contacts.getEmail(), 0);
            ExcelUtil.addCell(sheet, 4, i, contacts.getAddress(), 0);
            ExcelUtil.addCell(sheet, 5, i, contacts.getMobilePhone(), 0);
            ExcelUtil.addCell(sheet, 6, i, contacts.getTelephone(), 0);
            ExcelUtil.addCell(sheet, 7, i, contacts.getDescription(), 0);
            i++;
        }
    }
}
