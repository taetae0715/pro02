package com.happydog.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.happydog.dto.Notice;
import com.happydog.dto.Product;

public class ProductDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//상품(1개) 상세 보기
	public Product getProduct(int pro_code){
		Product pro = new Product();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SELECT_ONE);
			pstmt.setInt(1, pro_code);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pro.setPro_code(rs.getInt("pro_code"));
				pro.setPname(rs.getString("pname"));
				pro.setPstd(rs.getString("pstd"));
				pro.setPcost(rs.getString("pcost"));
				pro.setPcom(rs.getString("pcom"));
				pro.setAmount(rs.getInt("amount"));
				pro.setPic1(rs.getString("pic1"));
				pro.setPic2(rs.getString("pic2"));
				pro.setPic3(rs.getString("pic3"));
				pro.setCate(rs.getString("cate"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return pro;
	}
	
	//상품 전체 목록 불러오기
	public ArrayList<Product> getProductList(){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setPro_code(rs.getInt("pro_code"));
				pro.setPname(rs.getString("pname"));
				pro.setPstd(rs.getString("pstd"));
				pro.setPcost(rs.getString("pcost"));
				pro.setPcom(rs.getString("pcom"));
				pro.setAmount(rs.getInt("amount"));
				pro.setPic1(rs.getString("pic1"));
				pro.setPic2(rs.getString("pic2"));
				pro.setPic3(rs.getString("pic3"));
				pro.setCate(rs.getString("cate"));
				proList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return proList;
	}
	
	//카테고리 분류 번호에 따른 검색
	public ArrayList<Product> getCateProductList(String cate){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATE_SELECT);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setPro_code(rs.getInt("pro_code"));
				pro.setPname(rs.getString("pname"));
				pro.setPstd(rs.getString("pstd"));
				pro.setPcost(rs.getString("pcost"));
				pro.setPcom(rs.getString("pcom"));
				pro.setAmount(rs.getInt("amount"));
				pro.setPic1(rs.getString("pic1"));
				pro.setPic2(rs.getString("pic2"));
				pro.setPic3(rs.getString("pic3"));
				pro.setCate(rs.getString("cate"));
				proList.add(pro);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		return proList;
	}
	
	//카테고리 소분류 이름에 따른 검색
	public HashMap<String, String> getCategory(String cate){
		HashMap<String, String> cateMap = new HashMap<String, String>();
		String cateGroup = "";
		String cateName = "";
		if(cate.length()==2){
			cate = cate + "01";
		}
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATENAME_SELECT);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			if(rs.next()){
				cateGroup = "catename";
				cateName = rs.getString("catename");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			Oracle11.close(rs, pstmt, con);
		}
		cateMap.put(cateGroup, cateName);
		return cateMap;
	}
	
	//상품 등록
	public int insertProduct(Product pro){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.INSERT_PRODUCT);
			pstmt.setInt(1, pro.getPro_code());
			pstmt.setString(2, pro.getPname());
			pstmt.setString(3, pro.getPstd());
			pstmt.setString(4, pro.getPcost());
			pstmt.setString(5, pro.getPcom());
			pstmt.setInt(6, pro.getAmount());
			pstmt.setString(7, pro.getPic1());
			pstmt.setString(8, pro.getCate());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e){ e.printStackTrace();			
		} catch (Exception e){ e.printStackTrace(); }
		Oracle11.close(pstmt, con);
		return cnt; }
	
	//상품 삭제
	public int deleteProduct(int pro_code) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DELETE_PRODUCT);
			pstmt.setInt(1, pro_code);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e){	
			e.printStackTrace();			
		} catch (Exception e){	
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt; }
	
}