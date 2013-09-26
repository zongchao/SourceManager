<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <s:include value="/common/theme.jsp"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>添加资源类型</title>
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
                        if(checkFinanceTypeName()) {
                            from.submit();
                        }
                    }
                });
            });
            
            // 检查财务类型名称是否存在
            function checkFinanceTypeName() {
                var result = $.ajax({
                    type: "POST",
                    url: "checkFinanceTypeName.action",
                    data: 'financeType.name=' + $('#financeTypeName').val() + '&financeType.type=' + $('#financeTypeType').val(),
                    async: false,
                    success: function(msg) {
                        var label = $('#financeTypeName').parent().find("label[id=exist]");
                        if(label.length > 0) {
                            label.remove();            // 如果已经有提示信息了，就删除现有信息
                        }
                        if(msg == 'true') {
                            $('#financeTypeName').parent().append("<label id='exist' class='error'>类型名已存在！</label>");
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
                    <form action="addFinanceType.action" id="form" class="form-horizontal" method="post">
                        <fieldset>
                            <div class="page-header">
							<h1>
								<s:if test="financeType.type == 0">
									添加收入类型
								</s:if>
								<s:else>
									添加支出类型
								</s:else>
							</h1>
						</div>
                        <s:actionerror cssStyle="color: red" />
                        <input type="hidden" id="financeTypeType" name="financeType.type" value="<s:property value="financeType.type"/>">
                        <div class="control-group">
                            <label class="control-label" for="financeTypeName">
                                                                    资源名：
                            </label>
                            <div class="controls">
                                <input type="text" id="financeTypeName" name="financeType.name" class="{required:true}" onblur="checkFinanceTypeName()"/>
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