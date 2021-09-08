package com.jsp.action.pds;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.FileDownloadResolver;
import com.jsp.dto.AttachVO;
import com.jsp.service.PdsService;
import com.jsp.util.GetUploadPath;

public class PdsGetFileAction implements Action{
	
	private PdsService pdsService;
	
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}


	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		int ano = Integer.parseInt(request.getParameter("ano"));
		
		AttachVO attach = pdsService.getAttachByAno(ano);
		
		String savedPath = GetUploadPath.getUploadPath("pds.upload");
		
		FileDownloadResolver.sendFile(attach.getFileName(), savedPath, request, response);
		
		return null;
	}

}
