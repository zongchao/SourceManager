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
        <title>资源列表</title>
    </head>
    <body>
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="page-header">
                <h1 style="display: inline;">
                                    资源列表
                </h1>
                <div class="btn-group pull-right" style="display: inline;">
                    <a class="btn btn-primary dropdown-toggle" data-toggle="dropdown" href="#">操作<span class="caret"></span> </a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="addSourcePre.action?source.sourcesParent.id=<s:property value='source.sourcesParent.id' />">
                                <i class="icon-plus-sign"></i>添加资源
                            </a>
                        </li>
                        <s:if test="#session.user.userType == 0">
                            <li>
                                <a href="sourceTypeList.action"><i class="icon-cog"></i> 资源类型管理</a>
                            </li>
                        </s:if>
                        <li>
                            <a onclick="history.go(-1)"><i class="icon-backward"></i> 返回</a>
                        </li>
                    </ul>
                </div>
            </div>
            <ul class="breadcrumb">
                <s:iterator value="breadcrumb" status="stat">
                    <li>
                        <a href="sourceList.action?source.sourcesParent.id=<s:property value="key" />&pager.pageNo=1"><s:property value="value" /> </a>
                        <span class="divider">/</span>
                    </li>
                </s:iterator>
            </ul>
            <table class="table table-striped table-bordered">
                <tr>
                    <th width="5%">
                        序号
                    </th>
                    <th width="25%">
                        名称
                    </th>
                    <th width="20%">
                        资源类型
                    </th>
                    <th width="20%">
                        创建时间
                    </th>
                    <th width="20%">
                        最后修改时间
                    </th>
                    <th width="10%">
                        操作
                    </th>
                </tr>
                <s:iterator value="sourceList" status="stat">
                    <tr>
                        <td>
                            <s:property value="(#stat.index) + 1" />
                        </td>
                        <td>
                            <s:property value="name" />
                        </td>
                        <td>
                            <s:property value="sourceTypes.name" />
                        </td>
                        <td>
                            <i class="icon-time"></i>
                            <s:date name="createTime" format="yyyy-MM-dd" />
                        </td>
                        <td>
                            <i class="icon-time"></i>
                            <s:date name="lastModifyTime" format="yyyy-MM-dd" />
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span> </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="sourceList.action?source.sourcesParent.id=<s:property value="id" />&pager.pageNo=1"><i class="icon-forward"></i>
                                            查看子资源</a>
                                    </li>
                                    <li>
                                        <a href="sourceDetail.action?source.id=<s:property value="id" />"><i class="icon-exclamation-sign"></i> 详细信息</a>
                                    </li>
                                    <li>
                                        <a href="addSourcePre.action?source.sourcesParent.id=<s:property value='id' />"><i class="icon-plus-sign"></i> 添加子资源</a>
                                    </li>
                                    <li>
                                        <a href="modSourcePre.action?source.id=<s:property value="id" />"><i class="icon-edit"></i> 修改资源</a>
                                    </li>
                                    <li>
                                        <a href="delSource.action?source.id=<s:property value="id" />" onclick="return confirm('确定将此记录删除?')"> <i
                                            class="icon-trash"></i> 删除资源</a>
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

