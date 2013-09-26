<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>用户详细信息</title>
        <link type="text/css" href="../common/css/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
        <link type="text/css" href="../common/css/cmxform.css" rel="stylesheet" />
        <s:include value="/common/theme.jsp">
            <s:param name="path">../</s:param>
        </s:include>   
        <script type="text/javascript" src="../common/js/jquery-ui-1.8.22.custom.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.ui.datepicker-zh-CN.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.metadata.js"></script>
        <script type="text/javascript" src="../common/js/jquery.validate.js"></script>
    </head>
    <body>
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="row" style="display:block;">
                <div class="span2">&nbsp;</div>
                <div class="span8">
                    <form action="modUser.action" id="form" class="form-horizontal" method="post" onsubmit="return checkUsername()">
                        <fieldset>
                            <div class="page-header">
	                           <h1>用户详细信息</h1>
                            </div>
                            <s:actionerror cssStyle="color: red" />
                            <div class="control-group">
                                <label class="control-label" for="username">
                                                                        用户名：
                                </label>
                                <div class="controls">
                                    <p style="padding-top: 5px;"><s:property value='user.username' /></p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="userType">
                                                                       用户类型：
                                </label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <s:if test="user.userType == 0">
                                        	<abbr title="系统管理员" class="initialism"><i class="icon-user icon-blue"></i></abbr> 管理员
                                        </s:if>
                                        <s:else><i class="icon-user"></i> 用户</s:else>
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="isDelete">
                                                                       是否可用：
                                </label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <s:if test="user.isDelete == 0">
                                           <i class="icon-ok"></i> 可用
                                        </s:if>
                                        <s:else>
                                           <i class="icon-remove"></i> 不可用
                                        </s:else>
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="password">创建时间：</label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <i class="icon-time"></i><s:date name="user.createTime" format="yyyy-MM-dd" />
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="password">最后登陆时间：</label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <i class="icon-time"></i><s:date name="user.lastLoginTime" format="yyyy-MM-dd" />
                                    </p>
                                </div>
                            </div>
                            <div class="form-actions">
                                <a class="btn btn-primary" href="modUserPre.action?id=<s:property value="id" />">用户修改</a>&nbsp;&nbsp;
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