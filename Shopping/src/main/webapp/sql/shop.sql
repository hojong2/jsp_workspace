계정은 : system 계정으로 접속
계정 비밀번호 분실시
sqlplus sys/ as sysdba 로 접속

--상위 카테고리
create table topcategory(
	topcategory_id number primary key
	, category_name varchar(30)
);

--하위 카테고리
create table subcategory(
	subcategory_id number primary key
	, category_name varchar(30)
	, topcategory_id number
	, constraint fk_topcategory_subcategory foreign key (topcategory_id) references topcategory(topcategory_id)
)

--상품
create table product(
	product_id number primary key
	, product_name varchar(80)
	, brand varchar(30)
	, price number default 0
	, discount number default 0
	, detail clob
	, product_img varchar(100)
	, subcategory_id number
	, constraint fk_subcategory_product foreign key (subcategory_id) references subcategory(subcategory_id)
);

--상위 카테고리 시퀀스
create sequence seq_topcategory increment by 1 start with 1;
create sequence seq_subcategory increment by 1 start with 1;
create sequence seq_product increment by 1 start with 1;