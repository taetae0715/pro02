-- 두 번째 프로젝트인 쇼핑몰 프로젝트에 속하는 모든 테이블을 만들고, 더미 데이터를 추가하도록 한다.

-- 회원(user1)
--연번	컬럼참조	컬럼명	타입	크기	제약조건	기타
--1	아이디	id	varchar2	20	primary key	-
--2	비밀번호	pw	varchar2	300	not null	-
--3	회원명	name	varchar2	20	-	-
--4	연락처	tel	varchar2	13	-	-
--5	주소	addr	varchar2	300	-	-
--6	이메일	email	varchar2	50	-	-
--7	가입일	regdate	date	-	sysdate	default

create table user1(id varchar2(20) primary key,
pw varchar2(300) not null, name varchar2(20), tel varchar2(13),
addr varchar2(300), email varchar2(50), regdate date default sysdate);


--상품(product)						
--연번	컬럼참조	컬럼명	타입	크기	제약조건	기타
--1	상품코드	pcode	varchar2	8	primary key	-
--2	상품명	pname	varchar2	50	not null	-
--3	상품규격	pstd	varchar2	40	-	-
--4	단가	price	int	-	not null	-
--5	상품설명	pcom	varchar2	500	-	-
--6	수량	amount	int	-	not null	-

create table product(pcode varchar2(8) primary key,
pname varchar2(50) not null, pstd varchar2(40), price int not null,
pcom varchar2(500), amount int not null);


--주문(buy)						
--연번	컬럼참조	컬럼명	타입	크기	제약조건	기타
--1	주문번호	ocode	varchar2	8	primary key	-
--2	아이디	id	varchar2	20	foreign key	user
--3	상품코드	pcode	varchar2	8	foreign key	product
--4	수량	amount	int	-	1	default
--5	구입가	price	int	-	-	-
--6	구입일	odate	date	-	sysdate	default
--7	배송상태	ostate	varchar2	20	배송전	default
--8	연락처	tel	varchar2	13	not null	-
--9	택배사	dname	varchar2	30	-	-
--10	도착지 주소	addr	varchar2	300	not null	-
--11	화물코드	dcode	varchar2	20	-	-

create table buy(ocode varchar2(8) primary key,
id varchar2(20), pcode varchar2(8), amount int default 1,
price int, odate date default sysdate, ostate varchar2(20) default '배송전',
tel varchar2(13) not null, dname varchar2(30), addr varchar2(300) not null,
dcode varchar2(20),
constraint user_fk foreign key (id) references user1(id),
constraint product_fk foreign key (pcode) references product(pcode)
);



--장바구니(basket)						
--연번	컬럼참조	컬럼명	타입	크기	제약조건	기타
--1	순번	bnum	varchar2	8	primary key	-
--2	아이디	id	varchar2	20	foreign key	user
--3	상품코드	pcode	varchar2	8	foreign key	product
--4	수량	amount	int	-	not null	-

create table basket(bnum varchar2(8) primary key,
id varchar2(20), pcode varchar2(8), amount int not null,
constraint usb_fk foreign key (id) references user1(id),
constraint prb_fk foreign key (pcode) references product(pcode)
);



--결제(payment)						
--연번	컬럼참조	컬럼명	타입	크기	제약조건	기타
--1	결제번호	pnum	varchar2	8	primary key	-
--2	아이디	id	varchar2	20	foreign key	user
--3	주문번호	ocode	varchar2	8	foreign key	buy
--4	결제수단	ptype	varchar2	20	not null	-
--5	결제 수단 번호	ptnum	varchar2	20	not null	-
--6	결제금액	pprice	int	-	not null	-
--7	결제일	pdate	date	-	sysdate	default

create table payment(pnum varchar2(8) primary key,
id varchar2(20), ocode varchar2(8), ptype varchar2(20) not null,
ptnum varchar2(20) not null, pprice int not null,
pdate date default sysdate,
constraint usp_fk foreign key (id) references user1(id),
constraint byp_fk foreign key (ocode) references buy(ocode)
);



--이용후기(review)						
--연번	컬럼참조	컬럼명	타입	크기	제약조건	기타
--1	글번호	rcode	varchar2	8	primary key	-
--2	아이디	id	varchar2	20	foreign key	user
--3	주문번호	ocode	varchar2	8	foreign key	buy
--4	작성일	resdate	date	-	sysdate	default
--5	이용후기	rcontent	varchar2	500	not null	-
--6	별점	rpoint	int	-	5	default

create table review(rcode varchar2(8) primary key,
id varchar2(20), ocode varchar2(8), resdate date default sysdate,
rcontent varchar2(500) not null, rpoint int default 5,
constraint usr_fk foreign key (id) references user1(id),
constraint byr_fk foreign key (ocode) references buy(ocode)
);

