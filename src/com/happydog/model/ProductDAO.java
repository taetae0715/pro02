package com.happydog.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import com.happydog.dto.Notice;
import com.happydog.dto.Product;
import com.happydog.vo.CategoryVO;

public class ProductDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//상품(1개) 상세 보기
	public Product getProduct(String pcode){
		Product pro = new Product();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SELECT_ONE);
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();
			if(rs.next()){
				pro.setPcode(rs.getString("pcode"));
				pro.setPname(rs.getString("pname"));
				pro.setPstd(rs.getString("pstd"));
				pro.setPprice(rs.getInt("pprice"));
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
		return pro; }
	
	//상품 전체 목록 불러오기
	public ArrayList<Product> getProductList(){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SELECT_ALL);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setPcode(rs.getString("pcode"));
				pro.setPname(rs.getString("pname"));
				pro.setPstd(rs.getString("pstd"));
				pro.setPprice(rs.getInt("pprice"));
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
	
	//카테고리별 제품목록 로딩
	public ArrayList<Product> getCateProductList(String cate){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATE_SELECT);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setPcode(rs.getString("pcode"));
				pro.setPname(rs.getString("pname"));
				pro.setPstd(rs.getString("pstd"));
				pro.setPprice(rs.getInt("pprice"));
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
		return proList; }
	
	//관리자용 카테고리별 제품목록 로딩
	public ArrayList<Product> getAdminCateProductList(String cate){
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_CATE_SELECT3);
			pstmt.setString(1, cate);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setPcode(rs.getString("pcode"));
				pro.setPname(rs.getString("pname"));
				pro.setPstd(rs.getString("pstd"));
				pro.setPprice(rs.getInt("pprice"));
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
	
	//관리자용 품절 상품 목록 불러오기
	public ArrayList<Product> getSoldoutProductList() {
		ArrayList<Product> proList = new ArrayList<Product>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.PRODUCT_SOLDOUT_SELECT);
			rs = pstmt.executeQuery();
			while(rs.next()){
				Product pro = new Product();
				pro.setPcode(rs.getString("pcode"));
				pro.setPname(rs.getString("pname"));
				pro.setPstd(rs.getString("pstd"));
				pro.setPprice(rs.getInt("pprice"));
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
		return proList; }

	//카테고리 로딩
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
	
	//대분류 코드 반환
		public ArrayList<CategoryVO> getFirstCategoryList(){
			ArrayList<CategoryVO> cateList = new ArrayList<CategoryVO>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.FIRST_CATEGORY_SELECT);
				rs = pstmt.executeQuery();
				while(rs.next()){
					CategoryVO cate = new CategoryVO();
					cate.setCt(rs.getString("ct"));
					cate.setCategroup(rs.getString("categroup"));
					cateList.add(cate);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return cateList; }
		
		//중분류 코드 반환
		public ArrayList<CategoryVO> getSecondCategoryList(String ct){
			ArrayList<CategoryVO> cateList = new ArrayList<CategoryVO>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.SECOND_CATEGORY_SELECT);
				pstmt.setString(1, ct);
				rs = pstmt.executeQuery();
				while(rs.next()){
					CategoryVO cate = new CategoryVO();
					cate.setCate(rs.getString("cate"));
					cate.setCatename(rs.getString("catename"));
					cateList.add(cate);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return cateList; }
		
		//상품 코드 발생기
		public String getProductCodeGenerator(String cate){
			String pcode = "";
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.PCODE_GENERATOR);
				pstmt.setString(1, cate);
				rs = pstmt.executeQuery();
				if(rs.next()){
					pcode = rs.getString("pcode").substring(4);
				} else {
					pcode = "0";
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			int tmp = 0;
			if(pcode==null){
				pcode = tmp + "0001";
			} else {
				tmp = Integer.parseInt(pcode) + 1;
				if(tmp>=1000){
					pcode = tmp + "";
				} else if(tmp>=100) {
					pcode = "0" + tmp;
				} else if(tmp>=10) {
					pcode = "00" + tmp;
				} else {
					pcode = "000" + tmp;
				}			
			}
			return pcode; }
	
	//상품 등록
	public int insertProduct(Product pro){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.INSERT_PRODUCT);
			pstmt.setString(1, pro.getPcode());
			pstmt.setString(2, pro.getPname());
			pstmt.setString(3, pro.getPstd());
			pstmt.setInt(4, pro.getPprice());
			pstmt.setString(5, pro.getPcom());
			pstmt.setInt(6, pro.getAmount());
			pstmt.setString(7, pro.getPic1());
			pstmt.setString(8, pro.getPic2());
			pstmt.setString(9, pro.getPic3());
			pstmt.setString(10, pro.getCate());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e){ e.printStackTrace();			
		} catch (Exception e){ e.printStackTrace(); }
		Oracle11.close(pstmt, con);
		return cnt; }
	
	//입고 처리
	public int receiptProduct(String pcode, int amount, int pprice) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.RECEIPT_PRODUCT);
			pstmt.setInt(1, amount);
			pstmt.setInt(2, pprice);
			pstmt.setString(3, pcode);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}
	
	//상품 수정
	public int updateProduct(Product pro){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.UPDATE_PRODUCT2);
			pstmt.setString(1, pro.getPname());
			pstmt.setString(2, pro.getPstd());
			pstmt.setInt(3, pro.getPprice());
			pstmt.setString(4, pro.getPcom());
			pstmt.setInt(5, pro.getAmount());
			pstmt.setString(6, pro.getPic1());
			pstmt.setString(7, pro.getPic2());
			pstmt.setString(8, pro.getPic3());
			pstmt.setString(9, pro.getCate());
			pstmt.setString(10, pro.getPcode());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { e.printStackTrace();
		} catch (SQLException e){ e.printStackTrace();			
		} catch (Exception e){ e.printStackTrace(); }
		Oracle11.close(pstmt, con);
		return cnt; }
	
	//상품 삭제
	public int deleteProduct(String pcode) {
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DELETE_PRODUCT);
			pstmt.setString(1, pcode);
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
	
	//상품 판매
	public int salesProduct(String pcode, int amount){
		int cnt =0 ;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.SALES_PRODUCT);
			pstmt.setInt(1, amount);
			pstmt.setString(2, pcode);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) { //오라클 JDBC 클래스가 없거나 경로가 다른 경우 발생
			e.printStackTrace();
		} catch (SQLException e){	//sql 구문이 틀린 경우 발생
			e.printStackTrace();			
		} catch (Exception e){	//알 수 없는 예외인 경우 발생
			e.printStackTrace();
		}
		Oracle11.close(pstmt, con);
		return cnt;
	}
	
}