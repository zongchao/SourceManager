package com.neko.action;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.neko.common.service.CaptchaService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@Controller
@Namespace("/")
public class CommonAction extends ActionSupport
{
    @Resource(name = "captchaService")
    private CaptchaService captchaService;
    private ByteArrayInputStream inputStream;  
    
    @Action(value="getCaptcha", results={
            @Result(type="stream", params={
                    "contentType", "image/jpeg", "inputName", "inputStream"})
    })
    public String getCaptcha()
    {
        try
        {
            ByteArrayInputStream input = captchaService.getCaptcha();
            this.setInputStream(input);
            return SUCCESS;
        }
        catch (Exception e)
        {
           return ERROR;
        }
        
    }
    
    public ByteArrayInputStream getInputStream()
    {
        return inputStream;
    }

    public void setInputStream(ByteArrayInputStream inputStream)
    {
        this.inputStream = inputStream;
    }

    public CaptchaService getCaptchaService()
    {
        return captchaService;
    }

    public void setCaptchaService(CaptchaService captchaService)
    {
        this.captchaService = captchaService;
    }
    
    
}
