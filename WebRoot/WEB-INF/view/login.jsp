<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <s:include value="/common/theme.jsp" />
        <style type="text/css">
            @import url("common/css/stylesheet.css");
        </style>
        <title>用户登录</title>
        <style type="text/css">
            .login_form .formbody {
            	padding: 2em;
            	background-color: #E9F1F4;
            	overflow: hidden;
            	border-style: solid;
            	border-width: 1px 1px 2px;
            	border-color: #E9F1F4 #D8DEE2 #D8DEE2;
            	border-radius: 0 0 3px 3px;
            }
            
            .login_form h1 {
            	color: #fff;
            	font-size: 16px;
            	font-weight: bold;
            	background: #829aa8;
            	background: -moz-linear-gradient(#829aa8, #000000);
            	background: -webkit-linear-gradient(#829aa8, #000000);
            	-ms-filter:
            		"progid:DXImageTransform.Microsoft.gradient(startColorstr='#829aa8', endColorstr='#000000')"
            		;
            	border: 1px solid #677c89;
            	border-bottom-color: #6b808d;
            	border-radius: 3px 3px 0 0;
            	text-shadow: 0 -1px 0 rgba(0, 0, 0, 0.7);
            	margin: 0;
            	padding-left: 18px
            }
            
            #login {
            	width: 26em;
            }
        </style>
    </head>
    <body style="text-align: left">
        <div id="site-container" class="container" style="padding-top: 180px; width: 920px">
            <div id="login" class="login_form">
                <s:actionerror cssStyle="color: red" />
                <form action="login.action" method="post">
                    <h1>
                                            用户登录
                    </h1>
                    <div class="formbody">
                        <label for="login_field">
                                                        用户名
                            <br />
                            <input autocapitalize="off" autofocus="autofocus" class="text" id="login_field" name="user.username" style="width: 21em;" tabindex="1"
                                type="text">
                        </label>

                        <label for="password">
                                                        密码
                            <br />
                            <input autocomplete="disabled" class="text" id="password" name="user.password" style="width: 21em;" tabindex="2" type="password" value="">
                        </label>

                        <br/>
                        <label class="submit_btn">
                            <input name="commit" tabindex="3" class="btn btn-large" type="submit" value="登 录">
                        </label>
                    </div>
                </form>
            </div>
        </div>
        <div class="span3">
            &nbsp;
        </div>
    </body>
</html>