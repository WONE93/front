package co.yedam.app.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.app.common.Controller;
import co.yedam.app.member.model.MemberService;

public class MemberList implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("list", MemberService.getInstance().getMemberList());
		
		return "/member/memberList.jsp";
	}

}
