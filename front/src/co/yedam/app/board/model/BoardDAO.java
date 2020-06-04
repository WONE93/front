package co.yedam.app.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import co.yedam.app.common.ConnectionManager;
import co.yedam.app.member.model.MemberDAO;


public class BoardDAO {
	
	//싱글톤
	private static BoardDAO instance = new BoardDAO();
	public static BoardDAO getInstance() {
		return instance;
	}
	
	// 등록
	public int boardInsert(Connection conn, BoardVO board) {
		int r = 0;
		PreparedStatement psmt = null;

		try {
			// 2. sql구문 준비
			String sql = "insert into board (seq , title , contents ,  regdt , id )"
					+ " values ( seq_board.nextval, ?, ?, sysdate, ?)";

			psmt = conn.prepareStatement(sql);

			// 3. 실행
//			psmt.setString(1, member.getId());
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContents());
			psmt.setString(3, board.getId());

			r = psmt.executeUpdate();

			// 4. 결과처리
			System.out.println(r + " 건이 등록됨.");

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return r;
	}

	// 전체조회
	public ArrayList<BoardVO> getBoardList(Connection conn) {
		ArrayList<BoardVO> list = new ArrayList<BoardVO>(); // 1.어레이리스트에 담기	
		PreparedStatement psmt = null;
		try {
			// 2. 쿼리 준비
			String sql = "select board.*, member.name from board join member on(board.id = member.id) order by seq desc";
			psmt = conn.prepareStatement(sql);
			// 3. statement 실행
//			psmt.setString(1, id); // 첫번재 물음표 값이 id다  // 3. 단건에서의 ? 빠졌으니 set도 필요없음
			ResultSet rs = psmt.executeQuery();
			while (rs.next()) {
				BoardVO vo = new BoardVO(); // 4. 위치 while 안으로
				vo.setSeq(rs.getString("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdt(rs.getString("regdt"));
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));// 결과값에 담기
				list.add(vo); // 5.리스트에 담기
			}
			// 4. 결과 저장
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	// 단건조회
	public BoardVO getBoard(Connection conn, String id) {
		BoardVO vo = new BoardVO();
		PreparedStatement psmt = null;
		try {
			// 2. 쿼리 준비
			String sql = "select * from board where id=?";
			psmt = conn.prepareStatement(sql);
			// 3. statement 실행
			psmt.setString(1, id); // 첫번재 물음표 값이 id다
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				vo.setSeq(rs.getString("seq"));
				vo.setTitle(rs.getString("title"));
				vo.setContents(rs.getString("contents"));
				vo.setRegdt(rs.getString("regdt"));
				vo.setId(rs.getString("id"));// 결과값에 담기
			}
			// 4. 결과 저장
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// 5. 연결해제
			ConnectionManager.close(conn);
		}
		return vo;
	}

	// 수정
	public int boardUpdate(BoardVO board) {
		int r = 0;

		PreparedStatement psmt = null;

		try {

			// 2. sql구문 준비
			String sql = "update board set title=?, contents=? " + " where seq=?";

//			psmt = conn.prepareStatement(sql);

			// 3. 실행
			psmt.setString(1, board.getTitle());
			psmt.setString(2, board.getContents());
			psmt.setString(3, board.getSeq());

			r = psmt.executeUpdate();

			// 4. 결과처리
			System.out.println(r + " 건이 등록됨.");

		} catch (Exception e) {
			e.printStackTrace();
		} 
		return r;
	}
	
	//삭제
	public void boardDelete (BoardVO board) {
		Connection conn = null;
		PreparedStatement psmt = null;

		try {
			// 1. DB 연결
			conn = ConnectionManager.getConnnect();

			// 2. sql구문 준비
			String sql = "delete from board where seq= ?";

			psmt = conn.prepareStatement(sql);

			// 3. 실행
			psmt.setString(1, board.getSeq());

			psmt.execute();

			// 4. 결과처리
			

		} catch (Exception e) {
			e.printStackTrace();
		} 

	}

}
