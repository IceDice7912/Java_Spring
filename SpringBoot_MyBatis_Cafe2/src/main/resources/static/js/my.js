$(document).ready(function(){ 

$("#loginBtn").click(function(){//로그인 처리	 

 

var id=$("#id").val(); 

var pw=$("#pw").val(); 

 

//alert(id+":"+pw);		 

 

$.post("login.jes", 

  {			    

    id:id, 

    pw:pw 

  }, 

  function(data, status){	 

  	//alert(data);		     

$("#msgDiv").html(data);						    

  } 

);//end post()  

});//end 로그인 처리 
	

$("#memberInsertBtn").click(function(){//회원 가입 처리 

 

var name=$("#name").val(); 

var id=$("#id").val(); 

var pw=$("#pw").val(); 

 

//alert(name+":"+id+":"+pw); 

 

$.post("../memberInsert.jes", 

  { 

    name:name, 

    id:id, 

    pw:pw 

  }, 

  function(data, status){ 

    alert( data); 
    
    window.close();

  }); 

 

}); 

 

}); 

}
}