<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>人员信息管理</title>
<link href="${pageContext.request.contextPath }/util/css/style.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath }/js/jquery-1.4.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/jquery.form.js" type="text/javascript"></script>


<script type="text/javascript">
	
	$(function() {
	var flag = false;
		$('#xm').blur(function() {
			var xm = $(this).val();
			if (xm == "") {
				$('#msg').css("color", "red");
				$('#msg').text('姓名不能为空');
				return;
			}else{
				var patrn = /^[\u4e00-\u9fa5]$/;
				if (!patrn.exec(xm)) {
					$('#msg').css("color", "red");
					$("#msg").text("姓名只能是汉字");
					return;
				}
				$('#msg').css("color", "green");
				$('#msg').text('可以');
				flag = true;
			}

		});
		$('#sfzh').blur(function() {
			var sfzh = $(this).val();
			if (sfzh == "") {
				$('#msg1').css("color", "red");
				$('#msg1').text('身份证号不能为空');
				return;
			}else{
				var patrn = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
				if (!patrn.exec(sfzh)) {
					$('#msg1').css("color", "red");
					$("#msg1").text("请输入正确身份证号");
					return;
				}
				$('#msg1').css("color", "green");
				$('#msg1').text('可以');
				flag = true;
			}

		});

		$('#addsumbit').click(function() {
			if (!flag&&$("#pid").val()==null) {
				return;
			}
            var options = {
                url: '${pageContext.request.contextPath }/person/addPerson.do',
                type: 'post',
                dataType: 'text',
                data: $("#addPersonfrom").serialize(),
                success: function (data) {
                    alert(data);
                    if($("#pid").val()!=""){
                    	window.close();
                    }
                    $("input[type=reset]").trigger("click");
                }
            };
            $.ajax(options);
            return false;
		});

	});
</script>
</head>

<body onunload="window.opener.location.reload();">

	<!--<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="#">首页</a>
			</li>
			<li><a href="#">表单</a>
			</li>
		</ul>
	</div>-->

	<div class="formbody">

		<div class="formtitle">
			<span>基本信息</span>
		</div>
		<form action="" method="post" id="addPersonfrom">
			<ul class="forminfo">
				<li>
					<label>姓名</label><input name="xm" type="text" class="dfinput" id="xm" value="${zperson.xm}"/><i id="msg">必填</i>
				</li>
				<li>
					<label>身份证号</label><input name="sfzh" id = "sfzh" type="text" class="dfinput" value="${zperson.sfzh}"/><i id="msg1">必填</i>
				</li>
				<li>
					<label>性别</label><input name="xb" type="text" class="dfinput" value="${zperson.xb}"/>
					<input name="zt" type="hidden" value="0"/>
					<input name="id" id="pid" type="hidden" value="${zperson.id}"/>
					<input name="jybm" type="hidden" value="${zperson.jybm}"/>
					<input name="jysfzh" type="hidden" value="${zperson.jysfzh}"/>
					<input name="jysj" type="hidden" value="${zperson.jysj}"/>
					<input name="jyxm" type="hidden" value="${zperson.jyxm}"/>
				</li>
				<li>
					<label>民族</label><input name="mz" type="text" class="dfinput" value="${zperson.mz}"/>
				</li>
				<li>
					<label>手机号</label><input name="sjh" type="text" class="dfinput" value="${zperson.sjh}"/>
				</li>
				<li>
					<label>出生日期</label><input name="csrq" type="text" class="dfinput" value="${zperson.csrq}"/>
				</li>
				<li>
					<label>现住址</label><input name="xzz" type="text" class="dfinput" value="${zperson.xzz}"/>
				</li>
				<li>
					<label>现工作单位</label><input name="xgzdw" type="text" class="dfinput" value="${zperson.xgzdw}"/>
				</li>
				<li>
					<label>原因</label><input name="yy" type="text" class="dfinput" value="${zperson.yy}"/>
				</li>
				<li>
					<input type="reset" name="reset" style="display: none;" />
					<label>&nbsp;</label><input type="button" id="addsumbit" class="btn" value="确认保存" />
				</li>
			</ul>
		</form>
	</div>

</body>

</html>
