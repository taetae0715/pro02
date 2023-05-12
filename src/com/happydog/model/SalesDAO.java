package com.happydog.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.happydog.dto.Cart;
import com.happydog.dto.Order;
import com.happydog.dto.Pay;
import com.happydog.vo.CartVO;

public class SalesDAO {
	private Connection con = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//판매, 구매, 장바구니, 상품  4가지 트랜잭션 작업 메소드
	public int addSales(Order order, Pay pay, String cno){
		int cnt = 0;
		try {
			con = Oracle11.getConnection();
			con.setAutoCommit(false);	//트랜잭션 처리시 오토커밋을 비활성화
			
			// 1. 판매(order)에 데이터 추가
			pstmt = con.prepareStatement(Oracle11.ADD_SALES);
			pstmt.setInt(1, order.getOcode());
			pstmt.setString(2, order.getId());
			pstmt.setString(3, order.getPcode());
			pstmt.setInt(4, order.getAmount());
			pstmt.setString(5, order.getPrice());
			pstmt.setString(6, order.getOstate());
			pstmt.setString(7, order.getTel());
			pstmt.setString(8, order.getDname());
			pstmt.setString(9, order.getAddr());
			pstmt.setString(10, order.getDcode());
			cnt = pstmt.executeUpdate();	
			
			// 2. 구매(pay)에 데이터 추가
			pstmt = con.prepareStatement(Oracle11.ADD_PAY);
			pstmt.setInt(1, pay.getPno());
			pstmt.setString(2, pay.getId());
			pstmt.setInt(3, pay.getOcode());
			pstmt.setString(4, pay.getType());
			pstmt.setString(5, pay.getType_no());
			pstmt.setInt(6, pay.getPrice());
			cnt = cnt + pstmt.executeUpdate();
			
			// 3. 상품(product)에서 구매한 상품 수량만큼 차감
			pstmt = con.prepareStatement(Oracle11.SALES_PRODUCT);
			pstmt.setInt(1, order.getAmount());
			pstmt.setString(2, order.getPcode());
			cnt = cnt + pstmt.executeUpdate();
			
			// 4. 장바구니(cart)에서 구매로 넘어간 경우
			if(cno!=null){
				pstmt = con.prepareStatement(Oracle11.DEL_CART);
				pstmt.setString(1, cno);
				cnt = cnt + pstmt.executeUpdate();
			}
			// 장바구니를 거치지 않고 바로 구매한 경우 cnt는 3이 되고,
			// 장바구니에서 구매했을 경우 cnt는 4가 되며, 해당 cno 삭제 처리도 이루어진다.
			
			con.commit();	//수동 커밋
			con.setAutoCommit(true);	//오토커밋 활성화
			
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Oracle11.close(pstmt, con); }
		return cnt; 
	}
}


