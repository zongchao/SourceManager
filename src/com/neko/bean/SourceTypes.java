package com.neko.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * SourceTypes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="SourceTypes", schema="dbo", catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class SourceTypes implements java.io.Serializable
{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;                         // id
    private String name;                        // 资源类型名称 
    
    @OneToMany(mappedBy="sourceTypes", targetEntity=Sources.class)
    private Set sourceses = new HashSet(0);     // 对应资源

    // Constructors

    /** default constructor */
    public SourceTypes()
    {
    }

    /** minimal constructor */
    public SourceTypes(String name)
    {
        this.name = name;
    }

    /** full constructor */
    public SourceTypes(String name, Set sourceses)
    {
        this.name = name;
        this.sourceses = sourceses;
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

    public Set getSourceses()
    {
        return this.sourceses;
    }

    public void setSourceses(Set sourceses)
    {
        this.sourceses = sourceses;
    }

}