-- System 계정에 공지사항(게시판) notice 테이블 생성
create table notice(idx int PRIMARY KEY, title varchar2(100) not null,
content varchar2(1000), author varchar2(20), file1 varchar2(200),
resdate date default sysdate, readcnt int default 0);
desc notice;
select * from notice;
create sequence idx start with 1 increment by 1 minvalue 1 maxvalue 9999 nocycle;
INSERT INTO notice VALUES (idx.nextval, '더미 테스트1', '내용 없음', '관리자', '다운로드', DEFAULT, DEFAULT);
INSERT INTO notice VALUES (idx.nextval, '더미 테스트2', '내용 없음', '관리자', '다운로드', DEFAULT, DEFAULT);
INSERT INTO notice VALUES (idx.nextval, '더미 테스트3', '내용 없음', '관리자', '다운로드', DEFAULT, DEFAULT);
INSERT INTO notice VALUES (idx.nextval, '더미 테스트4', '내용 없음', '관리자', '다운로드', DEFAULT, DEFAULT);
-- 테이블 내용
-- 1	더미 테스트1	내용 없음	관리자	다운로드	23/05/04	11
-- 2	더미 테스트2	내용 없음	관리자	다운로드	23/05/04	15
-- 3	더미 테스트3	내용 없음	관리자	다운로드	23/05/04	8
-- 4	더미 테스트3	내용 없음	관리자	다운로드	23/05/04	0
-- 5	더미 테스트4	내용 없음	관리자	다운로드	23/05/04	0

commit;

