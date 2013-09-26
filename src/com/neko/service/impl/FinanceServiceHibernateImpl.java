package com.neko.service.impl;

import java.awt.Font;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.springframework.stereotype.Service;

import com.neko.bean.FinanceStatistic;
import com.neko.bean.Finances;
import com.neko.bean.Sources;
import com.neko.dao.FinancesDAO;
import com.neko.page.bean.Pager;
import com.neko.page.service.PagerService;
import com.neko.service.interfaces.FinanceServiceInterface;
import com.neko.util.MathUtil;

@Service("financeService")
public class FinanceServiceHibernateImpl implements FinanceServiceInterface
{
    public static Logger log = Logger.getLogger(FinanceServiceHibernateImpl.class.getName());
    
    @Resource(name = "financesDAO")
    private FinancesDAO financesDAO;
    
    @Override
    public Collection<Finances> getFinanceList(int userId)
    {
        log.info("获取财务信息列表");
        return financesDAO.findByProperty("users.id", userId);
    }
    
    @Override
    public Pager getFinancePager(int userId, int pageNo, int pageSize, Date startDate, Date endDate)
    {
        log.info("获取财务信息第"+ pageNo + "页,每页" + pageSize + "条" );
        
        DetachedCriteria criteria = getCriteria(userId, startDate, endDate);
        int totalCount = financesDAO.findByCriteria(criteria).size();
        Pager pager = new Pager();
        if(totalCount >= 1)
        {
            int startIndex = Pager.getStartOfPage(pageNo, pageSize);
            
            List<Finances> list = financesDAO.findListByPager(startIndex, pageSize, criteria);
            pager = new Pager(pageNo, totalCount, pageSize, list);
            PagerService pagerService = new PagerService(pager);
            
            StringBuffer clickUrl = new StringBuffer("financeList.action?");
            if(startDate != null && endDate != null)
            {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                clickUrl.append("&startDate=");
                clickUrl.append(sdf.format(startDate));
                clickUrl.append("&endDate=");
                clickUrl.append(sdf.format(endDate));
            }
            pager.setPageHtml(pagerService.paging(clickUrl.toString()));
        }
        return pager;
    }

    private DetachedCriteria getCriteria(int userId, Date startDate, Date endDate)
    {
        DetachedCriteria criteria = DetachedCriteria.forClass(Finances.class,"f");
        criteria.createCriteria("users","u")
        .add(Restrictions.eq("u.id", userId))
        .addOrder(Order.desc("f.happenTime"));
        if(startDate != null && endDate != null)
        {
            criteria.add(Restrictions.between("happenTime", startDate,endDate));
        }
        return criteria;
    }
    
