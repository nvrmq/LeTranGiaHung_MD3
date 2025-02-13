create database if not exists conver_erd;
use conver_erd;

create table phieu_xuat (
    ma_phieu int primary key,
    ngay_xuat date
);

create table vat_tu (
    ma_vtu int primary key,
    ten varchar(50)
);

create table phieu_nhap (
    ma_phieu int primary key,
    ngay date
);

create table don_dat_hang (
    ma_dat_hang int primary key,
    ngay date,
    ma_nccap int,
    foreign key (m_nccap) references nha_ccap(ma)
);

create table nha_ccap (
    ma int primary key,
    ten varchar(50),
    dia_chi varchar(50)
);

create table sdt_nha_ccap (
    id int auto_increment primary key,
    ma_ccap int,
    sdt varchar(20),
    foreign key (ma_ccap) references nha_ccap(ma)
);

create table chi_tiet_phieu_xuat (
    id int auto_increment primary key,
    ma_phieu int,
    ma_vtu int,
    don_gia decimal(10, 3),
    so_luong int,
    foreign key (ma_phieu) references phieu_xuat(ma_phieu),
    foreign key (ma_vtu) references vat_tu(ma_vtu)
);

create table chi_tiet_phieu_nhap (
    id int auto_increment primary key,
    ma_vtu int,
    ma_phieu int,
    don_gia decimal(10, 2),
    so_luong int,
    foreign key (ma_phieu) references phieu_nhap(ma_phieu),
    foreign key (ma_vtu) references vat_tu(ma_vtu)
);

create table chi_tiet_don_dat_hang (
    id int auto_increment primary key,
    ma_vtu int,
    ma_dat_hang int,
    foreign key (ma_dat_hang) references don_dat_hang(ma_dat_hang),
    foreign key (ma_vtu) references vat_tu(ma_vtu)
);

create table ccap (
    id int auto_increment primary key,
    ma_dat_hang int,
    ma_nha_ccap int,
    foreign key (ma_dat_hang) references don_dat_hang(ma_dat_hang),
    foreign key (ma_nha_ccap) references nha_ccap(ma)
);