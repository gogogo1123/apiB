<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@ page import="damain.MemberVo" %>  
    <%
    
    
    // 멤버 컨트롤러에서 저장해서 가져온 alist 값을 꺼내야하니깐 꺼내줌.
    
    
    // 회원 리스트 페이지 이니깐  회원 리스트 로직을 사용한
    // memberListALL 을 가져와야함 
    // 그것은 ArrayList<MemberVO> 인 배열에 담아두었으니깐 꺼내줌
    
    
    
    // 형 변환을 시켜줌 왼쪽 오른쪽 똑같이 맞춰 줘야함..
    
    ArrayList<MemberVo> alist = (ArrayList<MemberVo>)request.getAttribute("alist");
    
    %>
    
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
#btn{
float: right;
margin-right: 40px;
}

#btn1{
margin-left: 30px;
}

body{
display:flex;
text-align:center;
justify-content: center;
align-items: center;

}

#main{
margin-top: 150px;
}
</style>

<!-- FONT AWE -->
<script src="https://kit.fontawesome.com/6c060c00b1.js" crossorigin="anonymous"></script>

<!-- BOOT STRAP -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
  <div id="main">
        <h1 style="text-align: center;">가입한 회원</h1>
        <hr><br>
   
        <br><br>
        <table class="table table-hover" style="width: 1200px;" id="table1">
            <thead>
                <tr>
                    <th>회원번호</th>
                    <th>회원아이디</th>
                    <th>회원비밀번호</th>
                    <th>회원이름</th>
                    <th>회원성별</th>
                    <th>회원이메일</th>
                    <th>가입날짜</th>
                </tr>
            </thead>
            <tbody>
            
             <!-- MemberDao 의 oracle 결과값을 
             
              데이터 저장값인 MemberVo 를 불러와서 저장한 값을 가져옴..?
              
               -->
            
            <% for(MemberVo mv : alist) { %>
            
                <tr>
                    <td><%=mv.getmidx() %></td>
                    <td><%=mv.getMemberid() %></td>
                    <td><%=mv.getMemberPwd() %></td>
                     <td><%=mv.getMemberName() %></td>
                    <td><%=mv.getMembergender() %></td>
                    <td><%=mv.getMemberEmail() %></td>
                    <td><%=mv.getWriteday().substring(0,16) %></td>
                </tr>
            <%  } %>
            </tbody>
        </table>

   
        

        <br><br>
       
    </div>
</body>
</html>