package co.yedam.app.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.app.board.model.BoardService;
import co.yedam.app.common.Controller;


public class BoardList implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	request.setAttribute("list", BoardService.getInstance().getBoardList());
		
		return "/board/boardList.jsp";
	}

}
