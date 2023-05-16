package com.happydog.model;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.InvalidParameterSpecException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.crypto.util.AES256;
import com.happydog.dto.User;
public class UserDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	String key = "%03x";
	String qpw;
	
	//0516 추가
	public ArrayList<User> getUserList() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException{
		ArrayList<User> userList = new ArrayList<User>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.USER_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				qpw = AES256.decryptAES256(rs.getString("pw"), key);
				int k = qpw.length();	//암호 글자수 세기
				String vpw = qpw.substring(0, 3);	//3글자만 암호를 보여주기
				String hpw = "";
				for(int i=0;i<k-3;i++){	//나머지는 *로 넣기
					hpw+="*";
				}
				user.setPw(vpw+hpw);
				user.setHpw(qpw);
				user.setName(rs.getString("name"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
				user.setAddr(rs.getString("addr"));
				user.setPoint(rs.getInt("point"));
				user.setVisited(rs.getInt("visited"));
				userList.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return userList; 
	}

	//로그인 실행
	public int loginCheck(String id, String pw) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidParameterSpecException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException{
		userVisitedCount(id);
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.USER_LOGIN);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				qpw = AES256.decryptAES256(rs.getString("pw"), key);
				System.out.println(qpw);
				if(pw.equals(qpw)){ cnt = 1; } 
				else { cnt = 0; } 
			} else { cnt = 9; }
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Oracle11.close(rs, pstmt, con); }
		return cnt; }
	
	//이건 아마도 방문회수 세려고?
	public int loginPass(String id, String pw) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidParameterSpecException, UnsupportedEncodingException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException{
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.USER_LOGIN);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				qpw = AES256.decryptAES256(rs.getString("pw"), key);
				System.out.println(qpw);
				if(pw.equals(qpw)){ cnt = 1; } 
				else { cnt = 0; } 
			} else { cnt = 9; }
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Oracle11.close(rs, pstmt, con); }
		return cnt; }
	
	//아이디 중복 확인
	public int idCheck(String id) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.USER_LOGIN);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){ cnt = 1; }
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e) { e.printStackTrace();
		} finally { Oracle11.close(rs, pstmt, con); }
		return cnt; }
	
	//로그인시 방문회수 증가
	public void userVisitedCount(String id){
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.USER_VISIT_COUNT);
			pstmt.setString(1, id);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
	}
	
	//회원가입
	public int insertUser(User user){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.INSET_USER);
			pstmt.setString(1, user.getId());
			pstmt.setString(2, user.getPw());
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getTel());
			pstmt.setString(5, user.getAddr());
			pstmt.setString(6, user.getEmail());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
	
	//마이페이지 회원정보 보기
	public User myInfo(String id) throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException{
		User user = new User();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.USER_LOGIN);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getString("id"));
				qpw = AES256.decryptAES256(rs.getString("pw"), key);
				int k = qpw.length();	//암호 글자수 세기
				String vpw = qpw.substring(0, 3);	//3글자만 암호를 보여주기
				String hpw = "";
				for(int i=0;i<k-3;i++){	//3글자를 뺀 나머지는 *로 넣기
					hpw+="*";
				}
				user.setPw(vpw+hpw);
				user.setHpw(qpw);
				user.setName(rs.getString("name"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
				user.setAddr(rs.getString("addr"));
				user.setPoint(rs.getInt("point"));
				user.setVisited(rs.getInt("visited"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return user;
	}
	
	//비번 바꾸는 회원 정보 수정
	public int updateUser(User user){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_USER);
			pstmt.setString(1, user.getPw());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getTel());
			pstmt.setString(4, user.getAddr());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getId());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
	
	//비번 안 바꾸는 회원 정보 수정
	public int updateUser2(User user){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_USER2);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getTel());
			pstmt.setString(3, user.getAddr());
			pstmt.setString(4, user.getEmail());
			pstmt.setString(5, user.getId());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
	
	//회원 탈퇴
	public int deleteUser(String id){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DELETE_USER);
			pstmt.setString(1, id);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
	
	//관리자용 회원 목록
	public ArrayList<User> userList() throws InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeySpecException, InvalidAlgorithmParameterException, BadPaddingException, IllegalBlockSizeException{
		ArrayList<User> uList = new ArrayList<User>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.USER_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				User user = new User();
				user.setId(rs.getString("id"));
				qpw = AES256.decryptAES256(rs.getString("pw"), key);
				int k = qpw.length();	//암호 글자수 세기
				String vpw = qpw.substring(0, 3);	//3글자만 암호를 보여주기
				String hpw = "";
				for(int i=0;i<k-3;i++){	//나머지는 *로 넣기
					hpw+="*";
				}
				user.setPw(vpw+hpw);
				user.setName(rs.getString("name"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
				user.setAddr(rs.getString("addr"));
				user.setPoint(rs.getInt("point"));
				user.setVisited(rs.getInt("visited"));
				uList.add(user);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return uList;
	}
	
	//비밀번호 초기화를 위해 회원 전화번호 불러옴
	public User getTel(String id) {
		User user = new User();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.USER_LOGIN);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			if(rs.next()){
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setTel(rs.getString("tel"));
				user.setEmail(rs.getString("email"));
				user.setRegdate(rs.getString("regdate"));
				user.setAddr(rs.getString("addr"));
				user.setPoint(rs.getInt("point"));
				user.setVisited(rs.getInt("visited"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return user;
	}
	
	//회원 비밀번호 초기화
	public int resetPassword(String id, String passwd) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_PW_RESET);
			pstmt.setString(1, passwd);
			pstmt.setString(2, id);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(pstmt, con);
		}
		return cnt;
	}
}