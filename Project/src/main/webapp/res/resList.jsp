<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="damain.ResVo" %>

    
    <%
    
    
    ArrayList<ResVo> alist = (ArrayList<ResVo>)request.getAttribute("alist");
    
   
    
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
        <h1 style="text-align: center;">예약 내역</h1>
    

        <table class="table table-hover" style="width: 1200px;" id="table1">
            <thead>
                <tr>
                    <th>예약번호</th>
                    <th>회원번호</th>
                    <th>회원이름</th>
                    <th>연극이름</th>
                    <th>단위가격</th>
                    <th>예약인원</th>
                    <th>예약금액</th>
                    <th>예 약 일</th>
                    <th>관 람 일</th>
                    
                  
                   
                </tr>
            </thead>
            <% for(ResVo rv :alist) { %>
            <tbody>
                <tr>
                    <td><%=rv.getRidx() %></td>
                    <td><%=rv.getMidx() %></td>
                    <Td><%=rv.getMembername() %></Td>
                    <td><%=rv.getRvname() %></td>
                    <td><%=rv.getRvPrice() %></td>
                    <td><%=rv.getRvpeople() %></td>
                    <td><%=rv.getRvPriceSum() %></td>
                   <td><%=rv.getWritedayA().substring(0, 10) %></td>
                    <td><%=rv.getWriteday().substring(0, 10) %></td>
           
                   
                </tr>
            </tbody>
            <% } %>
        </table>

   
        

        <br><br>
        
    </div>
</body>
</html>