-- 쇼핑몰 프로젝트에 속하는 테이블에 더미 데이터 추가하기
select * from user1;        -- 1.회원
desc user1;  
insert into user1 VALUES ('admin', '1234', '관리자', '010-1234-1234', '서울시 마포구', 'admin@admin.com', default, default, default);
insert into user1 VALUES ('kmm', '4321', '김멍멍', '010-4321-4321', '김포시 양촌읍', 'kmm@kakao.com', default, default, default);
update user1 set regdate='2023-04-21' where id='kmm';
update user1 set regdate='2023-04-20' where id='admin';

select * from product;      -- 2.상품
desc  product; 

select * from order1;       -- 3.주문
desc  order1; 
insert into order1 VALUES (1001, 'kmm',	4207, 2, '11000', '2023-04-21', '배송완료',	 '010-4321-4321', 'CJ대한통운', '김포시 양촌읍', 'f828279');
insert into order1 VALUES (1002, 'jtj',	3993, 3, '18600', '2023-04-22', '배송완료',	 '010-9864-2514', '우체국', '고양시 덕양구', 'c456789');
insert into order1 VALUES (1003, 'ysr',	1352, 2, '23800', '2023-04-23', '배송중',	 '010-8163-0000', '우체국', '인천시 부평구', 'h125663');
insert into order1 VALUES (1004, 'smj',	8138, 4, '36000', '2023-04-24', '배송준비중',	 '010-7979-8282', null, '서울시 은평구', null);

select * from cart;         -- 4.장바구니
desc cart;
insert into cart VALUES (1001,	'kmm',	4207,	2);
insert into cart VALUES (1002,	'jtj',	3993, 3);
insert into cart VALUES (1003,	'ysr',	1352, 2);
insert into cart VALUES (1004,	'smj',	8138, 4);
insert into cart VALUES (1005,	'jws',	8162, 3);

select * from pay;          -- 5.결제
desc pay;
insert into pay VALUES (1001,	'kmm',	1001,	'신용카드',	'123-1234-123456',	'11000',	'2023-04-21');
insert into pay VALUES (1002,	'jtj',	1002,	'계좌이체',	'044-33-556677',	'18600',	'2023-04-22');
insert into pay VALUES (1003,	'ysr',	1003,	'체크카드',	'333-7894-5611',	'23800',	'2023-04-23');
insert into pay VALUES (1004,	'smj',	1004,	'신용카드',	'2233-4545-666',	'36000',	'2023-04-24');

select * from review;       -- 6.이용후기
desc review;
insert into review VALUES (1001,	'kmm',	1001,	'2023-04-22',	'껌이 딱딱해요.',	3);
insert into review VALUES (1002,	'jtj',	1002,	'2023-04-23',	'강아지 입맛이 돌아왔습니다.',	5);

select * from user1, product, order1
where user1.id=order1.id and product.pro_code=order1.pro_code;

