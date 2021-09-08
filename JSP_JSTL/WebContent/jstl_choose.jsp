<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:choose>
	<c:when test="${param.id eq 'mimi'}">
		<script>
			alert('mimi 환영합니다');
		</script>
	</c:when>
	<c:when test="${param.id eq 'mama'}">
		<script>
			alert('mama 환영합니다');
		</script>
	</c:when>
	<c:otherwise>
		<script>
			alert('넌누구니');
		</script>
	</c:otherwise>
</c:choose>



<%-- <%
	String param = request.getParameter("id");
	if(param.equals("mimi")){
%>
	<script>
		alert('mimi 환영합니다');
	</script>
<%
	}else if(param.equals("mama")){
%>
	<script>
		alert('mama 환영합니다');
	</script>	
<%
	}else{
%>
	<script>
		alert('넌 누구냐');
	</script>	
<%		
	}
%> --%>