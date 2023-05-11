package com.happydog.controller.product;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import com.happydog.model.ProductDAO;

@WebServlet("/ProductCodeGenerator.do")
public class ProductCodeGeneratorCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cate = request.getParameter("cate");
		String msg = "상품코드를 발급합니다.";
		
		ProductDAO dao = new ProductDAO();
		String pcode = dao.getProductCodeGenerator(cate);
		
		request.setAttribute("pcode", cate+pcode);
		request.setAttribute("msg", msg);
		
		response.setContentType("text/html; charset=UTF-8");
		JSONObject json = new JSONObject();
		json.put("pcode", cate+pcode);
		json.put("msg", msg);
		PrintWriter out = response.getWriter();
		out.println(json.toString());
	}

}
