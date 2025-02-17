create database if not exists QuanLyBanHang;
use QuanLyBanHang;
create table if not exists customers(
	cid int auto_increment primary key,
    cName varchar(20),
    cAge int(3)
);
create table if not exists products(
	pid int auto_increment primary key,
    pName varchar(25),
    pPrice decimal(10,3)
);
create table if not exists orders(
	oid int auto_increment primary key,
    cid int,
    oDate date not null,
    oTotalPrice decimal(10,3),
    foreign key(cid) references customers(cid)
);
create table if not exists order_details(
	oid int,
    cid int,
    oQTY int not null,cid
    foreign key (oid) references orders(oid),
    foreign key (cid) references customers(cid)
);

insert into customers(cName, cAge) values('Minh Quan', 10),('Ngoc Oanh', 20), ('Hong Ha', 50);
insert into orders(cid, oDate, oTotalPrice) values(1, 3/21/2006),(2, 3/23/2006), (1, 3/16/2006);
insert into products(pName, pPrice) values('May Giat', 3),('Tu Lanh', 5), ('Dieu Hoa', 7), ('Quat', 1),('Bep Dien', 2);
insert into order_details(oid, cid, oQTY) values(1, 1, 3),(1, 3, 7), (1, 4, 2), (2, 1, 1), (3, 1, 8), (2, 5, 4), (2, 3, 3);