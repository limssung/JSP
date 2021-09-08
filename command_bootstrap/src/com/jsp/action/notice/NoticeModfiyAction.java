package com.jsp.action.notice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.XSSResolver;
import com.jsp.dto.NoticeVO;
import com.jsp.service.NoticeService;

public class NoticeModfiyAction implements Action{
	private NoticeService noticeService;
	
	public void setNoticeService(NoticeService noticeService) {
		this.noticeService = noticeService;
	}


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "redirect:notice/detail.do?from=modify&nno="+request.getParameter("nno");

		XSSResolver.parseXSS(request);
		
		int nno = Integer.parseInt(request.getParameter("nno"));
		String title = (String) request.getAttribute("XSStitle");
		String content = request.getParameter("content");
		
		NoticeVO notice = new NoticeVO();
		
		notice.setNno(nno);
		notice.setTitle(title);
		notice.setContent(content);
		
		noticeService.modify(notice);
		
		return url;
	}
}
