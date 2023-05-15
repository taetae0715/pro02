package com.happydog.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.happydog.dto.Order;
import com.happydog.dto.Pay;
import com.happydog.vo.SalesVO;

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
			// 장바구니를 거치지 않고 바로 구매한 경우 cnt는 3이 되고,
			// 장바구니에서 구매했을 경우 cnt는 4가 되며, 해당 cno 삭제 처리도 이루어진다.
			}
			
			con.commit();	//수동 커밋
			con.setAutoCommit(true);	//오토커밋 활성화
			
		} catch (ClassNotFoundException e) {e.printStackTrace();
		} catch (SQLException e) {e.printStackTrace();
		} finally { Oracle11.close(pstmt, con); }
		return cnt; 
	}
	
	//관리자의 판매+결제 전체 목록 로딩
		public ArrayList<SalesVO> getSalesList(){
			ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.SALES_LIST);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesVO sale = new SalesVO();
					sale.setOcode(rs.getInt("ocode"));
					sale.setId(rs.getString("id"));
					sale.setPcode(rs.getString("pcode"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setOdate(rs.getString("odate"));
					sale.setOstate(rs.getString("ostate"));
					sale.setTel(rs.getString("tel"));
					sale.setDname(rs.getString("dname"));
					sale.setAddr(rs.getString("addr"));
					sale.setDcode(rs.getString("dcode"));
					sale.setPno(rs.getInt("pno"));
					sale.setType(rs.getString("type"));
					sale.setType_no(rs.getString("type_no"));
					salesList.add(sale);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return salesList;
		}
		
		//관리자의 특정 판매 데이터 로딩 
		public SalesVO getSales(int ocode){
			SalesVO sale = new SalesVO();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.SALES_INFO);
				pstmt.setInt(1, ocode);
				rs = pstmt.executeQuery();
				if(rs.next()){
					sale.setOcode(rs.getInt("ocode"));
					sale.setId(rs.getString("id"));
					sale.setPcode(rs.getString("pcode"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setOdate(rs.getString("odate"));
					sale.setOstate(rs.getString("ostate"));
					sale.setTel(rs.getString("tel"));
					sale.setDname(rs.getString("dname"));
					sale.setAddr(rs.getString("addr"));
					sale.setDcode(rs.getString("dcode"));
					sale.setPno(rs.getInt("pno"));
					sale.setType(rs.getString("type"));
					sale.setType_no(rs.getString("type_no"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return sale;
		}
		
		//특정 사용자의 구매 목록 로딩
		public ArrayList<SalesVO> getByIdSalesList(String id){
			ArrayList<SalesVO> salesList = new ArrayList<SalesVO>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_SALES_LIST);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					SalesVO sale = new SalesVO();
					sale.setOcode(rs.getInt("ocode"));
					sale.setId(rs.getString("id"));
					sale.setPcode(rs.getString("pcode"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setOdate(rs.getString("odate"));
					sale.setOstate(rs.getString("ostate"));
					sale.setTel(rs.getString("tel"));
					sale.setDname(rs.getString("dname"));
					sale.setAddr(rs.getString("addr"));
					sale.setDcode(rs.getString("dcode"));
					sale.setPno(rs.getInt("pno"));
					sale.setType(rs.getString("type"));
					sale.setType_no(rs.getString("type_no"));
					salesList.add(sale);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return salesList;
		}
		
		//특정 사용자의 특정 판매 데이터 로딩
		public SalesVO getByIdSales(String id, int ocode){
			SalesVO sale = new SalesVO();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_SALES_LIST);
				pstmt.setString(1, id);
				pstmt.setInt(2, ocode);
				rs = pstmt.executeQuery();
				while(rs.next()){
					sale.setOcode(rs.getInt("ocode"));
					sale.setId(rs.getString("id"));
					sale.setPcode(rs.getString("pcode"));
					sale.setAmount(rs.getInt("amount"));
					sale.setPrice(rs.getInt("price"));
					sale.setOdate(rs.getString("odate"));
					sale.setOstate(rs.getString("ostate"));
					sale.setTel(rs.getString("tel"));
					sale.setDname(rs.getString("dname"));
					sale.setAddr(rs.getString("addr"));
					sale.setDcode(rs.getString("dcode"));
					sale.setPno(rs.getInt("pno"));
					sale.setType(rs.getString("type"));
					sale.setType_no(rs.getString("type_no"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return sale;
		}
		
		//판매 정보 목록만 로딩
		public ArrayList<Order> getByIdBuyList(String id){
			ArrayList<Order> orderList = new ArrayList<Order>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYID_BUY_LIST);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Order order = new Order();
					order.setOcode(rs.getInt("ocode"));
					order.setId(rs.getString("id"));
					order.setPcode(rs.getString("pcode"));
					order.setAmount(rs.getInt("amount"));
					order.setPrice(rs.getString("price"));
					order.setOdate(rs.getString("odate"));
					order.setOstate(rs.getString("ostate"));
					order.setTel(rs.getString("tel"));
					order.setDname(rs.getString("dname"));
					order.setAddr(rs.getString("addr"));
					order.setDcode(rs.getString("dcode"));
					orderList.add(order);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return orderList;
		}
		
		//모든 결제 정보 목록만 로딩
		public ArrayList<Pay> getByPayList(){
			ArrayList<Pay> payList = new ArrayList<Pay>();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.PAY_LIST);
				rs = pstmt.executeQuery();
				while(rs.next()){
					Pay pay = new Pay();
					pay.setPno(rs.getInt("pnum"));
					pay.setId(rs.getString("id"));
					pay.setOcode(rs.getInt("ocode"));
					pay.setType(rs.getString("type"));
					pay.setType_no(rs.getString("type_no"));
					pay.setPrice(rs.getInt("price"));
					pay.setPdate(rs.getString("pdate"));
					payList.add(pay);
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return payList;
		}
		
		
		//특정 사용자의 특정 결제 정보
		public Pay getByIdPay(int ocode){
			Pay pay = new Pay();
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.BYOCODE_PAY);
				pstmt.setInt(1, ocode);
				rs = pstmt.executeQuery();
				if(rs.next()){
					pay.setPno(rs.getInt("pnum"));
					pay.setId(rs.getString("id"));
					pay.setOcode(rs.getInt("ocode"));
					pay.setType(rs.getString("type"));
					pay.setType_no(rs.getString("type_no"));
					pay.setPrice(rs.getInt("price"));
					pay.setPdate(rs.getString("pdate"));
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			return pay;
		}
		
		public int surveyUpdate(Order order){
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.UPDATE_SURVEY);
				pstmt.setString(1, order.getDname());
				pstmt.setString(2, order.getDcode());
				pstmt.setString(3, order.getOstate());
				pstmt.setInt(4, order.getOcode());
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

		public int cancleSales(int ocode, String pcode, int amount) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				con.setAutoCommit(false);	//트랜잭션 처리시 오토커밋을 비활성화
				pstmt = con.prepareStatement(Oracle11.DELETE_PAY);
				pstmt.setInt(1, ocode);
				cnt = cnt + pstmt.executeUpdate();
				
				pstmt = con.prepareStatement(Oracle11.DELETE_ORDER);
				pstmt.setInt(1, ocode);
				cnt = pstmt.executeUpdate();

				pstmt = con.prepareStatement(Oracle11.RETURN_PRODUCT);
				pstmt.setInt(1, amount);
				pstmt.setString(2, pcode);
				cnt = cnt + pstmt.executeUpdate();
				
				con.commit();	//수동 커밋
				con.setAutoCommit(true);	//오토커밋 활성화
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(pstmt, con);
			}
			return cnt;
		}

		public int returnSales(int ocode) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.RETURN_SALES);
				pstmt.setInt(1, ocode);
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

		public int okSales(int ocode) {
			int cnt = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.OK_SALES);
				pstmt.setInt(1, ocode);
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
		
		//주문(판매)코드 생성
		public int getOcodeGenerator(){
			int ocode = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.OCODE_GENERATOR);
				rs = pstmt.executeQuery();
				if(rs.next()){
					ocode = rs.getInt("ocode");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			
			int tmp = ocode + 1;
			ocode = tmp;
			return ocode;
		}
		
		//결제 번호 생성
		public int getPnoGenerator(){
			int pno = 0;
			try {
				con = Oracle11.getConnection();
				pstmt = con.prepareStatement(Oracle11.PNO_GENERATOR);
				rs = pstmt.executeQuery();
				if(rs.next()){
					pno = rs.getInt("pno");
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				Oracle11.close(rs, pstmt, con);
			}
			
			int tmp = pno + 1;
			pno = tmp;
			return pno;
		}
	}


