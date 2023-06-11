package com.fooeating.persistence;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

// @Repository : 스프링(root-context.xml)에 해당 파일이 DAO의 동작을 하는 객체라고 등록하는 것

@Repository
public class MemberDAOImpl implements MemberDAO {
	// DB 연결										=> SqlSessionFactory 객체 주입
	// SQL 작성 & pstmt -> (물음표 있다면 채우기)
	// SQL 실행 -> (select구문이라면 데이터 처리)
	
	@Inject
	private SqlSession sqlSession;
//	private SqlSessionFactory sqlFactory;
	// -> 이 코드만으로 Test에서는 가능하지만, 여기는 Test가 아니므로 당장 객체 주입X (가져올 곳을 찾지X)
	// -> 클래스에 @Repository를 해줘야 객체 주입이 된다!
	
	// 네임스페이스 (매퍼 위치를 상수로 미리 작성)
	private static final String NAMESPACE = "com.fooeating.mapper.MemberMapper";
	
	// 로거 작성 (단축키 등록한 걸로)
	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImpl.class);
	
	@Override
	public String getTime() {
		String time = sqlSession.selectOne(NAMESPACE + ".getTime");
		// -> memberMapper.xml의 namespace 복사 + . + 해당 id, 리턴은 string으로 받겠다고 정의했기 때문에 리턴은 String이 된다.
		
		logger.debug("DB 시간정보 : " + time);
		return time;
	}
	
}