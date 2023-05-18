package com.happydog.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.happydog.vo.FaqVO;

public class FaqDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//글번호 생성
	public String getFnoGenerator(){
		String fno = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.FNO_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				fno = rs.getString("fno");
			} else {
				fno = "00000000";
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		
		int tmp = Integer.parseInt(fno) + 1;
		if(fno=="00000000"){
			fno = "0000000" + tmp;
		} else if(tmp>=10000000){
			fno = tmp + "";
		} else if(tmp>=1000000){
			fno = "0" + tmp;
		} else if(tmp>=100000) {
			fno = "00" + tmp;
		} else if(tmp>=10000) {
			fno = "000" + tmp;
		} else if(tmp>=1000) {
			fno = "0000" + tmp;
		} else if(tmp>=100) {
			fno = "00000" + tmp;
		} else if(tmp>=10) {
			fno = "000000" + tmp;
		} else {
			fno = "0000000" + tmp;
		}
		return fno;
	}
	
	//FAQ 등록
	public int addFaq(FaqVO faq){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.ADD_FAQ);
			pstmt.setString(1, faq.getFno());
			pstmt.setString(2, faq.getFquestion());
			pstmt.setString(3, faq.getFanswer());				
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
	
	//FAQ 삭제
	public int delFaq(String fno){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DELETE_FAQ);
			pstmt.setString(1, fno);			
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
	
	//FAQ 수정
	public int updateFaq(FaqVO faq){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_FAQ);
			pstmt.setString(1, faq.getFquestion());
			pstmt.setString(2, faq.getFanswer());
			pstmt.setString(3, faq.getFno());
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
	
	public ArrayList<FaqVO> getFaqList(){
		ArrayList<FaqVO> faqList = new ArrayList<FaqVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.GET_FAQ);
			rs = pstmt.executeQuery();
			while(rs.next()){
				FaqVO faq = new FaqVO();
				faq.setFno(rs.getString("fno"));
				faq.setFquestion(rs.getString("fquestion"));
				faq.setFanswer(rs.getString("fanswer"));
				faq.setResdate(rs.getString("resdate"));
				faqList.add(faq);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return faqList;
	} 
	
	public FaqVO getFaq(String fno){
		FaqVO faq = new FaqVO();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.FAQ_SELECT_ONE);
			pstmt.setString(1, fno);
			rs = pstmt.executeQuery();
			if(rs.next()){
				faq.setFno(rs.getString("fno"));
				faq.setFquestion(rs.getString("fquestion"));
				faq.setFanswer(rs.getString("fanswer"));
				faq.setResdate(rs.getString("resdate"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return faq;
	} 
}
