package com.ryon.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.ryon.db.MemberDAO;
import com.ryon.dto.MemberDTO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/lg_ok")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("uid");
		String pw = request.getParameter("pw");
		HttpSession session = request.getSession();
		
		// 디비에 물어봄 id, pw 있는지??
		MemberDTO dto = MemberDAO.select(id, pw);
		if (dto == null) {
			response.getWriter().append("no");
		} else {
			//response.getWriter().append("ok");
			session.setAttribute("name", dto.getName());
			response.sendRedirect("main.jsp");
			
		}
		
		// 만약 id가 db에 있는 아이디가 있고 pw 가 맞다면
		// SELECT * FROM member WHERE id = id;
		// 로그인 성공
		// 아니면 실패
		
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
