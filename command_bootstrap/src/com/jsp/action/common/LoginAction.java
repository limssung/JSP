package com.jsp.action.common;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jsp.action.Action;
import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIDException;
import com.jsp.service.MemberService;

public class LoginAction implements Action {
	private MemberService memberSerivce;
	
	public void setMemberSerivce(MemberService memberSerivce) {
		this.memberSerivce = memberSerivce;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:index.do";
		
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		try {
			memberSerivce.login(id, pwd);
			
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", memberSerivce.getMember(id));
			session.setMaxInactiveInterval(6*60);
	
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} catch (InvalidPasswordException | NotFoundIDException e) {
			request.setAttribute("message", e.getMessage());
			url = "common/loginFail";
		}
		
		return url;
	}

}
