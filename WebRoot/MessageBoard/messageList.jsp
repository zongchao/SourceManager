<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <s:include value="/common/theme.jsp">
            <s:param name="path">../</s:param>
        </s:include>
        <link type="text/css" href="../common/css/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
        <link type="text/css" href="../common/css/cmxform.css" rel="stylesheet" />
        <link type="text/css" href="../common/css/bootstrap.css" rel="stylesheet" />
        <script src="../common/js/bootstrap-dropdown.js"></script>
        <script src="../common/js/bootstrap.js"></script>
        <script type="text/javascript" src="../common/js/jquery-ui-1.8.22.custom.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.ui.datepicker-zh-CN.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.metadata.js"></script>
        <script type="text/javascript" src="../common/js/jquery.validate.js"></script>
        <title>留言管理</title>
        <script type="text/javascript">
        $(function() {
            $.datepicker.setDefaults($.datepicker.regional["zh-CN"]);
            $( "#startDate" ).datepicker();
            $( "#startDate" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
            $( "#endDate" ).datepicker();
            $( "#endDate" ).datepicker( "option", "dateFormat", "yy-mm-dd" );
                
            $("#form").validate();
        	$("a[data-toggle=popover]").popover('show');
        });
</script>
    </head>
    <body>
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="page-header">
                <h1 style="display: inline; ">留言管理</h1>
                <div class="pull-right">
                    <form id="form" class="form-search" action="messageBoardList.action" style="display: inline;">
                        <input type="hidden" name="pager.pageNo" value="1" />
                        <div class="input-append">
                            <span class="add-on"><i class="icon-time"></i> </span>
                            <input type="text" name="startDate" id="startDate" style="width: 100px" class="{date:true}"
                                value="<s:date name="startDate" format="yyyy-MM-dd"/>" />
                        </div>
                        —
                        <div class="input-append">
                            <span class="add-on"><i class="icon-time"></i> </span>
                            <input type="text" name="endDate" id="endDate" style="width: 100px" class="{date:true}"
                                value="<s:date name="endDate" format="yyyy-MM-dd"/>" />
                        </div>
                        &nbsp;
                        <button type="submit" class="btn">
                                                    查询
                        </button>
                    </form>
                </div>
            </div>
            <table class="table table-striped table-bordered">
                <tr>
                    <th width="5%">
                                            序号
                    </th>
                    <th width="10%">
                                            用户名
                    </th>
                    <th width="10%">
                                            创建时间
                    </th>
                    <th width="65%">
                                            留言
                    </th>
                    <th width="10%">
                                            操作
                    </th>
                </tr>
                <s:iterator value="messageBoardList" status="stat">
                    <tr>
                        <td>
                            <s:property value="(#stat.index) + 1" />
                        </td>
                        <td>
                            <s:property value="users.username" />
                        </td>
                        <td>
                            <i class="icon-time"></i>
                            <s:date name="createTime" format="yyyy-MM-dd" />
                        </td>
                        <td>
                            <s:property value="message" />
                        </td>
                        <td>
                            <div class="btn-group">
                                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">操作 <span class="caret"></span> </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="messageBoardDetail.action?messageBoard.id=<s:property value="id" />"><i class="icon-exclamation-sign"></i> 详细</a>
                                    </li>
                                    <li>
                                        <a href="delMessageBoard.action?messageBoard.id=<s:property value="id" />" onclick="return confirm('确定将此记录删除?')"> <i
                                            class="icon-trash"></i> 删除</a>
                                    </li>
                                </ul>
                            </div>
                        </td>
                    </tr>
                </s:iterator>
            </table>
            <div class="pagination pagination-right">
                <ul>
                    <s:property value="pager.pageHtml" escape="false"/>
                </ul>
            </div>
        </div>
    </body>
</html>

