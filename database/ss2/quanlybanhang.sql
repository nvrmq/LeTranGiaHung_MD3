create database if not exists QuanLyBanHang;
use QuanLyBanHang;
create table customers(
	cid int auto_increment primary key,
    cName varchar(20),
    cAge int(3)
);
create table products(
	pid int auto_increment primary key,
    pName varchar(25),
    pPrice decimal(10,3)
);
create table orders(
	oid int auto_increment primary key,
    cid int,
    oDate date not null,
    oTotalPrice decimal(10,3),
    foreign key(cid) references customers(cid)
);
create table order_details(
	oid int,
    cid int,
    oQTY int not null,
    foreign key (oid) references orders(oid),
    foreign key (cid) references customers(cid)
);