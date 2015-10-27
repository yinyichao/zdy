<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>办案中心管理登录</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
<link href="<%=path%>/util/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>
<script src="<%=path%>/util/js/cloud.js" type="text/javascript"></script>
<script src="<%=path%>/js/jquery-1.4.3.js" type="text/javascript"></script>
<script language="javascript">
	$(function(){
    $('div .loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	$(window).resize(function(){  
    	$('div .loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
    });  
}); 
function clikcSubmit(){
	var username = $("#username").val();
	var password = $("#mm").val();
	if(username==""){
		$("#error").text("用户名不能为空");
		return;
	}
	if(password==""){
		$("#error").text("密码不能为空");
		return;
	}
	var patrn = /^[A-Za-z0-9]+$/;
	if (!patrn.exec(username)) {
		$("#error").text("请不要输入非法字符");
		return;
	}
	if (!patrn.exec(password)) {
		$("#error").text("请不要输入非法字符");
		return;
	}
	$("#userForm").submit();
} 
</script> 
</head>

<body style="background-color:#1c77ac; background-image:url(util/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录后台管理界面平台</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
       
    <div class="loginbox">
    <form action="<%=path%>/user/login.do" method="post" id="userForm">
    <ul>
    
    <li><input id="username" name="username" type="text" class="loginuser"  onclick="JavaScript:this.value=''"/></li>
    <li><input id="mm" name="password" type="password" class="loginpwd"  onclick="JavaScript:this.value=''"/></li>
    <li><font id="error" color="red">${error}&nbsp;</font></li>
    <li><input name="" type="button" class="loginbtn" value="登录" onclick="clikcSubmit()"/></li>
    </ul>
    </form>
    
    </div>
    
    </div>
    
  <!--   <div class="loginbm">版权所有  2013  <a href="http://www.mycodes.net">源码之家</a> </div> -->
  <div class="loginbm">版权所有  2015 汇创科技  </div>
	
    
</body>

</html>
