package com.neko.service.interfaces;

import java.util.Collection;
import java.util.Map;

import com.neko.bean.Sources;
import com.neko.page.bean.Pager;


/**
 * @author zc
 * @date 20121123
 */
public interface SourceServiceInterface
{
    /**
     * 根据父id返回资源列表
     * @param parentId 父节点id
     * @return 返回资源列表
     */
    Collection<Sources> getSourceList(int parentId);
    
    /**
     * 根据父id返回资源列表（分页的形式）
     * @param parentId 父节点id
     * @param pageNo 当前页号
     * @param pageSize 每页显示的条数
     * @return 分页信息
     */
    Pager getSourcePager(int parentId,int pageNo,int pageSize);
    
    /**
     * 添加资源
     * @param source 添加的资源
     * @return 是否成功
     */
    Boolean addSource(Sources source); 
    
    /**
     * 修改资源
     * @param source 修改的资源
     * @return 是否成功
     */
    Boolean modSource(Sources source);
    
    /**
     * 删除资源
     * @param id 删除的资源id
     * @return 是否成功
     */
    Boolean delSource(Integer id);
    
    /**
     * 通过id查找资源
     * @param id 资源id
     * @return 查找的资源
     */
    Sources findSourceById(Integer id);
    
    
    /**
     * 取得面包屑导航
     * @param id 父节点id
     * @return 面包屑列表
     */
    Map<Integer, String> getBreadcrumb(Integer id);
    
} 