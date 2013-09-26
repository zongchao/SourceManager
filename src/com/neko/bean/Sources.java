package com.neko.bean;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * Sources entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="Sources", schema="dbo", catalog="SourceManager")
@Cache(usage=CacheConcurrencyStrategy.READ_WRITE)
public class Sources implements java.io.Serializable
{

    // Fields
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;                            // id
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="sourceTypeId")
    private SourceTypes sourceTypes;               // 资源类型
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="parentId")
    private Sources sourcesParent;                 // 父资源
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="sourceImageId")
    private Attachments attachments;               // 资源图片
    private String name;                           // 资源名称
    private String description;                    // 资源描述
    private String otherInfo;                      // 其他信息（保留字段）
    private Date createTime;                       // 创建时间
    private Date lastModifyTime;                   // 最后修改时间
    
    @OneToMany(mappedBy="sourcesChildren", targetEntity=Sources.class)
    private Set sourcesChildren = new HashSet(0);  // 子资源
    // Constructors

    /** default constructor */
    public Sources()
    {
    }

    /** minimal constructor */
    public Sources(Integer id)
    {
        this.id = id;
    }

    /** full constructor */
    public Sources(Integer id, SourceTypes sourceTypes, Sources sourcesParent, Attachments attachments, String name, String description, String otherInfo,
            Timestamp createTime, Timestamp lastModifyTime, Set sourcesChildren)
    {
        this.id = id;
        this.sourceTypes = sourceTypes;
        this.sourcesParent = sourcesParent;
        this.attachments = attachments;
        this.name = name;
        this.description = description;
        this.otherInfo = otherInfo;
        this.createTime = createTime;
        this.lastModifyTime = lastModifyTime;
        this.sourcesChildren = sourcesChildren;
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

    public SourceTypes getSourceTypes()
    {
        return this.sourceTypes;
    }

    public void setSourceTypes(SourceTypes sourceTypes)
    {
        this.sourceTypes = sourceTypes;
    }

    public Attachments getAttachments()
    {
        return this.attachments;
    }

    public void setAttachments(Attachments attachments)
    {
        this.attachments = attachments;
    }

    public String getName()
    {
        return this.name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return this.description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getOtherInfo()
    {
        return this.otherInfo;
    }

    public void setOtherInfo(String otherInfo)
    {
        this.otherInfo = otherInfo;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getLastModifyTime()
    {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime)
    {
        this.lastModifyTime = lastModifyTime;
    }


    public Sources getSourcesParent()
    {
        return sourcesParent;
    }

    public void setSourcesParent(Sources sourcesParent)
    {
        this.sourcesParent = sourcesParent;
    }

    public Set getSourcesChildren()
    {
        return sourcesChildren;
    }

    public void setSourcesChildren(Set sourcesChildren)
    {
        this.sourcesChildren = sourcesChildren;
    }

    
}