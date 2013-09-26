package com.neko.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.neko.bean.Sources;
import com.neko.dao.SourcesDAO;
import com.neko.page.bean.Pager;
import com.neko.page.service.PagerService;
import com.neko.service.interfaces.SourceServiceInterface;

@Service("sourceService")
public class SourceServiceHibernateImpl implements SourceServiceInterface
{
    public static Logger log = Logger.getLogger(SourceServiceHibernateImpl.class.getName());
    
    @Resource(name = "sourcesDAO")
    private SourcesDAO sourcesDAO;
    
    @Override
    public Collection<Sources> getSourceList(int parentId)
    {
        log.info("获取资源列表");
        Sources parentSource = sourcesDAO.findById(parentId);
        return sourcesDAO.findByProperty("sourcesParent.id", parentSource.getId());
    }

    @Override
    public Pager getSourcePager(int parentId, int pageNo, int pageSize)
    {
        log.info("获取资源列表第"+ pageNo + "页,每页" + pageSize + "条" );
        Sources parentSource = sourcesDAO.findById(parentId);
        int totalCount= sourcesDAO.findByProperty("sourcesParent.id", parentSource.getId()).size();

        Pager pager = new Pager();
        if(totalCount >= 1)
        {
            int startIndex = Pager.getStartOfPage(pageNo, pageSize);
            List<Sources> list = sourcesDAO.findListByPager(parentId, startIndex, pageSize);
            pager = new Pager(pageNo, totalCount, pageSize, list);
            PagerService pagerService = new PagerService(pager);
            pager.setPageHtml(pagerService.paging("sourceList.action?source.sourcesParent.id=" + parentId));
        }
        return pager;
    }
    
    @Override
    public Boolean addSource(Sources source)
    {
        log.info("开始保存资源信息");
        Boolean flag = false;
        try
        {
            source.setOtherInfo("");
            source.setCreateTime(new Date());
            source.setLastModifyTime(new Date());
            sourcesDAO.save(source);
            flag = true;
            log.info("保存资源信息完成");
        }
        catch (Exception e)
        {
            log.info("保存资源信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    @Override
    public Boolean modSource(Sources source_temp)
    {
        log.info("开始修改资源信息");
        Boolean flag = false;
        try
        {
            Sources source = findSourceById(source_temp.getId());
            source.setAttachments(source_temp.getAttachments());
            source.setDescription(source_temp.getDescription());
            source.setName(source_temp.getName());
            source.setOtherInfo(source_temp.getOtherInfo());
            source.setSourceTypes(source_temp.getSourceTypes());
            source.setLastModifyTime(new Date());
            sourcesDAO.merge(source);
            flag = true;
            log.info("修改资源信息完成");
        }
        catch (Exception e)
        {
            log.info("修改资源信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    @Override
    public Boolean delSource(Integer id)
    {
        log.info("开始删除id="+ id +"的资源");
        Boolean flag = false;
        try
        {
            Sources source = findSourceById(id);
            sourcesDAO.delete(source);
            flag = true;
        }
        catch (Exception e)
        {
            flag = false;
            log.info("删除资源失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }

    @Override
    public Sources findSourceById(Integer id)
    {
        log.info("通过id获取资源信息");
        Sources source = sourcesDAO.findById(id);
        return source;
    }

    @Override
    public Map<Integer, String> getBreadcrumb(Integer id)
    {
        Map<Integer, String> breadcrumb = new TreeMap<Integer, String>();
        while (true)
        {
            Sources sources = findSourceById(id);
            breadcrumb.put(sources.getId(), sources.getName());
            if(sources.getSourcesParent() == null)
            {
                break;
            }
            id = sources.getSourcesParent().getId();
        }
        return breadcrumb;
    }
    
    public SourcesDAO getSourcesDAO()
    {
        return sourcesDAO;
    }

    public void setSourcesDAO(SourcesDAO sourcesDAO)
    {
        this.sourcesDAO = sourcesDAO;
    }
}
