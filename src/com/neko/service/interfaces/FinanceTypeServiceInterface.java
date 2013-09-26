package com.neko.service.interfaces;

import java.util.Collection;

import com.neko.bean.FinanceTypes;
import com.neko.bean.SourceTypes;


/**
 * @author zc
 * @date 20130220
 */
public interface FinanceTypeServiceInterface
{
    /**
     * 根据父id返回财务类型列表
     * @return 返回财务类型列表
     */
    Collection<FinanceTypes> getFinanceTypeList();
    
    /**
     * 根据父id返回财务类型列表
     * @param type 收入（0）\支出（1）
     * @return 返回财务类型列表
     */
    Collection<FinanceTypes> getFinanceTypeListByType(int type);
    
    /**
     * 添加财务类型
     * @param user 添加的财务类型
     * @return 是否成功
     */
    Boolean addFinanceType(FinanceTypes financeType);
    
    /**
     * 检查财务类型名称是否重复
     * @param name 财务类型名称
     * @param type 收入or支出
     * @return 是否重复
     */
    Boolean isFinanceTypeNameExist(String name,int type);
    
    /**
     * 通过id查找财务类型
     * @param Integer 财务类型id
     * @return 财务类型信息
     */
    FinanceTypes findFinanceTypeById(Integer id);
    
    /**
     * 删除财务类型
     * @param id 财务类型id
     * @return 是否成功
     */
    Boolean delFinanceType(Integer id);
    
    /**
     * 修改财务类型信息
     * @param user 修改后的财务类型信息
     * @return 是否成功
     */
    Boolean modifyFinanceType(FinanceTypes financeType);
    
}