commit;

-- 더미 데이터 추가하기
select * from user1;
-- user1(회원) 테이블에 기본값을 0으로하는 컬럼 point를 추가하여라
alter table user1 add point number default 0;
-- user1(회원) 테이블에 기본값을 0으로하는 컬럼 vsited을 추가하여라
alter table user1 add visited number default 0;
insert into user1 values ('kbs','4321','김병수','010-4321-4321','경기도 고양시 일산동구','kbs@naver.com', default, default, default);
insert into user1 values ('mbc','1111','문범철','010-1111-1111','경기도 파주시 문산읍','mbc@daum.net', default, default, default);
insert into user1 values ('sbs','3333','사본석','010-3333-3333','경기도 김포시 통진읍','sbs@gmail.com', default, default, default);
insert into user1 values ('jtb','2222','정태병','010-2222-2222','경기도 파주시 월롱면','jtb@hotmail.co.kr', default, default, default);
insert into user1 values ('kfm','7979','강편명','010-7979-7979','경기도 고양시 일산서구','kfm@ssangyoung.co.kr', default, default, default);

select * from product;
-- 상품 이미지 필드 추가
alter table product add pic1 varchar2(200);
alter table product add pic2 varchar2(200);
alter table product add pic3 varchar2(200);
insert into product values ('4192','시장학 개론','149X210X36',25000,'스노우폭스북스 · 2023년 04월 19일',10,'./img/4192.jpg','./img/4192_1.jpg','./img/4192_2.jpg');
insert into product values ('6186','살 때, 팔 때, 벌 때','152X225',22000,'21세기북스 · 2023년 02월 28일',12,'./img/6186.jpg','./img/6186_1.jpg','./img/6186_2.jpg');
insert into product values ('5072','흔한 남매 13','148X210',14500,'미래엔아이세움 · 2023년 04월 25일',8,'./img/5072.jpg','./img/5072_1.jpg','./img/5072_2.jpg');
insert into product values ('1352','고래','153X224X35',15000,'문학동네 · 2014년 04월 16일',13,'./img/1352.jpg','./img/1352_1.jpg','./img/1352_2.jpg');
insert into product values ('8138','파이썬 데이터 분석','183X235',39000,'한빛미디어 · 2023년 05월 01일',5,'./img/8138.jpg','./img/8138_1.jpg','./img/8138_2.jpg');
insert into product values ('8162','Servlet&JSP 프로그래밍','170X232X28',27000,'쌤즈 · 2022년 09월 19일',6,'./img/8162.jpg','./img/8162_1.jpg','./img/8162_2.jpg');
alter table product add utburl varchar2(500);       -- 유튜브 링크 추가
update product set utburl='https://www.youtube.com/embed/oVOcPt4eR9A' where pcode='4192';
update product set utburl='https://www.youtube.com/embed/qL3umFVNCU0' where pcode='6186';
update product set utburl='https://www.youtube.com/embed/YINtNRMGwQs' where pcode='5072';
update product set utburl='https://www.youtube.com/embed/hIpfM5QqZhA' where pcode='1352';
update product set utburl='https://www.youtube.com/embed/c0s_QYrsXZs' where pcode='8138';
update product set utburl='https://www.youtube.com/embed/x1wBBms7n9w' where pcode='8162';
alter table product add bookidx varchar2(1000);     -- 목차 추가
update product set bookidx='1장 가장 현실적이며 시급하게 묻다<br>2장 더 단단한 사장이 되는데 필요한 생각에 대한 ‘생각’<br>3장 직원 - 그들은 누구인가!<br>4장 사업을 넘어 기업으로<br>5장 자신만의 철학을 세워라<br>6장 사장이 되기로 결심한 그대' where pcode='4192';
update product set bookidx='PART 1 연금술에 빠진 개미들, 법칙이 아닌 원칙이 필요하다<br>PART 2 지표에 근거한 투자 전략<br>PART 3 무엇이든 살 수 있는 시장에서 뭘 사야 할지 모르는 투자자들<br>PART 4 투자, 채워가는 것이 아닌 비워내는 과정' where pcode='6186';
update product set bookidx='01화 내 방에서 나가라고~!<br>02화 방학 막바지에 이런 사람 꼭 있다!<br>03화 서프라이즈 장난 대결<br>04화 흔한남매, 반 배정 망하다?!<br>05화 오싹오싹 폭풍 공감 이야기<br>06화 소풍 도시락 싸기 대작전!<br>07화 으뜸이를 위한 K-참교육!<br>08화 오빠 친구, 장민철<br>09화 꿈속의 꿈속의 꿈속의 꿈?!<br>10화 데이지의 성장 앨범<br>11화 바둑이를 찾습니다!' where pcode='5072';
update product set bookidx='1부 부두<br>2부 평대<br>3부 공장<br><br>심사평<br><br>수상작가 인터뷰 : 이야기, 혹은 소설의 미래<br>수상소감' where pcode='1352';
update product set bookidx='CHAPTER 1 시작하기 전에<br>CHAPTER 2 파이썬 기초, Ipython과 주피터 노트북<br>CHAPTER 3 내장 자료구조, 함수, 파일<br>CHAPTER 4 넘파이 기본: 배열과 벡터 연산<br>CHAPTER 5 판다스 시작하기<br>CHAPTER 6 데이터 로딩과 저장, 파일 형식<br>CHAPTER 7 데이터 정제 및 준비<br>CHAPTER 8 데이터 준비하기: 조인, 병합, 변형<br>CHAPTER 9 그래프와 시각화<br>CHAPTER 10 데이터 집계와 그룹 연산<br>CHAPTER 11 시계열<br>CHAPTER 12 파이썬 모델링 라이브러리<br>CHAPTER 13 데이터 분석 예제<br>APPENDIX A 고급 넘파이<br>APPENDIX B IPython 시스템 더 알아보기' where pcode='8138';
update product set bookidx='01장 개발 환경 설정<br>02장 HTTP 프로토콜과 요청 방식<br>03장 서블릿 객체와 라이프 사이클<br>04장 JDBC 프로그래밍<br>05장 사용자 요청 처리<br>06장 서블릿 핵심 객체<br>07장 서블릿을 이용한 게시판 구현<br>08장 상태 정보 유지<br>09장 서블릿 객체와 정보 공유<br>10장 필터와 리스너<br>11장 JSP 개요 및 스크립트 기반 태그<br>12장 내장 객체와 게시판 프로그램 구현<br>13장 MVC 디자인 패턴 적용<br>14장 MVC 프레임워크 적용<br>15장 EL과 JSTL<br>' where pcode='8162';

