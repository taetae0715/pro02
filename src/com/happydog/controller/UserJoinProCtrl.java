package com.happydog.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.crypto.util.AES256;
import com.happydog.dto.User;
import com.happydog.model.UserDAO;

@WebServlet("/UserJoinPro.do")
public class UserJoinProCtrl extends HttpServlet {
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
		if(cnt>=1){
			response.sendRedirect("UserLogin.do");
		} else {
			response.sendRedirect("UserSignUp.do");
		}
		//회원 가입 축하 이메일 보내기
		try {
			naverMailSend(request, response);
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	//네이버 가입 축하 이메일
	public static void naverMailSend(HttpServletRequest req, HttpServletResponse res) throws AddressException, MessagingException{
		String host = "smtp.naver.com";
		String usr = "dlddld4495@naver.com"; // 자신의 네이버 계정
		String password = "";// 자신의 네이버 패스워드

		// 메일 받을 주소
		/* String to_email = m.getEmail(); */
		String to_email = req.getParameter("email");
		
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
	
	//네이버 파일 첨부 이메일
	public static void naverFileMailSend(HttpServletRequest req, HttpServletResponse res) throws AddressException, MessagingException{
        // 메일 관련 정보
        String host = "smtp.naver.com";
        final String username = "네이버아이디";
        final String password = "비밀번호";
        int port=465;
         
        // 메일 내용
        String recipient = req.getParameter("email");
        String subject = "네이버를 사용한 발송 테스트입니다.";
        String body = "내용 무";
         
        Properties props = System.getProperties();
         
         
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
          
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un=username;
            String pw=password;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(un, pw);
            }
        });
        session.setDebug(true); //for debug
          
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
         
        // 파일 첨부시에는 BodyPart를 사용
        // msg.setText(body);
         
        // 파일첨부를 위한 Multipart
        Multipart multipart = new MimeMultipart();
         
        // BodyPart를 생성
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(body);
         
        // 1. Multipart에 BodyPart를 붙인다.
        multipart.addBodyPart(bodyPart);
         
        // 2. 파일을 첨부한다.
        String filename = "D:/restful_java_with_jax-rs.pdf";
        DataSource source = new FileDataSource(filename);
        bodyPart.setDataHandler(new DataHandler(source));
        bodyPart.setFileName(filename);
         
