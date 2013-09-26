package com.neko.action;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.neko.bean.MessageBoards;
import com.neko.bean.Users;
import com.neko.page.bean.Pager;
import com.neko.service.interfaces.MessageBoardServiceInterface;
import com.neko.util.StringUtil;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
@Namespace("/MessageBoard")
public class MessageBoardAction extends ActionSupport implements  Preparable
{
    Collection<MessageBoards> messageBoardList;
    Pager pager;
    
    @Resource(name = "messageBoardService")
    MessageBoardServiceInterface messageBoardService;
    MessageBoards messageBoard;
    
    Date startDate;
    Date endDate;
    
    @Action(value="messageBoardList", results={
            @Result(location="/MessageBoard/messageBoardList.jsp"),
            @Result(name="login", type="redirect", location="/login.jsp" ),
    })
    public String messageBoardList()
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
        
        pager = messageBoardService.getMessageBoardPager(pager.getPageNo(), pager.getPageSize(), startDate, endDate);
        messageBoardList = (List<MessageBoards>)pager.getData();
        return SUCCESS;
    }

    @Action(value="messageList", results={
            @Result(location="/MessageBoard/messageList.jsp"),
            @Result(name="login", type="redirect", location="/login.jsp" ),
    })
    public String messageList()
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
        
        pager = messageBoardService.getMessageBoardList(pager.getPageNo(), pager.getPageSize(), startDate, endDate);
        messageBoardList = (List<MessageBoards>)pager.getData();
        return SUCCESS;
    }
    
    @Action(value="addMessageBoard", results={
            @Result(type="redirect", location="messageBoardList?pager.pageNo=1"),
            @Result(name="error", type="redirect", location="/error.jsp" ),
    })
    public String addMessageBoard()
    {
        if(StringUtil.IsNullOrWhiteSpace(messageBoard.getMessage()))
        {
            return ERROR;
        }
        if(messageBoardService.addMessageBoard(messageBoard))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="findMessageBoardById", results={
            @Result(location="/MessageBoard/messageBoardDetail.jsp"),
            @Result(name="error", type="redirect", location="/error.jsp" ),
    })
    public String findMessageBoardById()
    {
        if(messageBoard != null && messageBoard.getId() != null)
        {
            messageBoard = messageBoardService.findMessageBoardById(messageBoard.getId());
            if(messageBoard == null)
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
    
    @Action(value="delMessageBoard", results={
            @Result(type="redirect", location="messageList?pager.pageNo=1"),
            @Result(name="input", type="redirect", location="messageList?pager.pageNo=1" ),
            @Result(name="error", type="redirect", location="/error.jsp" )
    })
    public String delMessageBoard()
    {
        if(messageBoardService.delMessageBoard(this.messageBoard.getId()))
        {
            return SUCCESS;
        }
        else 
        {
            return ERROR;
        }
    }
    
    @Override
    public void prepare() throws Exception
    {
        clearActionErrors();
    }

    public Pager getPager()
    {
        return pager;
    }

    public void setPager(Pager pager)
    {
        this.pager = pager;
    }

    public Collection<MessageBoards> getMessageBoardList()
    {
        return messageBoardList;
    }

    public void setMessageBoardList(Collection<MessageBoards> messageBoardList)
    {
        this.messageBoardList = messageBoardList;
    }

    public MessageBoardServiceInterface getMessageBoardService()
    {
        return messageBoardService;
    }

    public void setMessageBoardService(MessageBoardServiceInterface messageBoardService)
    {
        this.messageBoardService = messageBoardService;
    }

    public MessageBoards getMessageBoard()
    {
        return messageBoard;
    }

    public void setMessageBoard(MessageBoards messageBoard)
    {
        this.messageBoard = messageBoard;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }
    
}
