package com.neko.bean;
// default package

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * FinanceTypes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="FinanceTypes", schema="dbo", catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class FinanceTypes implements java.io.Serializable
{
    
    // Fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private Integer type;
    
    @OneToMany(mappedBy="financeTypes", targetEntity=Finances.class)
    private Set financeses = new HashSet(0);
    
    // Constructors
    
    /** default constructor */
    public FinanceTypes()
    {
    }
    
    /** full constructor */
    public FinanceTypes(String name, Integer type, Set financeses)
    {
        this.name = name;
        this.type = type;
        this.financeses = financeses;
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
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public Integer getType()
    {
        return this.type;
    }
    
    public void setType(Integer type)
    {
        this.type = type;
    }
    
    public Set getFinanceses()
    {
        return this.financeses;
    }
    
    public void setFinanceses(Set financeses)
    {
        this.financeses = financeses;
    }
    
}