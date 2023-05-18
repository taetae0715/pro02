package com.happydog.controller.qna;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.model.QnaDAO;
import com.happydog.vo.QnaVO;

@WebServlet("/UpdateQna.do")
public class UpdateQnaCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		String qno = request.getParameter("qno");
		
		QnaDAO dao = new QnaDAO();
		ArrayList<QnaVO> qnaList = new ArrayList<QnaVO>();
		QnaVO qn = new QnaVO();
		
		qn = dao.getQna2(qno);	//해당 질문 불러오기
		qnaList = dao.getReplyList(qno);	//해당 질문에 대한 답변 목록 불러오기
		
		request.setAttribute("qn", qn);
		request.setAttribute("qnaList", qnaList);
		
		//디스패치로 view를 생성하여 noticeList.jsp로 요청 받은 notiList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/qna/updateQna.jsp");
		view.forward(request, response);
	}
}
