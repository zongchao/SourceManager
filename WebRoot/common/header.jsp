<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                        	<c:choose>
	                        	<c:when test="#session.user.username != null">
	                                <li>
	                                    <a> 欢迎 ${sessionScope.user.username} 登录本系统 </a>
	                                </li>
	                                <li>
	                                    <a href="${param.path}logout.action"> <i class="icon-off icon-white"></i> 注销 </a>
	                                </li>
	                            </c:when>
	                            <c:otherwise>
	                                <li>
	                                    <a href="${param.path}login.jsp"> <i class="icon-off icon-white"></i> 登录 </a>
	                                </li>
	                            </c:otherwise>
                            </c:choose>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
