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
        <title>留言列表</title>
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
                <h1 style="display: inline; ">留言板</h1>
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
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="messageList.action?pager.pageNo=1" class="btn btn-primary btn-small">留言管理</a>
                </div>
            </div>
            <div class="row" style="display: block;">
                <div class="span12" style="padding-top: 20px;border: 1px solid #EEE;">
                    <s:iterator value="messageBoardList" status="stat">
                        <s:if test="#session.user.id == users.id">
                            <div style="padding-bottom: 70px;" align="right" >
                                <a style="padding:8px" id="a" href="#" data-trigger="manual" data-toggle="popover" data-placement="left" title=""
                                    data-content="<s:property value="message"/>"><s:property value="users.username"/></a>
                            </div>
                        </s:if>
                        <s:else>
                            <div style="display: block;padding-bottom: 70px">
                                <a style="padding:8px"  id="a" href="#" data-trigger="manual" data-toggle="popover" title=""
                                    data-content="<s:property value="message"/>"><s:property value="users.username"/></a>
                            </div>
                        </s:else>
                    </s:iterator>
                    <div class="pagination pagination-right">
                        <ul>
                            <s:property value="pager.pageHtml" escape="false"/>
                        </ul>
                    </div>
                    <div>
                        <form action="addMessageBoard.action" id="form" class="form-horizontal" method="post" >
                            <fieldset>
                                <s:actionerror cssStyle="color: red" />
                                <hr/>
                                <input type="hidden" name="messageBoard.users.id" value="<s:property value="#session.user.id"/>">
                                <div class="control-group">
                                    <label class="control-label" style="width:50px" for="message">
                                                                            留言：
                                    </label>
                                    <div class="controls" style="margin-left: 70px;">
                                        <textarea id="message" style="width: 90%;" name="messageBoard.message" rows="5" class="{required:true}"></textarea>
                                    </div>
                                </div>
                                <div align="right">
                                    <input type="submit" class="btn btn-primary" value="提交" />
                                </div>
                            </fieldset>
                        </form>
                    </div>
                </div>
               
               
            </div>
        </div>
    </body>
</html>

