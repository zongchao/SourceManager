package com.neko.service.interfaces;

import java.util.Collection;
import java.util.List;

import com.neko.bean.SourceTypes;
import com.neko.bean.Sources;
import com.neko.bean.Users;


/**
 * @author zc
 * @date 20121123
 */
public interface SourceTypeServiceInterface
{
    /**
     * 根据父id返回资源类型列表
     * @return 返回资源类型列表
     */
    Collection<SourceTypes> getSourceTypeList();
    
    /**
     * 添加资源类型
     * @param user 添加的资源类型
     * @return 是否成功
     */
    Boolean addSourceType(SourceTypes sourceType);
    
    /**
     * 检查资源类型名称是否重复
     * @param String 资源类型名称
     * @return 是否重复
     */
    Boolean isSourceTypeNameExist(String sourceTypeName);
    
    /**
     * 通过id查找资源类型
     * @param Integer 资源类型id
     * @return 资源类型信息
     */
    SourceTypes findSourceTypeById(Integer id);
    
    /**
     * 删除资源类型
     * @param id 资源类型id
     * @return 是否成功
     */
    Boolean delSourceType(Integer id);
    
    /**
     * 修改资源类型信息
     * @param user 修改后的资源类型信息
     * @return 是否成功
     */
    Boolean modifySourceType(SourceTypes sourceType);
    
}
