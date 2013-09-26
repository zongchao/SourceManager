package com.neko.bean;

import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.core.GenericTypeResolver;

/**
 * Users entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Users", schema="dbo", catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Users implements java.io.Serializable
{

    // Fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;                 // id
    private String username;            // 用户名
    private String password;            // 密码
    private Integer userType;           // 用户类型（0管理员、1普通用户）
    private Date createTime;            // 创建时间
    private Date lastLoginTime;         // 最后登陆时间
    private Integer isDelete;           // 是否删除（0未删除、1已删除）

    // Constructors
    /** default constructor */
    public Users()
    {
    }

    /** full constructor */
    public Users(Integer id, String username, String password, Integer userType, Date createTime, Date lastLoginTime, Integer isDelete)
    {
        super();
        this.id = id;
        this.username = username;
        this.password = password;
        this.userType = userType;
        this.createTime = createTime;
        this.lastLoginTime = lastLoginTime;
        this.isDelete = isDelete;
    }


    public Integer getId()
    {
        return this.id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public Integer getUserType()
    {
        return this.userType;
    }

    public void setUserType(Integer userType)
    {
        this.userType = userType;
    }

    public Date getCreateTime()
    {
        return this.createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastLoginTime()
    {
        return this.lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime)
    {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getIsDelete()
    {
        return this.isDelete;
    }

    public void setIsDelete(Integer isDelete)
    {
        this.isDelete = isDelete;
    }

}