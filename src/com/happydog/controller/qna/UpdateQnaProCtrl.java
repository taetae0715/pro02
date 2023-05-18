package com.happydog.controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.model.QnaDAO;
import com.happydog.vo.QnaVO;

@WebServlet("/UpdateQnaPro.do")
public class UpdateQnaProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		QnaVO qna = new QnaVO();
		QnaDAO dao = new QnaDAO();
		
		String qno = request.getParameter("qno");
		
		qna.setQno(qno);
		qna.setTitle(request.getParameter("title"));
		qna.setContent(request.getParameter("content"));
		qna.setAuthor(request.getParameter("author"));
		
		int cnt = dao.updateQna(qna);
		
		if(cnt==0){ //질문수정 실패
			String msg = "질문을 수정하지 못했습니다.";
			request.setAttribute("msg", msg);
			response.sendRedirect("UpdateQna.do?qno="+qno);
		} else { //질문수정 성공
			response.sendRedirect("QnaList.do");
		}
	}
}
