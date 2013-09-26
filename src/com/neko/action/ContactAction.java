package com.neko.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.neko.bean.Attachments;
import com.neko.bean.ContactTypes;
import com.neko.bean.Contacts;
import com.neko.bean.Users;
import com.neko.page.bean.Pager;
import com.neko.service.interfaces.AttachmentServiceInterface;
import com.neko.service.interfaces.ContactServiceInterface;
import com.neko.service.interfaces.ContactTypeServiceInterface;
import com.neko.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
@Namespace("/ContactManager")
public class ContactAction extends ActionSupport implements  Preparable
{
    Collection<Contacts> contactList;
    Collection<ContactTypes> contactTypeList;
    
    @Resource(name = "contactService")
    ContactServiceInterface contactService;
    @Resource(name = "contactTypeService")
    ContactTypeServiceInterface contactTypeService;
    @Resource(name = "attachmentService")
    AttachmentServiceInterface attachmentService;
    
    Pager pager;
    Contacts contact;
    int modifyOption;
    String condition;
    
    // 上传附件用
    private File file;
    private String fileFileName; //文件名称
    private String fileContentType; //文件类型
    
    @Action(value="contactList", results={
            @Result(name="login", type="redirect", location="/login.jsp"),
            @Result(location="/ContactManager/contactList.jsp")
    })
    public String contactList()
    {
        if(pager == null)
        {
            pager = new Pager();
        }
        
        Map<String, Object> session = ActionContext.getContext().getSession();
        Users user = (Users)session.get("user");
        if(user == null)
        {
            return "login";
        }
        
        pager = contactService.getContactPager(user.getId(), pager.getPageNo(), pager.getPageSize(), condition);
        contactList = (List<Contacts>) pager.getData();
        return SUCCESS;
    }
    
    @Action(value="addContactPre", results={
            @Result(location="/ContactManager/addContact.jsp")
    })
    public String addContactPre()
    {
        contactTypeList = contactTypeService.getContactTypeList();
        return SUCCESS;
    }
    
