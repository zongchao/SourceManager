<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>添加联系人</title>
        <link type="text/css" href="../common/css/jquery-ui-1.8.22.custom.css" rel="stylesheet" />
        <link type="text/css" href="../common/css/cmxform.css" rel="stylesheet" />
        <s:include value="/common/theme.jsp">
            <s:param name="path">../</s:param>
        </s:include>   
        <script type="text/javascript" src="../common/js/jquery-ui-1.8.22.custom.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.ui.datepicker-zh-CN.min.js"></script>
        <script type="text/javascript" src="../common/js/jquery.metadata.js"></script>
        <script type="text/javascript" src="../common/js/jquery.validate.js"></script>
        <script type="text/javascript">
            $(function(){
                $("#form").validate();
            });
        </script>
    </head>
    <body>
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="row" style="display:block;">
                <div class="span2">&nbsp;</div>
                <div class="span8">
                    <form action="addContact.action" id="form" class="form-horizontal" method="post" enctype="multipart/form-data" >
                        <fieldset>
                            <div class="page-header">
                               <h1>添加联系人</h1>
                            </div>
                            <s:actionerror cssStyle="color: red" />
                            <div class="control-group">
                                <label class="control-label" for="name">姓名：</label>
                                <div class="controls">
                                    <input type="text" id="name" name="contact.name" class="{required:true}"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="contactType">关系：</label>
                                <div class="controls">
                                    <s:select id="contactType" name="contact.contactTypes.id" list="contactTypeList" listKey="id" listValue="name" cssClass="{required:true}"></s:select>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="email">电子邮箱：</label>
                                <div class="controls">
                                    <input type="text" id="email" name="contact.email" class="{email:true}"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="address">住址：</label>
                                <div class="controls">
                                    <input type="text" id="address" name="contact.address"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="mobilePhone">手机：</label>
                                <div class="controls">
                                    <input type="text" id="mobilePhone" name="contact.mobilePhone"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="telephone">宅电：</label>
                                <div class="controls">
                                    <input type="text" id="telephone" name="contact.telephone"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="photo">照片：</label>
                                <div class="controls">
                                    <input id="photo" type="file" name="file" /> 
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">描述：</label>
                                <div class="controls">
                                    <textarea id="description" name="contact.description" class="" rows="5"></textarea>
                                </div>
                            </div>
                            <div class="form-actions">
                                <input type="submit" class="btn btn-primary" value="保存更改" />&nbsp;&nbsp;
                                <input type="reset" class="btn" value="重置" /> 
                            </div>
                        </fieldset>
                    </form>
                </div>
                <div class="span2">&nbsp;</div>
            </div>
        </div>
    </body>
</html>