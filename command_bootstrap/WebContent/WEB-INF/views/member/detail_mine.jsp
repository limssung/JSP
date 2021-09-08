<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
</head>
<body>
<br>
<br>
<div class="input-form col-md-12 mx-auto">
	<h4 class="mb-3">${member.name }님의 상세정보</h4>
	<div class="row">
		<div class="col-md-3 mb-3">
			<label for="name">아이디 : ${member.id }</label> 
			<div class="invalid-feedback"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3 mb-3">
			<label for="name">패스워드 : ${member.pwd }</label> 
			<div class="invalid-feedback"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3 mb-3">
			<label for="name">이름 : ${member.name }</label>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3 mb-3">
			<label for="name">이메일 : ${member.email }</label>
			<div class="invalid-feedback"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3 mb-3">
			<label for="name">주소 : ${member.address }</label> 
			<div class="invalid-feedback"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-3 mb-3">
			<label for="name">전화번호 : 
				<c:if test="${member.phone.length() eq 11 }">
					${member.phone.substring(0,3) }-${member.phone.substring(3,7) }-${member.phone.substring(7) }
				</c:if>
				<c:if test="${member.phone.length() ne 11 }">
					${member.phone}
				</c:if>
			</label> 
			<div class="invalid-feedback"></div>
		</div>
	</div>
	<div class="row">
		<div class="col-sm-2 mb-3">
			<button class="btn btn-primary btn-sm btn-block" type="button" onclick="javascript:memberDelete('${member.id}');">회원 삭제</button>
		</div>                                        
		<div class="col-sm-2 mb-3">                        
			<button class="btn btn-primary btn-sm btn-block" type="button" onclick="window.close();">닫기</button>
		</div>
	</div>
</div>
<script>
function memberDelete(id){
	$.ajax({
		url : "delete.do",
		data : {"id" : id},
		success : function(data) {
			window.close();
			alert('회원 삭제 성공');
			window.opener.location.href='/member/list.do';
			
		}
	})
		
}
</script>

</body>
</html>