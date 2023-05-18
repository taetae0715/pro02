package com.happydog.controller.faq;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.model.FaqDAO;
import com.happydog.vo.FaqVO;

@WebServlet("/AddFaqPro.do")
public class AddFaqProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		FaqDAO dao = new FaqDAO();
		
		String fno = dao.getFnoGenerator();
		String fquestion = request.getParameter("fquestion");
		String fanswer = request.getParameter("fanswer");

		FaqVO faq = new FaqVO();
		faq.setFno(fno);
		faq.setFquestion(fquestion);
		faq.setFanswer(fanswer);
		
		int cnt = dao.addFaq(faq);
	
		if(cnt==0){
			response.sendRedirect("AddFaq.do");
		} else {
			response.sendRedirect("FaqList.do");
		}
	}
}

