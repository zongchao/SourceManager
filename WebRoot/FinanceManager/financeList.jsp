<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <s:include value="/common/theme.jsp">
            <s:param name="path">../</s:param>
        </s:include>   
        <script src="../common/js/bootstrap-dropdown.js" ></script>
        <title>财务信息列表</title>
        <link type="text/css" href="../common/css/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
        <link type="text/css" href="../common/css/cmxform.css" rel="stylesheet" />
        <script type="text/javascript" src="../common/js/jquery-ui-1.8.22.custom.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.ui.datepicker-zh-CN.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.metadata.js"></script>
        <script type="text/javascript" src="../common/js/jquery.validate.js"></script>
        <script src="../common/js/bootstrap.js" ></script>
        <script type="text/javascript">
            $(function(){
                // Datepicker
                $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
                $( "#startDate" ).datepicker();
                $( "#startDate" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
                $( "#endDate" ).datepicker();
                $( "#endDate" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
                $("#form").validate();
                
                $('#collapseOne').collapse('hide');
            });
        </script>
    </head>
    <body>
        <div class="container">
            <s:include value="/common/header.jsp">
               <s:param name="path">../</s:param>
            </s:include>
            <div class="page-header">
                <h1 style="display: inline; ">财务信息列表</h1>
                <div class="pull-right">
                    <form id="form" class="form-search" action="financeList.action" style="display: inline;">
                        <input type="hidden" name="pager.pageNo" value="1" />
                        <div class="input-append">
                            <span class="add-on"><i class="icon-time"></i> </span>
                            <input type="text" name="startDate" id="startDate" style="width: 100px" class="{date:true}"
                                value="<s:date name="startDate" format="yyyy-MM-dd"/>" />
                        </div>
                        —
                        <div class="input-append">
                            <span class="add-on"><i class="icon-time"></i> </span>
                            <input type="text" name="endDate" id="endDate" style="width: 100px" class="{date:true}"
                                value="<s:date name="endDate" format="yyyy-MM-dd"/>" />
                        </div>
                        &nbsp;
                        <button type="submit" class="btn">
                                                    查询
                        </button>
                    </form>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group">
                        <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">操作<span class="caret"></span> </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="addFinancePre.action?finance.financeTypes.type=0"><i class="icon-plus-sign"></i> 添加财务收入</a>
                            </li>
                            <li>
                                <a href="addFinancePre.action?finance.financeTypes.type=1"><i class="icon-plus-sign"></i> 添加财务支出</a>
                            </li>
                            <s:if test="#session.user.userType == 0">
                                <li>
                                    <a href="financeTypeList.action?financeType.type=0"><i class="icon-cog"></i> 收入类型管理</a>
                                </li>
                                <li>
                                    <a href="financeTypeList.action?financeType.type=1"><i class="icon-cog"></i> 支出类型管理</a>
                                </li>
                            </s:if>
                            <li>
                                <a href="financeStatistic.action"><i class="icon-cog"></i> 查看月统计</a>
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
            <table class="table table-striped table-bordered">
                <tr>
                    <th width="5%">序号</th>
                    <th width="20%">金额</th>
                    <th width="10%">收入\支出</th>
                    <th width="10%">产生原因 </th>
                    <th width="20%">产生时间</th>
                    <th width="25%">备注</th>
                    <th width="10%">操作</th>
                </tr>
                <s:iterator value="financeList" status="stat">
                    <tr>
                        <td>
                            <s:property value="(#stat.index) + 1" />
                        </td>
                        <td>
                            	￥<s:property value="money"/> 元
                        </td>
                        <td>
                        	<s:if test="financeTypes.type == 0">
                        		收入
                        	</s:if>
                        	<s:else>
                        		支出
                        	</s:else>
                        </td>
                        <td>
                            <s:property value="financeTypes.name"/>
                        </td>
                        <td>
                            <i class="icon-time"></i>
                            <s:date name="happenTime" format="yyyy-MM-dd HH:mm:ss" />
                        </td>
                        <td>
                            <s:property value="note"/>
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span> </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="financeDetail.action?finance.id=<s:property value="id" />"><i class="icon-exclamation-sign"></i> 详细信息</a>
                                    </li>
                                    <li>
                                        <a href="modFinancePre.action?finance.id=<s:property value="id" />&finance.financeTypes.type=<s:property value="financeTypes.type" />"><i class="icon-edit"></i> 修改财务信息</a>
                                    </li>
                                    <li>
                                        <a href="delFinance.action?finance.id=<s:property value="id" />" onclick="return confirm('确定将此记录删除?')"> 
                                        <i class="icon-trash"></i> 删除财务信息</a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <div class="pagination pagination-right">
                <ul>
                    <s:property value="pager.pageHtml" escape="false"/>
                </ul>
            </div>
        </div>
    </body>
</html>

