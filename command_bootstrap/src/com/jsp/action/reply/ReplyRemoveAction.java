package com.jsp.action.reply;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsp.action.Action;
import com.jsp.controller.JSONResolver;
import com.jsp.request.PageMaker;
import com.jsp.request.ReplyRegistCommand;
import com.jsp.request.SearchCriteria;
import com.jsp.service.ReplyService;
import com.sun.corba.se.spi.activation.Repository;

public class ReplyRemoveAction implements Action{
	private ReplyService replyService;
	
	public void setReplyService(ReplyService replyService) {
		this.replyService = replyService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		int rno = Integer.parseInt(request.getParameter("rno"));
		int bno = Integer.parseInt(request.getParameter("bno"));
		int page = Integer.parseInt(request.getParameter("page"));
		
		try {
			replyService.removeReply(rno);

			PageMaker pageMaker = new PageMaker();
			
			pageMaker.setCri(new SearchCriteria());
			pageMaker.setTotalCount(replyService.getReplyListCount(bno));
			
			int realEndPage = pageMaker.getRealEndPage();

			if(page > realEndPage) {
				page = realEndPage;
			}

			PrintWriter out = response.getWriter();
			
			out.print(page);
			out.close();
			
		}catch (SQLException e) {
			response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

}
