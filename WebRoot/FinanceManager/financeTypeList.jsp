<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <s:include value="/common/theme.jsp">
            <s:param name="path">../</s:param>
        </s:include>
        <script src="../common/js/bootstrap-dropdown.js">
</script>
        <title>财务信息类型列表</title>
    </head>
    <body>
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="page-header">
                <h1 style="display: inline;">
                    <s:if test="financeType.type == 0">
					收入类型列表
				</s:if>
                    <s:else>
					支出类型列表
				</s:else>
                </h1>
                <a href="addFinanceTypePre.action?financeType.type=<s:property value="financeType.type"/>" style="display: inline; float: right;"
                    class="btn btn-primary btn-small">添加财务信息类型</a>
            </div>
            <s:actionerror cssStyle="color: red" />
            <table class="table table-striped table-bordered">
                <tr>
                    <th width="5%">
                        序号
                    </th>
                    <th width="25%">
                        名称
                    </th>
                    <th width="10%">
                        操作
                    </th>
                </tr>
                <s:iterator value="financeTypeList" status="stat">
                    <tr>
                        <td>
                            <s:property value="(#stat.index) + 1" />
                        </td>
                        <td>
                            <s:property value="name" />
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span> </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="modFinanceTypePre.action?financeType.id=<s:property value="id" />"><i class="icon-edit"></i> 修改</a>
                                    </li>
                                    <li>
                                        <a href="delFinanceType.action?financeType.id=<s:property value="id" />" onclick="return confirm('确定将此记录删除?')"> <i
                                            class="icon-trash"></i> 删除 </a>
                                    </li>

                                </ul>
                            </div>
                        </td>
                    </tr>
                </s:iterator>
            </table>
        </div>
    </body>
</html>

