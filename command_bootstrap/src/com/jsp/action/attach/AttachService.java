package com.jsp.action.attach;

import java.util.List;

import com.jsp.dto.AttachVO;

public interface AttachService {
	List<AttachVO> getAttachList(int pno);
	
	void regist(AttachVO attach);
	
	void modify(AttachVO attach);
	
	void remove(AttachVO attach);
}
