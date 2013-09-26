<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>财务详细信息</title>
        <link type="text/css" href="../common/css/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
        <link type="text/css" href="../common/css/cmxform.css" rel="stylesheet" />
        <s:include value="/common/theme.jsp">
            <s:param name="path">../</s:param>
        </s:include>   
        <script type="text/javascript" src="../common/js/jquery-ui-1.8.22.custom.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.ui.datepicker-zh-CN.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.metadata.js"></script>
        <script type="text/javascript" src="../common/js/jquery.validate.js"></script>
        <script type="text/javascript" src="../common/js/FancyZoom.js"></script>
        <script type="text/javascript" src="../common/js/FancyZoomHTML.js"></script>
    </head>
    <body onload="setupZoom()">
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="row" style="display:block;">
                <div class="span2">&nbsp;</div>
                <div class="span8">
                    <form id="form" class="form-horizontal" method="post" >
                        <fieldset>
                            <div class="page-header">
	                           <h1>财务详细信息</h1>
                            </div>
                            <s:actionerror cssStyle="color: red" />
                            <div class="control-group">
                                <label class="control-label">
                                                                       金额：
                                </label>
                                <div class="controls">
                                    <p style="padding-top: 5px;"><s:property value='finance.money' /></p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >
                                                                    收入\支出：
                                </label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <s:if test="finance.financeTypes.type == 0">
			                        		收入
			                        	</s:if>
			                        	<s:else>
			                        		支出
			                        	</s:else>
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >
                                                                     产生原因：
                                </label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <s:property value='finance.financeTypes.name' />
                                    </p>
                                </div>
                            </div>
                             <div class="control-group">
                                <label class="control-label" >
                                                                     产生时间：
                                </label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                    	<i class="icon-time"></i> <s:date name="finance.happenTime" format="yyyy-MM-dd HH:mm:ss" />
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" >
                                                                        备注：
                                </label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <s:property value='finance.note' />
                                    </p>
                                </div>
                            </div>
                            <div class="form-actions">
                                <a class="btn btn-primary" href="modFinancePre.action?finance.id=<s:property value="finance.id" />&finance.financeTypes.type=<s:property value="financeTypes.type" />">修改财务信息</a>&nbsp;&nbsp;
                                <input type="button" class="btn" value="返回" onclick="history.go(-1)" /> 
                            </div>
                        </fieldset>
                	</form>
                </div>
                <div class="span2">&nbsp;</div>
            </div>
        </div>
       
    </body>
</html>