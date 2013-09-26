package com.neko.bean;

import java.util.Date;


public class FinanceStatistic
{
    private Date date;
    private Double income;
    private Double expenses;
    private Double amount;
    
    public FinanceStatistic()
    {
    }
    
    public FinanceStatistic(Date date, Double income, Double expenses, Double amount)
    {
        super();
        this.date = date;
        this.income = income;
        this.expenses = expenses;
        this.amount = amount;
    }


    public Date getDate()
    {
        return date;
    }
    
    public void setDate(Date date)
    {
        this.date = date;
    }
    
    public Double getIncome()
    {
        return income;
    }
    
    public void setIncome(Double income)
    {
        this.income = income;
    }
    
    public Double getExpenses()
    {
        return expenses;
    }
    
    public void setExpenses(Double expenses)
    {
        this.expenses = expenses;
    }

    public Double getAmount()
    {
        return amount;
    }

    public void setAmount(Double amount)
    {
        this.amount = amount;
    }    
}
