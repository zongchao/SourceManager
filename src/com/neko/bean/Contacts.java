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
 * Contacts entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Contacts", schema="dbo", catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Contacts implements java.io.Serializable
{
    
    // Fields
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="contactPhotoId")
    private Attachments attachments;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userId")
    private Users users;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="contactTypeId")
    private ContactTypes contactTypes;
    private String name;
    private String email;
    private String address;
    private String mobilePhone;
    private String telephone;
    private Date createTime;
    private Date lastModifyTime;
    private String otherInfo;
    private String description;
    
    // Constructors
    
    /** default constructor */
    public Contacts()
    {
    }
    
    /** minimal constructor */
    public Contacts(Users users)
    {
        this.users = users;
    }
    
    /** full constructor */
    public Contacts(Attachments attachments, Users users,
            ContactTypes contactTypes, String name, String email,
            String address, String mobilePhone, String telephone,
            Timestamp createTime, Date lastModifyTime, String otherInfo,
            String description)
    {
        this.attachments = attachments;
        this.users = users;
        this.contactTypes = contactTypes;
        this.name = name;
        this.email = email;
        this.address = address;
        this.mobilePhone = mobilePhone;
        this.telephone = telephone;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
        this.otherInfo = otherInfo;
        this.description = description;
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
    
    public Attachments getAttachments()
    {
        return this.attachments;
    }
    
    public void setAttachments(Attachments attachments)
    {
        this.attachments = attachments;
    }
    
    public Users getUsers()
    {
        return this.users;
    }
    
    public void setUsers(Users users)
    {
        this.users = users;
    }
    
    public ContactTypes getContactTypes()
    {
        return this.contactTypes;
    }
    
    public void setContactTypes(ContactTypes contactTypes)
    {
        this.contactTypes = contactTypes;
    }
    
    public String getName()
    {
        return this.name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getEmail()
    {
        return this.email;
    }
    
    public void setEmail(String email)
    {
        this.email = email;
    }
    
    public String getAddress()
    {
        return this.address;
    }
    
    public void setAddress(String address)
    {
        this.address = address;
    }
    
    public String getMobilePhone()
    {
        return this.mobilePhone;
    }
    
    public void setMobilePhone(String mobilePhone)
    {
        this.mobilePhone = mobilePhone;
    }
    
    public String getTelephone()
    {
        return this.telephone;
    }
    
    public void setTelephone(String telephone)
    {
        this.telephone = telephone;
    }
    
    public Date getCreateTime()
    {
        return this.createTime;
    }
    
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }
    
    public Date getLastModifyTime()
    {
        return this.lastModifyTime;
    }
    
    public void setLastModifyTime(Date lastModifyTime)
    {
        this.lastModifyTime = lastModifyTime;
    }
    
    public String getOtherInfo()
    {
        return this.otherInfo;
    }
    
    public void setOtherInfo(String otherInfo)
    {
        this.otherInfo = otherInfo;
    }
    
    public String getDescription()
    {
        return this.description;
    }
    
    public void setDescription(String description)
    {
        this.description = description;
    }
    
}