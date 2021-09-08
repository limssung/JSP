package com.jsp.action.member;

import java.io.File;
import java.io.FileInputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;

import com.jsp.action.Action;
import com.jsp.controller.FileUploadResolver;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.GetUploadPath;
import com.jsp.util.MakeFileName;
import com.jsp.util.MultipartHttpServletRequestParser;

public class MemberModifyAction implements Action {
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 업로드 파일 환경 설정
	private static final int MEMORY_THRESHOLD = 1024 * 500; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 1; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 2; // 200MB
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/modify_success";
		
		MultipartHttpServletRequestParser multi = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		
		String id = multi.getParameter("id");
		String pwd = multi.getParameter("pwd");
		String name = multi.getParameter("name");
		String authority = multi.getParameter("authority");
		String email = multi.getParameter("email");
		String phone = multi.getParameter("phone");

		MemberVO member = new MemberVO();

		member.setId(id);
		member.setPwd(pwd);
		member.setEmail(email);
		member.setPhone(phone);
		member.setName(name);
		member.setAuthority(authority);
		
		//file 처리
		String uploadPicture = multi.getParameter("uploadPicture");
		if(uploadPicture!=null&&!uploadPicture.isEmpty()) {
			
			String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
			File file = new File(uploadPath);
			if(!file.mkdirs()) {
				System.out.println(uploadPath + "가 이미 존재합니다");
			}
			
			File deleteFile = new File(uploadPath, multi.getParameter("oldPicture"));
			if(file.exists()) {
				file.delete();
			}
			
			List<File> fileList = FileUploadResolver.fileUpload(multi.getFileItems("picture"), uploadPath);
			File saveFile = fileList.get(0);
			
			member.setPicture(saveFile.getName());
			
		}else {
			member.setPicture(multi.getParameter("oldPicture"));
		}
		
		//db처리
		memberService.modify(member);
		request.setAttribute("member", member);
		
		request.setAttribute("parentReload", false);
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(member.getId().equals(loginUser.getId())) {
			request.setAttribute("parentReload", true);
			session.setAttribute("loginUser", memberService.getMember(member.getId()));
		}
		
		return url;
	}
}
