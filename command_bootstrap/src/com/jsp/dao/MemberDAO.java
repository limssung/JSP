package com.jsp.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dto.MemberVO;
import com.jsp.request.Criteria;
import com.jsp.request.SearchCriteria;

public interface MemberDAO {
	
	//회원정보 조회
	MemberVO selectMemberById(SqlSession session, String id) throws SQLException;
	
	List<MemberVO> selectMemberList(SqlSession session) throws SQLException;
	List<MemberVO> selectMemberList(SqlSession session, Criteria cri) throws SQLException;
	List<MemberVO> selectMemberList(SqlSession session, SearchCriteria cri) throws SQLException;
	
	int selectSearchMemberListCount(SqlSession session, SearchCriteria cri) throws SQLException;
	
	public void insertMember(SqlSession session, MemberVO member) throws SQLException;
	
	public void updateMember(SqlSession session, MemberVO member) throws SQLException;
	
	void deleteMember(SqlSession session, String id) throws SQLException;
	
	void disabledMember(SqlSession session, String id) throws SQLException;
	
	void enabledMember(SqlSession session, String id) throws SQLException;
}
