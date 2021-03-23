$(document).ready(function(){
	$("#loginBtn").click(function(){
		alert('이걸 왜 이렇게 힘들게 하는거지...일단 이건 js로 출력하는거임.');
		$.post("home",
				{},
				function(data, status){
					console.log(data);
					$("#msgDiv").html(data);
				});
	})
})