-- 제품 이미지 저장하기 및 SNS URL, 목차 추가하기

select * from buy;
insert into buy values('10001','kbs','4192',1,25000,default,'배송완료','010-4321-4321','CJ대한통운','경기도 고양시 일산동구', 'c123456');
insert into buy values('10002','sbs','1352',1,15000,default,'배송완료','010-3333-3333','로젠택배','경기도 김포시 통진읍', 'r781245');
insert into buy values('10003','kfm','8162',2,54000,default,'배송전','010-7979-7979','CJ대한통운','경기도 고양시 일산서구', 'c421357');
insert into buy values('10004','kbs','8138',1,39000,default,'배송중','010-4321-4321','한진택배','경기도 고양시 일산동구', 'h697812');

select * from basket;
insert into basket values('10001','mbc','6186',1);
insert into basket values('10002','kbs','5072',1);
insert into basket values('10003','jtb','8138',1);
insert into basket values('10004','sbs','8162',1);

select * from payment;
insert into payment values ('10001','kbs','10001','신용카드','123-1234-123456',25000,default);
insert into payment values ('10002','sbs','10002','계좌이체','083-21-467895',15000,default);
insert into payment values ('10003','kfm','10003','신용카드','462-127-9123458',54000,default);
insert into payment values ('10004','kbs','10004','체크카드','214-31-415617',39000,default);

select * from review;
insert into review values('10001','kbs','10001',default,'책 잘 받았습니다.',5);
insert into review values('10002','sbs','10002',default,'좋은 책 잘 읽겠습니다.',4);

-- 공지사항 테이블 생성
create table notice(idx int primary key,
title varchar2(100) not null,
content varchar2(1000),
author varchar2(20),
file1 varchar2(200),
resdate date default sysdate
);

create sequence noti_seq;

insert into notice values (noti_seq.nextval, '더미 게시글 제목1', '더미 게시글1에 대한 내용입니다. 참고 하세요',
'관리자', '', default);
insert into notice values (noti_seq.nextval, '더미 게시글 제목2', '더미 게시글2에 대한 내용입니다. 참고 하세요',
'관리자', '', default);
insert into notice values (noti_seq.nextval, '더미 게시글 제목3', '더미 게시글3에 대한 내용입니다. 참고 하세요',
'관리자', 'data/HTTP 상태 코드.pdf', default);

-- 읽은 횟수 추가
alter table notice add readcnt int default 0;

-- 모든 컬럼 검색 쿼리
select * from notice;

-- 특정 레코드 검색 쿼리
select * from notice where idx=?;

-- 레코드 추가
insert into notice values (noti_seq.nextval, ?, ?, ?, ?, default);

-- 레코드 갱신 쿼리
update notice set title=?, content=?, file1=?, resdate=sysdate where idx=?;

-- 레코드 삭제 쿼리
delete from notice where idx=?;

desc notice;
commit;
