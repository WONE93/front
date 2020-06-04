package co.yedam.app.member.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import co.yedam.app.common.ConnectionManager;

public class MemberService {

	// 싱글톤
	private static MemberService instance = new MemberService();

	public static MemberService getInstance() {
		return instance;
	}

	// 회원가입
	public void memberInsert(MemberVO member) {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnnect();
			conn.setAutoCommit(false); // false 설정안하면 디폴트는 true임
			// 회원테이블 등록
			MemberDAO.getInstance().memberInsert(conn, member);
			// 로그인 테이블에 등록

			// 권한테이블에 등록

			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback(); // 롤백도 예외처리 해줘야함
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			ConnectionManager.close(conn);

		}
	}
	
	//리스트
	public ArrayList<MemberVO> getMemberList() {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnnect();
			return MemberDAO.getInstance().getMemberList(conn);
		} finally {
			ConnectionManager.close(conn);
		}
	}
	
	//단건조회
	public MemberVO getMember(String id) {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnnect();
			return MemberDAO.getInstance().getMember(conn, id);
		} finally {
			ConnectionManager.close(conn);
		}
	}
	
	//수정
	public int memberUpdate(MemberVO member) {
		Connection conn = null;	
		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();
			return MemberDAO.getInstance().memberUpdate(conn, member);
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}
	
	}

	
}
