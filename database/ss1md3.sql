create database if not exists cl09md3;
use cl09md3;
create table if not exists Class(
  id int primary key auto_increment,
  `name` varchar(100) not null default ("abc")
);
create table if not exists Teachers(
  id int primary key auto_increment,
  `name` varchar(100) not null default ("abc"),
  `age` int(100) not null default(0),
  `country` varchar(100) not null default ("abc")
);