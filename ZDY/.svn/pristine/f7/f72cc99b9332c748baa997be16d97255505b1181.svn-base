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
<title>无标题文档</title>
<link href="<%=path%>/util/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script>  
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected");
		$(this).addClass("selected");
	})	;
});	
function opentochangeuser(){
	window.open ("<%=path%>/user/toChangeUser.do", "newwindow", "height=600, width=650, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=200,left=500");
}
</script>
</head>

<body style="background:url(${pageContext.request.contextPath }/util/images/topbg.gif) repeat-x;">
    <div class="topleft">
    	<a href="${pageContext.request.contextPath}/frame/getMain.do" target="_parent"><img src="${pageContext.request.contextPath }/util/images/logo.png" title="系统首页" /></a>
    </div>
    <div class="topright">    
	    <ul>
	    	<li><a href="javascript:void(0);" onclick="opentochangeuser()">修改密码</a></li>
	    	<li><a href="${pageContext.request.contextPath}/user/logoutUser.do" target="_parent">退出</a></li>
	    </ul>
	    <div class="user">
	    	<span>${user.username}</span>
	    </div>    
    </div>
</body>
</html>
