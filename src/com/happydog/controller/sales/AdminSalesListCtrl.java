package com.happydog.controller.sales;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;

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

@WebServlet("/AdminSalesList.do")
public class AdminSalesListCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SalesDAO rdao = new SalesDAO();
		ArrayList<SalesVO> sList = rdao.getSalesList();
		ProductDAO pdao = new ProductDAO();
		for(SalesVO sale : sList){
			Product pro = pdao.getProduct(sale.getPcode()); 
			sale.setPname(pro.getPname());
		}
		//사용자 정보 로딩
		UserDAO udao = new UserDAO();
		
		for(SalesVO sale : sList){
			User user = new User();
			try {
				user = udao.myInfo(sale.getId());
			} catch (InvalidKeyException | NoSuchPaddingException
					| NoSuchAlgorithmException | InvalidKeySpecException
					| InvalidAlgorithmParameterException | BadPaddingException
					| IllegalBlockSizeException e) {
				e.printStackTrace();
			}
			sale.setUsername(user.getName());
		}
		request.setAttribute("sList", sList);
		//디스패치로 view를 생성하여 proList.jsp로 요청 받은 proList를 포워드
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/salesList.jsp");
		view.forward(request, response);
	}

}
