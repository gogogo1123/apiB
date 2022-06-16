<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link rel="stylesheet" href="style1.css">

<script src="http://code.jquery.com/jquery-3.6.0.min.js"></script>







</head>
<body>
<section  class="logo-form">
<h1>회 원 가 입</h1>
<form action="<%=request.getContextPath()%>/member/memberJoinAction.do" method="post">
  <div class="int-area">
  <input type="text" name="memberId" id="id" class="input_id" autocomplete="off" required>
  <label for="id">User Id</label>  <!-- font check id 가 먼저실행됨.. -->
    <font id="checkId" size="2"></font>
  </div>
    <div class="int-area">
  <input type="password" name="memberPwd" id="pw" autocomplete="off" required>
   <label for="pw">PassWord</label>
  </div>
   <div class="int-area">
  <input type="text" name="memberName" id="name" autocomplete="off" required>
   <label for="name">User Name</label>
   </div>
    <div class="int-area">
  <input type="email" name="memberEmail" id="email" autocomplete="off" required>
   <label for="email">E-mail</label>
     </div>
     
     
     
     
     
     <div class="int-area">
  <input type="text" name="phone" id="phone"  autocomplete="off" required>
   <label for="email">휴대폰번호</label>
    <div class="btn-area">
    <input type="button" id="btn"  onclick="sendSms();" value="전송">
  </div>
     </div>
       <div class="int-area">
  <input type="text" name="sms" id="sms"  autocomplete="off" required>
   <label for="email">인증번호</label>
    <div class="btn-area">
    <input id="d"  type="button" value="전송" onclick="sendSms1()" >
  </div>
     </div>
     
     <!-- ------------------------------------------------------------------- -->   
     
         <script>
      function sendSms() {
    	  var code = "";
        $.ajax({
          url: "<%=request.getContextPath()%>/member/membersns.do",
          data: {
            phone: $("#phone").val()
          },
          type: "post",
          success: function(result) {
            if (result == "re") {
            	 alert("인증번호 전송 완료");
            	 code=result;
            } else {
              alert("인증번호 전송 실패");
            }
          }
        });
      }

   
      function sendSms1() {
    	  var code = "";
        $.ajax({
      
          success: function(result) {
            if ($("sms").val() == code) {
            	 alert("인증번호 전송 완료");
            	
            } else {
              alert("인증번호 전송 실패");
            }
          }
        });
      }

      
      
    </script>


<!-- ------------------------------------------------------------------- -->   
     
     
     
     
     <div class="int-area1">
   <input class="radio1" type="radio" name="memberGender" id="gender" value="M" autocomplete="off" required>
   <label class="label1" for="gender">남자</label>
    <input class="radio1" type="radio" name="memberGender" id="gender" value="W" autocomplete="off" required>
   <label class="label1" for="gender">여자</label>
   </div>
 

  <div class="btn-area">
    <button id="btn"  type="submit">회원가입</button>
  </div>
</form>
<div class="caption">
<a href="<%=request.getContextPath()%>/member/memberFindByid.do">forgot userid?</a><br>
<a href="<%=request.getContextPath()%>/member/memberFindBypwd.do">forgot password?</a>
</div>
</section>




<!-- java script -->









<script>




let id = $('#id');
let pw = $('#pw');
let btn = $('#btn');
let name = $('#name');
let email = $('#email');
let gender = $('gender');


$(btn).on('click',function(){
	if($(id).val()==""){
		$(id).next("label").addClass("warning");
		setTimeout(function(){
			$('label').removeClass('warning');
			
		},1500);
	}
	else if($(pw).val()==""){
		$(pw).next("label").addClass("warning");
		setTimeout(function(){
			$('label').removeClass('warning');	
		},1500);
		
	}	else if($(name).val()==""){
		$(name).next("label").addClass("warning");
		setTimeout(function(){
			$('label').removeClass('warning');	
		},1500);
	}	else if($(email).val()==""){
		$(email).next("label").addClass("warning");
		setTimeout(function(){
			$('label').removeClass('warning');	
		},1500);
	}	else if($(gender).val()==""){
		$(gender).next("label").addClass("warning");
		setTimeout(function(){
			$('label').removeClass('warning');	
		},1500);
	}
	  
	  
	  
	  
		
	
	
	
});




</script>

<script>

$('.input_id').focusout(function(){
	let userId = $('.input_id').val(); // input_id 에 입력되는 값
	
	$.ajax({
		url:"<%=request.getContextPath()%>/member/idcheck.do",
		type:"post",
		data:{userId:userId},
		dataType:'json',
		success:function(result){
			if(result==0){
				$("#checkId").html('사용할수 없는 아이디입니다');
				$("#checkId").attr('color','red');
			}else{
				$("#checkId").html('사용할수 있는 아이디입니다');
				$("#checkId").attr('color','<span>green</span>');
			}
		},
		error : function(){
			alert("서버요청실패");
		}
	})
	
})



</script>



</body>
</html>