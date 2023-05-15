package com.happydog.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.happydog.model.SalesDAO;

@WebServlet("/OkBuy.do")
public class OkBuyCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int ocode = Integer.parseInt(request.getParameter("ocode"));
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("sid");
		
		SalesDAO dao = new SalesDAO();
		int cnt = dao.okSales(ocode);
		
		if(cnt>0){
			System.out.println("구매 완료 성공");
			response.sendRedirect("MySalesList.do?id="+id);
		} else {
			System.out.println("구매 완료 실패");
			response.sendRedirect("MySalesList.do?id="+id);
		}
	}
}
