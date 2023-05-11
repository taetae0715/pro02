package com.happydog.controller.cart;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Cart;
import com.happydog.model.CartDAO;

@WebServlet("/InsertCartPro.do")
public class InsertCartProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cart cart = new Cart();
		cart.setPcode(request.getParameter("pcode"));
		cart.setId(request.getParameter("id"));
		cart.setAmount(Integer.parseInt(request.getParameter("amount")));
		
		CartDAO dao = new CartDAO();
		int cnt = dao.insertCart(cart);
		
		response.sendRedirect("MyCart.do?id="+request.getParameter("id"));
	}
}
