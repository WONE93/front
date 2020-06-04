package co.yedam.app.member.controller;

import java.io.IOException;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import co.yedam.app.common.Controller;
import co.yedam.app.member.model.MemberService;
import co.yedam.app.member.model.MemberVO;

public class MemberUpdate implements Controller {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		//1. 파라미터 받아서 VO에 담기
				MemberVO member = new MemberVO();
				try {
					BeanUtils.copyProperties(member, request.getParameterMap());
					member.setHobby(Arrays.toString(request.getParameterValues("hobby")));
				} catch (Exception e) {
					e.printStackTrace();
				}
				//2. 서비스 로직 처리
				MemberService.getInstance().memberUpdate(member);
				
				//3. 결과 저장
				
				//4. 뷰페이지로 이동
				String path = request.getContextPath() + "/MemberList.do";
				return "redirect:" +path;
	}

}
