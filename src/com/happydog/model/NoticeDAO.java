package com.happydog.model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.happydog.dto.Notice;
public class NoticeDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	public ArrayList<Notice> noticeListAll(){
		ArrayList<Notice> notiList = new ArrayList<Notice>();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.NOTICE_SELECT_ALL);
			rs = pstmt.executeQuery();	
			while(rs.next()){
				Notice noti = new Notice();
				noti.setIdx(rs.getInt("idx"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setAuthor(rs.getString("author"));
				noti.setFile1(rs.getString("file1"));
				noti.setResdate(rs.getString("resdate"));
				noti.setReadcnt(rs.getInt("readcnt"));
				notiList.add(noti);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Oracle11.close(rs, pstmt, conn);
		return notiList;
	}
	
	public Notice getNotice(int idx){
		updateReadCount(idx);
		Notice noti = new Notice();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.NOTICE_SELECT_ONE);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();	
			if(rs.next()){
				noti.setIdx(rs.getInt("idx"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setAuthor(rs.getString("author"));
				noti.setFile1(rs.getString("file1"));
				noti.setResdate(rs.getString("resdate"));
				noti.setReadcnt(rs.getInt("readcnt"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Oracle11.close(rs, pstmt, conn);
		return noti;
	}

	private void updateReadCount(int idx) {
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.NOTICE_READCOUNT_UPDATE);
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		Oracle11.close(pstmt, conn);
	}	
	
	public int insertNotice(Notice noti){
		int cnt = 0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.INSERT_NOTICE);
			pstmt.setString(1, noti.getTitle());
			pstmt.setString(2, noti.getContent());
			pstmt.setString(3, noti.getAuthor());
			pstmt.setString(4, noti.getFile1());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e){ e.printStackTrace();			
		} catch (Exception e){ e.printStackTrace(); }
		Oracle11.close(pstmt, conn);
		return cnt; }
	
	public Notice updateNotice(int idx){
		Notice noti = new Notice();
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.NOTICE_SELECT_ONE);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			if(rs.next()){
				noti.setIdx(rs.getInt("idx"));
				noti.setTitle(rs.getString("title"));
				noti.setContent(rs.getString("content"));
				noti.setAuthor(rs.getString("author"));
				noti.setFile1(rs.getString("file1"));
				noti.setResdate(rs.getString("resdate"));
				noti.setReadcnt(rs.getInt("readcnt"));
			}
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e){ e.printStackTrace();			
		} catch (Exception e){ e.printStackTrace();
		} Oracle11.close(rs, pstmt, conn);
		return noti; }

	public int updateNoticePro(Notice noti) {
		int cnt = 0;
		try {
			conn = Oracle11.getConnection();
			if(noti.getFile1()==null) {
				pstmt = conn.prepareStatement(Oracle11.UPDATE_NOTICE2);
				pstmt.setString(1, noti.getTitle());
				pstmt.setString(2, noti.getContent());
				pstmt.setInt(3, noti.getIdx());
			} else {
				pstmt = conn.prepareStatement(Oracle11.UPDATE_NOTICE);
				pstmt.setString(1, noti.getTitle());
				pstmt.setString(2, noti.getContent());
				pstmt.setString(3, "data/"+noti.getFile1());
				pstmt.setInt(4, noti.getIdx());
			}
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e){ e.printStackTrace();			
		} catch (Exception e){ e.printStackTrace();
		} Oracle11.close(pstmt, conn);
		return cnt; }

	public int deleteNotice(int idx) {
		int cnt = 0;
		try {
			conn = Oracle11.getConnection();
			pstmt = conn.prepareStatement(Oracle11.DELETE_NOTICE);
			pstmt.setInt(1, idx);

			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){	
			e.printStackTrace();			
		} catch (Exception e){	
			e.printStackTrace();
		}
		Oracle11.close(pstmt, conn);
		return cnt;
	}
}
