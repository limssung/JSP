package com.jsp.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.jsp.dao.MenuDAO;
import com.jsp.dto.MenuVO;

public class MockMenuDAOImpl implements MenuDAO {
	
	@Override
	public List<MenuVO> selectMainMenu(SqlSession session) throws SQLException {
		List<MenuVO> menuList = new ArrayList<>();

		if(session != null) {
		MenuVO vo = new MenuVO();
		
		vo.setMcode("M000000");
		vo.setMname("메인화면");
		vo.setUpcode("M000000");
		menuList.add(vo);
		}
		
		return menuList;
	}

	@Override
	public List<MenuVO> selectSubMenu(SqlSession session, String mCode) throws SQLException {
		List<MenuVO> menuList = new ArrayList<>();
		
		if(mCode.equals("M010000")) {
			
		MenuVO vo = new MenuVO();
		vo.setMcode("M010000");
		vo.setMname("로그인화면");
		vo.setUpcode("M000000");
		menuList.add(vo);
		}

		return menuList;
	}

	@Override
	public MenuVO selectMenuByMcode(SqlSession session, String mCode) throws SQLException {
		MenuVO menu = new MenuVO();
			
		if(mCode.equals("M010000")) {
			menu.setMcode(mCode);
		}

		return menu;
	}

	@Override
	public MenuVO selectMenuByMname(SqlSession session, String mName) throws SQLException {
		MenuVO menu = new MenuVO();
		
		if(mName.equals("로그인화면")) {
			menu.setMname(mName);
		}
		return menu;
	}

}
