package com.neko.util;

import java.io.IOException;
import java.io.PrintWriter;

import org.apache.struts2.ServletActionContext;

public class CommonUtil
{
    public static void printMessage(Boolean message) 
    {
        ServletActionContext.getResponse().setContentType ("text/html;charset=utf-8");
        ServletActionContext.getResponse().setHeader("Pragma", "no-cache");
        ServletActionContext.getResponse().setDateHeader("Expires", 0);
        PrintWriter out;
        try
        {
            out = ServletActionContext.getResponse().getWriter();
            out.print(message);
            out.flush();
            out.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
    }
}
