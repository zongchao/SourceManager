package com.neko.page.bean;

import java.util.ArrayList;

public class Pager
{
    private static int DEFAULT_PAGE_SIZE = 10;
    
    private int pageSize = DEFAULT_PAGE_SIZE;     // 每页的记录数
    private Object data;                          // 当前页中存放的记录，类型一般为List
    private int totalCount;                       // 总记录数
    private int pageNo;                           // 当前页号
    private String pageHtml;                      // 分页在网页上的内容

    /**
     * 构造方法，只构造空页
     */
    public Pager()
    {
        this(0, 0, DEFAULT_PAGE_SIZE, new ArrayList());
    }

    /**
     * 构造方法
     * @param startIndex 起始索引
     * @param totalCount 总条数
     * @param pageSize 每页的大小
     * @param data 数据集
     */
    public Pager(int pageNo, int totalCount, int pageSize, Object data)
    {
        this.pageSize = pageSize;
        this.data = data;
        this.totalCount = totalCount;
        this.pageNo = pageNo;
    }
    
    /**
     * 取每页数据容量
     */
    public int getPageSize()
    {
        return pageSize;
    }

    /**
     * 获取下一页的页号
     * @param pageNo 当前页号
     * @return 下一页的页号
     */
    public int getNextPageNo(int pageNo)
    {
        return pageNo + 1;
    }

    /**
     * 获取上一页的页号
     * @param pageNo 当前页号
     * @return 上一页的页号
     */
    public int getPreviousPageNo(int pageNo)
    {
        return pageNo - 1;
    }

    /**
     * 取总记录数
     */
    public int getTotalCount()
    {
        return totalCount;
    }

    /**
     * 取该页当前页码，页码从1开始
     */
    public int getPageNo()
    {
        return this.pageNo;
    }
    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    /**
     * 该页是否有下一页
     */
    public boolean hasNextPage()
    {
        return this.getPageNo() < this.getTotalPageCount();
    }

    /**
     * 该页是否有上一页
     */
    public boolean hasPreviousPage()
    {
        return this.getPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值
     * @param pageNo 当前页号
     * @see #getStartOfPage(int,int)
     * @return 数据集的位置
     */
    protected static int getStartOfPage(int pageNo)
    {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取任一页第一条数据在数据集的位置
     * @param pageNo 当前页号
     * @param pageSize 每页显示的条数
     * @return 数据集的位置
     */
    public static int getStartOfPage(int pageNo, int pageSize)
    {
        return (pageNo - 1) * pageSize;
    }

    /**
     * 取得总页数
     * @return 总页数
     */
    public int getTotalPageCount()
    {
        int totalPageCount = 0;
        if (totalCount % pageSize == 0)
        {
            totalPageCount = totalCount / pageSize;
        }
        else
        {
            totalPageCount = totalCount / pageSize + 1;
        }
        return totalPageCount;
    }

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data = data;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public void setTotalCount(int totalCount)
    {
        this.totalCount = totalCount;
    }

    public String getPageHtml()
    {
        return pageHtml;
    }

    public void setPageHtml(String pageHtml)
    {
        this.pageHtml = pageHtml;
    }
    
}