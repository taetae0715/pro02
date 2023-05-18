package com.happydog.controller.qna;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.model.QnaDAO;

@WebServlet("/DelReply.do")
public class DelReplyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		QnaDAO dao = new QnaDAO();	
		String qno = request.getParameter("qno");

		
		int cnt = dao.delReply(qno);
		
		if(cnt==0){ //답변삭제 실패
			String msg = "답변이 삭제되지 못했습니다.";
			request.setAttribute("msg", msg);
			response.sendRedirect("GetReply.do?qno="+qno);
		} else { //답변삭제 성공
			response.sendRedirect("QnaList.do");
		}
	}
}
