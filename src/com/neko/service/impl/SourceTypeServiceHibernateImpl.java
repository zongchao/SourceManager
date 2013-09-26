package com.neko.service.impl;

import java.util.Collection;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neko.bean.SourceTypes;
import com.neko.bean.Users;
import com.neko.dao.SourceTypesDAO;
import com.neko.service.interfaces.SourceTypeServiceInterface;

@Service("sourceTypeService")
public class SourceTypeServiceHibernateImpl implements SourceTypeServiceInterface
{
    public static Logger log = Logger.getLogger(SourceTypeServiceHibernateImpl.class.getName());
    
    @Resource(name="sourceTypesDAO")
    SourceTypesDAO sourceTypesDAO;
    SourceTypes sourceType;
    
    @Override
    public Collection<SourceTypes> getSourceTypeList()
    {
        log.info("获取资源类型列表");
        return sourceTypesDAO.findAll();
    }

    @Override
    public Boolean addSourceType(SourceTypes sourceType)
    {
        log.info("开始保存资源类型");
        Boolean flag = false;
        try
        {
            sourceTypesDAO.save(sourceType);
            flag = true;
            log.info("保存资源类型完成");
        }
        catch (Exception e)
        {
            log.info("保存资源类型失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean isSourceTypeNameExist(String sourceTypeName)
    {
        log.info("判断资源类型名称是否存在");
        Boolean isExist = false;
        if(sourceTypesDAO.findByName(sourceTypeName).size() > 0)
        {
            isExist = true;
        }
        return isExist;
    }
    
    @Override
    public Boolean delSourceType(Integer id)
    {
        log.info("开始删除资源类型");
        Boolean flag = false;
        try
        {
            SourceTypes sourceTpye = findSourceTypeById(id);
            sourceTypesDAO.delete(sourceTpye);
            flag = true;
            log.info("保存删除类型完成");
        }
        catch (Exception e)
        {
            log.info("保存删除类型失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public SourceTypes findSourceTypeById(Integer id)
    {
        log.info("通过id获取资源类型");
        return sourceTypesDAO.findById(id);
    }
    
    @Override
    public Boolean modifySourceType(SourceTypes sourceType)
    {
        log.info("开始修改资源类型");
        Boolean flag = false;
        try
        {
            sourceTypesDAO.merge(sourceType);
            flag = true;
            log.info("修改资源类型完成");
        }
        catch (Exception e)
        {
            log.info("修改资源类型失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    
    public SourceTypesDAO getSourceTypesDAO()
    {
        return sourceTypesDAO;
    }

    public void setSourceTypesDAO(SourceTypesDAO sourceTypesDAO)
    {
        this.sourceTypesDAO = sourceTypesDAO;
    }

    
    
    
}
