<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<style type="text/css">

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');

	
*{

font-family: 'Noto Sans KR', sans-serif;

text-decoration: none;
}



p{
margin-left: 70px;
}

body{

margin: 0px;

}



div{

display: flex;
justify-content: center;
align-items: center;
height: 100vh;
}


.btn1{
	display: flex;
	
}

a{
font-size:24px;

list-style: none;

}

</style>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://kit.fontawesome.com/6c060c00b1.js" crossorigin="anonymous"></script>
   <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>

<a href="<%=request.getContextPath()%>/board/boardWrite.do">게시판작성</a>
	<a href="<%=request.getContextPath() %>/board/boardList.do">게시판 리스트</a>
	<a href="<%=request.getContextPath()%>/member/memberList.do">회원리스트</a>
	<a href="<%=request.getContextPath()%>/board/boardWrite.do">게시판작성</a>
<a href="<%=request.getContextPath()%>/item/itemA.do">zz</a>





<div class="btn1">
<button type="button" class="btn btn-outline-primary">Primary</button>
<button type="button" class="btn btn-outline-secondary">Secondary</button>
<button type="button" class="btn btn-outline-success">Success</button>
<button type="button" class="btn btn-outline-danger">Danger</button>
<button type="button" class="btn btn-outline-warning">Warning</button>
<button type="button" class="btn btn-outline-info">Info</button>
<button type="button" class="btn btn-outline-light">Light</button>
<button type="button" class="btn btn-outline-dark">Dark</button>
	</div>
   
   
          
                            
</body>
</html>