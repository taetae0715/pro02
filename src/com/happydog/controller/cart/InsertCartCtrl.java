package com.happydog.controller.cart;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Product;
import com.happydog.model.ProductDAO;

@WebServlet("/InsertCart.do")
public class InsertCartCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pcode = request.getParameter("pcode");
		
		ProductDAO dao = new ProductDAO();
		Product pro = dao.getProduct(pcode);
		
		request.setAttribute("pro", pro);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/cart/insertCart.jsp");
		view.forward(request, response);
	}
}
