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

</style>


</head>
<body>




<%   if(session.getAttribute("midx")==null){ %>
	
	<a href="<%=request.getContextPath()%>/board/boardWrite.do">게시판작성</a>
	<a href="<%=request.getContextPath() %>/board/boardList.do">게시판 리스트</a>
	<a href="<%=request.getContextPath()%>/member/memberList.do">회원리스트</a>
	<a href="<%=request.getContextPath()%>/board/boardWrite.do">게시판작성</a>
	<nav id="navbar">
	  <div class="navbar_icon">
	  <i class="fa-solid fa-graduation-cap"></i>
	  <a>메모리 판매</a>
	  </div>
	  <div class="navbar_menu1">
	    <ul class="navbar_menu1">
	      <li><a href="##" active>홈</a></li>
	      <li><a href="##">상품</a></li>
	      <li><a href="##">고객센터</a></li>
	      <li><a href="##">마이페이지</a></li>
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
	
	<a href="<%=request.getContextPath()%>/board/boardWrite.do">게시판작성</a>
	<a href="<%=request.getContextPath() %>/board/boardList.do">게시판 리스트</a>
	<a href="<%=request.getContextPath()%>/member/memberList.do">회원리스트</a>
	<a href="<%=request.getContextPath()%>/board/boardWrite.do">게시판작성</a>
	<nav id="navbar">
	  <div class="navbar_icon">
	  <i class="fa-solid fa-graduation-cap"></i>
	  <a>메모리 판매</a>
	  </div>
	  <div class="navbar_menu1">
	    <ul class="navbar_menu1">
	      <li><a href="##" active>홈</a></li>
	      <li><a href="##">상품</a></li>
	      <li><a href="##">고객센터</a></li>
	      <li><a href="##">마이페이지</a></li>
	    </ul>
	  </div>
	  <div class="navbar_menu2">
	    <ul class="navbar_menu2">
	      <li><a href="<%=request.getContextPath()%>/member/memberLogout.do">로그아웃</a>
	      
	      
	      
	      </li>
		<%if((int)session.getAttribute("midx") == 1){ %>
	   <li><a href="<%=request.getContextPath()%>/member/memberLogout.do">관리자페이지</a></li>
        <% } %>
	    </ul>
	  </div>
	</nav>
	
	
	<% } %>
	










<!-- item -->

<div class="items">
  <div class="item">
    <div class="item_image">
    <img src="image/image1.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
     <strong>지포스 GTX 1660 Super</strong>
   
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>365.000</strong></span><span>원</span><span style="color: blue;">(10%할인)</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/image2.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
      <Strong>지포스 GTX 1060 Super</Strong>
    </div>
    <div class="item_des">
     <p><strong>가격</strong></p><span><strong>315.000</strong></span><span>원</span><span style="color: blue;">(15%할인)</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/image3.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
      <strong>지포스 GTX 2060 Super</strong>
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>324.000</strong></span><span>원</span><span style="color: blue;">(20%할인)</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/image4.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
   <strong>지포스 GTX 3060Ti</strong>
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>465.000</strong></span><span>원</span><span style="color: blue;">(45%할인 매진임박)</span>
    </div>
  </div>
</div>

<!-- 2 items -->

<div class="items">
  <div class="item">
    <div class="item_image">
    <img src="image/a1.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
     <strong>DDR-4 8GB</strong>
   
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>164.000</strong></span><span>원</span><span style="color: RED;">[신제품]</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/a2.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
      <Strong>DDR-4 8GB 레볼루션</Strong>
    </div>
    <div class="item_des">
     <p><strong>가격</strong></p><span><strong>185.000</strong></span><span>원</span><span style="color: RED;">[신제품]</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/a3.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
      <strong>DDR-4 8GB 한정판</strong>
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>324.000</strong></span><span>원</span><span style="color: blue;">(5%할인)</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/a4.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
   <strong>DDR-4 8GB 인피니트</strong>
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>465.000</strong></span><span>원</span><span style="color: blue;">(5%할인)</span>
    </div>
  </div>
</div>
<!-- 3번째 item -->
<div class="items">
  <div class="item">
    <div class="item_image">
    <img src="image/image1.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
     <strong>지포스 GTX 1660 Super</strong>
   
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>365.000</strong></span><span>원</span><span style="color: blue;">(10%할인)</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/image2.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
      <Strong>지포스 GTX 1060 Super</Strong>
    </div>
    <div class="item_des">
     <p><strong>가격</strong></p><span><strong>315.000</strong></span><span>원</span><span style="color: blue;">(15%할인)</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/image3.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
      <strong>지포스 GTX 2060 Super</strong>
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>324.000</strong></span><span>원</span><span style="color: blue;">(20%할인)</span>
    </div>
  </div>
  <div class="item">
    <div class="item_image">
    <img src="image/image4.jpg" width="200px" height="200px">
    </div>
    <div class="item_subject">
   <strong>지포스 GTX 3060Ti</strong>
    </div>
    <div class="item_des">
    <p><strong>가격</strong></p><span><strong>465.000</strong></span><span>원</span><span style="color: blue;">(45%할인 매진임박)</span>
    </div>
  </div>
</div>
</body>
</html>