    @Action(value="addContact", results={
            @Result(type="redirect", location="contactList?pager.pageNo=1"),
            @Result(name="input", type="redirect", location="addContactPre"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String addContact()
    {
        if(StringUtil.IsNullOrWhiteSpace(contact.getName()))
        {
            addActionError("请确认联系人姓名是否输入");
            return INPUT;
        }
        Map<String, Object> session = ActionContext.getContext().getSession();
        Users user = (Users)session.get("user");
        String realPath = ServletActionContext.getServletContext().getRealPath("/");
        if (file != null)
        {
            try
            {
                Attachments attachment = attachmentService.addAttachment(file, realPath, fileFileName);
                contact.setAttachments(attachment);
            }
            catch (Exception e)
            {
                addActionError("上传附件错误");
                return INPUT;
            }
        }
        contact.setUsers(user);
        if(contactService.addContact(contact))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }

    @Actions({
        @Action(value="contactDetail", results={
                @Result(location="/ContactManager/contactDetail.jsp"),
                @Result(name="error", type="redirect", location="/error.jsp")
        }),
        @Action(value="modContactPre", results={
                @Result(location="/ContactManager/modContact.jsp"),
                @Result(name="error", type="redirect", location="/error.jsp")
        })
    })
    public String findContactById()
    {
        contactTypeList = contactTypeService.getContactTypeList();
        if(contact != null && contact.getId() != null)
        {
            contact = contactService.findContactById(contact.getId());
            if(contact == null)
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
    
    @Action(value="modContact", results={
            @Result(type="redirect", location="contactList?pager.pageNo=1"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String modContact()
    {
        Boolean isSuccess = true;
        
        if(StringUtil.IsNullOrWhiteSpace(contact.getName()))
        {
            addActionError("请确认联系人姓名是否输入");
            return INPUT;
        }
        String realPath = ServletActionContext.getServletContext().getRealPath("/");
        try
        {
            // 根据用户的选择对附件进行修改
            switch (modifyOption)
            {
                
                case 1:   // 删除附件
                    Integer oldAttachmentId = contact.getAttachments().getId();
                    contact.setAttachments(null);
                    contactService.modContact(contact);
                    attachmentService.delAttachment(realPath,oldAttachmentId);
                    break;
                case 2:   // 修改附件
                    // 之前已存在附件
                    if(contact.getAttachments() != null)
                    {
                        contact.setAttachments(attachmentService.modAttachment(file, realPath, fileFileName,contact.getAttachments().getId()));
                    }
                    else   // 新建附件
                    {
                        Attachments attachment = attachmentService.addAttachment(file, realPath, fileFileName);
                        contact.setAttachments(attachment);
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
       
        if(contactService.modContact(contact))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="delContact", results={
            @Result(type="redirect", location="contactList?pager.pageNo=1"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String delContact()
    {
        Boolean isSuccess = true;
        Contacts contact = contactService.findContactById(this.contact.getId());
        String realPath = ServletActionContext.getServletContext().getRealPath("/");
        if(!contactService.delContact(this.contact.getId()))
        {
            isSuccess = false;
        }
        else 
        {
            if(contact.getAttachments() != null)
            {
                if(!attachmentService.delAttachment(realPath,contact.getAttachments().getId()))
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
    
    public InputStream getContactExcel()
    {
        Map<String, Object> session = ActionContext.getContext().getSession();
        Users user = (Users)session.get("user");
        fileFileName = "通讯录.xls";
        String realPath = ServletActionContext.getServletContext().getRealPath("/");
        InputStream is = null;
        File excel = null;
        try
        {
            excel = contactService.generateExcel(realPath,user.getId());
            is = new FileInputStream(excel);
        } 
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        finally
        {
            excel.delete();
        }
        return is;
    }
      
    @Action(value="generateContactExcel", results={
            @Result(type="stream", params={
                    "contentType", "application/vnd.ms-excel", 
                    "contentDisposition", "attachment;fileName=${fileFileName}", 
                    "inputName", "contactExcel", 
                    "bufferSize", "4096"
                    })
    })
    public String generateContactExcel()
    {
        return SUCCESS;
    }

    @Override
    public void prepare() throws Exception
    {
        clearActionErrors();
    }
    
    public File getFile()
    {
        return file;
    }

    public ContactServiceInterface getContactService()
    {
        return contactService;
    }

    public void setContactService(ContactServiceInterface contactService)
    {
        this.contactService = contactService;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public Contacts getContact()
    {
        return contact;
    }

    public void setContact(Contacts contact)
    {
        this.contact = contact;
    }

    public String getFileFileName()
    {
        HttpServletResponse response = ServletActionContext.getResponse();
        response.setHeader("charset", "UTF-8");
        
        try
        {
            fileFileName =  URLEncoder.encode(fileFileName, "UTF-8");
        }
        catch (UnsupportedEncodingException e)
        {
            e.printStackTrace();
        }
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

    public Pager getPager()
    {
        return pager;
    }

    public void setPager(Pager pager)
    {
        this.pager = pager;
    }

    public Collection<Contacts> getContactList()
    {
        return contactList;
    }
    public void setContactList(Collection<Contacts> contactList)
    {
        this.contactList = contactList;
    }

    public Collection<ContactTypes> getContactTypeList()
    {
        return contactTypeList;
    }

    public void setContactTypeList(Collection<ContactTypes> contactTypeList)
    {
        this.contactTypeList = contactTypeList;
    }

    public ContactTypeServiceInterface getContactTypeService()
    {
        return contactTypeService;
    }

    public void setContactTypeService(ContactTypeServiceInterface contactTypeService)
    {
        this.contactTypeService = contactTypeService;
    }

    public String getCondition()
    {
        return condition;
    }

    public void setCondition(String condition)
    {
        this.condition = condition;
    }
   
}
