-- 회원 테이블
create table v_member(
	m_id varchar2(10 char) primary key,
	m_pw varchar2(15 char) not null,
	m_name varchar2(10 char) not null,
	m_email varchar2(30 char) not null
);
insert into v_member values('admin', '1234', '관리자', 'admin@gmail.com');
select * from v_member;
alter table v_member add m_regdate date default sysdate not null;
update v_member set m_regdate = '2023-05-15' where m_id = 'wl6717';
update v_member set m_regdate = '2023-05-14' where m_id = 'qwert';

-- 자원 봉사 테이블 -- 1 : 모집중 / 0 : 마감 / 2 : 종료(날짜 지남)
create table volunteer(
	v_no number(3) primary key,
	v_writer varchar2(10 char) not null,
	v_type varchar2(20 char) not null,
	v_title varchar2(30 char) not null,
	v_content varchar2(200 char) not null,
	v_date date not null,
	v_time number(2) not null,
	v_area varchar2(10 char) not null,
	v_recruit number(2) not null,
	v_staff number(2) default 0 not null,
	v_status number(1) default 1 not null, 
	v_chat varchar2(100 char) not null,
	v_regdate date default sysdate,
	constraint fk_volun foreign key(v_writer)
		references v_member(m_id)
		on delete cascade
);

insert into volunteer values(volunteer_seq.nextval, 'admin', '동물복지 봉사 활동', '자원봉사', '내용', '2023-07-26', 18, '서울 강남구', 10, 0, 1, '채팅링크', sysdate-4);

create sequence volunteer_seq;
select * from volunteer;
drop table volunteer;
drop sequence volunteer_seq;

update VOLUNTEER set v_staff = 2 where v_no = 104;
update VOLUNTEER set v_status = 0 where v_recruit = v_staff;
update VOLUNTEER set v_status = 1 where v_recruit != v_staff;
update VOLUNTEER set v_status = 2
 where v_date <= sysdate;
 
-- 일 별로 작성된 봉사글 갯수 가져오기 최근날짜부터 10일 전까지
select count(*), to_char(to_date(v_regdate, 'yy-mm-dd'), 'yyyy-mm-dd')
from volunteer 
where to_date(v_regdate, 'yy-mm-dd') < to_date(sysdate, 'yy-mm-dd') and to_date(v_regdate, 'yy-mm-dd') > to_date(sysdate, 'yy-mm-dd')-10
group by to_char(to_date(v_regdate, 'yy-mm-dd'), 'yyyy-mm-dd');

select v_no, to_char(to_date(v_regdate, 'yy-mm-dd'), 'yyyy-mm-dd')
from volunteer 

select count(*), to_char(to_date(v_regdate, 'yy-mm-dd'), 'yyyy-mm-dd')
from volunteer 
group by to_char(to_date(v_regdate, 'yy-mm-dd'), 'yyyy-mm-dd')
having to_char(to_date(v_regdate, 'yy-mm-dd'), 'yyyy-mm-dd') < sysdate and to_char(to_date(v_regdate, 'yy-mm-dd'), 'yyyy-mm-dd') > sysdate-10;

select count(*), to_char(v_regdate, 'yyyy-mm-dd')
		from volunteer 
		group by to_char(v_regdate, 'yyyy-mm-dd')
		having to_char(v_regdate, 'yyyy-mm-dd') < to_char(sysdate, 'yyyy-mm-dd') and to_char(v_regdate, 'yyyy-mm-dd') > to_char(sysdate-10, 'yyyy-mm-dd')
		
select count(*), to_date(to_char(v_regdate, 'yyyy-mm-dd'), 'yyyy-MM-dd') as v_regdate
from volunteer 
group by to_char(v_regdate, 'yyyy-mm-dd')

-- 봉사 모임 테이블 - 명단 체크용
create table v_list(
	v_no number(3) not null,
	v_id varchar2(10 char) not null,
	constraint fk_no foreign key(v_no)
		references volunteer(v_no)
		on delete cascade,
	constraint fk_id foreign key(v_id)
		references v_member(m_id)
		on delete cascade
);

select * from v_list;
drop table v_list;

select * from v_list where v_no = 81 and v_id = 't';

-- 댓글 테이블
create table cmt(
	c_no number(3) primary key,
	v_no number(3) not null,
	c_id varchar2(10 char),
	c_content varchar2(200 char) not null,
	c_regdate date default sysdate,
	c_indent number(5) default 0,
	c_ansnum number(5) default 0,
	constraint cmt_no foreign key(v_no)
		references volunteer(v_no)
		on delete cascade,
	constraint cmt_id foreign key(c_id)
		references v_member(m_id)
		on delete cascade
);

create sequence cmt_seq;
drop sequence cmt_seq;

select * from cmt;
drop table cmt;

-- 기부 테이블
create table donation(
	d_no number(5) primary key,
	d_title varchar2(30 char) not null,
	d_content varchar2(200 char) not null,
	d_photo varchar2(200 char),
	d_startDate date not null,
	d_endDate date not null,
	d_goal number(8) not null, -- 목표 금액
	d_amount number(8) default 0 not null, -- 현재 금액
	d_status number(1) default 1 not null, -- 상태 0 : 종료 / 1 : 모금중 / 2 : 모금완료
	d_regdate date default sysdate
);

drop table donation;
drop sequence donation_seq;
create sequence donation_seq;
insert into donation values(donation_seq.nextval, '유기동물보호센터 후원금', 'ㅇㅇ시에 위치한 유기동물보호센터의 후원금을 모금합니다.', 'img1.jpg','2023-05-12', '2023-06-12', 1000000, 0, 1, sysdate);
select * from donation;

-- 기부 참여 명단 테이블 - 명단 체크용
create table d_list(
	d_no number(3) not null,
	m_id varchar2(10 char) not null,
	d_amount number(8) not null,
	d_regno varchar2(40 char) not null,	-- 결제시 생성되는 주문번호
	d_regdate date default sysdate,
	constraint do_no foreign key(d_no)
		references donation(d_no)
		on delete cascade,
	constraint do_id foreign key(m_id)
		references v_member(m_id)
		on delete cascade
);

drop table d_list;
select * from d_list;
