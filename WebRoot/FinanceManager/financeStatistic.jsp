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
        <title>财务信息统计</title>
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
        <s:include value="/common/header.jsp">
           <s:param name="path">../</s:param>
        </s:include>
        <div class="container">
            <div class="page-header">
                <h1 style="display: inline; ">财务信息统计</h1>
	            <div class="pull-right" style="display: inline;">
					<form id="form" class="form-search" action="financeStatistic.action">
                        <div class="input-append">
	                        <span class="add-on"><i class="icon-time"></i> </span>
							<input type="text" name="startDate" id="startDate" style="width:100px" class="{date:true}" value="<s:date name="startDate" format="yyyy-MM-dd"/>" />
						</div>
						 —
						<div class="input-append">
	                        <span class="add-on"><i class="icon-time"></i> </span>
							<input type="text" name="endDate" id="endDate" style="width:100px" class="{date:true}" value="<s:date name="endDate" format="yyyy-MM-dd"/>" /> 
						</div>
						&nbsp;<button type="submit" class="btn">查询</button>
					</form>
				</div>
            </div>
            <table class="table table-striped table-bordered">
                <tr>
                    <th width="25%">日期</th>
                    <th width="25%">收入</th>
                    <th width="25%">支出</th>
                    <th width="25%">合计</th>
                </tr>
                <s:iterator value="financeStatistic" status="stat" id="statistic">
                    <tr>
                        <td>
                            <s:if test="date == null">合计</s:if>
                            <s:else>
                                <i class="icon-time"></i> <s:date name="date" format="yyyy年MM月"/>
                            </s:else>
                        </td>
                        <td>
                        	￥<s:property value="income"/> 元
                        </td>
                        <td>
                        	￥<s:property value="expenses"/> 元
                        </td>
                        <td>
                         	￥<s:property value="amount"/> 元
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <div class="accordion" id="accordion2">
                <div class="accordion-group">
                    <div class="accordion-heading">
                        <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapseOne"> 财务统计折线图 </a>
                    </div>
                    <div id="collapseOne" class="accordion-body collapse in">
                        <div class="accordion-inner">
                            <img src="<s:property value='graphURL'/>">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>

