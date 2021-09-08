package com.jsp.action.pds;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.request.SearchCriteria;
import com.jsp.service.PdsService;
import com.jsp.util.ExceptionLoggerHelper;

public class PdsListAction implements Action{
	private PdsService pdsService;
	
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "pds/list";

		String page = request.getParameter("page");
		String perPageNum = request.getParameter("perPageNum");
		String searchType = request.getParameter("searchType");
		String keyword = request.getParameter("keyword");
		
		SearchCriteria cri = new SearchCriteria();
		
		cri.setPage(page);
		cri.setPerPageNum(perPageNum);
		cri.setSearchType(searchType);
		cri.setKeyword(keyword);
		
		
		try {
			
		Map<String, Object> dataMap = pdsService.getList(cri);
		request.setAttribute("dataMap", dataMap);
		
		}catch (Exception e) {
			e.printStackTrace();
			ExceptionLoggerHelper.write(request, e, pdsService);
			throw e;
		}
		return url;
	}

}
