<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.*" %>
<%@ page import="damain.*"%>


<%


 MemberVo mv = (MemberVo)session.getAttribute("mv");


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
<h1>아이디 찾기</h1>
<!-- form action="<%//=request.getContextPath()%>/member/findByIdAction.do" method="post"  -->
  <div class="int-area">
  <input type="text" name="membername" id="id" autocomplete="off" required value="회원아이디=<%=mv.getMemberid()%>" readonly="readonly" >



 
  </div>

<div class="caption">
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