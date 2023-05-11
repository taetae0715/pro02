package com.happydog.controller.product;
import java.io.IOException;
import java.util.Enumeration;

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
		
		String savePath = "/product/img";	
		int uploadFileSizeLimit = 10 * 1024 * 1024;	
		String encType = "UTF-8";		
		ServletContext context = getServletContext();	
		String uploadFilePath = context.getRealPath(savePath);  
		System.out.println("지정된 업로드 디렉토리 : "+savePath);
		System.out.println("서버 상의 실제 업로드되는 디렉토리 : "+uploadFilePath);
		
		int n = 0;
		String[] fileName = new String[3];
		String[] oriFileName = new String[3];
		ProductDAO dao = new ProductDAO();
		Product pro = new Product();
		
		try { 
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 
					uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
			Enumeration<?> files = multi.getFileNames();
			while(files.hasMoreElements()) {
				String file = (String) files.nextElement();
				fileName[n] = multi.getFilesystemName(file);
				//중복된 파일을 업로드할 경우 파일명이 바뀐다.
				oriFileName[n] = multi.getOriginalFileName(file);
				n++;
			}
			
			if (fileName[0] == null) { // 파일이 업로드 되지 않았을때
				System.out.print("파일1 업로드 실패");
			} else {
				pro.setPic1("img/"+fileName[0]);
			}
			
			if (fileName[1] == null) { // 파일이 업로드 되지 않았을때
				System.out.print("파일2 업로드 실패");
			} else {
				pro.setPic2("img/"+fileName[1]);
			}

			if (fileName[2] == null) { // 파일이 업로드 되지 않았을때
				System.out.print("파일3 업로드 실패");
			} else {
				pro.setPic3("img/"+fileName[2]);
			}
			pro.setPcode(multi.getParameter("pcode"));
			pro.setPname(multi.getParameter("pname"));
			pro.setPstd(multi.getParameter("pstd"));
			pro.setPprice(Integer.parseInt(multi.getParameter("pprice")));
			pro.setPcom(multi.getParameter("pcom"));
			pro.setAmount(Integer.parseInt(multi.getParameter("amount")));
			pro.setCate(multi.getParameter("cate"));
		} catch (Exception e) {
			System.out.print("예외 발생 : " + e);
		}

		int cnt = dao.insertProduct(pro);	
		if(cnt==0){ String msg = "상품이 등록되지 못했습니다."; request.setAttribute("msg", msg);
			RequestDispatcher view = request.getRequestDispatcher("InsertProduct.do");
			view.forward(request, response); } 
		else { response.sendRedirect("ProductList.do"); }
	}
}