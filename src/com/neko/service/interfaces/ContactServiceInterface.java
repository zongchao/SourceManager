package com.neko.service.interfaces;

import java.io.File;
import java.util.Collection;

import com.neko.bean.Contacts;
import com.neko.page.bean.Pager;


/**
 * @author zc
 * @date 20130214
 */
public interface ContactServiceInterface
{
    /**
     * 根据用户id返回联系人列表
     * @param userId 用户id
     * @return 返回联系人列表
     */
    Collection<Contacts> getContactList(int userId);
    
    /**
     * 根据用户id返回联系人列表（分页的形式）
     * @param userId 用户id
     * @param pageNo 当前页号
     * @param pageSize 每页显示的条数
     * @param condition 查询条件
     * @return 分页信息
     */
    Pager getContactPager(int userId,int pageNo,int pageSize,String condition);
    
    /**
     * 添加联系人
     * @param source 添加的联系人
     * @return 是否成功
     */
    Boolean addContact(Contacts contact); 
    
    /**
     * 修改联系人
     * @param source 修改的联系人
     * @return 是否成功
     */
    Boolean modContact(Contacts contact);
    
    /**
     * 删除联系人
     * @param id 删除的联系人id
     * @return 是否成功
     */
    Boolean delContact(Integer id);
    
    /**
     * 通过id查找联系人
     * @param id 资源id
     * @return 查找的联系人
     */
    Contacts findContactById(Integer id);
    
    
    /**
     * 生产通讯录excel
     * @param realPath 网站根目录
     * @param userId 用户id
     * @return 生成的excel文件
     */
    File generateExcel(String realPath,int userId);
} 