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
<title>修改密码</title>
<link href="${pageContext.request.contextPath }/util/css/style.css" rel="stylesheet" type="text/css" />

<script src="${pageContext.request.contextPath }/js/jquery-1.4.3.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath }/js/jquery.form.js" type="text/javascript"></script>


<script type="text/javascript">
function toChangePassword(){
			var password = $('#password').val();
			var password1 = $('#password1').val();
			var password2 = $('#password2').val();
			if (password == "") {
				$('#msg').text('请输入原始密码');
				$('#msg').css({color : "red"});
				return;
			}else {
				$('#msg').text('可以');
				$('#msg').css({color : "black"});
			}
			if (password1 == "") {
				$('#msg1').text('请输入新密码');
				$('#msg1').css({color : "red"});
				return;
			} else {
				$('#msg1').text('可以');
				$('#msg1').css({color : "black"});
			}
			if (password2 == "") {
				$('#msg2').text('请输入新密码');
				$('#msg2').css({color : "red"});
				return;
			} else if(password1!=password2){
				$('#msg2').text('请保证新密码和重复输入的新密码相同');
				$('#msg2').css({color : "red"});
				return;
			}else {
				$('#msg2').text('可以');
				$('#msg2').css({color : "black"});
			}
            var options = {
                url: '${pageContext.request.contextPath }/user/changeUser.do',
                type: 'post',
                dataType: 'text',
                data: $("#adduserfrom").serialize(),
                success: function (data) {
                	if(data==0){
                		alert("密码修改成功！");
                	}else if(data==1){
                		alert("原始密码输入错误！");
                	}	
                }
            };
            $.ajax(options);
            return false;
		}
</script>
</head>

<body>

	<div class="formbody">

		<div class="formtitle">
			<span>修改密码</span>
		</div>
		<form action="" method="post" id="adduserfrom">
			<ul class="forminfo">
				<li>
					<label>原始密码</label><input name="password" type="text" class="dfinput" id="password" value=""/><i id="msg">必填</i>
				</li>
				<li>
					<label>新密码</label><input name="passwords" type="text" class="dfinput" id="password1" value=""/><i id="msg1">必填</i>
				</li>
				<li>
					<label>再次输入新密码</label><input type="text" class="dfinput" id="password2" value=""/><i id="msg2">必填</i>
					<input type="hidden" value="${user.mm}" id="mm">
				</li>
				<li>
					<input type="reset" name="reset" style="display: none;" />
					<label>&nbsp;</label><input type="button" id="addsumbit" class="btn" value="确认保存" onclick="toChangePassword()">
				</li>
			</ul>
		</form>
	</div>

</body>

</html>