        // 이메일 메시지의 내용에 Multipart를 붙인다.
        msg.setContent(multipart);
        Transport.send(msg);
    }
	
	//네이버 이미지 첨부 이메일
	public static void naverImageMailSend(HttpServletRequest req, HttpServletResponse res) throws AddressException, MessagingException{
        // 메일 관련 정보
        String host = "smtp.naver.com";
        final String username = "네이버아이디";
        final String password = "비밀번호";
        int port=465;
         
        // 메일 내용
        String recipient = req.getParameter("email");
        String subject = "네이버를 사용한 발송 테스트입니다.";
        String body = "내용 무";
         
        Properties props = System.getProperties();
         
         
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.trust", host);
          
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            String un=username;
            String pw=password;
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(un, pw);
            }
        });
        session.setDebug(true); //for debug
          
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(username));
        msg.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(subject);
        msg.setSentDate(new Date());
         
        // 파일 첨부시에는 BodyPart를 사용
        // msg.setText(body);
         
        // 파일첨부를 위한 Multipart
        Multipart multipart = new MimeMultipart();
         
        // BodyPart를 생성
        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText(body);
         
        // 1. Multipart에 BodyPart를 붙인다.
        multipart.addBodyPart(bodyPart);
         
        // 2. 이미지를 첨부한다.
        bodyPart = new MimeBodyPart();
        String filename = "D:/image.png";
        DataSource source = new FileDataSource(filename);
        bodyPart.setDataHandler(new DataHandler(source));
        bodyPart.setFileName(filename);
        //Trick is to add the content-id header here
        bodyPart.setHeader("Content-ID", "image_id");
        multipart.addBodyPart(bodyPart);
         
        //third part for displaying image in the email body
        bodyPart = new MimeBodyPart();
        bodyPart.setContent("<h1>Attached Image</h1>" + "<img src='cid:image_id'>", "text/html");
        multipart.addBodyPart(bodyPart);
         
        // 이메일 메시지의 내용에 Multipart를 붙인다.
        msg.setContent(multipart);
        Transport.send(msg);
	}
	
	//지메일 가입축하 이메일 보내기
	public static void gMailSend(HttpServletRequest req, HttpServletResponse res) throws AddressException, MessagingException{
        String host = "smtp.gmail.com";
        String username = "지메일아이디@gmail.com";
        String password = "비밀번호";
         
        // 메일 내용
        String recipient = req.getParameter("email");
        String subject = "지메일을 사용한 발송 테스트입니다.";
        String body = "내용 무";
				
        //properties 설정
        Properties props = new Properties();
        props.put("mail.smtps.auth", "true");
        // 메일 세션
        Session session = Session.getDefaultInstance(props);
        MimeMessage msg = new MimeMessage(session);
 
        // 메일 관련
        msg.setSubject(subject);
        msg.setText(body);
        msg.setFrom(new InternetAddress(username));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
 
        // 발송 처리
        Transport transport = session.getTransport("smtps");
        transport.connect(host, username, password);
        transport.sendMessage(msg, msg.getAllRecipients());
        transport.close();
	}
	
	//지메일 TSL/SSL 사용시의 회원 가입 축하 이메일
	public static void gMailSSLSend(HttpServletRequest req, HttpServletResponse res) throws MessagingException, UnsupportedEncodingException{
        // 발신, 수신 정보
        final String fromEmail = "지메일 아이디@gmail.com";
        final String password = "비밀번호";
        final String toEmail = req.getParameter("email");
         
        // 메일 내용
        String subject="메일 발송 테스트";
        String body="내용 무";
         
        Properties props = new Properties();
        // SSL 사용하는 경우
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port
         
        // TLS 사용하는 경우
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.port", "587"); //TLS Port
        props.put("mail.smtp.auth", "true"); //enable authentication
        props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
         
        // 인증
        Authenticator auth = new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        };
         
        // 메일 세션
        Session session = Session.getInstance(props, auth);
         
        MimeMessage msg = new MimeMessage(session);
        //set message headers
        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
        msg.addHeader("format", "flowed");
        msg.addHeader("Content-Transfer-Encoding", "8bit");
         
        msg.setFrom(new InternetAddress(fromEmail, "관리자"));
        msg.setReplyTo(InternetAddress.parse("no_reply@goodcodes.co.kr", false));
 
        msg.setSubject(subject, "UTF-8");
        msg.setText(body, "UTF-8");
        msg.setSentDate(new Date());
 
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
        Transport.send(msg); 
	}
	
	public static void authNaverMailSend(HttpServletRequest req, HttpServletResponse res){
		String host = "smtp.naver.com";
		String usr = "kkt09072@naver.com"; // 자신의 네이버 계정
		String password = "";// 자신의 네이버 패스워드

		// 메일 받을 주소
		/* String to_email = m.getEmail(); */
		String to_email = req.getParameter("email");
		
		// SMTP 서버 정보를 설정한다.
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", 465);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.ssl.enable", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		
		// 인증 번호 생성기
		StringBuffer temp = new StringBuffer();
		Random rnd = new Random();
		for (int i = 0; i < 10; i++) {
			int rIndex = rnd.nextInt(3);
			switch (rIndex) {
			case 0:
				// a-z
				temp.append((char) ((int) (rnd.nextInt(26)) + 97));
				break;
			case 1:
				// A-Z
				temp.append((char) ((int) (rnd.nextInt(26)) + 65));
				break;
			case 2:
				// 0-9
				temp.append((rnd.nextInt(10)));
				break;
			}
		}
		String AuthenticationKey = temp.toString();
		System.out.println(AuthenticationKey);

		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(usr, password);
			}
		});

		// email 전송
		try {
			MimeMessage msg = new MimeMessage(session);
			msg.setFrom(new InternetAddress(usr, "msg"));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(to_email));

			// 메일 제목
			msg.setSubject("회원 인증 번호 입니다.");
			// 메일 내용
			msg.setText("인증 번호는 :" + temp);	//인증 번호 발송시

			Transport.send(msg);
			System.out.println("이메일 전송");

		} catch (Exception e) {
			e.printStackTrace();// TODO: handle exception
		}
		HttpSession saveKey = req.getSession();
		saveKey.setAttribute("AuthenticationKey", AuthenticationKey);
	}
}