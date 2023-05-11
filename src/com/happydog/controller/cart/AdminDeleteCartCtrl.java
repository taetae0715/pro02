package com.happydog.controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Cart;
import com.happydog.model.CartDAO;

@WebServlet("/AdminDeleteCart.do")
public class AdminDeleteCartCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cno = request.getParameter("cno");
		
		CartDAO dao = new CartDAO();
		Cart cart = dao.getCartDetail(cno);
		String id = cart.getId();
		int cnt = dao.deleteCart(cno);
		if(cnt==1){
			response.sendRedirect("AdminCartList.do");
		} else {
			response.sendRedirect("AdminCartList.do");
		}
	}
}
