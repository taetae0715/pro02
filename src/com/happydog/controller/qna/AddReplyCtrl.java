package com.happydog.controller.qna;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.model.QnaDAO;
import com.happydog.vo.QnaVO;

@WebServlet("/AddReply.do")
public class AddReplyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String qno = request.getParameter("parno"); 
		
		QnaDAO dao = new QnaDAO();
		QnaVO qna = dao.getQna2(qno);
		
		String msg = "답변을 시작합니다.";
		request.setAttribute("msg", msg);
		request.setAttribute("qna", qna);
		
		//디스패치로 view를 생성하여 noticeList.jsp로 요청 받은 notiList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/qna/addReply.jsp");
		view.forward(request, response);
	}

}
