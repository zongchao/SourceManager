package com.neko.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.neko.service.interfaces.AttachmentServiceInterface;
import com.opensymphony.xwork2.ActionSupport;

@Controller
public class AttachmentAction extends ActionSupport
{
    @Resource(name = "attachmentService")
    private AttachmentServiceInterface attachmentService;

    public AttachmentServiceInterface getAttachmentService()
    {
        return attachmentService;
    }

    public void setAttachmentService(AttachmentServiceInterface attachmentService)
    {
        this.attachmentService = attachmentService;
    }
}
