package com.neko.bean;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * MessageBoard entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="MessageBoards", schema="dbo" ,catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class MessageBoards implements java.io.Serializable
{

    // Fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;                        // 唯一标示
    private String message;                    // 留言内容
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="userId")
    private Users users;                       // 留言用户信息
    private Date createTime;                   // 留言创建时间

    // Constructors

    /** default constructor */
    public MessageBoards()
    {
    }

    /** full constructor */
    public MessageBoards(String message, Users users, Date createTime)
    {
        this.message = message;
        this.users = users;
        this.createTime = createTime;
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

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

    public Users getUsers()
    {
        return users;
    }

    public void setUsers(Users users)
    {
        this.users = users;
    }

    public Date getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

}