package com.happydog.controller.review;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Review;
import com.happydog.model.ReviewDAO;

@WebServlet("/AddReviewPro.do")
public class AddReviewProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		ReviewDAO dao = new ReviewDAO();
		Review rev = new Review();
		rev.setRcode(dao.getRcodeGenerator());
		
		String id = request.getParameter("id"); 
		rev.setId(id);
		
		int ocode = Integer.parseInt(request.getParameter("ocode"));
		rev.setOcode(ocode);
		rev.setRcontent(request.getParameter("rcontent"));
		rev.setRpoint(request.getParameter("rpoint"));
		
		int cnt = dao.addReview(rev);
		if(cnt>0){
			response.sendRedirect("MySalesList.do?id="+id);
		} else {
			response.sendRedirect("AddResultUserReview.do?ocode="+ocode);
		}
	}
}
