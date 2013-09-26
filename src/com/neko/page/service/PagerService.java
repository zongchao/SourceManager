package com.neko.page.service;

import java.util.ArrayList;
import java.util.List;

import com.neko.page.bean.Pager;

public class PagerService
{
    private final int PAGEALLCOUNT = 10;
    private final int PAGEPRECOUNT = 4;
    private final int PAGELASTCOUNT = 4;
    
    private Pager pager;
    /**
     * 构造方法，只构造空页
     */
    public PagerService(Pager pager)
    {
        this.pager = pager;
    }

    public String paging(String cilckUrl)
    {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("<ul>");
        strBuffer.append(getPrePagingHtml(cilckUrl));
        if(pager.getTotalPageCount() <= PAGEALLCOUNT)
        {
            strBuffer.append(pagingAll(cilckUrl));
        }
        else
        {
            if(pager.getPageNo() <= PAGEPRECOUNT)
            {
                strBuffer.append(pagingPre(cilckUrl));
            }
            else if(pager.getPageNo() > PAGEPRECOUNT && pager.getPageNo() <= pager.getTotalPageCount() - PAGELASTCOUNT)
            {
                strBuffer.append(pagingCenter(cilckUrl));
            }
            else if(pager.getPageNo() > pager.getTotalPageCount() - PAGELASTCOUNT)
            {
                strBuffer.append(pagingLast(cilckUrl));
            }
        }
        strBuffer.append(getNextPagingHtml(cilckUrl));
        strBuffer.append("</ul>");
        return strBuffer.toString();
    }

    private String pagingAll(String cilckUrl)
    {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 1; i <= pager.getTotalPageCount(); i++)
        {
            strBuffer.append("\n");
            if(pager.getPageNo() == i)
            {
                strBuffer.append(getDisabledPageHtml(String.valueOf(i)));
            }
            else
            {
                strBuffer.append(getEnabledPageHtml(cilckUrl, String.valueOf(i), String.valueOf(i)));
            }
        }
        return strBuffer.toString();
    }
    
    private String pagingPre(String cilckUrl)
    {
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 1; i <= PAGEPRECOUNT + 1; i++)
        {
            strBuffer.append("\n");
            if(pager.getPageNo() == i)
            {
                strBuffer.append(getDisabledPageHtml(String.valueOf(i)));
            }
            else
            {
                strBuffer.append(getEnabledPageHtml(cilckUrl, String.valueOf(i), String.valueOf(i)));
            }
        }
        strBuffer.append(getDisabledPageHtml("•••"));
        String totalPageCountStr = String.valueOf(pager.getTotalPageCount());
        strBuffer.append(getEnabledPageHtml(cilckUrl, totalPageCountStr, totalPageCountStr));
        return strBuffer.toString();
    }
    
    private String pagingLast(String cilckUrl)
    {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(getEnabledPageHtml(cilckUrl, "1", "1"));
        strBuffer.append(getDisabledPageHtml("•••"));
        int totalPageCount = pager.getTotalPageCount();
        for (int i = totalPageCount - PAGELASTCOUNT; i <= totalPageCount; i++)
        {
            strBuffer.append("\n");
            if(pager.getPageNo() == i)
            {
                strBuffer.append(getDisabledPageHtml(String.valueOf(i)));
            }
            else
            {
                strBuffer.append(getEnabledPageHtml(cilckUrl, String.valueOf(i), String.valueOf(i)));
            }
        }
        return strBuffer.toString();
    }

    private String pagingCenter(String cilckUrl)
    {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append(getEnabledPageHtml(cilckUrl, "1", "1"));
        strBuffer.append(getDisabledPageHtml("•••"));
        int totalPageCount = pager.getTotalPageCount();
        for (int i = pager.getPageNo() - 2; i <= pager.getPageNo() + 2; i++)
        {
            strBuffer.append("\n");
            if(pager.getPageNo() == i)
            {
                strBuffer.append(getDisabledPageHtml(String.valueOf(i)));
            }
            else
            {
                strBuffer.append(getEnabledPageHtml(cilckUrl, String.valueOf(i), String.valueOf(i)));
            }
        }
        strBuffer.append(getDisabledPageHtml("•••"));
        String totalPageCountStr = String.valueOf(pager.getTotalPageCount());
        strBuffer.append(getEnabledPageHtml(cilckUrl, totalPageCountStr, totalPageCountStr));
        return strBuffer.toString();
    }
    
    private String getDisabledPageHtml(String value)
    {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("<li class='disabled'>");
        strBuffer.append("<a>"+ value +"</a>");
        strBuffer.append("</li>");
        return strBuffer.toString();
    }
    
    private String getPrePagingHtml(String cilckUrl)
    {
        StringBuffer strBuffer = new StringBuffer();
        
        if(!pager.hasPreviousPage())
        {
            strBuffer.append(getDisabledPageHtml("«"));
        }
        else
        {
            strBuffer.append(getEnabledPageHtml(cilckUrl, String.valueOf(pager.getPreviousPageNo(pager.getPageNo())), "«"));
        }
        return strBuffer.toString();
    }
    
    private String getNextPagingHtml(String cilckUrl)
    {
        StringBuffer strBuffer = new StringBuffer();
        if(!pager.hasNextPage())
        {
            strBuffer.append(getDisabledPageHtml("»"));
        }
        else
        {
            strBuffer.append(getEnabledPageHtml(cilckUrl, String.valueOf(pager.getNextPageNo(pager.getPageNo())), "»"));
        }
        return strBuffer.toString();
    }

    private String getEnabledPageHtml(String cilckUrl,String pageNo,String value)
    {
        StringBuffer strBuffer = new StringBuffer();
        strBuffer.append("<li>");
        strBuffer.append("<a href='");
        strBuffer.append(cilckUrl);
        strBuffer.append("&pager.pageNo=" + pageNo);
        strBuffer.append("&pager.pageSize=" + pager.getPageSize());
        strBuffer.append("'>");
        strBuffer.append(value);
        strBuffer.append("</a>");
        strBuffer.append("</li>");
        return strBuffer.toString();
    }
    
    public static void main(String[] args)
    {
        Pager pager = new Pager();
        pager.setTotalCount(113);
        pager.setPageNo(0);
        PagerService service = new PagerService(pager);
        String str = service.paging("aa");
        System.out.println(str);
    }
}
