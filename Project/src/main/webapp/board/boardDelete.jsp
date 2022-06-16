<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="damain.BoardVo" %>
    
    
    
 <%
 
 String bidx = (String) request.getAttribute("bidx");
 
 
 
 

if (session.getAttribute("memberId") == null){
	out.println("<script>alert('로그인해주세요');location.href='"+request.getContextPath()+"/member/memberLogin.do'</script>");
}

 
 
 %>
   
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style type="text/css">

#btnList{
margin-right: 10px;
}
</style>


<!-- FONT AWE -->
<script src="https://kit.fontawesome.com/6c060c00b1.js" crossorigin="anonymous"></script>

<!-- BOOT STRAP -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</head>
<body>
<body>

<form action="<%=request.getContextPath()%>/board/boardDeleteAction.do?bidx=<%=bidx %>" method="post">
<input type="hidden" value="<%=bidx%>">
		<div class="container" role="main" style="width: 700px;padding-top: 100px;">

			

			

				<div class="mb-3" style="text-align: center;">
				
				

					<label for="title" style="text-align: center;"><strong>삭제하시겠습니까?</strong></label>

				</div>
				<br>
				<br>
				<br>
	</form>		

			

			<div style="justify-content: center;display: flex;float: right;">

				<button type="submit" class="btn btn-sm btn-primary" id="btnList">삭제</button>

				<button type="button" class="btn btn-sm btn-primary" id="btnList" onclick="location.href='<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=bidx%>'">취소</button>

			</div>

	



</body>

</html>



</body>
</html>