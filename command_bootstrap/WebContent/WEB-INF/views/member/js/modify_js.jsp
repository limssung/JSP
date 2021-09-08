<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<script>
window.onload=function(){
	MemberPictureThumb($('#pictureView')[0], '${member.picture}', '<%=request.getContextPath()%>');
}

function changePicture_go(){
	var picture = $('#inputFile')[0];
	
	var fileFormat = picture.value.substr(picture.value.lastIndexOf(".")+1).toUpperCase();
	
	if(!(fileFormat == "JPG") || fileFormat == "JPEG"){
		alert("이미지는 jpg/jpeg 형식만 가능합니다");
		picture.value ="";
		return;
	}
	
	
	if(picture.files[0].size > 1024*1024*1){
		alert("사진 용량은 1MB 이하만 가능합니다");
		return;
	}
	
	document.getElementById('inputFileName').value = picture.files[0].name;
	
	if(picture.files && picture.files[0]){
		var reader = new FileReader();
		
		reader.onload = function(e) {
		     $('div#pictureView')
             .css({'background-image':'url('+e.target.result+')',
                'background-position':'center',
                'background-size':'cover',
                'background-repeat':'no-repeat'
                });
		}
		
		$('input[name="uploadPicture"]').val(picture.files[0].name);
		
		reader.readAsDataURL(picture.files[0]);
	}
		
}
function modify_go(){
	var form = $('form[role="form"]');
	form.submit();
}
</script> 