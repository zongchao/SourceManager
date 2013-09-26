package com.neko.bean;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Attachments entity. @author MyEclipse Persistence Tools
 */

@Entity
@Table(name="Attachments", schema="dbo", catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Attachments implements java.io.Serializable
{

    // Fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;                               // id
    private String name;                              // 附件名称
    private String path;                              // 附件路径
    private Date uploadTime;                          // 上传时间
    private String type;                              // 附件类型
    
    @OneToMany(mappedBy="attachments",targetEntity=Sources.class)
    private Set sourceses = new HashSet(0);           // 资源

    // Constructors
    /** default constructor */
    public Attachments()
    {
    }

    /** minimal constructor */
    public Attachments(Integer id)
    {
        this.id = id;
    }

    /** full constructor */
    public Attachments(Integer id, String name, String path, Date uploadTime, String type, Set sourceses)
    {
        this.id = id;
        this.name = name;
        this.path = path;
        this.uploadTime = uploadTime;
        this.type = type;
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

    public String getPath()
    {
        return this.path;
    }

    public void setPath(String path)
    {
        this.path = path;
    }

    public Date getUploadTime()
    {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime)
    {
        this.uploadTime = uploadTime;
    }

    public String getType()
    {
        return this.type;
    }

    public void setType(String type)
    {
        this.type = type;
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