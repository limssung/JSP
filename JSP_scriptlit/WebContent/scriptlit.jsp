<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
	<%
	for(int dan = 2; dan < 10; dan++){
// 		System.out.println(dan + "단 입니다.");
	%>
	
	<%-- <% out.println("<h3>"+dan + "단 입니다.</h3>");%> --%>
	<%--<h3> <% out.println(dan); %>단 입니다. </h3> --%>
	<h3> <%=dan %> 단 입니다</h3>
	<%
		for(int gop = 1; gop < 10; gop++){
// 			System.out.println(dan +" * "+ gop +" = " + dan*gop);
	%>
	
	<%--<%	out.println("<p>"+dan +" * "+ gop +" = " + dan*gop+"</p>");%> --%>
			<p><%=dan %> * <%=gop %> = <%=dan*gop %></p>
	<%
		}
	// 		System.out.println();
	%>

	<%--<%		out.println("<br/>");%> --%>
	<br/>		

	<%
	}
	%>

</body>
</html>