package co.yedam.app.common;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.app.board.controller.BoardInsert;
import co.yedam.app.board.controller.BoardInsertForm;
import co.yedam.app.board.controller.BoardList;
import co.yedam.app.board.controller.BoardUpdate;
import co.yedam.app.board.controller.BoardUpdateForm;
import co.yedam.app.member.controller.MemberInsert;
import co.yedam.app.member.controller.MemberInsertForm;
import co.yedam.app.member.controller.MemberList;
import co.yedam.app.member.controller.MemberUpdate;
import co.yedam.app.member.controller.MemberUpdateForm;




//@WebServlet("*.do")  -->web.xml에 넣었음
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	HashMap<String, Controller> list = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		list = new HashMap<String, Controller>();
		//url 요청과 서브컨트롤러 매핑
		list.put("/MemberInsertForm.do", new MemberInsertForm()); //회원등록폼
		list.put("/MemberInsert.do", new MemberInsert()); //회원등록처리
		list.put("/MemberUpdateForm.do", new MemberUpdateForm()); //회원정보수정폼
		list.put("/MemberUpdate.do", new MemberUpdate()); //회원정보수정처리
		list.put("/MemberList.do", new MemberList()); //회원목록
		list.put("/BoardInsertForm.do", new BoardInsertForm()); //회원등록폼
		list.put("/BoardInsert.do", new BoardInsert()); //회원등록처리
		list.put("/BoardUpdateForm.do", new BoardUpdateForm()); //회원정보수정폼
		list.put("/BoardUpdate.do", new BoardUpdate()); //회원정보수정처리
		list.put("/BoardList.do", new BoardList()); //회원목록
	}
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		//!! url 분석 -> 컨트롤러 검색 -> 실행
		String url = request.getRequestURI();   //http://localhost/edu/MemberInsert.do?  -> localhost다음주소값 불러옴
		String contextPath = request.getContextPath(); //edu 
		String path = url.substring(contextPath.length());  //contextPath값을 substring 즉 잘라서 MemberInser.do 값만 남기게
		
		//!! 서브 컨트롤러 검색
		Controller subController = list.get(path);  
		
		if(subController == null) {
			response.getWriter().print("no command.");
			return;
		}
		
		//!! 로그인 체르, 권한체크
		
		//!! 서브 컨트롤 실행
		String view = subController.execute(request, response);
		
		if (view != null) {
			if(view.startsWith("redirect:")) {
				response.sendRedirect(view.substring(9));
			}else {
				
				//!! 뷰페이지에 포워드
				request.getRequestDispatcher(view).forward(request, response);
			}
		}
	}




   

}
