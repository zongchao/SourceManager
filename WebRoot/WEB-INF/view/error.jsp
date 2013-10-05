<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <s:include value="/common/theme.jsp" />
        <style type="text/css">@import url("common/css/stylesheet.css");</style>
        <script type="text/javascript">
            
            $(function(){
                $("#back").click(function(){
                    history.go(-1);
                });
                
                $("#back").mouseover(function(){
                    $("#back").css("cursor","pointer");
                });
            });
        </script>
        <title>错误页面</title>
    </head>
    <body>
        <s:include value="/common/header.jsp">
            <s:param name="path"></s:param>
        </s:include>
        <div class="container">
            <div id="warp">

                <!-- Header top -->
                <div id="header_top"></div>
                <!-- End header top -->

                <!-- Header -->
                <div id="header">
                    <h2>
                                                页面发生错误或没有找到
                    </h2>
                    <h5>
                                                如果您看到了这个页面，可能您进行了一些错误的操作。
                    </h5>
                </div>
                <!-- End Header -->

                <!-- The content div -->
                <div id="content">

                    <!-- text -->
                    <div id="text">
                        <!-- The info text -->
                        <p>
                                                        很抱歉，显然你要做的操作存在错误。请使用导航下面的链接，找到更多的资源和信息。
                        </p>
                        <br />
                        <h3>
                                                        迷惑了? 我们建议...
                        </h3>
                        <!-- End info text -->

                        <!-- Page links -->
                        <ul>
                            <li>
                                <a href="#">&raquo; 首页</a>
                            </li>
                            <li>
                                <a href="#">&raquo; 关于我们</a>
                            </li>
                            <li>
                                <a id="back">&raquo; 返回</a>
                            </li>
                        </ul>
                        <!-- End page links -->
                    </div>
                    <!-- End info text -->

                    <!-- Book icon -->
                    <img id="book" src="common/img/img-01.png" alt="Book iCon" />
                    <!-- End Book icon -->

                    <div style="clear: both;"></div>
                </div>
                <!-- End Content -->

                <!-- Footer -->
            </div>
            <!-- End Warp around everything -->
        </div>
    </body>
</html>