create table products (
	id serial PRIMARY KEY,
	name VARCHAR(50),
	producer varchar(50),
	count INTEGER DEFAULT 0,
	price INTEGER
)

create trigger discount_trigger
	after insert
	on products
	for each row
	execute Procedure discount();


CREATE
OR REPLACE FUNCTION discount()
	returns trigger as
	$$
	Begin
		UPDATE products
		SET price = price - price * 0.2
		where count <= 5
		and id = new.id;
		return new;
	END;
	$$
LANGUAGE 'plpgsql';



insert into products (name, producer, count, price)
values ('product_3','producer_3', 8, 115);


insert into products (name, producer, count, price )
values ('product_1', 'producer_1', 3, 50);



alter table products disable trigger discount_trigger;

drop trigger discount_trigger on products;


create trigger tax_trigger
	after insert
	on products
	referencing new table as
					inserted
	for each statement
	execute procedure tax();


create
or replace function tax()
	returns trigger as
$$
	Begin
		UPDATE products
		set price = price - price * 0.2
		where id = ( select id from inserted)
		and count <= 5;

	end;
$$
language 'plpgsql';


create or replace function nds()
returns trigger as
$$
begin
    new.price := new.price + new.price * 0.15;
    return new;
end;
$$
language plpgsql;


create trigger nds_trigger
before insert
on products
for each row
execute function nds();



create or replace function nds_add()
returns trigger as
$$
begin
	update products
	set price = price + price * 0.15
	where id = (select id from inserted)
	return null;
end;
$$
language plpqsql;



create trigger nds_trigger1
after insert
on products
referencing new table as inserted
for each statement
execute function nds_add();


create table history_of_price (
	id serial PRIMARY KEY,
	name varchar(50),
	price INTEGER,
	date TiMESTAMP
);



create or replace function logger()
returns trigger as
$$
	BEGIN
	insert into history_of_price (name, price, date)
	values (new.name, new.price, now());
	return new;
	END;
$$
language 'plpgsql';

create trigger save
	after insert
	on products
	for each row
execute procedure logger();















