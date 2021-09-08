package com.jsp.action.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.action.Action;
import com.jsp.controller.FileDownloadResolver;
import com.jsp.util.GetUploadPath;

public class SummernoteDownloadImgAction implements Action{
	
	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = null;
		
		String fileName = request.getParameter("fileName");
		
		String savedPath = GetUploadPath.getUploadPath("summernote.img");
		
		FileDownloadResolver.sendFile(fileName, savedPath, request, response);
		
		return null;
	}
}
