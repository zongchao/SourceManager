<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>修改用户信息</title>
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
                $("#form").validate({
                    submitHandler: function (form) {
                        if(checkUsername()) {
                            from.submit();
                        }
                    }
                });
            });
            
            // 更换新的验证码
            function changeValidateCode(obj) {  
                //获取当前的时间作为参数，无具体意义  
                var timenow = new Date().getTime();  
                //每次请求需要一个不同的参数，否则可能会返回同样的验证码  
                //这和浏览器的缓存机制有关系，也可以把页面设置为不缓存，这样就不用这个参数了。  
                obj.src="../getCaptcha.action?d="+timenow;  
            }  
            
            
            // 检查用户名是否存在
            function checkUsername() {
                var result = $.ajax({
                    type: "POST",
                    url: "checkUsername.action",
                    data: 'username=' + $('#username').val(),
                    success: function(msg) {
                        var label = $('#username').parent().find("label[id=exist]");
                        if(label.length > 0) {
                            label.remove();            // 如果已经有提示信息了，就删除现有信息
                        }
                        if(msg == 'true') {
                            if($('#username').val() != $('#oldUsername').val()) {
                                $('#username').parent().append("<label id='exist' class='error'>用户名已存在！</label>");
                            }
                            return false;
                        }
                        else {
                            return true;
                        }
                    }
                });
                return !(result.responseText == 'true');
            }
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
                    <form action="modUser.action" id="form" class="form-horizontal" method="post">
                        <fieldset>
                            <input type="hidden" name="user.id" value="<s:property value='user.id' />" />
                            <div class="page-header">
                               <h1>修改用户信息</h1>
                            </div>
                            <s:actionerror cssStyle="color: red" />
                            <div class="control-group">
                                <label class="control-label" for="username">
                                                                        用户名：
                                </label>
                                <div class="controls">
                                    <input type="text" id="username" name="user.username" class="{required:true}" onblur="checkUsername()"  value="<s:property value='user.username' />"/>
                                    <input type="hidden" id="oldUsername" name="oldUsername" value="<s:property value='user.username' />" />
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="password">
                                                                        密码：
                                </label>
                                <div class="controls">
                                    <input type="password" id="password" name="user.password" class="{required:true,minlength:5}" value="<s:property value='user.password' />"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="captcha">
                                                                        验证码：
                                </label>
                                <div class="controls">
                                    <input type="text" name="captcha"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                    <img id="captcha" src="../getCaptcha.action" onclick="changeValidateCode(this)"/>  
                                    <a href="#" onclick="changeValidateCode(document.getElementById('captcha'))" >看不清楚</a>
                                </div>
                            </div>
                            <%--<div class="control-group">
                                <label class="control-label" for="datepicker">日期：</label>
                                <div class="controls">
                                    <input type="text" id="datepicker"> 
                                </div>
                            </div>--%>
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