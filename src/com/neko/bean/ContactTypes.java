package com.neko.bean;
// default package

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
 * ContactTypes entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="ContactTypes", schema="dbo", catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class ContactTypes implements java.io.Serializable
{
    
    // Fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    private String name;
    
    @OneToMany(mappedBy="contactTypes", targetEntity=Contacts.class)
    private Set contactses = new HashSet(0);
    
    // Constructors
    
    /** default constructor */
    public ContactTypes()
    {
    }
    
    /** full constructor */
    public ContactTypes(String name, Set contactses)
    {
        this.name = name;
        this.contactses = contactses;
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
    
    public Set getContactses()
    {
        return this.contactses;
    }
    
    public void setContactses(Set contactses)
    {
        this.contactses = contactses;
    }
    
}