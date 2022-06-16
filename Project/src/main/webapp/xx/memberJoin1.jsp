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
	}else if(a.memberName.value==""){
		alert("이름을 입력해주세요")
		return;
	}else if(a.memberEmail.value==""){
		alert("이메일을 입력해주세요");
		a.memberEmail.focus();
		return;
	}else if(a.memberGender.value==""){
		alert("하나 이상 선택해주세요")
		return;
	}
	
	
	
	    alert("전송합니다..");
		a.action = "<%=request.getContextPath()%>/member/memberJoinAction.do";
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

body{
background-color: #e9e9e9;
}
</style>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<form name="frm">
<div class="container">

  <div class="py-5 text-center">
  <H2>회원 가입</H2>
  </div>
  <!--  아이디 -->

  <div>

  <label class="py-1">회원 아이디</label>
  <input type="text" name="memberId" class="form-control" placeholder="이름을 입력하세요">
  </div>
  <!--  비밀번호 -->
  <div>
  <label class="py-1">회원 비밀번호</label><br>
  <input type="password" name="memberPwd" class="form-control" placeholder="비밀번호를 입력해주세요">
  </div>
  <!--  이름 -->
  <div>
  <label for="memberName" class="py-1">회원 이름</label><br>
  <input type="text" name="memberName" class="form-control" placeholder="이름을 입력하세요">
  </div>
  <!--  이메일 -->
  <div>
  <label for="memberEmail" class="py-1">회원 이메일</label><br>
  <input type="email" name="memberEmail" class="form-control" placeholder="ABCD@naver.com">
  </div>
  <!--  성별나누기. -->
  <div>
  <label for="memberGender" class="py-1">회원 성별</label><br>
   <div class="form-check form-check-inline">
  <input type="radio" id="memberGender"
  name="memberGender" class="form-check-input" value="M">남자
  </div>
   <div class="form-check form-check-inline">
  <input type="radio" id="memberGender"
  name="memberGender" class="form-check-input" value="W">여자
  </div>
</div>

<!-- 버튼 -->
<div class="py-3 row justify-content-center">
  <button type="button" class="btn btn-primary btn-lg" onclick="check();">확인</button>
</div>
<div class="row justify-content-center">
  <button type="button" class="btn btn-danger btn-lg" onclick="location.href='index1.jsp'">취소</button>
</div>
</form>
</body>
</html>