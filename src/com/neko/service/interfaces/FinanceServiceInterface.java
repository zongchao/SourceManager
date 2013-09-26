package com.neko.service.interfaces;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import com.neko.bean.FinanceStatistic;
import com.neko.bean.Finances;
import com.neko.page.bean.Pager;


/**
 * @author zc
 * @date 20121123
 */
public interface FinanceServiceInterface
{
    /**
     * 根据父id返回财务列表
     * @param userId 用户id
     * @return 返回财务列表
     */
    Collection<Finances> getFinanceList(int userId);
    
    /**
     * 
     * 根据父id返回财务列表（分页的形式）
     * @param userId 用户id
     * @param pageNo 当前页号
     * @param pageSize 每页显示的条数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 分页信息
     */
    Pager getFinancePager(int userId,int pageNo,int pageSize, Date startDate, Date endDate);
    
    /**
     * 添加财务
     * @param source 添加的财务
     * @return 是否成功
     */
    Boolean addFinance(Finances finance); 
    
    /**
     * 修改财务
     * @param source 修改的财务
     * @return 是否成功
     */
    Boolean modFinance(Finances finance);
    
    /**
     * 删除财务
     * @param id 删除的财务id
     * @return 是否成功
     */
    Boolean delFinance(Integer id);
    
    /**
     * 通过id查找财务
     * @param id 财务id
     * @return 查找的财务
     */
    Finances findFinanceById(Integer id);
    
    
    /**
     * 取得财务统计
     * @param userId 所属用户
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 统计结果String[4] 日期,收入金额，支出金额，合计
     */
    List<FinanceStatistic> getFinanceStatistic(int userId,Date startDate,Date endDate);
    
    /**
     * 取得统计图
     * @param statisticList 统计结果
     * @param session 
     * @return 统计图
     */
    String getStatisticChart(List<FinanceStatistic> statisticList,HttpSession session);
    
    List<FinanceStatistic> getFinanceStatisticsAmount(List<FinanceStatistic> statisticList);
    
    
} 