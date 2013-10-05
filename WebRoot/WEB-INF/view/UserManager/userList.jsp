<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <jsp:include page="/common/theme.jsp">
            <jsp:param name="path" value="../" />
        </jsp:include>
        <script src="../common/js/bootstrap-dropdown.js"></script>
        <title>用户列表</title>
    </head>
    <body>
        <div class="container">
            <jsp:include page="/common/header.jsp">
                <jsp:param name="path"value="../" />
            </jsp:include>
            <div class="page-header">
                <h1 style="display: inline;">
                                    用户列表
                </h1>
                <a href="addUser.jsp" style="display: inline; float: right;" class="btn btn-primary btn-small">添加用户</a>
            </div>
            <table class="table table-striped table-bordered">
                <tr>
                    <th width="5%">
						序号
                    </th>
                    <th width="25%">
						用户名
                    </th>
                    <th width="20%">
						用户类型
                    </th>
                    <th width="20%">
						最后登陆时间
                    </th>
                    <th width="20%">
						是否可用
                    </th>
                    <th width="10%">
						操作
                    </th>
                </tr>
                <c:forEach var="user" items="${ userList }" varStatus="stat">
                	<tr>
                        <td>
                        	<c:out value="${ stat.index} "></c:out>
                        </td>
                        <td>
                        	<c:out value="${ user.username }"></c:out>
                        </td>
                        <td>
                        	<c:choose>
                        		<c:when test="${ user.userType == '0' }">
                        			<abbr title="系统管理员" class="initialism"><i class="icon-user icon-blue"></i> </abbr> 管理员 
                        		</c:when>
                        		<c:otherwise>
                        			<i class="icon-user "></i> 用户
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                        <td>
                        	<i class="icon-time"></i>
                        	<fmt:formatDate value="${user.lastLoginTime}" pattern="yyyy-MM-dd" />
                        </td>
                        <td>
                        	<c:choose>
                        		<c:when test="${ user.isDelete == '0' }">
                        			<i class="icon-ok"></i> 可用
                        		</c:when>
                        		<c:otherwise>
                        			<i class="icon-remove"></i> 不可用
                        		</c:otherwise>
                        	</c:choose>
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span> </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="userDetail.action?id=<c:out value="${ user.id}"></c:out>"><i class="icon-exclamation-sign"></i> 详细</a>
                                    </li>
                                    <li>
                                        <a href="modUserPre.action?id=<c:out value="${ user.id}"></c:out>"><i class="icon-edit"></i> 修改</a>
                                    </li>
                                    <!-- 管理员不可删除 -->
	                        		<c:if test="${ user.userType != '0' }">
	                        			<c:choose>
			                        		<c:when test="${ user.isDelete == '0' }">
			                        			<li>
	                                                <a href="forbidOrActivateUser.action?id=<c:out value="${ user.id}"></c:out>"><i class="icon-remove-sign"></i> 禁用</a>
	                                            </li>
			                        		</c:when>
			                        		<c:otherwise>
				                        		<li>
				                        			<a href="forbidOrActivateUser.action?id=<c:out value="${ user.id}"></c:out>"><i class="icon-ok-sign"></i> 恢复</a>
				                        		</li>
			                        		</c:otherwise>
			                        	</c:choose>
			                        	<li>
                                            <a href="delUser.action?id=<c:out value="${ user.id}"></c:out>" onclick="return confirm('确定将此记录删除?')"> <i
                                                class="icon-trash"></i> 删除</a>
                                        </li>
	                        		</c:if>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>

