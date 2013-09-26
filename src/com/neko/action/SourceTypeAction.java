package com.neko.action;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.InterceptorRef;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.neko.bean.SourceTypes;
import com.neko.service.interfaces.SourceTypeServiceInterface;
import com.neko.util.CommonUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
@Namespace("/SourceManager")
public class SourceTypeAction extends ActionSupport implements  Preparable
{
    Collection<SourceTypes> sourceTypeList;
    SourceTypes sourceType;
    
    @Resource(name = "sourceTypeService")
    SourceTypeServiceInterface sourceTypeService;
    
    @Action(value="addSourceType", results = {
            @Result(type="redirect", location="sourceTypeList"),
            @Result(name="input", location="/SourceManager/addSourceType.jsp" ),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String addSourceType()
    {
        if (sourceTypeService.isSourceTypeNameExist(sourceType.getName()))
        {
            addActionError("类型名已存在");
            return INPUT;
        }
        if(sourceTypeService.addSourceType(sourceType))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
        
    }
    
    @Action(value="checkSourceTypeName", results = {
            @Result(type="plainText", params={
                    "location", "/blank.jsp", "charSet", "UTF-8"
            }),
    })
    public void checkSourceTypeName()
    {
        if(sourceTypeService.isSourceTypeNameExist(sourceType.getName()))
        {
            CommonUtil.printMessage(true);
        }
        else 
        {
            CommonUtil.printMessage(false);
        }
    }
    
    @Action(value="modSourceTypePre", results = {
            @Result(location="/SourceManager/modSourceType.jsp"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String findSourceTypeById()
    {
        if(sourceType != null && sourceType.getId() != null)
        {
            sourceType = sourceTypeService.findSourceTypeById(sourceType.getId());
            if(sourceType == null)
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
    
    @Action(value="modSourceType", results = {
            @Result(type="redirect", location="sourceTypeList"),
            @Result(name="input", type="redirect", location="modSourceTypePre?sourceType.id=${sourceType.id}"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String modSourceType()
    {
        if (sourceTypeService.isSourceTypeNameExist(sourceType.getName()))
        {
            addActionError("类型名已存在");
            return INPUT;
        }
        SourceTypes oldSourceType = sourceTypeService.findSourceTypeById(sourceType.getId());
        if(oldSourceType.getSourceses().size() > 0)
        {
            addActionError("请先确认没有使用此类型的数据后删除");
            return INPUT;
        }
        
        if(sourceTypeService.modifySourceType(sourceType))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="delSourceType", results = {
            @Result(type="redirect", location="sourceTypeList"),
            @Result(name="input", type="chain", params={
                    "actionName", "sourceTypeList", "namespace", "/SourceManager"
            }),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String delSourceType()
    {
        sourceType = sourceTypeService.findSourceTypeById(sourceType.getId());
        if(sourceType.getSourceses().size() > 0)
        {
            addActionError("请先确认没有使用此类型的数据后删除");
            return INPUT;
        }
        if(sourceTypeService.delSourceType(sourceType.getId()))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="sourceTypeList", results = {
            @Result(location="/SourceManager/sourceTypeList.jsp")
            },
            interceptorRefs = {
                @InterceptorRef(value="store", params = {
                        "operationMode", "RETRIEVE"
            })
    })
    public String sourceTypeList()
    {
        sourceTypeList = sourceTypeService.getSourceTypeList();
        return SUCCESS;
    }
    
    @Override
    public void prepare() throws Exception
    {
        clearActionErrors();
    }

    public SourceTypeServiceInterface getSourceTypeService()
    {
        return sourceTypeService;
    }

    public void setSourceTypeService(SourceTypeServiceInterface sourceTypeService)
    {
        this.sourceTypeService = sourceTypeService;
    }

    public Collection<SourceTypes> getSourceTypeList()
    {
        return sourceTypeList;
    }

    public void setSourceTypeList(Collection<SourceTypes> sourceTypeList)
    {
        this.sourceTypeList = sourceTypeList;
    }

    public SourceTypes getSourceType()
    {
        return sourceType;
    }

    public void setSourceType(SourceTypes sourceType)
    {
        this.sourceType = sourceType;
    }

}
