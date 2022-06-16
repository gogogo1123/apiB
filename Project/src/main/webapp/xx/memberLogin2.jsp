<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
<title>Insert title here</title>
<script type="text/javascript">

function check() {
	
	var a = document.frm
	if(a.memberId.value==""){
	alert("아이디를 입력해주세요");
	a.memberId.focus();
	return;
	}else if(a.memberPwd.value==""){
		alert("비밀번호를 입력해주세요");
		a.memberPwd.focus();
		return;
	}
	
	
	
	    alert("전송합니다..");
		a.action = "<%=request.getContextPath()%>/member/memberLoginAction.do";
		a.method = "post";
		a.submit();  
}

</script>
<style type="text/css">

.container{
max-width:600px;
te
}

h2{
margin-top: 50px;
}
</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<form name="frm">
<div class="container">

  <div class="py-5 text-center">
  <H2>로그인</H2>
  </div>
  <!--  아이디 -->

  <div>

  <label class="py-1">회원 아이디</label>
  <input type="text" name="memberId" class="form-control" placeholder="이름을 입력하세요">
  </div>
  <!--  비밀번호 -->
  <div>
  <label for="memberPwd" class="py-1">회원 비밀번호</label><br>
  <input type="password" name="memberPwd" class="form-control" placeholder="비밀번호를 입력해주세요">
  </div>


<!-- 버튼 -->
<div class="py-3 col justify-content-center">
  <button type="button" class="w-100 btn btn-primary btn-lg" onclick="check();">확인</button>
</div>
<div class="col justify-content-center">
  <button type="button" class="w-100 btn btn-danger btn-lg" onclick="location.href='index1.jsp'">취소</button>
</div>
</form>
</body>
</html>