package com.jsp.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.exception.InvalidPasswordException;
import com.jsp.exception.NotFoundIdException;
import com.jsp.service.MemberServiceImpl;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MemberServiceImpl memberService = new MemberServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/loginForm.jsp").forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String url = "/loginSuccess.jsp";
		String message = "";
		//입력
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		
		//처리
		try {
			memberService.login(id,pwd);
		} catch (InvalidPasswordException | NotFoundIdException e) {
			request.setAttribute("message", e.getMessage());
			url = "/loginFail.jsp";
		}
		
		request.getRequestDispatcher(url).forward(request, response);
		
		//출력
		/*
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('"+message+"')");
		out.println("location.href='login';");
		out.println("</script>");
		response.sendRedirect(request.getContextPath()+"/login");
		*/
	}
	

}
