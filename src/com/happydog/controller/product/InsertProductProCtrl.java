package com.happydog.controller.product;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Notice;
import com.happydog.dto.Product;
import com.happydog.model.NoticeDAO;
import com.happydog.model.ProductDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
@WebServlet("/InsertProductPro.do")
public class InsertProductProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		String savePath = "/data";	
		int uploadFileSizeLimit = 10 * 1024 * 1024;	
		String encType = "UTF-8";		
		ServletContext context = getServletContext();	
		String uploadFilePath = context.getRealPath(savePath);  
		
		int pro_code = 0;
		String pname = "";
		String pstd = "";
		String pcost = "";
		String pcom = "";
		int amount = 0;
		String fileName = "";
		String cate = "";

		ProductDAO dao = new ProductDAO();
		Product pro = new Product();
		
		//pro_code, pname, pstd, pcost, pcom, amount, pic1, cate
		try { MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 
					uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("pic1");
			if (fileName == null) { System.out.print("첨부 파일 없음"); } 
			else { pro.setPic1("data/"+fileName); }
			pro_code = Integer.parseInt(multi.getParameter("pro_code"));
			pname = multi.getParameter("pname");
			pstd = multi.getParameter("pstd");
			pcost = multi.getParameter("pcost");
			pcom = multi.getParameter("pcom");
			amount = Integer.parseInt(multi.getParameter("amount"));
			cate = multi.getParameter("cate");
		} catch (Exception e) { System.out.print("예외 발생 : " + e); }
		
		pro.setPro_code(pro_code);
		pro.setPname(pname);
		pro.setPstd(pstd);
		pro.setPcost(pcost);
		pro.setPcom(pcom);
		pro.setAmount(amount);
		pro.setCate(cate);
		
		int cnt = dao.insertProduct(pro);	
		if(cnt==0){ String msg = "상품이 등록되지 못했습니다."; request.setAttribute("msg", msg);
			RequestDispatcher view = request.getRequestDispatcher("/product/insertProduct.jsp");
			view.forward(request, response); } 
		else { response.sendRedirect("ProductList.do"); }
	}
}