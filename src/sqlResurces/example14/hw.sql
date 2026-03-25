insert into products (name, count, price) values
('product_1', 1 , 5),
('product_2', 2 , 10),
('product_3', 3 , 25),
('product_4', 4 , 35),
('product_5', 5 , 45),
('product_6', 6 , 55),
('product_7', 7 , 65),
('product_8', 8 , 75),
('product_9', 9 , 85),
('product_10', 10 , 95),
('product_11', 11 , 105),
('product_12', 12 , 115),
('product_13', 13 , 125);




begin;
declare
cursor_products cursor for
select * from products;

move forward 13 from cursor_products;

move backward 2 from cursor_products;



fetch 1 from cursor_products;

move backward 6 from cursor_products;
fetch 1 from cursor_products;


move backward 5 from cursor_products;
fetch 1 from cursor_products;


move backward 5 from cursor_products;
fetch 1 from cursor_products;

select * from cursor_products


close cursor_products;
commit;







