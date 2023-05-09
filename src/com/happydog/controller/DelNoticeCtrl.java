package com.happydog.controller;
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
@WebServlet("/DelNotice.do")
public class DelNoticeCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		int idx = Integer.parseInt(request.getParameter("idx"));
		NoticeDAO ndao = new NoticeDAO();
		
		int cnt = ndao.deleteNotice(idx);
		if(cnt==0){ String msg = "공지사항 글을 삭제하지 못했습니다.";
			request.setAttribute("msg", msg);
			response.sendRedirect("GetNotice.do?idx="+idx);
		} else { response.sendRedirect("NoticeList.do"); }
	}
}
