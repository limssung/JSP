package com.jsp.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JSONResolver {
	public static void view(HttpServletResponse response, Object target) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();

		// 리스폰스에 대한 세팅을 먼저 해줘야함 (Content Type 결정)
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();

		// 내보내기
		out.println(mapper.writeValueAsString(target));

		// out 객체를 종료하고 환원.
		out.close();
	}
}
