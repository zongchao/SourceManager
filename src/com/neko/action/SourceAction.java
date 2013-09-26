package com.neko.action;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.neko.bean.Attachments;
import com.neko.bean.SourceTypes;
import com.neko.bean.Sources;
import com.neko.page.bean.Pager;
import com.neko.service.interfaces.AttachmentServiceInterface;
import com.neko.service.interfaces.SourceServiceInterface;
import com.neko.service.interfaces.SourceTypeServiceInterface;
import com.neko.util.StringUtil;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
@Namespace("/SourceManager")
public class SourceAction extends ActionSupport implements  Preparable
{
    Collection<Sources> sourceList;
    Collection<SourceTypes> sourceTypeList;
    Map<Integer, String> breadcrumb;
    Pager pager;
    
    @Resource(name = "sourceService")
    SourceServiceInterface sourceService;
    
    @Resource(name="sourceTypeService")
    SourceTypeServiceInterface sourceTypeService;
    
    @Resource(name="attachmentService")
    AttachmentServiceInterface attachmentService;
    
    Sources source;
    int modifyOption;
    
    // 上传附件用
    private File file;
    private String fileFileName; //文件名称
    private String fileContentType; //文件类型
    
    @Action(value="sourceList", results={
            @Result(location="/SourceManager/sourceList.jsp"),
    })
    public String sourceList()
    {
        if(pager == null)
        {
            pager = new Pager();
        }
        
        pager = sourceService.getSourcePager(source.getSourcesParent().getId(), pager.getPageNo(), pager.getPageSize());
        sourceList = (List<Sources>)pager.getData();
        breadcrumb = sourceService.getBreadcrumb(source.getSourcesParent().getId());
        return SUCCESS;
    }
    
    @Action(value="addSourcePre", results={
            @Result(location="/SourceManager/addSource.jsp"),
    })
    public String addSourcePre()
    {
        sourceTypeList = sourceTypeService.getSourceTypeList();
        return SUCCESS;
    }
    
    @Action(value="addSource", results={
            @Result(type="redirect", location="sourceList?source.sourcesParent.id=${source.sourcesParent.id}&pager.pageNo=1"),
            @Result(name="input", type="redirect", location="addSourcePre" ),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String addSource()
    {
        if(StringUtil.IsNullOrWhiteSpace(source.getName()))
        {
            addActionError("请确认资源名是否输入");
            return INPUT;
        }
        String realPath = ServletActionContext.getServletContext().getRealPath("/");
        if (file != null)
        {
            try
            {
                Attachments attachment = attachmentService.addAttachment(file, realPath, fileFileName);
                source.setAttachments(attachment);
            }
            catch (Exception e)
            {
                addActionError("上传附件错误");
                return INPUT;
            }
        }
       
        if(sourceService.addSource(source))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="delSource", results={
            @Result(type="redirect", location="sourceList?source.sourcesParent.id=${source.sourcesParent.id}&pager.pageNo=1"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String delSource()
    {
        Boolean isSuccess = true;
        source = sourceService.findSourceById(this.source.getId());
        String realPath = ServletActionContext.getServletContext().getRealPath("/");
        if(!sourceService.delSource(source.getId()))
        {
            isSuccess = false;
        }
        else 
        {
            if(source.getAttachments() != null)
            {
                if(!attachmentService.delAttachment(realPath,source.getAttachments().getId()))
                {
                    isSuccess = false;
                }
            }
        }
        if(isSuccess)
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
        
    }
    
    @Actions({
        @Action(value="sourceDetail", results={
                @Result(location="/SourceManager/sourceDetail.jsp"),
                @Result(name="error", type="redirect", location="/error.jsp")
        }),
        @Action(value="modSourcePre", results={
                @Result(location="/SourceManager/modSource.jsp"),
                @Result(name="error", type="redirect", location="/error.jsp")
        }),
    })
    public String findSourceById()
    {
        sourceTypeList = sourceTypeService.getSourceTypeList();
        if(source != null && source.getId() != null)
        {
            source = sourceService.findSourceById(source.getId());
            if(source == null)
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
    
    @Action(value="modSource", results={
            @Result(type="redirect", location="sourceList?source.sourcesParent.id=${source.sourcesParent.id}&pager.pageNo=1"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String modSource()
    {
        Boolean isSuccess = true;
        
        if(StringUtil.IsNullOrWhiteSpace(source.getName()))
        {
            addActionError("请确认资源名是否输入");
            return INPUT;
        }
        String realPath = ServletActionContext.getServletContext().getRealPath("/");
        try
        {
            // 根据用户的选择对附件进行修改
            switch (modifyOption)
            {
                
                case 1:   // 删除附件
                    Integer oldAttachmentId = source.getAttachments().getId();
                    source.setAttachments(null);
                    sourceService.modSource(source);
                    attachmentService.delAttachment(realPath,oldAttachmentId);
                    break;
                case 2:   // 修改附件
                    // 之前已存在附件
                    if(source.getAttachments() != null)
                    {
                        source.setAttachments(attachmentService.modAttachment(file, realPath, fileFileName,source.getAttachments().getId()));
                    }
                    else   // 新建附件
                    {
                        Attachments attachment = attachmentService.addAttachment(file, realPath, fileFileName);
                        source.setAttachments(attachment);
                    }
                    break;
                default:
                    break;
            }
            
        }
        catch (Exception e)
        {
            addActionError("修改附件错误");
            return INPUT;
        }
       
        if(sourceService.modSource(source))
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

    public Collection<Sources> getSourceList()
    {
        return sourceList;
    }

    public void setSourceList(Collection<Sources> sourceList)
    {
        this.sourceList = sourceList;
    }

    public SourceServiceInterface getSourceService()
    {
        return sourceService;
    }

    public void setSourceService(SourceServiceInterface sourceService)
    {
        this.sourceService = sourceService;
    }

    public Collection<SourceTypes> getSourceTypeList()
    {
        return sourceTypeList;
    }

    public void setSourceTypeList(Collection<SourceTypes> sourceTypeList)
    {
        this.sourceTypeList = sourceTypeList;
    }

    public SourceTypeServiceInterface getSourceTypeService()
    {
        return sourceTypeService;
    }

    public void setSourceTypeService(SourceTypeServiceInterface sourceTypeService)
    {
        this.sourceTypeService = sourceTypeService;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public Sources getSource()
    {
        return source;
    }

    public void setSource(Sources source)
    {
        this.source = source;
    }

    public String getFileFileName()
    {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType()
    {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType)
    {
        this.fileContentType = fileContentType;
    }

    public AttachmentServiceInterface getAttachmentService()
    {
        return attachmentService;
    }

    public void setAttachmentService(AttachmentServiceInterface attachmentService)
    {
        this.attachmentService = attachmentService;
    }

    public int getModifyOption()
    {
        return modifyOption;
    }

    public void setModifyOption(int modifyOption)
    {
        this.modifyOption = modifyOption;
    }

    public Map<Integer, String> getBreadcrumb()
    {
        return breadcrumb;
    }

    public void setBreadcrumb(Map<Integer, String> breadcrumb)
    {
        this.breadcrumb = breadcrumb;
    }

    public Pager getPager()
    {
        return pager;
    }

    public void setPager(Pager pager)
    {
        this.pager = pager;
    }
    
}
