<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<body>
	<script>
		alert('회원가입 완료');
		
		window.onload=function(){
			$.ajax({
				url:"<%=request.getContextPath()%>/getMcode.do?mName=회원목록",
				type : 'get',
				success : function(menu){
					console.log(menu);
					window.opener.parent.location.href="/index.do?mCode"+menu.mcode;
// 					window.close();
				}
			});
		}
		
	</script>
</body>