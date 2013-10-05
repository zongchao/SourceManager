<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%@ page import="com.neko.bean.*,org.springframework.context.*,org.springframework.context.support.*,com.neko.service.interfaces.*;"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%response.sendRedirect("UserManager/userList.action"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    This is my JSP page. <br>
    <%--<%
    	TestSpring test = new TestSpring();
    	test.say();
    %>--%>
    
    <%--<%
	    ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
    	UserServiceInterface userService = (UserServiceInterface)context.getBean("userService");
    	System.out.println(userService.getUserList());
    %>
  --%></body>
</html>
