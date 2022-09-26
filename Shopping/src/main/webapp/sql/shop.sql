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

--회원
create table member(
	member_id number primary key
	, customer_id varchar(30)
	, customer_name varchar(30)
	, customer_pass varchar(30)
	, customer_email varchar(50)
);
create sequence seq_member
increment by 1
start with 1;

--결제 방법
create table paymethod(
	paymethod_id number primary key
	, payname varchar(30)
);

--주문 요약
create table ordersummary(
	ordersummary_id number primary key
	, member_id number  --누가?
	, paymethod_id number  --어떤 결제 방법으로?
	, totalbuy number default 0  --얼마나?
	, totalpay number default 0  --실제 결제금은 얼마?
	, buydate date default sysdate  --언제샀는지?
	, constraint fk_member_ordersummary foreign key (customer_id) references member(customer_id)
	, constraint fk_paymethod_ordersummary foreign key (paymethod_id) references paymethod(paymethod_id)
);

--주문 상세 (어떤 주문에 대한 상세정보)
create table orderdetail(
	orderdetail_id number primary key
	, product_id number --뭘 샀는지?
	, ea number default 0 --몇개 샀는지?
	, ordersummary_id number
	, constraint fk_ordersummary_orderdetail foreign key (ordersummary_id) references ordersummary(ordersummary_id)
);

--시퀀스
create sequence seq_paymethod
increment by 1
start with 1;

create sequence seq_ordersummary
increment by 1
start with 1;

create sequence seq_orderdetail
increment by 1
start with 1;

--결제방법
INSERT INTO paymethod (paymethod_id, payname) VALUES(seq_paymethod.nextval, '카드결제');
INSERT INTO paymethod (paymethod_id, payname) VALUES(seq_paymethod.nextval, '핸드폰');
INSERT INTO paymethod (paymethod_id, payname) VALUES(seq_paymethod.nextval, '가상계좌');
INSERT INTO paymethod (paymethod_id, payname) VALUES(seq_paymethod.nextval, '온라인입금');
