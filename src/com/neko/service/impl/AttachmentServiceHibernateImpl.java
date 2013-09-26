package com.neko.service.impl;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.neko.bean.Attachments;
import com.neko.dao.AttachmentsDAO;
import com.neko.service.interfaces.AttachmentServiceInterface;
import com.neko.service.interfaces.SourceServiceInterface;
import com.neko.service.interfaces.SourceTypeServiceInterface;
import com.neko.util.FileUtil;
import com.neko.util.StringUtil;
import com.sun.jmx.snmp.Timestamp;

@Service("attachmentService")
public class AttachmentServiceHibernateImpl implements AttachmentServiceInterface
{

    private final String SOURCEIMAGESPATH = "/sourceImages";
    public static Logger log = Logger.getLogger(AttachmentServiceHibernateImpl.class.getName());
    
    @Resource(name = "attachmentsDAO")
    private AttachmentsDAO attachmentsDAO;
    
    @Override
    public Attachments addAttachment(File uploadFile, String realPath, String fileName)
    {
        String extension = FileUtil.getExtension(fileName);
        String randomFileName = String.valueOf(System.currentTimeMillis()) + extension;
        uploadFile(realPath,randomFileName,uploadFile);
        
        Attachments attachment = new Attachments();
        try
        {
            log.info("开始把附件信息存入数据库");
            attachment.setName(FileUtil.removeExtension(fileName));
            attachment.setType(extension);
            attachment.setUploadTime(new Date()); 
            attachment.setPath(SOURCEIMAGESPATH + "/" + randomFileName);
            attachmentsDAO.save(attachment);
            log.info("附件信息存入数据库成功");
        }
        catch (Exception e)
        {
            log.info("附件信息存入数据库失败");
        } 
        return attachment;
    }

    /**
     * 上传文件
     * @param realPath 网站路径
     * @param randomFileName 随机的文件名（取得系统当前时间）
     * @param uploadFile 上传的文件
     */
    private void uploadFile(String realPath,String randomFileName,File uploadFile)
    {
        try
        {
            log.info("开始文件上传服务器");
            File savefile = new File(new File(realPath + SOURCEIMAGESPATH), randomFileName);
            FileUtil.createFolder(savefile.getParentFile().getName());
            FileUtil.copyFile(uploadFile, savefile);
            log.info("文件上传服务器成功");
        }
        catch (Exception e)
        {
            log.info("文件上传服务器时发生了问题");
            log.error(e.getMessage());
        }
    }
    
    @Override
    public Attachments modAttachment(File uploadFile, String realPath, String fileName, Integer id)
    {
        Attachments oldAttachments = findAttachmenById(id);
        // 上传新附件
        String extension = FileUtil.getExtension(fileName);
        String randomFileName = String.valueOf(System.currentTimeMillis()) + extension;
        uploadFile(realPath,randomFileName,uploadFile);
        
        String fullPath = "";
        if(StringUtil.IsNullOrWhiteSpace(oldAttachments.getPath()))
        {
            fullPath = realPath + oldAttachments.getPath();
        }
        
        // 修改数据库中的附件信息
        try
        {
            log.info("开始修改数据库中的附件信息");
            oldAttachments.setName(FileUtil.removeExtension(fileName));
            oldAttachments.setType(extension);
            oldAttachments.setUploadTime(new Date()); 
            oldAttachments.setPath(SOURCEIMAGESPATH + "/" + randomFileName);
            attachmentsDAO.merge(oldAttachments);
            log.info("修改数据库中的附件信息成功");
        }
        catch (Exception e)
        {
            log.info("修改数据库中的附件信息失败");
        } 
        
        // 如果服务器上附件还存在，删除附件
        removeFile(fullPath);
        return oldAttachments;
        
    }
    
    @Override
    public Boolean delAttachment(String realPath,Integer id)
    {
        Boolean flag = false;
        Attachments attachment = findAttachmenById(id);
        log.info("开始删除附件" + attachment.getId() + attachment.getName());
        try
        {
            String fullPath = realPath + attachment.getPath();
            attachmentsDAO.delete(attachment);
            
            // 如果服务器上附件还存在，删除附件
            removeFile(fullPath);
            flag = true;
            log.info("删除附件成功");
        }
        catch (Exception e)
        {
            log.info("删除附件失败");
            log.error(e.getMessage());
        }
        return flag;
    }

    /**
     * 删除服务器上的附件
     * @param fullPath
     */
    private void removeFile(String fullPath)
    {
        if(new File(fullPath).exists())
        {
            FileUtil.delFile(fullPath);
        }
    }
    
    @Override
    public Attachments findAttachmenById(Integer id)
    {
        log.info("通过id"+ id +"查找附件");
        return attachmentsDAO.findById(id);
    }

    
    public AttachmentsDAO getAttachmentsDAO()
    {
        return attachmentsDAO;
    }

    public void setAttachmentsDAO(AttachmentsDAO attachmentsDAO)
    {
        this.attachmentsDAO = attachmentsDAO;
    }
    
}
