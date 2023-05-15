package com.happydog.controller.sales;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Order;
import com.happydog.dto.Pay;
import com.happydog.model.SalesDAO;

@WebServlet("/AddPay.do")
public class AddPayCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int amount = Integer.parseInt(request.getParameter("amount"));
		String id = request.getParameter("id");
		String pcode = request.getParameter("pcode");
		String cno = request.getParameter("cno");
		String cate = request.getParameter("cate");

		Pay pay = new Pay();
		Order order = new Order();
		SalesDAO dao = new SalesDAO();
		
		order.setOcode(dao.getOcodeGenerator());
		order.setId(id);
		order.setPcode(pcode);
		order.setAmount(amount);
		order.setPrice(request.getParameter("payamount"));
		order.setTel(request.getParameter("tel"));
		order.setAddr(request.getParameter("address1")+" "+request.getParameter("address2"));
		order.setOstate("상품준비중");
		order.setDname("");
		order.setDcode("");

		pay.setPno(dao.getPnoGenerator());
		pay.setId(request.getParameter("id"));
		pay.setOcode(order.getOcode());
		pay.setType(request.getParameter("type"));
		pay.setType_no(request.getParameter("type_no"));
		pay.setPrice(Integer.parseInt(request.getParameter("payamount")));
		
		int cnt = dao.addSales(order, pay, cno);
		if(cnt>=3){
			System.out.println("트랜잭션 처리 성공");
			response.sendRedirect("ProductList.do?cate="+cate);
		} else {
			System.out.println("트랜잭션 처리 실패");
			response.sendRedirect("AddSales.do?cno="+cno+"&pcode="+pcode+"&amount="+amount+"&id="+id);
		}
	}
}
