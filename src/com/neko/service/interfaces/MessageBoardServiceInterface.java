package com.neko.service.interfaces;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.neko.bean.ContactTypes;
import com.neko.bean.Finances;
import com.neko.bean.MessageBoards;
import com.neko.bean.SourceTypes;
import com.neko.bean.Sources;
import com.neko.bean.Users;
import com.neko.page.bean.Pager;

/**
 * @author zc
 * @date 20130328
 */
public interface MessageBoardServiceInterface
{
    /**
     * 根据日期返回财务列表（分页的形式）
     * @param pageNo 当前页号
     * @param pageSize 每页显示的条数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 分页信息
     */
    Pager getMessageBoardList(int pageNo, int pageSize, Date startDate, Date endDate);
    
    /**
     * 
     * 根据日期返回财务列表（分页的形式）
     * @param pageNo 当前页号
     * @param pageSize 每页显示的条数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 分页信息
     */
    Pager getMessageBoardPager(int pageNo,int pageSize, Date startDate, Date endDate);
    
    /**
     * 通过id查找留言
     * @param Integer 留言id
     * @return 留言信息
     */
    MessageBoards findMessageBoardById(Integer id);
    
    /**
     * 添加留言
     * @param source 添加的留言
     * @return 是否成功
     */
    Boolean addMessageBoard(MessageBoards messageBoard); 
    
    /**
     * 删除留言
     * @param id 删除的留言id
     * @return 是否成功
     */
    Boolean delMessageBoard(Integer id);
    
}