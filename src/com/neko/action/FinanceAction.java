package com.neko.action;

import java.io.File;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Actions;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.stereotype.Controller;

import com.neko.bean.FinanceStatistic;
import com.neko.bean.FinanceTypes;
import com.neko.bean.Finances;
import com.neko.bean.Users;
import com.neko.page.bean.Pager;
import com.neko.service.interfaces.FinanceServiceInterface;
import com.neko.service.interfaces.FinanceTypeServiceInterface;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.Preparable;

@Controller
public class FinanceAction extends ActionSupport implements  Preparable
{
    Collection<Finances> financeList;
    Collection<FinanceTypes> financeTypeList;
    Pager pager;
    
    @Resource(name = "financeService")
    FinanceServiceInterface financeService;
    @Resource(name = "financeTypeService")
    FinanceTypeServiceInterface financeTypeService;
    
    Finances finance;
    List<FinanceStatistic> financeStatistic;
    
    Date startDate;
    Date endDate;
    String graphURL;
    
    @Action(value="financeList", results={
            @Result(location="/FinanceManager/financeList.jsp"),
            @Result(name="login", type="redirect", location="/login.jsp")
    })
    public String financeList()
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
        pager = financeService.getFinancePager(user.getId(), pager.getPageNo(), pager.getPageSize(), startDate, endDate);
        financeList = (List<Finances>)pager.getData();
        return SUCCESS;
    }
    
    @Action(value="addFinancePre", results={
            @Result(location="/FinanceManager/addFinance.jsp"),
    })
    public String addFinancePre()
    {
        financeTypeList = financeTypeService.getFinanceTypeListByType(finance.getFinanceTypes().getType());
        return SUCCESS;
    }
    
    @Action(value="addFinance", results={
            @Result(type="redirect", location="financeList?pager.pageNo=1"),
            @Result(name="input", type="redirect", location="addFinancePre" ),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String addFinance()
    {
        if(finance.getMoney() <= 0)
        {
            addActionError("请确认金额是否输入");
            return INPUT;
        }
        
        Map<String, Object> session = ActionContext.getContext().getSession();
        Users user = (Users)session.get("user");
        finance.setUsers(user);
        
        if(financeService.addFinance(finance))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="delFinance", results={
            @Result(type="redirect", location="financeList?pager.pageNo=1"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String delFinance()
    {
        Boolean isSuccess = true;
        Finances finance = financeService.findFinanceById(this.finance.getId());
        if(!financeService.delFinance(finance.getId()))
        {
            isSuccess = false;
        }
        if(isSuccess)
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
        
    }
    
    @Actions({
        @Action(value="financeDetail", results={
                @Result(location="/FinanceManager/financeDetail.jsp"),
                @Result(name="error", type="redirect", location="/error.jsp")
        }),
        @Action(value="modFinancePre", results={
                @Result(location="/FinanceManager/modFinance.jsp"),
                @Result(name="error", type="redirect", location="/error.jsp")
        }),
    })
    public String findFinanceById()
    {
        if(finance.getFinanceTypes() == null || finance.getFinanceTypes().getType() == null)
        {
            financeTypeList = financeTypeService.getFinanceTypeList();
        }
        else
        {
            financeTypeList = financeTypeService.getFinanceTypeListByType(finance.getFinanceTypes().getType());
        }
        if(finance != null && finance.getId() != null)
        {
            finance = financeService.findFinanceById(this.finance.getId());
            if(finance == null)
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
    
    @Action(value="modFinance", results={
            @Result(type="redirect", location="financeList?pager.pageNo=1"),
            @Result(name="input", type="redirect", location="modFinancePre?finance.id=${finance.id}&amp;finance.financeTypes.type=${finance.financeTypes.type}"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String modFinance()
    {
        Boolean isSuccess = true;
        if(financeService.modFinance(finance))
        {
            return SUCCESS;
        }
        else
        {
            return ERROR;
        }
    }
    
    @Action(value="financeStatistic", results={
            @Result(location="/FinanceManager/financeStatistic.jsp"),
            @Result(name="login", type="redirect", location="/login.jsp"),
            @Result(name="error", type="redirect", location="/error.jsp")
    })
    public String financeStatistic()
    {
        Map<String, Object> session = ActionContext.getContext().getSession();
        Users user = (Users)session.get("user");
        if(user == null)
        {
            return "login";
        }
        financeStatistic = financeService.getFinanceStatistic(user.getId(),startDate,endDate);
        graphURL = financeService.getStatisticChart(financeStatistic,ServletActionContext.getRequest().getSession());
        financeStatistic = financeService.getFinanceStatisticsAmount(financeStatistic);
        return SUCCESS;
    }
    
    @Override
    public void prepare() throws Exception
    {
        clearActionErrors();
    }

    public Collection<Finances> getFinanceList()
    {
        return financeList;
    }
    
    public void setFinanceList(Collection<Finances> financeList)
    {
        this.financeList = financeList;
    }
    
    public Collection<FinanceTypes> getFinanceTypeList()
    {
        return financeTypeList;
    }
    
    public void setFinanceTypeList(Collection<FinanceTypes> financeTypeList)
    {
        this.financeTypeList = financeTypeList;
    }
    
    public FinanceServiceInterface getFinanceService()
    {
        return financeService;
    }
    
    public void setFinanceService(FinanceServiceInterface financeService)
    {
        this.financeService = financeService;
    }
    
    public FinanceTypeServiceInterface getFinanceTypeService()
    {
        return financeTypeService;
    }
    
    public void setFinanceTypeService(FinanceTypeServiceInterface financeTypeService)
    {
        this.financeTypeService = financeTypeService;
    }
    
    public Finances getFinance()
    {
        return finance;
    }
    
    public void setFinance(Finances finance)
    {
        this.finance = finance;
    }
    
    public Pager getPager()
    {
        return pager;
    }
    
    public void setPager(Pager pager)
    {
        this.pager = pager;
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

    public String getGraphURL()
    {
        return graphURL;
    }

    public void setGraphURL(String graphURL)
    {
        this.graphURL = graphURL;
    }

    public List<FinanceStatistic> getFinanceStatistic()
    {
        return financeStatistic;
    }

    public void setFinanceStatistic(List<FinanceStatistic> financeStatistic)
    {
        this.financeStatistic = financeStatistic;
    }
    
}
