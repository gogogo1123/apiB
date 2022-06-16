<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
<meta charset="UTF-8">

<title>Insert title here</title>


<Style>

#center{

display: flex;
justify-content: space-evenly;


}

.center_image{
margin-top:190px;

display:flex;
align-items: center;
}

.center_image_a{
border-radius: 10px;
}



.table{

align-items:center;
margin-top: 300px;

}

button{


margin-left:120px;
justify-content: center;
}


body{
background-color:#d3d3d3;
margin: 0px;

}

input{

height: 50px;
background: gray;
}


</Style>

<script type="text/javascript">

function check(){  
	
	  var fm = document.form;
	  
	  
	  if (fm.respeople.value==""){
	  		alert("수량을 적어주세요");
	  		fm.respeople.focus();
	  		return;
	  }else if(fm.data.value==""){
		  alert("관람일자 를 선택해주세요")
		  fm.data.focus();
		  return;
	  }
	  		
	
	         alert("예약 완료 됐습니다..");
	  		fm.action = "<%=request.getContextPath()%>/res/rescontentAction.do";
	  		fm.method = "post";
	  		fm.submit();  
  			
	
	   return;
	 
	  }  
</script>

<script type="text/javascript">
	$(document).ready(function() {
		
		today = new Date();
		console.log("today.toISOString() >>>" + today.toISOString());
		today = today.toISOString().slice(0, 10);
		console.log("today >>>> " + today);
		bir = document.getElementById("today");
		bir.value = today;
		
	});
</script>


</head>
<body onload="init();">




<script language="JavaScript">

var sell_price;
var respeople;

function init () {
	sell_price = document.form.sell_price.value;
	itemQuantity = document.form.respeople.value;
	document.form.sum.value = sell_price;
	change();
}

function add () {
	hm = document.form.respeople;
	sum = document.form.sum;
	hm.value ++ ;

	sum.value = parseInt(hm.value) * sell_price;
}

function del () {
	hm = document.form.respeople;
	sum = document.form.sum;
		if (hm.value > 1) {
			hm.value -- ;
			sum.value = parseInt(hm.value) * sell_price;
		}
}

function change () {
	hm = document.form.respeople;
	sum = document.form.sum;

		if (hm.value < 0) {
			hm.value = 0;
		}
	sum.value = parseInt(hm.value) * sell_price;
}  

</script>



<section id="center">


<div class="center_image">

    <img class="center_image_a" src="../image/연극2.jpg" alt="..."  width="600px" height="600px"/>

</div>
<div class="b">

<form  method="post" name="form">

<table class="table" style="width: 700px;">
  <thead>
    <tr style="margin-bottom: 20px;">
      
      <th scope="col" style="text-align: center; font-size: 30px; margin-bottom: 30px;">예약하기</th>
    

    </tr>
  </thead>
  <tbody>
    <tr>
   
      <td>
      
    <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">연극명</span>
  <input type="text" class="form-control" placeholder="Username" aria-label="Username" name="Name" aria-describedby="addon-wrapping" readonly="readonly" value="아인슈타인의 별">
</div>
      
      </td>
   
    </tr>
    
    
    <tr>
   
      <td>
      
    <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">가     격&nbsp&nbsp</span>
  <input type="text" class="form-control"  name="sell_price" aria-describedby="addon-wrapping" readonly="readonly" value="14000">
</div>
      
      </td>
   
    </tr>
    
    
    <tr>
    
      <td>
      
          <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">인원수</span>
  <input type="text" class="form-control" placeholder="수량" value="1" max="" name="respeople" aria-label="Username" aria-describedby="addon-wrapping">
   <input class="btn btn-primary btn-lg" type="button" value="+" onclick="add();"> 
    <input class="btn btn-danger btn-lg" type="button" value="-" onclick="del();"> 
</div>
      
      
      </td>
      
      <tr>
   
      <td>
      
    <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">총금액</span>
  <input type="text" class="form-control"  name="sum" aria-describedby="addon-wrapping" readonly="readonly">
</div>
      
      </td>
   
    </tr>
      
      
        <tr>
    
      <td>
      
          <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">예약일</span>
  <input type="date" class="form-control" placeholder="수량" name="dataA" aria-label="Username" aria-describedby="addon-wrapping" id="today">
</div>
      
      
      </td>
      
     
      
      
      
   <tr>
    
      <td>
      
          <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">관람일</span>
  <input type="date" class="form-control" placeholder="수량" name="data" aria-label="Username" aria-describedby="addon-wrapping">
</div>
      
      
      </td>


  </tbody>
</table>

     <button type="button" class="btn btn-primary btn-lg" onclick="check();">예약하기</button>
<button type="button" class="btn btn-secondary btn-lg" onclick="location.href='<%=request.getContextPath()%>/'">취소하기</button><br>


</form>

</div>

</section>
                    
</body>
</html>