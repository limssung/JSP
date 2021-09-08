package com.jsp.datasource;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.jsp.dao.MenuDAO;
import com.jsp.dao.MockMenuDAOImpl;
import com.jsp.dto.MenuVO;

public class TestMenuDAOImpl {
	private SqlSessionFactory sqlSessionFactory = new OracleMybatisSqlSessionFactory();
	private SqlSession session;
	private MenuDAO menuDAO;

	@Before
	public void init() {
		session = sqlSessionFactory.openSession(false);
		menuDAO = new MockMenuDAOImpl();
	}

	@Test
	public void testSelectMainMenu() throws SQLException {
		List<MenuVO> menuList = new ArrayList<>();

		MenuVO vo = new MenuVO();

		vo.setMcode("M000000");
		vo.setMname("메인화면");
		vo.setUpcode("M000000");
		menuList.add(vo);

		List<MenuVO> list = menuDAO.selectMainMenu(session);

		Assert.assertEquals(menuList.get(0).getMcode(), list.get(0).getMcode());
	}

	@Test
	public void testSelectSubMenu() throws SQLException {

		String mCode = "M010000";
		List<MenuVO> list = menuDAO.selectSubMenu(session, mCode);

		Assert.assertEquals(list.get(0).getMcode(), mCode);
	}

	@Test
	public void testSelectMenuByMcode() throws SQLException {
		String mCode = "M010000";
		MenuVO vo = menuDAO.selectMenuByMcode(session, mCode);

		Assert.assertEquals(mCode, vo.getMcode());
	}

	@Test
	public void testSelectMenuByMname() throws SQLException {
		String mName = "로그인화면";
		MenuVO vo = menuDAO.selectMenuByMname(session, mName);

		Assert.assertEquals(mName, vo.getMname());
	}

	@After
	public void complete() {
		session.rollback();
		session.close();
	}
}
