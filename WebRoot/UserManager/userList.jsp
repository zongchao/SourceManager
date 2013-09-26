<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <s:include value="/common/theme.jsp">
            <s:param name="path">../</s:param>
        </s:include>
        <script src="../common/js/bootstrap-dropdown.js"></script>
        <title>用户列表</title>
    </head>
    <body>
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
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
                <s:iterator value="userList" status="stat">
                    <tr>
                        <td>
                            <s:property value="(#stat.index) + 1" />
                        </td>
                        <td>
                            <s:property value="username" />
                        </td>
                        <td>
                            <s:if test="userType == 0">
                                <abbr title="系统管理员" class="initialism"><i class="icon-user icon-blue"></i> </abbr> 管理员 
                           </s:if>
                            <s:else>
                                <i class="icon-user "></i> 用户
                           </s:else>
                        </td>
                        <td>
                            <i class="icon-time"></i>
                            <s:date name="lastLoginTime" format="yyyy-MM-dd" />
                        </td>
                        <td>
                            <s:if test="isDelete == 0">
                                <i class="icon-ok"></i> 可用
                           </s:if>
                            <s:else>
                                <i class="icon-remove"></i> 不可用
                           </s:else>
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span> </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="userDetail.action?id=<s:property value="id" />"><i class="icon-exclamation-sign"></i> 详细</a>
                                    </li>
                                    <li>
                                        <a href="modUserPre.action?id=<s:property value="id" />"><i class="icon-edit"></i> 修改</a>
                                    </li>
                                    <!-- 管理员不可删除 -->
                                    <s:if test="userType != 0">
                                        <input type="hidden" name="user.id" value="<s:property value="id" />">
                                        <s:if test="isDelete == 0">
                                            <li>
                                                <a href="forbidOrActivateUser.action?id=<s:property value="id" />"><i class="icon-remove-sign"></i> 禁用</a>
                                            </li>
                                        </s:if>
                                        <s:else>
                                            <li>
                                                <a href="forbidOrActivateUser.action?id=<s:property value="id" />"><i class="icon-ok-sign"></i> 恢复</a>
                                            </li>
                                        </s:else>
                                        <li>
                                            <a href="delUser.action?id=<s:property value="id" />" onclick="return confirm('确定将此记录删除?')"> <i
                                                class="icon-trash"></i> 删除</a>
                                        </li>
                                    </s:if>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>

