package com.neko.service.impl;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neko.bean.FinanceTypes;
import com.neko.dao.FinanceTypesDAO;
import com.neko.service.interfaces.FinanceTypeServiceInterface;

@Service("financeTypeService")
public class FinanceTypeServiceHibernateImpl implements FinanceTypeServiceInterface
{
    public static Logger log = Logger.getLogger(FinanceTypeServiceHibernateImpl.class.getName());
    FinanceTypes financeType;
    
    @Resource(name = "financeTypesDAO")
    FinanceTypesDAO financeTypesDAO;
    
    @Override
    public Collection<FinanceTypes> getFinanceTypeList()
    {
        log.info("获取财务信息类型列表");
        return financeTypesDAO.findAll();
    }

    @Override
    public Collection<FinanceTypes> getFinanceTypeListByType(int type)
    {
        log.info("根据类型获取财务信息类型列表");
        return  financeTypesDAO.findByProperty("type", type);
    }
    
    @Override
    public Boolean addFinanceType(FinanceTypes financeType)
    {
        log.info("开始保存财务信息类型");
        Boolean flag = false;
        try
        {
            financeTypesDAO.save(financeType);
            flag = true;
            log.info("保存财务信息类型完成");
        }
        catch (Exception e)
        {
            log.info("保存财务信息类型失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean isFinanceTypeNameExist(String name,int type)
    {
        log.info("判断财务信息类型名称是否存在");
        Boolean isExist = false;
        FinanceTypes financeType = new FinanceTypes();
        financeType.setName(name);
        financeType.setType(type);
        if(financeTypesDAO.findByExample(financeType).size() > 0)
        {
            isExist = true;
        }
        return isExist;
    }

    @Override
    public FinanceTypes findFinanceTypeById(Integer id)
    {
        log.info("通过id获取财务信息类型");
        return financeTypesDAO.findById(id);
    }

    @Override
    public Boolean delFinanceType(Integer id)
    {
        log.info("开始删除财务信息类型");
        Boolean flag = false;
        try
        {
            FinanceTypes financeTpye = findFinanceTypeById(id);
            financeTypesDAO.delete(financeTpye);
            flag = true;
            log.info("保存删除财务信息类型完成");
        }
        catch (Exception e)
        {
            log.info("保存删除财务信息类型失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Boolean modifyFinanceType(FinanceTypes financeType)
    {
        log.info("开始修改财务信息类型");
        Boolean flag = false;
        try
        {
            financeTypesDAO.merge(financeType);
            flag = true;
            log.info("修改财务信息类型完成");
        }
        catch (Exception e)
        {
            log.info("修改财务信息类型失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    public FinanceTypesDAO getFinanceTypesDAO()
    {
        return financeTypesDAO;
    }

    public void setFinanceTypesDAO(FinanceTypesDAO financeTypesDAO)
    {
        this.financeTypesDAO = financeTypesDAO;
    }

}
