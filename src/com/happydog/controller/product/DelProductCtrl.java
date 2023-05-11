package com.happydog.controller.product;
import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Notice;
import com.happydog.model.NoticeDAO;
import com.happydog.model.ProductDAO;
@WebServlet("/DelProduct.do")
public class DelProductCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		String pcode = request.getParameter("pcode");
		ProductDAO dao = new ProductDAO();
		
		int cnt = dao.deleteProduct(pcode);
		if(cnt==0){ String msg = "상품을 삭제하지 못했습니다.";
			request.setAttribute("msg", msg);
			response.sendRedirect("ProductDetail.do?pcode="+pcode);
		} else { response.sendRedirect("ProductList.do"); }
	}
}
