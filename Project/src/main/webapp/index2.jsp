<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>DDD</title>

<meta name="viewport" content="width=device-width, initial-scale=1">

<!-- 부트스트랩 css 사용 --> <link rel="stylesheet" href="/webapp/css/bootstrap.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

<script src="https://kit.fontawesome.com/6c060c00b1.js" crossorigin="anonymous"></script>

<style>


body{
margin: 0;

}


#navbar{
display: flex;
justify-content: space-between;

padding: 16px;
background-color: #000;
color: white;

}

.navbar_menu1{
display: flex;
color: white;
text-align:center;
margin: 0;
float: letf;
}

.navbar_menu2{
display: flex;
color: white;
text-align:center;
margin: 0;
float: right;
}

ul{
list-style: none;
display: flex;
text-decoration: none;
color: white;
}

li{
padding: 8px 12px;
margin: 0px 16px;
color: white;

}


a{
text-decoration: none;
color: white;
}


.navbar_icon{
text-align:Center;
padding: 8px 12px;
margin: 0px 4px;
}

i{
text-align: center;
 
}

.items{
display: flex;
justify-content: space-around;
text-align: center;

margin: auto;
}


.item{
margin: 0px 100px;
}

.item_image{
border:1px solid gray;
border-radius:6px;
width:300px;
height:300px;
line-height: 250px;
margin-top: 50px;
position: relative;

}

.item_image img:hover{
rotate:(10deg);
scale:(1.1);
}




span{
color: black;
margin-bottom: 50px;
}

.item_subject{

margin-bottom: 30px;
}


.item_des{
text-align: center;
}


.active{
border: 1px solid white;
padding:20px;
width:40px;
height:20px;
background-color: white;
}



.li{
border: 1px solid gray;
background-color: white;
}



.btn1{
display: flex;
justify-content: center;
align-items: center;
height: 100vh;
font-size: 30px;
size: 30px;
}



button {
	font-size: 30px;
	size: 30px;
}



</style>



</head>
<body>




<%   if(session.getAttribute("midx")==null){ %>
	
	<nav id="navbar" class="navbar navbar-expand-lg navbar-black bg-secondary text-muted">
	  <div class="navbar_icon">
	  <i class="fa-solid fa-graduation-cap"></i>
	  <a>메모리 판매</a>
	  </div>
	  <div class="navbar_menu1">
	    <ul class="navbar_menu1">
	      <li><a href="<%=request.getContextPath()%>/" active>홈</a></li>
	    </ul>
	  </div>
	  <div class="navbar_menu2">
	    <ul class="navbar_menu2">
	      <li><a href="<%=request.getContextPath()%>/member/memberLogin.do">로그인</a></li>
	      <li><a href="<%=request.getContextPath()%>/member/memberJoin.do">회원가입</a></li>
	  
	    </ul>
	  </div>
	</nav>
	<% } %>
	
	<%   if(session.getAttribute("midx") !=null){ %>
	
	
	<nav id="navbar" class="navbar navbar-expand-lg navbar-black bg-secondary text-muted">
	  <div class="navbar_icon">
	  <i class="fa-solid fa-graduation-cap"></i>
	  <a>메모리 판매</a>
	  </div>
	  <div class="navbar_menu1">
	    <ul class="navbar_menu1">
	      <li><a href="<%=request.getContextPath()%>/" active>홈</a></li>

	    </ul>
	  </div>
	  <div class="navbar_menu2">
	    <ul class="navbar_menu2">
	      <li><a href="<%=request.getContextPath()%>/member/memberLogout.do">로그아웃</a>
	      
	      
	      
	      </li>
	
	    </ul>
	  </div>
	</nav>
	
	
	<% } %>
	


<div class="btn1">
<div>
<button type="button" class="btn btn-outline-primary btn-lg" onclick="location='<%=request.getContextPath()%>/board/boardList.do'">게시판리스트</button>
</div>
<div>
<button type="button" class="btn btn-outline-primary btn-lg" onclick="location='<%=request.getContextPath()%>/item/itemList.do'">주문 내역</button>
</div>
<div>
<button type="button" class="btn btn-outline-primary btn-lg" onclick="location='<%=request.getContextPath()%>/res/resList.do'">예약내역</button>
</div>
<button type="button" class="btn btn-outline-primary btn-lg" onclick="location='<%=request.getContextPath()%>/member/memberList.do'">회원가입리스트</button>

	</div>
   








</body>
</html>