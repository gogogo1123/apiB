<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="damain.MemberVo" %>
    
    <%
    
   ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
    
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="style.css">


<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>

</head>
<body>
<section  class="logo-form">
<h1>로 그 인</h1>
<form action="<%=request.getContextPath()%>/member/memberLoginAction.do" method="post">
<input type="hidden" name="midx">
  <div class="int-area">
  <input type="text" name="memberId" id="id" autocomplete="off" required>
  <label for="id">USER ID</label>
  </div>
    <div class="int-area">
  <input type="password" name="memberPwd" id="pw" autocomplete="off" required>
   <label for="id">password</label>
  </div>
  <div class="btn-area">
    <button id="btn"  type="submit">login</button>
  </div>
</form>
<div class="caption">
<a href="<%=request.getContextPath()%>/member/memberFindByid.do">forgot userid?</a><Br><br>
<a href="<%=request.getContextPath()%>/member/memberFindBypwd.do">forgot password?</a>
</div>
</section>




<!-- java script -->

<script>




let id = $('#id');
let pw = $('#pw');
let btn = $('#btn');

$(btn).on('click',function(){
	if($(id).val()==""){
		$(id).next("label").addClass("warning");
		setTimeout(function(){
			$('label').removeClass('warning');
			
		},1500);
	}
	else if($(pw).val()==""){
		$(pw).next("label").addClass("warning");
		setTimeout(function(){
			$('label').removeClass('warning');	
		},1500);
	}
	    action = "<%=request.getContextPath()%>/member/memberJoinAction.do";
		method = "post";
		
	
	
	
});




</script>




</body>
</html>