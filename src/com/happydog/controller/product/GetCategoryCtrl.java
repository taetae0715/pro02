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
import com.happydog.vo.CategoryVO;

@WebServlet("/GetCategory.do")
public class GetCategoryCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String categroup = request.getParameter("categroup");
		
		ProductDAO dao = new ProductDAO();
		ArrayList<CategoryVO> cateList = dao.getCategoryName(categroup);
		request.setAttribute("cateList", cateList);
		
		//디스패치로 view를 생성하여 proList.jsp로 요청 받은 proList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/cateList.jsp");
		view.forward(request, response);
	}
}