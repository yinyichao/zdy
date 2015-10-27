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
<title>信息管理系统界面</title>
<script src="<%=path%>/js/jquery-1.4.3.js" type="text/javascript"></script>  
<script type="text/javascript">
	$(function(){
		$('#leftFrame').attr("src","${pageContext.request.contextPath}/frame/getLeft.do");
	});
	
</script>
</head>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
	<frame src="${pageContext.request.contextPath}/frame/getTop.do" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
	<frameset cols="187,*" frameborder="no" border="0" framespacing="0">
		<frame name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
		<frame src="${pageContext.request.contextPath}/frame/getIndex.do" name="rightFrame" id="rightFrame" title="rightFrame" />
	</frameset>
</frameset>
<noframes>
	<body></body>
</noframes>
</html>
