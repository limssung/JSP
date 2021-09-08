<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<script>
	alert("로그인 성공하셨습니다")
	location.href = "<%=request.getContextPath()%>/main.jsp";
</script>