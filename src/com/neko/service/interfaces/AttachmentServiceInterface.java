package com.neko.service.interfaces;

import java.io.File;

import com.neko.bean.Attachments;


/**
 * @author zc
 * @date 20121218
 */
public interface AttachmentServiceInterface
{
    /**
     * 添加文件
     * @param uploadFile 上传的文件
     * @param realPath 网站路径
     * @param fileName 文件名
     * @return
     */
    Attachments addAttachment(File uploadFile,String realPath,String fileName); 
    
    /**
     * 修改文件
     * @param uploadFile 上传的文件
     * @param realPath 网站路径
     * @param fileName 文件名
     * @return
     */
    Attachments modAttachment(File uploadFile,String realPath,String fileName,Integer id);
    
    /**
     * 删除数据库中的附件信息以及删除服务器上的对应附件
     * @param realPath 网站路径
     * @param id 附件id
     * @return 是否成功
     */
    Boolean delAttachment(String realPath,Integer id);
    
    /**
     * 通过id查找附件
     * @param id 附件id
     * @return 查询的附件
     */
    Attachments findAttachmenById(Integer id);
} 