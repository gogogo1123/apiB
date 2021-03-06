<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="damain.BoardVo" %> 
<%@page import="damain.PageMaker" %>
    
    <%
    
    ArrayList<BoardVo> alist = (ArrayList<BoardVo>)request.getAttribute("alist");
    
    PageMaker pm = (PageMaker)request.getAttribute("pm");
    
    
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
        <h1 style="text-align: center; ">고객센터</h1>
        <hr><br>
        <button type="button" class="btn btn-primary" id="btn1" onclick="location.href='<%=request.getContextPath()%>/'">홈으로</button>
        <button type="button" class="btn btn-info" id="btn" onclick="location.href='<%=request.getContextPath()%>/board/boardWrite.do'">글쓰기</button>
        <form name="frm" action="<%=request.getContextPath() %>/board/boardList.do" method="post">
        <table style="text-align: right;margin-top: 30px;">
        <tr>
        <td width="1000px;">
           <select name="searchType">
           <option value="subject">제목</option>
           <option value="writer">작성자</option>
           </select>
         </td>
         <td>
         <input type="text" name="keyword" size="20">
         </td>
         <td>
         <input type="submit" name="submit" value="검색">
         </td>
        </tr>
    </table> 
        </form>
        
        
        <br><br>
        <table class="table table-hover" style="width: 1200px; text-align: center;" id="table1">
            <thead>
                <tr>
                    <th>번호</th>
                    <th>제목</th>
                    <th>글쓴이</th>
                    <th>작성일</th>
                    <th>조회수</th>
                </tr>
            </thead>
            
            <% for(BoardVo bv : alist) {  %>
            <tbody>
                <tr>
                    <td><%=bv.getBidx() %></td>
                    <td>
                    
                    <%
                    
                    for (int i=1; i<= bv.getLevel_(); i++) { 
                    	out.println("&nbsp;&nbsp;");
                    	if (i == bv.getLevel_()){
                    		out.println("ㄴ");
                    	}	
                    }		
                    %>
                    
                     
                    
                    <a href="<%=request.getContextPath()%>/board/boardContent.do?bidx=<%=bv.getBidx()%>"><%=bv.getSubject() %></a>
                    
                    </td>
                    
                    
                    <td><%=bv.getWriter() %></td>
                    <td><%=bv.getWriteday().substring(0,16) %></td>
                    <td><%=bv.getHit() %></td>
                </tr>
           
            </tbody>
            <% } %>
        </table>
          



  
<
        <br><br>
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
              <li class="page-item">
              
              <%  
              
              String keyword = pm.getScri().getKeyword();            // boardtotal 결과 값 키워드 가져오기
              String searchType = pm.getScri().getSearchType();      //   / /      결과 값 서치타입 가져오기
              
              
              
              
              if (pm.isPrev() == true){
          		out.println("<a class=page-link  href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getStartPage()-1)+"&keyword="+keyword+"&searchType="+searchType+"'>◀</a>"); 	}
              %>
          	
          	</li>
              <li class="page-item" style="display: flex;">
              <%
     
     for(int i = pm.getStartPage(); i<=pm.getEndPage(); i++){
    	 out.println("<a class=page-link href='"+request.getContextPath()+"/board/boardList.do?page="+i+"&keyword="+keyword+"&searchType"+searchType+"'>"+i+"</a>");
     }
     
               %>
              </li>
          
              <li class="page-item">
              
                <%
     
     if(pm.isNext()&&pm.getEndPage()>0){
    	 out.println("<a class=page-link href='"+request.getContextPath()+"/board/boardList.do?page="+(pm.getEndPage()+1)+"&keyword="+keyword+"&searchType="+searchType+"'>▶</a>");
     }
     
               
                %>
                
              </li>
            </ul>
        </nav>
         
    </div>
</body>
</html>

<!-- class="page-link" -->