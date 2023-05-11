package com.happydog.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.happydog.dto.Cart;
import com.happydog.vo.CartVO;

public class CartDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//관리자용 장바구니 전체 목록 불러오기
	public ArrayList<CartVO> getCartList(){
	ArrayList<CartVO> cartList = new ArrayList<CartVO>();
	try {
		con = Oracle11.getConnection();
		pstmt = con.prepareStatement(Oracle11.CART_SELECT_ALL2);
		rs = pstmt.executeQuery();
		while(rs.next()){
			CartVO cart = new CartVO();
			cart.setCno(rs.getString("cno"));
			cart.setId(rs.getString("id"));
			cart.setName(rs.getString("name"));
			cart.setPcode(rs.getString("pcode"));
			cart.setPname(rs.getString("pname"));
			cart.setAmount(rs.getInt("amount"));
			cart.setPprice(rs.getInt("pprice"));
			cartList.add(cart);
		}
	} catch (ClassNotFoundException e) {e.printStackTrace();
	} catch (SQLException e) {e.printStackTrace();
	} finally { Oracle11.close(rs, pstmt, con); }
	return cartList; }
	
	//특정 사용자 장바구니 보기
	public ArrayList<CartVO> getByIdCartList(String id){
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.CART_SELECT_BYID2);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CartVO cart = new CartVO();
				cart.setCno(rs.getString("cno"));
				cart.setId(rs.getString("id"));
				cart.setName(rs.getString("name"));
				cart.setPcode(rs.getString("pcode"));
				cart.setPname(rs.getString("pname"));
				cart.setAmount(rs.getInt("amount"));
				cart.setPprice(rs.getInt("pprice"));
				cartList.add(cart);
			}
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Oracle11.close(rs, pstmt, con); }
		return cartList; }

	//특정 상품별 장바구니 보기
	public ArrayList<CartVO> getByProductCartList(String pcode){
		ArrayList<CartVO> cartList = new ArrayList<CartVO>();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.CART_SELECT_BYPRODUCT);
			pstmt.setString(1, pcode);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CartVO cart = new CartVO();
				cart.setCno(rs.getString("cno"));
				cart.setId(rs.getString("id"));
				cart.setPcode(rs.getString("pcode"));
				cart.setAmount(rs.getInt("amount"));
				cartList.add(cart);
			}
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Oracle11.close(rs, pstmt, con); }
		return cartList; }
	
	//장바구니 상세보기
	public Cart getCartDetail(String cno){
		Cart cart = new Cart();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.CART_SELECT_BYCNO);
			pstmt.setString(1, cno);
			rs = pstmt.executeQuery();
			if(rs.next()){
				cart.setCno(rs.getString("cno"));
				cart.setId(rs.getString("id"));
				cart.setPcode(rs.getString("pcode"));
				cart.setAmount(rs.getInt("amount"));
			}
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Oracle11.close(rs, pstmt, con); }
		return cart; }
	
	//장바구니 추가
	public int insertCart (Cart cart){
		int cnt=0;
		String cno = createCNO();
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.INSERT_CART);
			pstmt.setString(1, cno);
			pstmt.setString(2, cart.getId());
			pstmt.setString(3, cart.getPcode());
			pstmt.setInt(4, cart.getAmount());
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Oracle11.close(pstmt, con); }
		return cnt; }

	//장바구니 번호 발급
	public String createCNO() {
		String cno = "";
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.CNO_GENERATOR);
			rs = pstmt.executeQuery();
			if(rs.next()){
				cno = rs.getString("cno");
			}
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Oracle11.close(rs, pstmt, con); }
		
		int tmp = Integer.parseInt(cno) + 1;
		cno = tmp + "";
		
		return cno; }
	
	//장바구니 삭제
	public int deleteCart(String cno){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			pstmt = con.prepareStatement(Oracle11.DEL_CART);
			pstmt.setString(1, cno);
			cnt = pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Oracle11.close(pstmt, con); }
		return cnt;}
}


