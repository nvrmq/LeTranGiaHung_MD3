create database if not exists demo_ss5;
use demo_ss5;

create table products(
	id int auto_increment primary key,
    product_code varchar(20) unique,
    product_name varchar(30) not null,
    product_price decimal(10,2),
    product_amount int,
    product_description text,
    product_status enum('done', 'ongoing', 'unknown') default 'unknown'
);
insert into products(product_code, product_name, product_price, product_amount, product_description, product_status)
values
('p001', 'laptop asus', 2100.00, 10, 'laptop asus ryzen 7', 'done'),
('p002', 'surface pro', 2200.00, 5, 'surface pro snapdragon x elite', 'ongoing'),
('p003', 'macbook air', 2000.00, 3, 'macbook air m3', 'done')

-- tạo index cho product
create unique index index_product_code on products(product_code);
-- composite index
create index index_productnameprice on products(product_name, product_price);

-- explain code
explain select * from products where product_code = 'p002';

-- view
create view product_view as 
select product_code, product_name, product_price, product_status from products;

-- thao tác với view
drop view if exists product_view;
create view product_view as 
select product_code from products;

-- stored procedure
delimiter //
create procedure get_all_products()
begin
	select * from products;
end //
delimiter ;

call get_all_products();

-- thêm sp
delimiter //
create procedure update_product(
	in p_id int,
    in p_name varchar(100),
    in p_price decimal(10,2),
    in p_amount int,
    in p_description text,
    in p_status varchar(10)
)
begin
	update products
    set product_name = p_name,
		product_price = p_price,
		product_amount = p_amount,
        product_description = p_description,
        product_status = p_status
	where id = p_id;
end //
delimiter ;

call update_product(1, 'dell laptop', 2200.00, 12,'laptop dell i7', 'ongoing');

-- delete

delimiter //
create procedure delete_product(in p_id int)
begin
	delete from products where id = p_id;
end //
delimiter ;

call delete_product(2);