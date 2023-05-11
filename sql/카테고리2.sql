desc notice;
desc product;
desc category;
select * from notice;
select * from product;
select * from category;
alter table notice add constraint noti_fk foreign key (author) references user1 (id);
update notice set author='admin' WHERE author='관리자';
commit;

alter table product add cate VARCHAR2(10);

CREATE table category(cate VARCHAR2(10) primary key, categroup VARCHAR2(50) not null, catename VARCHAR2(100));
insert into category values('0101', '수제간식', '껌');
insert into category values('0102', '수제간식', '저키/스틱/사시미');
insert into category values('0103', '수제간식', '건조간식');
insert into category values('0104', '수제간식', '베이커리');
insert into category values('0105', '수제간식', '특식');

insert into category values('0201', '강아지용품', '사료');
insert into category values('0202', '강아지용품', '영양제');
insert into category values('0203', '강아지용품', '위생/배변용품');
insert into category values('0204', '강아지용품', '미용/목욕용품');
insert into category values('0205', '강아지용품', '하우스/실내용품');
insert into category values('0206', '강아지용품', '장난감/훈련용품');
insert into category values('0207', '강아지용품', '의류/액세서리');
insert into category values('0208', '강아지용품', '급수/급식기');
insert into category values('0209', '강아지용품', '이동장/리드줄/야외용품');

insert into category values('0301', '고양이용품', '사료');
insert into category values('0302', '고양이용품', '캣닢/영양제');
insert into category values('0303', '고양이용품', '미용/목욕용품');
insert into category values('0304', '고양이용품', '화장실/모래/위생용품');
insert into category values('0305', '고양이용품', '하우스/캣타워');
insert into category values('0306', '고양이용품', '장난감/스크래처');
insert into category values('0307', '고양이용품', '의류/액세서리');
insert into category values('0308', '고양이용품', '급수/급식기');

insert into category values('0401', '관상어용품', '사료');
insert into category values('0402', '관상어용품', '수조/어항');
insert into category values('0403', '관상어용품', '부속품');
insert into category values('0404', '관상어용품', '장식품');
insert into category values('0405', '관상어용품', '청소용품');

insert into category values('0501', '기타동물용품', '토끼/기니피그');
insert into category values('0502', '기타동물용품', '햄스터/다람쥐');
insert into category values('0503', '기타동물용품', '파충류');
insert into category values('0504', '기타동물용품', '조류');
insert into category values('0505', '기타동물용품', '곤충');
commit;

update product set cate='0101' where pname='돼지등갈비껌';
update product set cate='0103' where pname='고구마말랭이';
update product set cate='0102' where pname='오리황태말이';
update product set cate='0102' where pname='닭가슴살육포';
update product set cate='0105' where pname='오리목뼈';
update product set cate='0103' where pname='무염황태채';
commit;

update product set pic1='img/9852_103_01.jpg', pic2='img/9852_103_02.jpg', pic3='img/9852_103_03.jpg' where pro_code=9852;
update product set pic1='img/4207_101_01.jpg', pic2='img/4207_101_02.jpg', pic3='img/4207_101_02.jpg' where pro_code=4207;
update product set pic1='img/3993_102_01.jpg', pic2='img/3993_102_02.jpg', pic3='img/3993_102_03.jpg' where pro_code=3993;
update product set pic1='img/1352_102_01.jpg', pic2='img/1352_102_02.jpg', pic3='img/1352_102_03.jpg' where pro_code=1352;
update product set pic1='img/8138_105_01.jpg', pic2='img/8138_105_02.jpg', pic3='img/8138_105_03.jpg' where pro_code=8138;
update product set pic1='img/8162_103_01.jpg', pic2='img/8162_103_02.jpg', pic3='img/8162_103_03.jpg' where pro_code=8162;
update product set pic1='img/4221_0209_01.jpg', pic2='img/4221_0209_02.jpg', pic3='img/4221_0209_03.jpg' where pro_code=4221;
COMMIT;