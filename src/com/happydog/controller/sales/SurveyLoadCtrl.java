package com.happydog.controller.sales;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

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
import com.happydog.model.SalesDAO;
import com.happydog.model.UserDAO;
import com.happydog.vo.SalesVO;

@WebServlet("/SurveyLoad.do")
public class SurveyLoadCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		int ocode = Integer.parseInt(request.getParameter("ocode"));
		
		SalesDAO sdao = new SalesDAO();
		ProductDAO pdao = new ProductDAO();
		UserDAO udao = new UserDAO();
		
		//특정 주문정보의 전체 판매+결제 내용 로딩 
		SalesVO sale = sdao.getSales(ocode);
		
		//상품명 로딩
		Product pro = pdao.getProduct(sale.getPcode()); 
		sale.setPname(pro.getPname());
		
		//사용자 이름 로딩
		User user = new User();
		try {
			user = udao.myInfo(sale.getId());
			sale.setUsername(user.getName());
		} catch (InvalidKeyException | NoSuchPaddingException
				| NoSuchAlgorithmException | InvalidKeySpecException
				| InvalidAlgorithmParameterException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		} 
		
		request.setAttribute("sale", sale);	//구매 정보
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/surveyLoad.jsp");
		view.forward(request, response);
	}
}
