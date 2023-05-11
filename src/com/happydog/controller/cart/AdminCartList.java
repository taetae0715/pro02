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

@WebServlet("/AdminCartList.do")
public class AdminCartList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		CartDAO dao = new CartDAO();
		
		ArrayList<CartVO> cartList = dao.getCartList();
		request.setAttribute("cartList", cartList);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/cartList.jsp");
		view.forward(request, response);
		
	}

}
