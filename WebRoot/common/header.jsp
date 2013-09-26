<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <style type="text/css">
body {
	padding-top: 60px;
	padding-bottom: 40px;
}

.temp1 {
	color: red;
}
</style>
    </head>
    <body>
        <div class="navbar navbar-inverse navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container">
                    <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse"> <span class="icon-bar"></span> <span class="icon-bar"></span>
                        <span class="icon-bar"></span> </a>
                    <a class="brand" href="#">家庭信息系统</a>
                    <div class="nav-collapse">
                        <ul class="nav">
                            <li>
                                <a href="#"><i class="icon-home icon-white"></i> 首页</a>
                            </li>
                            <li>
                                <a href="${ param.path }UserManager/index.jsp">用户管理</a>
                            </li>
                            <li>
                                <a href="${ param.path }SourceManager/index.jsp">资源管理</a>
                            </li>
                            <li>
                                <a href="${ param.path }ContactManager/index.jsp">通讯录管理</a>
                            </li>
                            <li>
                                <a href="${ param.path }FinanceManager/index.jsp">理财管理</a>
                            </li>
                            <li>
                                <a href="${ param.path }MessageBoard/index.jsp">留言板</a>
                            </li>
                        </ul>
                        <ul class="nav pull-right">
                            <s:if test="#session.user.username != null">
                                <li>
                                    <a> 欢迎 <s:property value="#session.user.username" /> 登录本系统 </a>
                                </li>
                                <li>
                                    <a href="${param.path}logout.action"> <i class="icon-off icon-white"></i> 注销 </a>
                                </li>
                            </s:if>
                            <s:else>
                                <li>
                                    <a href="${param.path}login.jsp"> <i class="icon-off icon-white"></i> 登录 </a>
                                </li>
                            </s:else>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
