package com.jsp.action.member;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.dto.MemberVO;
import com.jsp.service.MemberService;
import com.jsp.util.GetUploadPath;

public class MemberRemoveAction implements Action{
	private MemberService memeberService;
	
	public void setMemeberService(MemberService memeberService) {
		this.memeberService = memeberService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "member/remove_success";
		
		String id = request.getParameter("id");
		
		MemberVO member = memeberService.getMember(id);
		
		String uploadPath = GetUploadPath.getUploadPath("member.picture.upload");
		String fileName = member.getPicture();
		
		File file = new File(uploadPath, fileName);
		
		if(file.exists()) {
			file.delete();
		}
		
		memeberService.remove(id);
		
		HttpSession session = request.getSession();
		MemberVO loginUser = (MemberVO) session.getAttribute("loginUser");
		
		if(loginUser.getId().equals(member.getId())) {
			session.invalidate();
		}
		
		return url;
	}
}
