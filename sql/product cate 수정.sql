-- 데이터 변경을 위해서 참조 무결성 해제(외래키 제거)
ALTER TABLE order1 DROP CONSTRAINT PRO_FK;
ALTER TABLE cart DROP CONSTRAINT cart_fk2;
ALTER TABLE payment DROP CONSTRAINT byp_fk;
select * from product;
desc product;
--update product set cate='0422', pcode='04220001', pic1='./img/04220001.jpg', pic2='./img/04220001_1.jpg', pic3='./img/04220001_2.jpg' where pcode='4192';
update product set cate='0103', pro_code=01030001, pic1='./img/01030001.jpg', pic2='./img/01030001_2.jpg', pic3='./img/01030001_3.jpg' where pro_code='9852';
update product set cate='0101', pro_code=01010001, pic1='./img/01010001.jpg', pic2='./img/01010001_2.jpg', pic3='./img/01010001_3.jpg' where pro_code='4207';

commit;
INSERT INTO product VALUES ('01010001', '돼지등갈비껌', '120g', 5500, '구강관리, 치석제거, 스트레스완화', 12,
'img/01010001.jpg', 'img/01010001_2.jpg', 'img/01010001_3.jpg', '0101');
INSERT INTO product VALUES ('01020001', '오리황태말이', '160g', 6200, '영양공급, 눈건강, 항산화, 피모관리, 면역력강화', 8,
'img/01020001.jpg', 'img/01020001_2.jpg', 'img/01020001_3.jpg', '0102');
INSERT INTO product VALUES ('01020002', '닭가슴살육포', '200g', 11900, '영양공급, 저알러지, 스트레스완화, 면역력강화', 13,
'img/01020002.jpg', 'img/01020002_2.jpg', 'img/01020002_3.jpg', '0102');
INSERT INTO product VALUES ('01030001', '무염황태채', '50g', 5400, '영양공급, 항산화, 스트레스완화, 식욕증진(기호성)', 6,
'img/01030001.jpg', 'img/01030001_2.jpg', 'img/01030001_3.jpg', '0103');
INSERT INTO product VALUES ('01030002', '고구마말랭이', '100g', 4800, '영양공급, 스트레스완화, 항산화, 분리불안해소, 면역력강화', 10,
'img/01030002.jpg', 'img/01030002_2.jpg', 'img/01030002_3.jpg', '0103');
INSERT INTO product VALUES ('01050001', '오리목뼈', '200g', 9000, '치석제거, 구강관리, 관절강화, 분리불안해소, 스트레스완화', 5,
'img/01050001.jpg', 'img/01050001_2.jpg', 'img/01050001_3.jpg', '0105');
INSERT INTO product VALUES ('02090001', '하네스/리드줄', '1세트', 30000, '산책할 때 사용하는 하네스/리드줄 세트입니다.', 3,
'img/02090001.jpg', 'img/02090001_2.jpg', 'img/02090001_3.jpg', '0209');
commit;

update product set pic1='img/02070001.jpg' where pname='의자';
update product set pic1='img/02040001.jpg' where pname='깔끔한 강쥐';

select * from cart;
delete from cart;
desc cart;
select * from user1;
insert into cart values ('1001',	'kmm',	'01030002',	2);
insert into cart values('1002',	'jtj',	'01050001',	3);
insert into cart values('1003',	'jtj',	'02090001',	1);
insert into cart values('1004',	'ysr',	'01030001',	2);
insert into cart values('1005',	'smj',	'01030002',	4);
insert into cart values('1006',	'jws',	'01020002',	3);
insert into cart values('1007',	'jws',	'01020001',	2);
commit;