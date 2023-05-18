package com.happydog.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.model.FaqDAO;
import com.happydog.vo.FaqVO;

@WebServlet("/UpdateFaqPro.do")
public class UpdateFaqProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String fno = request.getParameter("fno");
		String fquestion = request.getParameter("fquestion");
		String fanswer = request.getParameter("fanswer");

		FaqVO faq = new FaqVO();
		faq.setFno(fno);
		faq.setFquestion(fquestion);
		faq.setFanswer(fanswer);
		
		FaqDAO dao = new FaqDAO();
		int cnt = dao.updateFaq(faq);
	
		if(cnt==0){
			response.sendRedirect("UpdateFaq.do?fno="+fno);
		} else {
			response.sendRedirect("FaqList.do");
		}
	}
}

