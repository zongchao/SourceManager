package com.neko.service.interfaces;

import java.util.Collection;
import java.util.List;

import com.neko.bean.Users;


/**
 * @author zc
 * @date 20120615
 */
public interface UserServiceInterface
{
    /**
     * 添加用户
     * @param user 添加的用户
     * @return 是否成功
     */
    Boolean addUser(Users user);
    
    /**
     * 删除用户
     * @param id 用户id
     * @return 是否成功
     */
    Boolean delUser(Integer id);
    
    /**
     * 禁用或启用用户（标记删除）
     * @param id 用户id
     * @return 是否成功
     */
    Boolean forbidOrActivateUser(Integer id);
    
    /**
     * 修改用户信息
     * @param user 修改后的用户信息
     * @return 是否成功
     */
    Boolean modUser(Users user);
    
    /**
     * 通过用户id查找用户
     * @param id 用户id
     * @return 查找的用户
     */
    Users findUserById(Integer id);
    
    /**
     * 通过用户名密码查找用户
     * @param username 用户名
     * @param password 密码
     * @return 用户
     */
    Users findUserByUserInfo(String username,String password);
    
    /**
     * 返回用户列表
     * @return 返回用户列表
     */
    Collection<Users> getUserList();
    
    /**
     * 判断用户名是否存在
     * @param username 用户名
     * @return 是否存在
     */
    Boolean isUsernameExist(String username);
}
