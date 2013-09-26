<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>联系人详细信息</title>
        <link type="text/css" href="../common/css/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
        <link type="text/css" href="../common/css/cmxform.css" rel="stylesheet" />
        <s:include value="/common/theme.jsp">
            <s:param name="path">../</s:param>
        </s:include>   
        <script type="text/javascript" src="../common/js/jquery-ui-1.8.22.custom.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.ui.datepicker-zh-CN.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.metadata.js"></script>
        <script type="text/javascript" src="../common/js/jquery.validate.js"></script>
        <script type="text/javascript" src="../common/js/FancyZoom.js"></script>
        <script type="text/javascript" src="../common/js/FancyZoomHTML.js"></script>
    </head>
    <body onload="setupZoom()">
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="row" style="display:block;">
                <div class="span2">&nbsp;</div>
                <div class="span8">
                    <form id="form" class="form-horizontal" method="post" >
                        <fieldset>
                            <div class="page-header">
	                           <h1>联系人详细信息</h1>
                            </div>
                            <s:actionerror cssStyle="color: red" />
                            <div class="control-group">
                                <label class="control-label" for="name">姓名：</label>
                                <div class="controls">
                                    <p style="padding-top: 5px;"><s:property value='contact.name' /></p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="photo">照片：</label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <s:if test="contact.attachments == null || contact.attachments.path.length <= 0">
                                            <img alt="暂无图片" src="../common/img/thumbnail_blank.png" class="img-rounded img">
                                        </s:if>
                                        <s:else>
                                            <a href="..<s:property value='contact.attachments.path'/>">
                                                <img src="..<s:property value='contact.attachments.path'/>" class="img-rounded img">
                                            </a>
                                        </s:else>
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="contactType">关系：</label>
                                <div class="controls">
                                	<p style="padding-top: 5px;"><s:property value='contact.contactTypes.name' /></p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="email">电子邮箱：</label>
                                <div class="controls">
                                	<p style="padding-top: 5px;"><s:property value='contact.email' /></p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="address">住址：</label>
                                <div class="controls">
                                	<p style="padding-top: 5px;"><s:property value='contact.address' /></p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="mobilePhone">手机：</label>
                                <div class="controls">
                                	<p style="padding-top: 5px;"><s:property value='contact.mobilePhone' /></p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="telephone">宅电：</label>
                                <div class="controls">
                                	<p style="padding-top: 5px;"><s:property value='contact.telephone' /></p>
                                </div>
                            </div>
                             <div class="control-group">
                                <label class="control-label" for="createTime">创建时间：</label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <i class="icon-time"></i> <s:date name="contact.createTime" format="yyyy-MM-dd" />
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="lastLoginTime">最后修改时间：</label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <i class="icon-time"></i> <s:date name="contact.lastModifyTime" format="yyyy-MM-dd" />
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">描述：</label>
                                <div class="controls">
                                	<p style="padding-top: 5px;"><s:property value='contact.description' /></p>
                                </div>
                            </div>
                            <div class="form-actions">
                                <a class="btn btn-primary" href="modContactPre.action?contact.id=<s:property value="contact.id" />">修改联系人</a>&nbsp;&nbsp;
                                <input type="button" class="btn" value="返回" onclick="history.go(-1)" /> 
                            </div>
                        </fieldset>
                	</form>
                </div>
                <div class="span2">&nbsp;</div>
            </div>
        </div>
       
    </body>
</html>