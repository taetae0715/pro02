-- 모든 컬럼 검색 쿼리
select * from notice;
select * from user1;

-- 특정 레코드 검색 쿼리
select * from notice where idx=?;

-- 레코드 추가
insert into notice values (noti_seq.nextval, ?, ?, ?, ?, default);

-- 레코드 갱신 쿼리
update notice set title=?, content=?, file1=?, resdate=sysdate where idx=?;

-- 레코드 삭제 쿼리
delete from notice where idx=?

-- 회원 로그인
select * form user1 where id=? and pw=?;

-- 회원 로그인 시 방문횟수 증가
update user1 set visted=visted+1 where id=?

-- 아이디 중복 체크
select * from user1 where id=?

-- 회원가입
insert into user1(id, pw, name, tel, addr, email) values (?,?,?,?,?,?);

-- 회원정보수정
update user1 set pw=?, name=?, email=?, tel=?, addr=? where id=?;

-- 회원 탈퇴
delete from user1 where id=?;

select * from user1;

select * from user1 order by regdate desc;

select * from user1 where rownum >= 1 and rownum <= 5;

select * from user1 order by regdate desc where rownum >= 1 and rownum <= 5;

-- 회원 목록을 페이징 처리하는 경우 startNum과 endNum을 받아서 rownum으로 처리
select * from (select * from user1 order by regdate desc) where rownum >= 1 and rownum <= 5;

-- 비밀번호 암호화
update user1 set pw='JZgpBi2Co8IxFb8OHdR7hktb8Jyk4+4k5U3gkYvghsL0Mre9F3sth49pnw9OUs6Oa/V4sw==' where id='jtj'; --0715
update user1 set pw='mI1zXrXiyjxgT2K74LaYDV6SPDqqSVsz4rKhpYJtO2QbhyjWllP7al3hyX3YaV0hen6x9g==' where id='jws'; --0104
update user1 set pw='6sMJkwH+FhEl2GE2oym5BIqWPX9CjcnKwz3jDpaW6tvxKHW4wr2IvxNTIghQR+ErX0au2w==' where id='ysr'; --1023
update user1 set pw='Z1u38Q+OsEVloDHEKeeGbAvl/WgmhavF+MUD2kFuN+wXOMDzScrVwsD4Yhg2uUuY3yUHqA==' where id='smj'; --0505
update user1 set pw='N8ySvu4rhuRW0Hp7RPTHTJ+mBa5cZxdSJuQnuCdgSBApGUlLtAeUwGjCZDZz3do6u1+Y/Q==' where id='kdh'; --0614
update user1 set pw='u8vbIuRVkNY1+WkYuxsDiiwXnFG0WIIBa8yWTD0nDw8v6CDcjXbzidgiZmjUszavKAqyYQ==' where id='admin'; --1234
update user1 set pw='nXvwt1jTv9pZ4x7fQQNc5bI8WAMDAk9spaCv6NlFpLXsZxX1Jc49NEudVhmn7eD6Yyg/Bw==' where id='kmm'; --4321

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
insert into order1 VALUES (1004, 'smj',	8138, 4, '36000', '2023-04-24', '배송준비중',	 '010-7979-8282', '-', '서울시 은평구', '-');

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

