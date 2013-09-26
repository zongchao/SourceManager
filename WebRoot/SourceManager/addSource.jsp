<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>添加资源</title>
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
                    <form action="addSource.action" id="form" class="form-horizontal" method="post" enctype="multipart/form-data" >
                        <fieldset>
                            <input type="hidden" name="source.sourcesParent.id" value="<s:property value='source.sourcesParent.id' />" />
                            <div class="page-header">
                               <h1>添加资源</h1>
                            </div>
                            <s:actionerror cssStyle="color: red" />
                            <div class="control-group">
                                <label class="control-label" for="name">
                                                                        资源名：
                                </label>
                                <div class="controls">
                                    <input type="text" id="name" name="source.name" class="{required:true}"/>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="description">
                                                                        资源描述：
                                </label>
                                <div class="controls">
                                    <textarea id="description" name="source.description" class="" rows="5"></textarea>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="sourceType">
                                                                        资源类型：
                                </label>
                                <div class="controls">
                                    <s:select id="sourceType" name="source.sourceTypes.id" list="sourceTypeList" listKey="id" listValue="name" cssClass="{required:true}"></s:select>
                                </div>
                            </div>
                            <div class="control-group">
                                <label class="control-label" for="sourceImage">
                                                                        资源图片：
                                </label>
                                <div class="controls">
                                    <input id="sourceImage" type="file" name="file" /> 
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