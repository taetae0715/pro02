select * from member;
select * from board;
insert into member VALUES ('admin', '1234', '관리자', '010-1234-1234', 'admin@admin.com', '고양시 덕양구', default);
insert into board VALUES (DEFAULT, '더미글 제목1', '첫 번째 더미글에 대한 내용입니다.', 'admin', DEFAULT);
commit;








