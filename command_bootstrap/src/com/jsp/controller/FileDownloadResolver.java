package com.jsp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jsp.util.MakeFileName;

public class FileDownloadResolver {

	public static void sendFile(String fileName, String savedPath, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String filePath = savedPath + File.separator + fileName;

		File downloadFile = new File(filePath);
		FileInputStream inStrean = new FileInputStream(downloadFile);

		ServletContext context = request.getServletContext();
		String mimeType = context.getMimeType(filePath);
		if (mimeType == null) {
			mimeType = "application/octet-stream";
		}

		response.setContentType(mimeType);
		response.setContentLengthLong((int) downloadFile.length());

		String headerKey = "Content-Disposition";

		String sendFileName = MakeFileName.parseFileNameFromUUID(downloadFile.getName(), "\\$\\$");

		String header = request.getHeader("User-Agent");
		if (header.contains("MSIE")) {
			URLEncoder.encode(sendFileName, "UTF-8");
		} else {
			sendFileName = new String(sendFileName.getBytes("utf-8"), "ISO-8859-1");
		}

		String headerValue = String.format("attachment; filename=\"%s\"", sendFileName);
		response.setHeader(headerKey, headerValue);

		OutputStream outStream = response.getOutputStream();

		byte[] buffer = new byte[4096];
		int byteRead = -1;

		try {

			while ((byteRead = inStrean.read(buffer)) != -1) {
				outStream.write(buffer, 0, byteRead);
			}
		} finally {
			outStream.close();
			inStrean.close();
		}
	}
}
