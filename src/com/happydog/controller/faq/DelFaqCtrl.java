package com.happydog.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.model.FaqDAO;

@WebServlet("/DelFaq.do")
public class DelFaqCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String fno = request.getParameter("fno");
		
		FaqDAO dao = new FaqDAO();

		int cnt = dao.delFaq(fno);
		if(cnt==0){
			response.sendRedirect("UpdateFaq.do?fno="+fno);
		} else {
			response.sendRedirect("FaqList.do");
		}
	}
}

