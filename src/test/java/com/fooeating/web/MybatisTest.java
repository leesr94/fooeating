package com.fooeating.web;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fooeating.persistence.MemberDAO;
import com.fooeating.persistence.MemberDAOImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class MybatisTest {
	// DB 연결 (MyBatis 사용) -> SqlSessionFactory 객체
	
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	// 객체 의존 주입
	@Inject
	private SqlSessionFactory sqlFactory;
	
	// DAO 객체 의존 주입 (root-context.xml 안에 있음!)
	@Inject
	private MemberDAO mdao;
//	 위 코드는 MemberDAO dao = new MemberDAOImpl(); 와 같은 코드
	
	@Test
	public void DB시간정보조회() throws Exception {			// Test용이니까 일단 한글로 메서드명 해도 ok
		// DB 연결 + SQL 실행
		// SqlSession sqlSession = sqlFactory.openSession();
		// String time = sqlSession.selectOne("com.itwillbs.mapper.MemberMapper.getTime");
		//	=> 해당 작업은 DAOImpl에서 하고 있기 때문에 DAO를 생성하여 불러오기
		String time = mdao.getTime();
		logger.debug("DB 시간정보 : " + time);
	}
}