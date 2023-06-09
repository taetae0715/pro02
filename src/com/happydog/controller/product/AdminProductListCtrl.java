package com.happydog.controller.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Product;
import com.happydog.model.ProductDAO;

@WebServlet("/AdminProductList.do")
public class AdminProductListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO dao = new ProductDAO();
		ArrayList<Product> proList = dao.getProductList();
		HashMap<String, String> cateMap = new HashMap<String, String>();
		
		cateMap.put("catename", "전체");
		request.setAttribute("proList", proList);
		request.setAttribute("cateMap", cateMap);
		
		//RequestDispatcher(디스패치)로 view를 생성하여 proList.jsp로 요청받은proList를 포워드 
		RequestDispatcher view = request.getRequestDispatcher("/product/proList.jsp");
		view.forward(request, response);
	}
}
