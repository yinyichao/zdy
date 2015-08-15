<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<link href="<%=path%>/util/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=path%>/js/jquery-1.9.1.min.js"></script> 
<script type="text/javascript">
/*$(document).ready(function(){
  $(".click").click(function(){
  $(".tip").fadeIn(200);
  });
  
  $(".tiptop a").click(function(){
  $(".tip").fadeOut(200);
});

  $(".sure").click(function(){
  $(".tip").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip").fadeOut(100);
});

});*/


function opentoadduser(){
	window.open ("<%=path%>/user/toAddUser.do", "newwindow", "height=500, width=650, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=200,left=500");
}

//操作刷新



$(function(){
		$('#check').click(function(){
					if($('#check').prop('checked')){
						$("[name = checkbox]:checkbox").prop("checked", true);
					}else{
						$("[name = checkbox]:checkbox").prop("checked", false);
					}
				}
		);
		
		
		//批量删除
		
		 $("#deluser").click(function() {  
        // 判断是否至少选择一项  
        var checkedNum = $("input[name='checkbox']:checked").length;  
        if(checkedNum == 0) {  
            alert("请选择至少选择一项！");  
            return;  
        }  
          
        // 批量选择   
        if(confirm("确定要删除所选项目？")) {  
            var checkedList = new Array(); 
           // var checkedList; 
            $("input[name='checkbox']:checked").each(function() { 
                checkedList.push($(this).val());  
            });
            $.ajax({  
                type: "POST",  
                url: "<%=path%>/user/delUser.do",  
                data: {'ids':checkedList.toString()},  
                success: function(result) {  
                    alert(result);  
                    $("#check").prop("checked", false);
                    $("[name = checkbox]:checkbox").prop("checked", false);
                    window.location.reload();  
                }  
            });  
        }             
   		});  
   		
   		
   		 $("#updateuser").click(function() {  
        // 判断是否至少选择一项  
        var checkedNum = $("input[name='checkbox']:checked").length; 
        if(checkedNum == 0){
        	alert("请选择一项进行修改");  
            return;
        } 
        if(checkedNum != 1) {  
            alert("只能选择一项进行修改");  
            return;  
        }  
        
        $("input[name='checkbox']:checked").each(function() { 
      		   window.open ("<%=path%>/user/toUpdateUser.do?id="+$(this).val(), "newwindow", "height=500, width=600, toolbar=no, menubar=no, scrollbars=no, resizable=no, location=no, status=no,top=200,left=500");
        });
          
   		});  
   		
   		
   		$('#selpage').change(function(){
		
			$(this).val();
			
			toPage($(this).val());
		});
		
	});
	
	/*function back(){
		$("#check").prop("checked", false);
		$("[name = checkbox]:checkbox").prop("checked", false);
		window.location.reload();
	}*/
	
	function toPage(currPage) {
            	//将隐藏框设置为用户选中的页
            	//document.getElementById("page").value=currPage;
            	//通过脚本提交表单
            	$('#page').val(currPage);
            	$('#usermanager').submit();
    }
</script>
<link rel="stylesheet" href="<%=path%>/fy/style.css" />

</head>


<body>

	<div class="place">
    	<span>用户管理</span>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
	        <li onclick="opentoadduser()"><span><img src="<%=path%>/util/images/t01.png" /></span>添加</li>
	        <li id="updateuser"><span><img src="<%=path%>/util/images/t02.png" /></span>修改</li>
	        <li id="deluser"><span><img src="<%=path%>/util/images/t03.png" /></span>删除</li>
	        <!-- <li onclick="back()"><span><img src="<%=path%>/util/images/t04.png" /></span>刷新</li> -->
        </ul>
        
    
    </div>
    <div style="font-size: 25px">
     <form action="<%=path%>/user/getUserPageAll.do" method="post" id="usermanager">
   		<span> 
		           用户名:<input type="text" name="userName" value="${username}" />
		    <input type="hidden" name="page" id="page" value="${page }" />
	     	<input type="submit" value="查找"/> 
     	</span>
     </form>  
    </div>
    <table class="tablelist"  id="table">
    	<thead>
	    	<tr>
		        <th><input name="" type="checkbox" id="check"/></th>
		        <th>用户名</th>
		        <th>姓名</th>
		        <th>权限</th>
		        <th>所在部门</th>
		        <th>手机号</th>
	        </tr>
        </thead>
        <tbody>
        
	        <c:forEach items="${pages.root }" var="zuser">
		        <tr>
			        <td><input name="checkbox" type="checkbox" value="${zuser.id }" /></td>
			        <%-- <td>${caseuser.orgId }</td> --%>
			        <td>${zuser.username }</td>
			        <td>${zuser.xm }</td>
			        <td><c:if test="${zuser.qx eq 0}">开启</c:if><c:if test="${zuser.qx eq 1}"> 关闭</c:if></td>
			        <td>${zuser.bm }</td>
			        <td>${zuser.sj }</td>
		        </tr> 
	        </c:forEach>
          
        </tbody>
    </table>
    
    <div class="pagin">
    
    	<div class="message">
	    	<select id="selpage">
		    	<c:forEach  begin="1" end="${pages.totalPage }" var="i" >
		    		<option value="${i }"  <c:if test="${page == i }">selected="selected"</c:if> >${i }</option>
		    	</c:forEach> 
	    	</select>共<i class="blue">${pages.total }</i>条记录，当前显示第&nbsp;<i class="blue">${page }&nbsp;</i>页 共<i class="blue">${pages.totalPage }</i>页
    	</div>
        <ul class="paginList">
	        <li class="paginItem"><a href="javascript:toPage(1);">首页</a></li>
		        <c:choose> 
			        <c:when test="${page == 1 }"><li class="paginItem"><a href="#">上页</a></li></c:when>
			        <c:otherwise><li class="paginItem"><a href="javascript:toPage(${page-1});">上页</a></li></c:otherwise>
		        </c:choose>
	        <li class="paginItem current"><a href="javascript:;">${page }</a></li>
		        <c:choose>
			         <c:when test="${page == pages.totalPage }"><li class="paginItem"><a href="#">下页</a></li></c:when>
			         <c:otherwise><li class="paginItem"><a href="javascript:toPage(${page+1});">下页</a></li></c:otherwise>
		        </c:choose>
	        <li class="paginItem"><a href="javascript:toPage(${pages.totalPage});">尾页</a></li>
        </ul>
        
    </div>
    
    <!--<div class="tip">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
	        <span><img src="${pageContext.request.contextPath }/util/images/ticon.png" /></span>
	        <div class="tipright">
		        <p>是否确认对信息的修改 ？</p>
		        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
	        </div>
      </div>
        
      <div class="tipbtn">
	        <input name="" type="button"  class="sure" value="确定" />&nbsp;
	        <input name="" type="button"  class="cancel" value="取消" />
      </div>
    
    </div>-->
    
    </div>
    
    <script type="text/javascript">
		$('.tablelist tbody tr:odd').addClass('odd');//:odd 奇数行
	</script>
	
</body>

</html>