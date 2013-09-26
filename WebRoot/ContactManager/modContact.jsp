<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>修改联系人</title>
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
        <script type="text/javascript">
            $(function(){
                $("#form").validate();
                
                $("[name=modifyOption]").change(function() {  
                    var option = $(this).val();
                    // 选择修改附件
                    if(option == 2) {
                        $("#modAttachment").show("slow");
                    }
                    else {
                        $("#modAttachment").hide("slow");
                    }
                });
            });
        </script>
    </head>
    <body onload="setupZoom()">
        <div class="container">
            <s:include value="/common/header.jsp">
                <s:param name="path">../</s:param>
            </s:include>
            <div class="row" style="display:block;">
                <div class="span2">&nbsp;</div>
                <div class="span8">
                    <form action="modContact.action" id="form" class="form-horizontal" method="post" enctype="multipart/form-data" >
                        <fieldset>
                            <input type="hidden" name="contact.id" value="<s:property value='contact.id' />" />
                            <div class="page-header">
                               <h1>修改联系人</h1>
                            </div>
                            <s:actionerror cssStyle="color: red" />
                            <div class="control-group">
                                <label class="control-label" for="name">姓名：</label>
                                <div class="controls">
                                    <input type="text" id="name" name="contact.name" class="{required:true}" value="<s:property value='contact.name'/>" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="attachment">照片：</label>
                                <div class="controls">
                                    <p style="padding-top: 5px;">
                                        <s:if test="contact.attachments == null || contact.attachments.path.length() <= 0">
                                            <img id="attachment" alt="暂无图片" src="../common/img/thumbnail_blank.png" class="img-rounded img">
                                        </s:if>
                                        <s:else>
                                            <a href="..<s:property value='contact.attachments.path'/>">
                                                <img id="attachment" src="..<s:property value='contact.attachments.path'/>" class="img-rounded img">
                                            </a>
                                            <input type="hidden" name="contact.attachments.id" value="<s:property value='contact.attachments.id'/>"/>
                                        </s:else>
                                    </p>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label">照片操作：</label>
                                <div class="controls" style="padding-top:5px">
                                    <label style="display:inline" for="isntModAttachment">
                                        <input type="radio" name="modifyOption" id="isntModAttachment" value="0" checked="checked" />
                                                                                无修改&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label> 
                                    
                                    <label style="display:inline" for="isModAttachment">
                                        <input type="radio" name="modifyOption" id="isModAttachment" value="2" /> 修改附件&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </label>
                                    <s:if test="contact.attachments != null && contact.attachments.path.length() > 0">
                                        <label style="display:inline" for="isDelAttachment">
                                            <input type="radio" name="modifyOption" id="isDelAttachment" value="1" /> 删除附件
                                        </label> 
                                    </s:if>
                                </div>
                            </div>
                            <div class="control-group" id="modAttachment" style="display: none;">
                                <label class="control-label" for="photo">
                                                                       更换照片：
                                </label>
                                <div class="controls">
                                    <input id="photo" type="file" name="file" value="${contact.attachments.path}" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="contactType">关系：</label>
                                <div class="controls">
                                	<s:select name="contact.contactTypes.id" id="contactType" list="contactTypeList" listKey="id" listValue="name" cssClass="{required:true}"></s:select>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="email">电子邮箱：</label>
                                <div class="controls">
                                    <input type="text" id="email" name="contact.email" class="{email:true}" value="<s:property value='contact.email'/>"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="address">住址：</label>
                                <div class="controls">
                                    <input type="text" id="address" name="contact.address" value="<s:property value='contact.address'/>"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="mobilePhone">手机：</label>
                                <div class="controls">
                                    <input type="text" id="mobilePhone" name="contact.mobilePhone" value="<s:property value='contact.mobilePhone'/>"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="telephone">宅电：</label>
                                <div class="controls">
                                    <input type="text" id="telephone" name="contact.telephone" value="<s:property value='contact.telephone'/>"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">描述：</label>
                                <div class="controls">
                                    <textarea id="description" name="contact.description" class="" rows="5"><s:property value='contact.description' /></textarea>
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