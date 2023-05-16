package com.happydog.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Oracle11 {
	static String DRIVER = "oracle.jdbc.driver.OracleDriver";
	static String URL = "jdbc:oracle:thin:@localhost:1521:xe";
	static String USER = "system";
	static String PASS = "1234";
	
	//공지사항 notice
	final static String NOTICE_SELECT_ALL = "select * from notice order by idx desc";
	final static String NOTICE_SELECT_ONE = "select * from notice where idx=?";
	final static String NOTICE_READCOUNT_UPDATE = "update notice set readcnt=readcnt+1 where idx=?";
	final static String INSERT_NOTICE = "insert into notice values (idx.nextval, ?, ?, ?, ?, default, default)";
	final static String UPDATE_NOTICE = "update notice set title=?, content=?, file1=?, resdate=sysdate where idx=?";
	final static String UPDATE_NOTICE2 = "update notice set title=?, content=?, resdate=sysdate where idx=?";
	final static String DELETE_NOTICE = "delete from notice where idx=?";
	
	//회원 user
	final static String USER_SELECT_ALL = "select * from user1 order by regdate desc";
	final static String USER_LOGIN =  "select * from user1 where id=?";
	final static String USER_VISIT_COUNT = "update user1 set visited=visited+1 where id=?";
	final static String INSET_USER = "insert into user1(id, pw, name, tel, addr, email) values (?,?,?,?,?,?)";
	final static String UPDATE_USER = "update user1 set pw=?, name=?, tel=?, addr=?, email=? where id=?";
	final static String UPDATE_USER2 = "update user1 set name=?, tel=?, addr=?, email=? where id=?";
	final static String DELETE_USER = "delete from user1 where id=?";
	final static String USER_SELECT_TEL = "select tel from user1 where id=?";
	final static String UPDATE_PW_RESET = "update user1 set pw=? where id=?";
	
	//상품 product
	final static String PRODUCT_CATENAME_SELECT = "select * from category where cate like ?||'%'";
	final static String PRODUCT_SELECT_ALL = "select * from product order by cate desc";
	final static String PRODUCT_SELECT_ONE = "select * from product where pcode=?";
	final static String PRODUCT_SOLDOUT_SELECT = "select * from product where amount<=0";
	final static String PRODUCT_CATE_SELECT = "select * from product where cate=?";
	final static String PRODUCT_CATE_SELECT2 = "select * from product where cate like ?||'%'";
	final static String PRODUCT_CATE_SELECT3 = "select * from product where cate like concat(?, '%')";
	
	final static String FIRST_CATEGORY_SELECT = "select distinct substr(cate,1,2) as ct, categroup from category group by substr(cate,1,2), categroup order by ct";
	final static String SECOND_CATEGORY_SELECT = "select cate, catename from category where cate like ?||'%' order by cate";
	final static String PCODE_GENERATOR = "select pcode from (select * from product where cate=? order by pcode desc) where rownum = 1";
	
	final static String INSERT_PRODUCT = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
	final static String RECEIPT_PRODUCT = "update product set amount=amount+?, pprice=? where pcode=?";
	final static String UPDATE_PRODUCT = "update product set amount=amount+?, pprice=? where pcode=?";
	final static String UPDATE_PRODUCT2 = "update product set pname=?, pstd=?, pprice=?, pcom=?, amount=?, pic1=?, pic2=?, pic3=?, cate=? where pcode=?";
	final static String SALES_PRODUCT = "update product set amount=amount-? where pcode=?";
	final static String DELETE_PRODUCT = "delete from product where pcode=?";
	
	final static String NOT_SALES_PRODUCT = "select * from product where pcode not in (select pcode from order1)";
	final static String CATEGORY_ALL = "select * from category order by cate asc";
	final static String CATEGORY_SELECT = "select * from category where categroup=? order by cate asc";
	
	//장바구니 cart
	final static String CART_SELECT_ALL = "select * from cart order by cno desc";
	final static String CART_SELECT_ALL2 = "select cart.cno as cno, cart.id as id, user1.name as name, cart.pcode as pcode, product.pname as pname, cart.amount as amount, product.pprice as pprice from cart, user1, product where cart.id=user1.id and cart.pcode=product.pcode";
	final static String CART_SELECT_BYID = "select * from basket where id=?";
	final static String CART_SELECT_BYID2 = "select cart.cno as cno, cart.id as id, user1.name as name, cart.pcode as pcode, product.pname as pname, cart.amount as amount, product.pprice as pprice from cart, user1, product where cart.id=user1.id and cart.pcode=product.pcode and cart.id=?";
	final static String CART_SELECT_BYPRODUCT = "select * from cart where pcode=?";
	final static String CART_SELECT_BYCNO = "select * from cart where cno=?";
	final static String INSERT_CART = "insert into cart values (?,?,?,?)";
	final static String DEL_CART = "delete from cart where cno=?";
	final static String CNO_GENERATOR = "select cno from (select cno from cart order by cno desc) where rownum = 1";
	
	//판매 sales
	final static String OCODE_GENERATOR = "select ocode from (select * from order1 order by ocode desc) where rownum = 1";
	final static String PNO_GENERATOR = "select pno from (select * from pay order by pno desc) where rownum = 1";
	//위에 두 코드는 주문번호, 결제번호 생성 코드인데 나는 시퀀스로 설정했기 때문에 생략하려고 했는데 null 나옴 그래서 그냥 생성했다~~
	final static String ADD_SALES = "insert into order1 values (?,?,?,?,?,default,?,?,?,?,?)";
	final static String ADD_PAY = "insert into pay values (?,?,?,?,?,?,default)";
	final static String BUY_TRANS_CART = "delete from cart where cno=?";
	
	final static String BYID_BUY_LIST = "select * from order1 where id=? order by ocode desc";
	final static String BYID_BUY = "select * from order1 where id=? and ocode=?";
	final static String PAY_LIST = "select * from pay order by pno desc";
	final static String BYOCODE_PAY = "select * from pay where ocode=? order by pno desc";
	
	final static String SALES_LIST = "select order1.ocode as ocode, order1.id as id, order1.pcode as pcode, order1.amount as amount, order1.price as price, order1.odate as odate, order1.ostate as ostate, order1.tel as tel, order1.dname as dname, order1.addr as addr, order1.dcode as dcode, pay.pno as pno, pay.type as type, pay.type_no as type_no from order1, pay where pay.ocode=order1.ocode order by order1.ocode";
	final static String SALES_INFO = "select order1.ocode as ocode, order1.id as id, order1.pcode as pcode, order1.amount as amount, order1.price as price, order1.odate as odate, order1.ostate as ostate, order1.tel as tel, order1.dname as dname, order1.addr as addr, order1.dcode as dcode, pay.pno as pno, pay.type as type, pay.type_no as type_no from order1, pay where pay.ocode=order1.ocode and order1.ocode=? order by order1.ocode";
	final static String BYID_SALES_LIST = "select order1.ocode as ocode, order1.id as id, order1.pcode as pcode, order1.amount as amount, order1.price as price, order1.odate as odate, order1.ostate as ostate, order1.tel as tel, order1.dname as dname, order1.addr as addr, order1.dcode as dcode, pay.pno as pno, pay.type as type, pay.type_no as type_no from order1, pay where pay.ocode=order1.ocode and order1.id=? order by order1.ocode";
	final static String BYID_GET_SALE = "select order1.ocode as ocode, order1.id as id, order1.pcode as pcode, order1.amount as amount, order1.price as price, order1.odate as odate, order1.ostate as ostate, order1.tel as tel, order1.dname as dname, order1.addr as addr, order1.dcode as dcode, pay.pno as pno, pay.type as type, pay.type_no as type_no from order1, pay where pay.ocode=order1.ocode and id=? and order1.ocode=?";
	
	final static String UPDATE_SURVEY = "update order1 set dname=?, dcode=?, ostate=? where ocode=?";
	final static String DELETE_ORDER = "delete from order1 where ocode=?";
	final static String DELETE_PAY = "delete from pay where ocode=?";
	
	final static String RETURN_PRODUCT = "update product set amount=amount+? where pcode=?";
	final static String RETURN_SALES = "update order1 set ostate='반품요청' where ocode=?";
	final static String OK_SALES = "update order1 set ostate='구매완료' where ocode=?";
	
	//DB 연결
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		Connection conn = DriverManager.getConnection(URL, USER, PASS);
		return conn; }
	
	public static void close(PreparedStatement pstmt, Connection conn){
		if(pstmt!=null){
			try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
		if(conn!=null){
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } } }
	
	public static void close(ResultSet rs, PreparedStatement pstmt, Connection conn){
		if(rs!=null){
			try { rs.close(); } catch (SQLException e) { e.printStackTrace(); } }
		if(pstmt!=null){
			try { pstmt.close(); } catch (SQLException e) { e.printStackTrace(); } }
		if(conn!=null){
			try { conn.close(); } catch (SQLException e) { e.printStackTrace(); } } }
}
