package co.yedam.app.board.model;

import java.sql.Connection;
import java.util.ArrayList;

import co.yedam.app.common.ConnectionManager;


public class BoardService {
	
	// 싱글톤
	private static BoardService instance = new BoardService();

	public static BoardService getInstance() {
		return instance;
	}
	
	//리스트
	public ArrayList<BoardVO> getBoardList() {
		Connection conn = null;
		try {
			conn = ConnectionManager.getConnnect();
			return BoardDAO.getInstance().getBoardList(conn);
		} finally {
			ConnectionManager.close(conn);
		}
	}

}
