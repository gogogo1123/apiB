<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style type="text/css">

@import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');


*{

ㄱㄱㄷㄱㄱㄺㄱ

font-family: 'Noto Sans KR', sans-serif;




}



p{
margin-left: 70px;
}

body{
background: url("image/back.jpg");
margin: 0px;

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


<%   if(session.getAttribute("midx")==null){ %>

  <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-white text-muted">
            <div class="container">
             <i class="fa-solid fa-cart-shopping"></i>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/">별시장</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-1 mb-lg-0 ms-lg-2">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/">홈</a></li>
               
                        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/board/boardList.do">고객센터</a></li>
                            </ul>
                        </li>
                    </ul>
                       <form class="d-flex">
                        <button class="btn btn-outline-dark" type="button" onclick="location='<%=request.getContextPath()%>/member/memberLogin.do'">
                        
                             
                            로그인
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="button" onclick="location='<%=request.getContextPath()%>/member/memberJoin.do'">
                       
                              
                            회원가입
                        </button>
                    </form>
                </div>
            </div>
        </nav>



<% } %>


<%   if(session.getAttribute("midx")!=null){ %>

  <!-- Navigation-->
        <nav class="navbar navbar-expand-lg navbar-light bg-secondary text-muted">
            <div class="container">
             <i class="fa-solid fa-cart-shopping"></i>
                <a class="navbar-brand" href="<%=request.getContextPath()%>/">별시장</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-1 mb-lg-0 ms-lg-2">
                        <li class="nav-item"><a class="nav-link active" aria-current="page" href="<%=request.getContextPath()%>/">홈</a></li>
                 
                        <li class="nav-item"><a class="nav-link" href="<%=request.getContextPath() %>/board/boardList.do">고객센터</a></li>
                    
                            </ul>
                        </li>
                    </ul>
                    
                    <form class="d-flex">
                        <button class="btn btn-outline-dark" type="button" onclick="location='<%=request.getContextPath()%>/member/memberLogout.do'">
                        
                           
                            로그아웃
                        </button>
                    </form>
                    
                    	<%if((int)session.getAttribute("midx") == 1){ %>
                    	   <form class="d-flex">
                        <button class="btn btn-outline-dark" type="button" onclick="location='index2.jsp'">
                     
                          
                            관리자페이지
                        </button>
                    </form>
                    	
                    	
                        	<% } %>
                </div>
            </div>
        </nav>



<% } %>


   
           <!-- Header-->
        <header class="py-5">
            <div class="container px-4 px-lg-5 my-5">
                <div class="text-center text-white">
                    <h1 class="display-4 fw-bolder">별에 관한 이야기</h1>
                    <p class="lead fw-normal text-white-40 mb-0">연극 & 스티커</p>
                </div>
            </div>
        </header>
     <Div>
          <h2 style="text-align: center;color: white;"><button style="outline: none; background: none; border: 1px solid black; background:grey; border-radius: 8px;"><strong>연극 상영표</strong></button></h2>
     </Div>
         <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="image/연극.jpg" alt="..."  width="450px" height="300px"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h4 class="fw-bolder">(연극)</h3>
                                    <h5 class="fw-bolder">별무리</h5>
                                    <!-- Product price-->
                                      13,000원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/res/rescontent.do">예약하기</a></div>
                        
                            </div>
                        </div>
                    </div> 
                    
                       <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Hit</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="image/연극1.jpg" alt="..." width="450PX" height="300px"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                       <h4 class="fw-bolder">(연극)</h3>
                                    <h5 class="fw-bolder">우리 읍내</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                   
                                    12.000 원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/res/rescontent1.do">예약하기</a></div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Hit</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="image/연극2.jpg" alt="..." width="450PX" height="300px"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                       <h4 class="fw-bolder">(연극)</h3>
                                    <h5 class="fw-bolder">아인슈타인의 별</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                   
                                    14.000 원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/res/rescontent2.do">예약하기</a></div>
                            </div>
                        </div>
                    </div>
                    
                    
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Hit</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="image/연극3.jpg" alt="..." width="450PX" height="300px"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                       <h4 class="fw-bolder">(연극)</h3>
                                    <h5 class="fw-bolder">별이 빛나는 밤</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                   
                                    12.000 원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/res/rescontent3.do">예약하기</a></div>
                            </div>
                        </div>
                    </div>
                    
                    
                    
                    </div> 
                    </div>
                    
                    
                    </section>
                    
                    
                      <Div>
          <h2 style="text-align: center;color: white;"><button style="outline: none; background: none; border: 1px solid black; background:grey; border-radius: 8px;"><strong>별자리 스티커</strong></button></h2>
     </Div>
        <!-- Section-->
        <section class="py-5">
            <div class="container px-4 px-lg-5 mt-5">
                <div class="row gx-4 gx-lg-5 row-cols-2 row-cols-md-3 row-cols-xl-4 justify-content-center">
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="image/a.jpg" alt="..."  width="450px" height="300px"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">양자리 스티커</h5>
                                    <!-- Product price-->
                                      8,000 원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/item/itemA.do">구매하기</a></div>
                        
                            </div>
                        </div>
                    </div>
                    
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Hit</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="image/b.jpg" alt="..." width="450PX" height="300px"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">처녀자리 스티커</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">9.000 원</span>
                                    7.500 원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/item/itemB.do">구매하기</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="image/c.jpg" alt="..." width="450PX" height="300px"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">염소자리 스티커</h5>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">6.000 원</span>
                                    5.500 원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/item/itemC.do">구매하기</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="image/d.jpg" alt="..." width="450PX" height="300px" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">전갈자리 스티커</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                   10.000원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/item/itemD.do">구매하기</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="image/e.jpg" alt="..." width="450PX" height="300px"/>
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">물고기 자리 스티커</h5>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">11.000 원</span>
                                     10.500 원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/item/itemE.do">구매하기</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="image/f.jpg" alt="..." width="450PX" height="300px" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">천칭자리 스티커</h5>
                                    <!-- Product price-->
                                  12.000원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/item/itemF.do">구매하기</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Sale badge-->
                            <div class="badge bg-dark text-white position-absolute" style="top: 0.5rem; right: 0.5rem">Sale</div>
                            <!-- Product image-->
                            <img class="card-img-top" src="image/g.jpg" alt="..." width="450PX" height="300px" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">황소자리 스티커</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    <span class="text-muted text-decoration-line-through">15.000원</span>
                                    13.000원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/item/itemG.do">구매하기</a></div>
                            </div>
                        </div>
                    </div>
                    <div class="col mb-5">
                        <div class="card h-100">
                            <!-- Product image-->
                            <img class="card-img-top" src="image/h.jpg" alt="..." width="450PX" height="300px" />
                            <!-- Product details-->
                            <div class="card-body p-4">
                                <div class="text-center">
                                    <!-- Product name-->
                                    <h5 class="fw-bolder">암자리 스티커</h5>
                                    <!-- Product reviews-->
                                    <div class="d-flex justify-content-center small text-warning mb-2">
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                        <div class="bi-star-fill"></div>
                                    </div>
                                    <!-- Product price-->
                                    16.500원
                                </div>
                            </div>
                            <!-- Product actions-->
                            <div class="card-footer p-4 pt-0 border-top-0 bg-transparent">
                                <div class="text-center"><a class="btn btn-outline-dark mt-auto" href="<%=request.getContextPath()%>/item/itemH.do">구매하기</a></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
                            
</body>
</html>