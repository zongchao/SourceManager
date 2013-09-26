package com.neko.bean;
// default package

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * Finances entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Finances", schema="dbo", catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Finances implements java.io.Serializable
{
    
    // Fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="financeTypeId")
    private FinanceTypes financeTypes;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userId")
    private Users users;
    private Double money;
    private Date happenTime;
    private String note;
    
    // Constructors
    
    /** default constructor */
    public Finances()
    {
    }
    
    /** full constructor */
    public Finances(FinanceTypes financeTypes, Users users, Double money, Date happenTime, String note)
    {
        this.financeTypes = financeTypes;
        this.users = users;
        this.money = money;
        this.happenTime = happenTime;
        this.note = note;
    }
    
    // Property accessors
    
    public Integer getId()
    {
        return this.id;
    }
    
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    public FinanceTypes getFinanceTypes()
    {
        return this.financeTypes;
    }
    
    public void setFinanceTypes(FinanceTypes financeTypes)
    {
        this.financeTypes = financeTypes;
    }
    
    public Users getUsers()
    {
        return this.users;
    }
    
    public void setUsers(Users users)
    {
        this.users = users;
    }
    
    public Double getMoney()
    {
        return this.money;
    }
    
    public void setMoney(Double money)
    {
        this.money = money;
    }
    
    public Date getHappenTime()
    {
        return this.happenTime;
    }
    
    public void setHappenTime(Date happenTime)
    {
        this.happenTime = happenTime;
    }
    
    public String getNote()
    {
        return this.note;
    }
    
    public void setNote(String note)
    {
        this.note = note;
    }
    
}