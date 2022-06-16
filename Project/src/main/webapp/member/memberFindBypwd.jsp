<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<h1>비밀번호 찾기</h1>
<form action="<%=request.getContextPath()%>/member/memberfindByPwdAction.do" method="post">
  <div class="int-area">
  <input type="text" name="memberid" id="id" autocomplete="off" required>
  <label for="id">USER ID</label>
  </div>
    <div class="int-area">
  <input type="text" name="memberName" id="pw" autocomplete="off" required>
   <label for="id">USER NAME</label>
  </div>
  <div class="btn-area">
    <button id="btn"  type="submit">비밀번호 찾기</button>
  </div>
</form>
<div class="caption">
<a href="<%=request.getContextPath()%>/member/memberFindByid.do">forgot userid?</a>
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