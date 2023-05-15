package com.happydog.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Order;
import com.happydog.model.SalesDAO;

@WebServlet("/SurveyPro.do")
public class SurveyProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int ocode = Integer.parseInt(request.getParameter("ocode"));
		
		Order buy = new Order();
		buy.setOcode(Integer.parseInt(request.getParameter("ocode")));
		buy.setDname(request.getParameter("dname"));
		buy.setDcode(request.getParameter("dcode"));
		buy.setOstate(request.getParameter("ostate"));
		
		SalesDAO dao = new SalesDAO();
		int cnt = dao.surveyUpdate(buy);
		if(cnt>0){
			response.sendRedirect("Survey.do");
		} else {
			response.sendRedirect("SurveyLoad.do?ocode="+ocode);
		}
	}
}
