package com.jsp.action.pds;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.ibatis.annotations.Update;

import com.jsp.action.Action;
import com.jsp.action.attach.AttachService;
import com.jsp.controller.FileUploadResolver;
import com.jsp.dto.AttachVO;
import com.jsp.dto.PdsVO;
import com.jsp.service.PdsService;
import com.jsp.util.GetUploadPath;
import com.jsp.util.MultipartHttpServletRequestParser;

public class PdsModifyAction implements Action{
	
	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 200; // 200MB
	
	private PdsService pdsService;
	
	public void setPdsService(PdsService pdsService) {
		this.pdsService = pdsService;
	}

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String url = "pds/modify_success";
		
		PdsVO pds = modifyFile(request, response);
		pdsService.modify(pds);
		
		request.setAttribute("pds", pds);
		
		return url;
	}

	
	private PdsVO modifyFile(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, Exception {
		PdsVO pds = new PdsVO();
		
		MultipartHttpServletRequestParser multi = new MultipartHttpServletRequestParser(request, MEMORY_THRESHOLD, MAX_FILE_SIZE, MAX_REQUEST_SIZE);
		String[] removeFiles = multi.getParameterValues("deleteFile");
		
		
		if(removeFiles != null) {
			for (String removeAno : removeFiles) {
				int ano = Integer.parseInt(removeAno);
				AttachVO attach = pdsService.getAttachByAno(ano);
				
				String filePath = attach.getUploadPath()+File.separator+ attach.getFileName();
				
				File target = new File(filePath);
				
				if(target.exists()) {
					target.delete();
				}
				pdsService.removeAttachByAno(ano);
			}
		}
		
		
		String uploadPath = GetUploadPath.getUploadPath("pds.upload");
		
		FileItem[] fileItems = multi.getFileItems("uploadFile");
		
		List<File> fileList = FileUploadResolver.fileUpload(fileItems, uploadPath);

		ArrayList<AttachVO> attachList = new ArrayList<AttachVO>();
		
		if(fileList != null && fileList.size()>0) for (File file : fileList) {
			AttachVO attach = new AttachVO();
			
			String fileName = file.getName();
			
			attach.setUploadPath(uploadPath);
			attach.setFileName(fileName);
			attach.setFileType(fileName.substring(fileName.lastIndexOf(".")+1));

			attachList.add(attach);
		}
		
		pds.setPno(Integer.parseInt(multi.getParameter("pno")));
		pds.setTitle(multi.getParameter("title"));
		pds.setContent(multi.getParameter("content"));
		pds.setWriter(multi.getParameter("writer"));
		pds.setAttachList(attachList);
		
		return pds;
	}
}