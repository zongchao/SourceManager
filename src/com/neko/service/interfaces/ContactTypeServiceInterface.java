package com.neko.service.interfaces;

import java.util.Collection;
import java.util.List;

import com.neko.bean.ContactTypes;
import com.neko.bean.SourceTypes;
import com.neko.bean.Sources;
import com.neko.bean.Users;

/**
 * @author zc
 * @date 20130214
 */
public interface ContactTypeServiceInterface
{
    /**
     * 根据父id返回联系人类型列表
     * @return 返回联系人类型列表
     */
    Collection<ContactTypes> getContactTypeList();
    
    
    /**
     * 通过id查找联系人类型
     * @param Integer 联系人类型id
     * @return 联系人类型信息
     */
    ContactTypes findContactTypeById(Integer id);
    
}