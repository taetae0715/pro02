package com.happydog.controller.sales;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Product;
import com.happydog.dto.User;
import com.happydog.model.ProductDAO;
import com.happydog.model.UserDAO;

@WebServlet("/AddSales.do")
public class AddSalesCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//상품코드, 아이디를 받아서 dao로 전달하여 한 개의 특정 상품과 회원에 대한 정보를 로딩
		String pcode = request.getParameter("pcode");
		String id = request.getParameter("id");
		
		//장바구니에서 넘어온 데이터 처리하기
		String cno = "";
		int amount = 1;
		String pcs = request.getParameter("amount");
		if(request.getParameter("cno")!=null){
			cno = request.getParameter("cno");
			amount = Integer.parseInt(pcs);
			request.setAttribute("cno", cno);
			request.setAttribute("amount", amount);
		}
		
		//상품 정보 로딩
		String msg = "상품을 구매합니다.";
		ProductDAO dao = new ProductDAO();
		Product pro = dao.getProduct(pcode);
		
		//사용자 정보 로딩
		UserDAO udao = new UserDAO();
		User user = new User();
		try {
			user = udao.myInfo(id);
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("user", user);	//구매자 정보
		request.setAttribute("pro", pro); 	//한 개의 상품 정보
		request.setAttribute("amount", amount);
		request.setAttribute("id", id);
		request.setAttribute("msg", msg);
		
		//RequestDispatcher(디스패치)로 view를 생성하여 proList.jsp로 요청받은proList를 포워드 
		RequestDispatcher view = request.getRequestDispatcher("/sales/addSales.jsp");
		view.forward(request, response);
	}

}
