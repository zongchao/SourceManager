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
        <title>联系人列表</title>
        <link type="text/css" href="../common/css/cmxform.css" rel="stylesheet" />
        <script type="text/javascript" src="../common/js/jquery.metadata.js"></script>
        <script type="text/javascript" src="../common/js/jquery.validate.js"></script>
        <script src="../common/js/bootstrap.js" ></script>
        <script type="text/javascript">
            $(function(){
                // Datepicker
                 $("#form").validate();
            });
        </script>
    </head>
    <body>
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="page-header">
                <h1 style="display: inline;">
                    <s:property value="#session.user.username" /> 的通讯录
                </h1>
                <div class="pull-right">
                    <form id="form" class="form-search" action="contactList.action" style="display: inline;">
                        <input type="hidden" name="pager.pageNo" value="1" />
                        <input type="text" name="condition" id="condition" value="<s:property value="condition"/>" />
                        &nbsp;<button type="submit" class="btn">查询</button>
                    </form>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <div class="btn-group " style="display: inline;">
                        <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">操作<span class="caret"></span> </a>
                        <ul class="dropdown-menu">
                            <li>
                                <a href="addContactPre.action"><i class="icon-plus-sign"></i> 添加联系人</a>
                            </li>
                            <li>
                                <a href="generateContactExcel.action"><i class="icon-plus-sign"></i> 导出EXCEL</a>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
            <table class="table table-striped table-bordered">
                <tr>
                    <th width="5%">
                        序号
                    </th>
                    <th width="15%">
                        名称
                    </th>
                    <th width="10%">
                        关系
                    </th>
                    <th width="20%">
                        宅电
                    </th>
                    <th width="20%">
                        手机
                    </th>
                    <th width="20%">
                        电子邮箱
                    </th>
                    <th width="10%">
                        操作
                    </th>
                </tr>
                <s:iterator value="contactList" status="stat">
                    <tr>
                        <td>
                            <s:property value="(#stat.index) + 1" />
                        </td>
                        <td>
                            <s:property value="name" />
                        </td>
                        <td>
                            <s:property value="contactTypes.name" />
                        </td>
                        <td>
                            <s:property value="telephone" />
                        </td>
                        <td>
                            <s:property value="mobilePhone" />
                        </td>
                        <td>
                            <i class="icon-envelope"></i>
                            <s:property value="email" />
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span> </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="contactDetail.action?contact.id=<s:property value="id" />"><i class="icon-exclamation-sign"></i> 详细信息</a>
                                    </li>
                                    <li>
                                        <a href="modContactPre.action?contact.id=<s:property value="id" />"><i class="icon-edit"></i> 修改联系人</a>
                                    </li>
                                    <li>
                                        <a href="delContact.action?contact.id=<s:property value="id" />" onclick="return confirm('确定将此记录删除?')"> <i
                                            class="icon-trash"></i> 删除联系人</a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <div class="pagination pagination-right">
                <ul>
                    <s:property value="pager.pageHtml" escape="false" />
                </ul>
            </div>
        </div>
    </body>
</html>

