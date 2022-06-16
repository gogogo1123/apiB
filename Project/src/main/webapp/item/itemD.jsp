<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>


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
border-radius: 50%;
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
	  
	  
	  if (fm.itemQuantity.value==""){
	  		alert("수량을 적어주세요");
	  		fm.itemQuantity.focus();
	  		return;
	  }
	  		
	
	         alert("구매완료됬습니다.");
	  		fm.action = "<%=request.getContextPath()%>/item/itemAction.do";
	  		fm.method = "post";
	  		fm.submit();  
  			
	
	   return;
	 
	  }  
</script>








</head>
<body onload="init();">

<script language="JavaScript">

var sell_price;
var itemQuantity;

function init () {
	sell_price = document.form.sell_price.value;
	itemQuantity = document.form.itemQuantity.value;
	document.form.sum.value = sell_price;
	change();
}

function add () {
	hm = document.form.itemQuantity;
	sum = document.form.sum;
	hm.value ++ ;

	sum.value = parseInt(hm.value) * sell_price;
}

function del () {
	hm = document.form.itemQuantity;
	sum = document.form.sum;
		if (hm.value > 1) {
			hm.value -- ;
			sum.value = parseInt(hm.value) * sell_price;
		}
}

function change () {
	hm = document.form.itemQuantity;
	sum = document.form.sum;

		if (hm.value < 0) {
			hm.value = 0;
		}
	sum.value = parseInt(hm.value) * sell_price;
}  

</script>



<section id="center">




<div class="center_image">

    <img class="center_image_a" src="../image/d.jpg" alt="..."  width="600px" height="600px"/>

</div>
<div class="b">

<form action="<%=request.getContextPath()%>/item/itemAction.do" method="get" name="form">

<table class="table" style="width: 700px;">
  <thead>
    <tr style="margin-bottom: 20px;">
      
      <th scope="col" style="text-align: center; font-size: 30px; margin-bottom: 30px;">구매하기</th>
    

    </tr>
  </thead>
  <tbody>
  

  
  
    <tr>
   
      <td>
      
    <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">제품명</span>
  <input type="text" class="form-control" placeholder="Username" aria-label="Username" name="itemName" aria-describedby="addon-wrapping" readonly="readonly" value="전갈자리 스티커">
</div>








      
      </td>
   
    </tr>
    
    
    <tr>
   
      <td>
      
    <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">가격</span>
  <input type="text" class="form-control"  name="sell_price" aria-describedby="addon-wrapping" readonly="readonly" value="10000">
</div>
      
      </td>
   
    </tr>
    
    <tr>
    
      <td>
      

         <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">수량</span>
  <input type="text" class="form-control" placeholder="수량" value="1" name="itemQuantity"  aria-describedby="addon-wrapping">
  <input class="btn btn-primary btn-lg" type="button" value="+" onclick="add();"> 
    <input class="btn btn-danger btn-lg" type="button" value="-" onclick="del();"> 
    <Br>
</div>
      

   <tr>
   
      <td>
      
    <div class="input-group flex-nowrap">
  <span class="input-group-text" id="addon-wrapping">총금액</span>
  <input type="text" class="form-control"  name="sum" aria-describedby="addon-wrapping" readonly="readonly">
</div>
      
      </td>
   
    </tr>
      
      
    
 
    

  </tbody>
</table>

     <button type="button" class="btn btn-primary btn-lg" onclick="check();">구매하기</button>
<button type="button" class="btn btn-secondary btn-lg" onclick="location.href='<%=request.getContextPath()%>/'">돌아가기</button><br>
<div class="d-grid gap-1 col-10">
  <button class="btn btn-primary btn-lg" type="button" style="display:flex;justify-content: center;margin-right:110px;margin-top: 30px; " >Button</button>
</div>
</form>

</div>

</section>
                    
</body>
</html>