package com.neko.service.impl;

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import com.neko.bean.Finances;
import com.neko.bean.MessageBoards;
import com.neko.dao.MessageBoardsDAO;
import com.neko.page.bean.Pager;
import com.neko.page.service.PagerService;
import com.neko.service.interfaces.MessageBoardServiceInterface;

@Service("messageBoardService")
public class MessageBoardServiceHibernateImpl implements MessageBoardServiceInterface
{
    public static Logger log = Logger.getLogger(MessageBoardServiceHibernateImpl.class.getName());
    
    @Resource(name = "messageBoardsDAO")
    MessageBoardsDAO messageBoardsDAO;
    
    @Override
    public Boolean addMessageBoard(MessageBoards messageBoard)
    {
        log.info("开始保存留言");
        Boolean flag = false;
        try
        {
            messageBoard.setCreateTime(new Date());
            messageBoardsDAO.save(messageBoard);
            flag = true;
            log.info("保存留言完成");
        }
        catch (Exception e)
        {
            log.info("保存留言失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    @Override
    public Boolean delMessageBoard(Integer id)
    {
        log.info("开始删除id="+ id +"的留言信息");
        Boolean flag = false;
        try
        {
            MessageBoards messageBoard = findMessageBoardById(id);
            messageBoardsDAO.delete(messageBoard);
            flag = true;
        }
        catch (Exception e)
        {
            flag = false;
            log.info("删除留言信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    @Override
    public MessageBoards findMessageBoardById(Integer id)
    {
        log.info("通过id获取留言信息");
        MessageBoards messageBoard = messageBoardsDAO.findById(id);
        return messageBoard;
    }
    
    @Override
    public Pager getMessageBoardList(int pageNo, int pageSize, Date startDate, Date endDate)
    {
        log.info("获取留言第"+ pageNo + "页,每页" + pageSize + "条" );
        
        DetachedCriteria criteria = getCriteria(startDate, endDate);
        int totalCount = messageBoardsDAO.findByCriteria(criteria).size();
        Pager pager = new Pager();
        if(totalCount >= 1)
        {
            int startIndex = Pager.getStartOfPage(pageNo, pageSize);
            
            List<MessageBoards> list = messageBoardsDAO.findListByPager(startIndex, pageSize, criteria);
            pager = new Pager(pageNo, totalCount, pageSize, list);
            PagerService pagerService = new PagerService(pager);
            
            StringBuffer clickUrl = new StringBuffer("messageList.action?");
            if(startDate != null && endDate != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                clickUrl.append("&startDate=");
                clickUrl.append(sdf.format(startDate));
                clickUrl.append("&endDate=");
                clickUrl.append(sdf.format(endDate));
            }
            pager.setPageHtml(pagerService.paging(clickUrl.toString()));
        }
        return pager;
    }
    
    @Override
    public Pager getMessageBoardPager(int pageNo, int pageSize, Date startDate, Date endDate)
    {
        log.info("获取留言第"+ pageNo + "页,每页" + pageSize + "条" );
        
        DetachedCriteria criteria = getCriteria(startDate, endDate);
        int totalCount = messageBoardsDAO.findByCriteria(criteria).size();
        Pager pager = new Pager();
        if(totalCount >= 1)
        {
            int startIndex = Pager.getStartOfPage(pageNo, pageSize);
            
            List<MessageBoards> list = messageBoardsDAO.findListByPager(startIndex, pageSize, criteria);
            pager = new Pager(pageNo, totalCount, pageSize, list);
            PagerService pagerService = new PagerService(pager);
            
            StringBuffer clickUrl = new StringBuffer("messageBoardList.action?");
            if(startDate != null && endDate != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                clickUrl.append("&startDate=");
                clickUrl.append(sdf.format(startDate));
                clickUrl.append("&endDate=");
                clickUrl.append(sdf.format(endDate));
            }
            pager.setPageHtml(pagerService.paging(clickUrl.toString()));
        }
        return pager;
    }
    
    private DetachedCriteria getCriteria(Date startDate, Date endDate)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(MessageBoards.class,"mb")
        .addOrder(Order.desc("mb.createTime"));
        if(startDate != null && endDate != null)
        {
            criteria.add(Restrictions.between("createTime", startDate,endDate));
        }
        return criteria;
    }
    
    public MessageBoardsDAO getMessageBoardsDAO()
    {
        return messageBoardsDAO;
    }
    
    public void setMessageBoardsDAO(MessageBoardsDAO messageBoardsDAO)
    {
        this.messageBoardsDAO = messageBoardsDAO;
    }

    
}
