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

@WebServlet("/AddQnaPro.do")
public class AddQnaProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		QnaVO qna = new QnaVO();
		QnaDAO dao = new QnaDAO();
		
		qna.setQno(dao.getQnoGenerator());
		qna.setTitle(request.getParameter("title"));
		qna.setContent(request.getParameter("content"));
		qna.setAuthor(request.getParameter("author"));
		
		int cnt = dao.addQna(qna);
		
		if(cnt==0){ //질문하기 실패
			String msg = "질문이 등록되지 못했습니다.";
			request.setAttribute("msg", msg);
			
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/qna/addQna.jsp");
			view.forward(request, response);
		} else { //질문하기 성공
			response.sendRedirect("QnaList.do");
		}
	}
}

