package com.neko.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.neko.bean.FinanceTypes;
import com.neko.bean.SourceTypes;
import com.neko.service.interfaces.FinanceTypeServiceInterface;
import com.neko.service.interfaces.SourceTypeServiceInterface;
import com.neko.util.CommonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
public class FinanceTypeAction extends ActionSupport implements  Preparable
{
    Collection<FinanceTypes> financeTypeList;
    FinanceTypes financeType;
    
    @Resource(name = "financeTypeService")
    FinanceTypeServiceInterface financeTypeService;
    
    @Action(value="financeTypeList", results = {
            @Result(location="/FinanceManager/financeTypeList.jsp")
    })
    public String financeTypeList()
    {
        financeTypeList = financeTypeService.getFinanceTypeListByType(financeType.getType());
        return SUCCESS;
    }
    
    @Action(value="addFinanceTypePre", results = {
            @Result(location="/FinanceManager/addFinanceType.jsp"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String addFinanceTypePre()
    {
        return SUCCESS;
    }
    
    @Action(value="addFinanceType", results = {
            @Result(type="redirect", location="financeTypeList?financeType.type=${financeType.type}"),
            @Result(name="input", type="redirect", location="addFinanceTypePre?financeType.type=${financeType.type}" ),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String addFinanceType()
    {
        if (financeTypeService.isFinanceTypeNameExist(financeType.getName(),financeType.getType()))
        {
            addActionError("类型名已存在");
            return INPUT;
        }
        if(financeTypeService.addFinanceType(financeType))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="checkFinanceTypeName", results = {
            @Result(type="plainText", params={
                    "location", "/blank.jsp", "charSet", "UTF-8"
            }),
    })
    public void checkFinanceTypeName()
    {
        if(financeTypeService.isFinanceTypeNameExist(financeType.getName(),financeType.getType()))
        {
            CommonUtil.printMessage(true);
        }
        else 
        {
            CommonUtil.printMessage(false);
        }
    }
    
    @Action(value="modFinanceTypePre", results = {
            @Result(location="/FinanceManager/modFinanceType.jsp"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String findFinanceTypeById()
    {
        if(financeType != null && financeType.getId() != null)
        {
            financeType = financeTypeService.findFinanceTypeById(financeType.getId());
            if(financeType == null)
            {
                return ERROR;
            }
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="modFinanceType", results = {
            @Result(type="redirect", location="financeTypeList?financeType.type=${financeType.type}"),
            @Result(name="input", type="redirect", location="modFinanceTypePre?financeType.id=${financeType.id}"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String modFinanceType()
    {
        FinanceTypes oldFinanceType = financeTypeService.findFinanceTypeById(financeType.getId());
        if(oldFinanceType.getFinanceses().size() > 0)
        {
            addActionError("请先确认没有使用此类型的数据后删除");
            return INPUT;
        }
        if (!financeTypeService.findFinanceTypeById(financeType.getId()).getName().equals(financeType.getName()) && financeTypeService.isFinanceTypeNameExist(financeType.getName(),financeType.getType()))
        {
            addActionError("类型名已存在");
            return INPUT;
        }
        if(financeTypeService.modifyFinanceType(financeType))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="delFinanceType", results = {
            @Result(type="redirect", location="financeTypeList?financeType.type=${financeType.type}"),
            @Result(type="redirect", location="financeTypeList?financeType.type=${financeType.type}"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String delFinanceType()
    {
        financeType = financeTypeService.findFinanceTypeById(financeType.getId());
        if(financeType.getFinanceses().size() > 0)
        {
            addActionError("请先确认没有使用此类型的数据后删除");
            return INPUT;
        }
        if(financeTypeService.delFinanceType(financeType.getId()))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Override
    public void prepare() throws Exception
    {
        clearActionErrors();
    }

    public FinanceTypeServiceInterface getFinanceTypeService()
    {
        return financeTypeService;
    }

    public void setFinanceTypeService(FinanceTypeServiceInterface financeTypeService)
    {
        this.financeTypeService = financeTypeService;
    }

    public Collection<FinanceTypes> getFinanceTypeList()
    {
        return financeTypeList;
    }

    public void setFinanceTypeList(Collection<FinanceTypes> financeTypeList)
    {
        this.financeTypeList = financeTypeList;
    }

    public FinanceTypes getFinanceType()
    {
        return financeType;
    }

    public void setFinanceType(FinanceTypes financeType)
    {
        this.financeType = financeType;
    }

}
