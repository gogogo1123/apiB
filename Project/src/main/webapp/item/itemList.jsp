<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="damain.ItemVo" %>
    
    <%
    
    
    ArrayList<ItemVo> alist = ( ArrayList<ItemVo>)request.getAttribute("alist");
    
    
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
        <h1 style="text-align: center;">구매내역</h1>
        <hr><br>
    
        <br><br>
        <table class="table table-hover" style="width: 1200px;" id="table1">
            <thead>
                <tr>
                    <th>고객번호</th>
                    <th>고객이름</th>
                    <th>제품명</th>
                    <th>단위가격</th>
                    <th>제품수량</th>
                    <th>구매금액</th>
                    <th>구매일자</th>
                   
                </tr>
            </thead>
            <% for(ItemVo iv :alist) { %>
            <tbody>
                <tr>
                    <td><%=iv.getMidx() %></td>
                    <td><%=iv.getMemberName() %></td>
                    <td><%=iv.getItemName() %></td>
                    <td><%=iv.getItemPrice() %></td>
                    <td><%=iv.getItemQuantity() %></td>
                    <td><%=iv.getItemPriceSum()%></td>
                    <td><%=iv.getWriteday().substring(0, 10) %></td>
                
                   
                </tr>
            </tbody>
            <% } %>
        </table>

   
        

        <br><br>
 
      
    </div>
</body>
</html>