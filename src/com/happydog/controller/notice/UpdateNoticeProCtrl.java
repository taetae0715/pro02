package com.happydog.controller.notice;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.happydog.dto.Notice;
import com.happydog.model.NoticeDAO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
@WebServlet("/UpdateNoticePro.do")
public class UpdateNoticeProCtrl extends HttpServlet {
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
		
		String title = "";
		String content = "";
		String author = "";
		String fileName = "";
		int idx = 0;

		try {
			MultipartRequest multi = new MultipartRequest(request, uploadFilePath, 
					uploadFileSizeLimit, encType, new DefaultFileRenamePolicy());
			fileName = multi.getFilesystemName("file1"); 
			if (fileName == null) { System.out.print("파일 업로드 실패"); } 
			idx = Integer.parseInt(multi.getParameter("idx"));
			author = multi.getParameter("author");
			title = multi.getParameter("title");
			content = multi.getParameter("content");
		} catch (Exception e) { System.out.print("예외 발생 : " + e); }
		
		NoticeDAO ndao = new NoticeDAO();
		Notice noti = new Notice();
		noti.setIdx(idx);
		noti.setTitle(title);
		noti.setContent(content);
		noti.setFile1(fileName);
		noti.setAuthor(author);
		int cnt = ndao.updateNoticePro(noti);	
		if(cnt==0){ String msg = "공지사항 글을 수정하지 못했습니다.";
			request.setAttribute("msg", msg);
			response.sendRedirect("UpdateNotice.do?idx="+noti.getIdx());
		} else { response.sendRedirect("NoticeList.do"); }
	}
}