    @Override
    public Boolean addFinance(Finances finance)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        log.info("开始保存财务信息");
        Boolean flag = false;
        try
        {
            finance.setHappenTime(new Date());
            financesDAO.save(finance);
            flag = true;
            log.info("保存财务信息完成");
        }
        catch (Exception e)
        {
            log.info("保存财务信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    @Override
    public Boolean modFinance(Finances finance_temp)
    {
        log.info("开始修改财务信息");
        Boolean flag = false;
        try
        {
            Finances finance = findFinanceById(finance_temp.getId());
            finance.setFinanceTypes(finance_temp.getFinanceTypes());
            finance.setMoney(finance_temp.getMoney());
            finance.setNote(finance_temp.getNote());
            financesDAO.merge(finance);
            flag = true;
            log.info("修改财务信息完成");
        }
        catch (Exception e)
        {
            log.info("修改财务信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    @Override
    public Boolean delFinance(Integer id)
    {
        log.info("开始删除id="+ id +"的财务信息");
        Boolean flag = false;
        try
        {
            Finances finance = findFinanceById(id);
            financesDAO.delete(finance);
            flag = true;
        }
        catch (Exception e)
        {
            flag = false;
            log.info("删除财务信息失败");
            log.error(e.getMessage());
            System.out.println(e.getMessage());
        }
        return flag;
    }
    
    @Override
    public Finances findFinanceById(Integer id)
    {
        log.info("通过id获取财务信息");
        Finances finance = financesDAO.findById(id);
        return finance;
    }
    
    @Override
    public List<FinanceStatistic> getFinanceStatistic(int userId,Date startDate,Date endDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        
        if(startDate == null || endDate == null)
        {
            startDate = getStartMonthInDate(new Date());
            endDate = addDate(addDate(startDate,Calendar.MONTH,12), Calendar.MILLISECOND, -1);
        }
        else
        {
            startDate = reomveDay(startDate);
            endDate = addDate(addDate(reomveDay(endDate),Calendar.MONTH,1), Calendar.MILLISECOND, -1);;
        }
        
        Map<Date,Double> incomeMap = new HashMap<Date, Double>();
        Map<Date,Double> expensesMap = new HashMap<Date, Double>();
        
        List list = financesDAO.getStatistic(userId,startDate,endDate);
        List<Date> monthList = getMonthList(startDate,getDistanceMonth(startDate, endDate));
        
        clearList(incomeMap, expensesMap, list);
       
        List<FinanceStatistic> statisticList = getStatisticList(incomeMap, expensesMap, monthList);
        return statisticList;
    }
    
    /**
     * 获取开始日期与结束日期之间相差的月数
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @return 相差月数
     */
    private static int getDistanceMonth(Date startDate, Date endDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        int distanceYear = endDate.getYear() - startDate.getYear();
        return distanceYear * 12 + endDate.getMonth() - startDate.getMonth();
    }
    
    /**
     * 将数据库中取得统计数据列表 整理成 收入map 支出map
     * @param incomeMap 收入map 输出参数
     * @param expensesMap 支出map 输出参数
     * @param list 统计数据列表
     */
    private void clearList(Map<Date, Double> incomeMap, Map<Date, Double> expensesMap, List list)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (Object object : list)
        {
            Object[] objects = (Object[])object;
            try
            {
                if((Integer)objects[2] == 0)
                {
                    incomeMap.put(sdf.parse(objects[1].toString()), MathUtil.round((Double)objects[0], 3));
                }
                else
                {
                    expensesMap.put(sdf.parse(objects[1].toString()), MathUtil.round((Double)objects[0], 3));
                }
            } 
            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * 取得统计列表
     * @param incomeMap 收入map
     * @param expensesMap 支出map
     * @param monthList 月列表
     * @return 统计列表
     */
    private List<FinanceStatistic> getStatisticList(Map<Date, Double> incomeMap, Map<Date, Double> expensesMap, List<Date> monthList)
    {
        List<FinanceStatistic> list = new ArrayList<FinanceStatistic>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        for (Date month : monthList)
        {
            FinanceStatistic statistic = new FinanceStatistic();
            statistic.setDate(month);
            if(incomeMap.containsKey(month))
            {
                statistic.setIncome(incomeMap.get(month));
            }
            else 
            {
                statistic.setIncome(Double.valueOf(0));
            }
            
            if(expensesMap.containsKey(month))
            {
                statistic.setExpenses(expensesMap.get(month));
            }
            else 
            {
                statistic.setExpenses(Double.valueOf(0));
            }
            
            statistic.setAmount(MathUtil.sub(statistic.getIncome(), statistic.getExpenses()));
            list.add(statistic);
        }
        return list;
    }
    
    /**
     * 去除日期中的日，如2012-01-30，执行后为2012-01-01
     * @param date 日期
     * @return 去除后的日期
     */
    private static Date reomveDay(Date date)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Date removedDayDate = null;
        try
        {
            removedDayDate = sdf.parse(sdf.format(date));
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
        return removedDayDate;
    }
    
    /**
     * 取出当年第一月
     * @param startDate 开始日期
     * @return 起始月
     */
    private static Date getStartMonthInDate(Date startDate)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startDate);
        String year = String.valueOf( calendar.get(Calendar.YEAR));
        Date date = new Date();
        try
        {
            date = sdf.parse(year + "-1");
        } 
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return date;
    }
    
    /**
     * 对当前日期添加对应的时间
     * @param date
     * @param field calendar的枚举，
     * @param amount
     * @return
     */
    private static Date addDate(Date date, int field, int amount)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        date = calendar.getTime();
        return date;
    }
    
    /**
     * 获取月份列表
     * @param startDate 开始日期
     * @param distance 距离
     * @return 月份列表
     */
    private static List<Date> getMonthList(Date startDate,int distance)
    {
        List<Date> monthList = new ArrayList<Date>();
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        calendar.setTime(startDate);
        for (int i = 0; i <= distance; i++)
        {
            monthList.add(calendar.getTime());
            calendar.add(calendar.MONTH, 1);
        }
        return monthList;
    }
    
    @Override
    public String getStatisticChart(List<FinanceStatistic> statisticList,HttpSession session)
    {
        TimeSeriesCollection dataset = createDateset(statisticList);
        JFreeChart chart = createChart(dataset);
        
        String filename = null;
        try
        {
            filename = ServletUtilities.saveChartAsPNG(chart, 800, 450, null, session);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        } 
        String graphURL = "display.jsp?filename=" + filename; 
        return graphURL;
    }
    
    /**
     * 创建图表
     * @param dataset 数据集
     * @return JFreeChart
     */
    private JFreeChart createChart(TimeSeriesCollection dataset)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        JFreeChart chart = ChartFactory.createTimeSeriesChart("统计图",
                "月份", 
                "金额", 
                dataset, 
                true, 
                true, 
                false);
        chart.getTitle().setFont(new Font("黑体",Font.BOLD,20));
        chart.getLegend().setItemFont(new Font("宋体",Font.TYPE1_FONT,12));
        DateAxis domainAxis = (DateAxis) chart.getXYPlot().getDomainAxis();
        domainAxis.setTickLabelFont(new Font("宋体",Font.TYPE1_FONT,12));
        domainAxis.setLabelFont(new Font("宋体",Font.BOLD,20));
        domainAxis.setDateFormatOverride(sdf);
        
        ValueAxis valueAxis = chart.getXYPlot().getRangeAxis();//(柱状图的y轴)   
        valueAxis.setTickLabelFont(new Font("宋体",Font.TYPE1_FONT,12));//设置y轴坐标上的字体   
        valueAxis.setLabelFont(new Font("宋体",Font.BOLD,20));//设置y轴坐标上的标题的字体
        return chart;
    }
    
    /**
     * 创建数据集
     * @param statisticList 统计列表
     * @return 时间结果集
     */
    private TimeSeriesCollection createDateset(List<FinanceStatistic> statisticList)
    {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        TimeSeriesCollection  dataset = new TimeSeriesCollection ();
        TimeSeries incomeSeries = new TimeSeries("收入");
        TimeSeries expensesSeries = new TimeSeries("支出");
        for (FinanceStatistic statistic : statisticList)
        {
            try
            {
                incomeSeries.add(new Month(statistic.getDate()),Double.valueOf(statistic.getIncome()));
                expensesSeries.add(new Month(statistic.getDate()),statistic.getExpenses());
            }
            catch (NumberFormatException e)
            {
                e.printStackTrace();
            }
        }
        dataset.addSeries(incomeSeries);
        dataset.addSeries(expensesSeries);
        
        return dataset;
    }
    
    public FinancesDAO getFinancesDAO()
    {
        return financesDAO;
    }
    
    public void setFinancesDAO(FinancesDAO financesDAO)
    {
        this.financesDAO = financesDAO;
    }

    @Override
    public List<FinanceStatistic> getFinanceStatisticsAmount(List<FinanceStatistic> statisticList)
    {
        Double incomeSum = 0.0;
        Double expensesSum = 0.0;
        
        for (FinanceStatistic financeStatistic : statisticList)
        {
            incomeSum = MathUtil.add(incomeSum, financeStatistic.getIncome());
            expensesSum = MathUtil.add(expensesSum, financeStatistic.getExpenses());
        }
        FinanceStatistic financeStatistic = new FinanceStatistic();
        financeStatistic.setIncome(incomeSum);
        financeStatistic.setExpenses(expensesSum);
        financeStatistic.setAmount(MathUtil.sub(incomeSum ,expensesSum));
        statisticList.add(financeStatistic);
        return statisticList;
    }


}
