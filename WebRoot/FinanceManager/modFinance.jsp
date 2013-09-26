<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>修改财务信息</title>
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
        <script type="text/javascript">
        $(function(){
            $("#form").validate();
        });
        </script>
    </head>
    <body onload="setupZoom()">
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="row" style="display:block;">
                <div class="span2">&nbsp;</div>
                <div class="span8">
                    <form action="modFinance.action" id="form" class="form-horizontal" method="post">
                        <fieldset>
                            <input type="hidden" name="finance.id" value="<s:property value='finance.id' />" />
                            <div class="page-header">
                               <h1>修改财务信息</h1>
                            </div>
                            <s:actionerror cssStyle="color: red" />
                            <div class="control-group">
                                <label class="control-label" for="money">
                                                                         金额：
                                </label>
                                <div class="controls">
                                	<div class="input-append">
                                    	<span class="add-on">￥</span>
                                    	<input type="text" id="money" name="finance.money" class="{required:true,number:true}" style="width:179px" value="<s:property value='finance.money' />"/>
                                    </div>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="financeType">
                                                                        产生原因：
                                </label>
                                <div class="controls">
                                    <s:select id="financeType" name="finance.financeTypes.id" list="financeTypeList" listKey="id" listValue="name" cssClass="{required:true}"></s:select>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="note">
                                                                        备注：
                                </label>
                                <div class="controls">
                                    <textarea id="note" name="finance.note" class="" rows="5"><s:property value="finance.note" /> </textarea>
                                </div>
                            </div>
                            <div class="form-actions">
                                <input type="submit" class="btn btn-primary" value="保存更改" />&nbsp;&nbsp;
                                <input type="reset" class="btn" value="重置" /> 
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="span2">&nbsp;</div>
            </div>
        </div>
       
    </body>
</html>