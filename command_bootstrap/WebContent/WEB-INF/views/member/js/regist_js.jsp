<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<script>
var formData = "";

function picture_go(){
	formData = new FormData($('form[role="imageForm"]')[0]);
	
	var form = $('form[role="imageForm"]');
	var picture = form.find('[name=pictureFile]')[0];
	
	
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
	
	//업로드 확인변수 초기화
	form.find('[name="checkUpload"]').val(0);
	
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
		reader.readAsDataURL(picture.files[0]);
	}
}

function upload_go(){
	if($('input[name="pictureFile"]').val()==""){
		alert('사진을 선택하세요');
		$('input[name="pictureFile"]').click();
		return;
	};
	
	if($('input[name="checkUpload"]').val()==1){
		alert('이미업로드 된 사진입니다.');
		return;
	}
	
	$.ajax({
		url : 'picture.do',
		data : formData,
		type : 'post',
		processData : false,
		contentType : false,
		success : function(data){
			$('input[name="checkUpload"]').val(1);
			
			$('input#oldFile').val(data);
			$('form[role="form"] input[name="picture"]').val(data);
			alert("사진이 업로드 되었습니다.")
		},
		
		error: function(error){
			alert('현재 사진 업로드가 불가합니다. \n 관리자에게 연락바랍니다');
		}
	})
}

function idCheck_go(){
	var input_ID=$('input[name="id"');
	
	if(input_ID.val() == ""){
		alert("아이디를 입력하시오");
		input_ID.focus();
		return;
	}else{
		
		var reqID = /^[a-z]{1}[a-zA-Z0-9]{3,12}$/;
		if(!reqID.test($('input[name="id"]').val())){
			alert('아이디는 첫글자는 영소문자이며, \n 4~13자의 영문자와 숫자로만 입력해야 합니다.');
			$('input[name="id"]').focus();
			return;
		}
	}
	
	$.ajax({
		url : "idCheck.do?id="+input_ID.val().trim(),
		method : 'get',
		success : function(result) {
			if(result == "duplicated"){
				alert('중복된 아이디 입니다');
				$('input[name="id"]').focus();
			}else{
				alert('사용가능한 아이디입니다');
				checkedID = input_ID.val().trim();
				$('input[name="id"]').val(checkedID);
			}
		},
		error : function(error) {
			alert('시스템장애로 가입이 불가합니다');
		}
	})
}

function regist_go(){
	var uploadCheck = $('input[name="checkUpload"]').val();
	if(uploadCheck == 0){
		alert('사진업로드는 필수입니다.');
		return;
	}
	
	if($('input[name="id"]').val() == ""){
		alert('아이디는 필수입니다.');
		return;
	}
	
	if($('input[name="id"]').val() != checkedID){
		alert('아이디는 중복확인이 필요합니다');
		return;
	}
	
	if($('input[name="pwd"]').val() == ""){
		alert('패스워드는 필수입니다.');
		return;
	}
	if($('input[name="name"]').val() == ""){
		alert('이름은 필수입니다.');
		return;
	}
	
	var form = $('form[role="form"]');
	form.submit();
	
}
</script>