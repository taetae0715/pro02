package com.happydog.controller.cart;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.model.CartDAO;
import com.happydog.vo.CartVO;

@WebServlet("/MyCart.do")
public class MyCartCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String id = request.getParameter("id");
		CartDAO dao = new CartDAO();
		
		ArrayList<CartVO> cartList = dao.getByIdCartList(id);
		request.setAttribute("cartList", cartList);
		
		String username = "";
		for(CartVO cart : cartList){
			username = cart.getName(); }
		request.setAttribute("username", username);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/cart/myCart.jsp");
		view.forward(request, response);
		
	}
}
