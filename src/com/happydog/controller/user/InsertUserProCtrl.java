package com.happydog.controller.user;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.crypto.util.AES256;
import com.happydog.dto.User;
import com.happydog.model.UserDAO;

@WebServlet("/InsertUserPro.do")
public class InsertUserProCtrl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		
		User user = new User();
		String key = "%03x";
		
		String pw = request.getParameter("pw");
		String passwd = "";
		try {
			passwd = AES256.encryptAES256(pw, key);
		} catch (InvalidKeyException | NoSuchAlgorithmException
				| InvalidKeySpecException | NoSuchPaddingException
				| InvalidParameterSpecException | BadPaddingException
				| IllegalBlockSizeException e) {
			e.printStackTrace();
		}
		
		user.setId(request.getParameter("id"));
		user.setPw(passwd);
		user.setName(request.getParameter("name"));
		user.setEmail(request.getParameter("email"));
		user.setTel(request.getParameter("tel"));
		user.setAddr(request.getParameter("address1")+" "+request.getParameter("address2"));
		
		UserDAO dao = new UserDAO();
		int cnt = dao.insertUser(user);
		
		//회원 등록 확인 이메일 보내기
		try {
			naverMailSend(request, response);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
		if(cnt>=1){
			response.sendRedirect("MemberList.do");
		} else {
			response.sendRedirect("MemberList.do");
		}
	}
	
	//네이버 가입 축하 이메일
	public static void naverMailSend(HttpServletRequest req, HttpServletResponse res) throws AddressException, MessagingException{
		String host = "smtp.naver.com";
		String usr = "아이디@naver.com"; // 자신의 네이버 계정
		String password = "";// 자신의 네이버 패스워드

		// 메일 받을 주소
		/* String to_email = m.getEmail(); */
		//String to_email = req.getParameter("email");
		
		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
        String recipient = req.getParameter("email");
        String subject = "회원 가입 축하 이메일";
        String body = "회원 가입을 축하드립니다.";
		
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un=usr;
            String pw=password;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(un, pw);
            }
        });
        session.setDebug(true); //for debug
          
        Message mimeMessage = new MimeMessage(session);
        mimeMessage.setFrom(new InternetAddress(usr));
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        mimeMessage.setSubject(subject);
        mimeMessage.setText(body);
        Transport.send(mimeMessage);
	}

}
