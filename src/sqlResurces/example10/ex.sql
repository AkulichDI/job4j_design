create or replace PROCEDURE insert_data ( i_name varchar, prod varchar,i_count integer, i_price integer )
language 'plpgsql'as
$$
	begin
		insert into products (name, producer, count, price )
		values ( i_name, prod, i_count, i_price );
	end
$$;


call insert_data ('product_2', 'producer_2', 15, 32);


create or replace procedure update_data ( u_count integer, tax float, u_id integer)
language 'plpgsql' as
$$
	Begin
		if u_count > 0 then
			update products
			set count = count - u_count
			where id = u_id;
		end if;
		if tax > 0 then
			update products
			set price = price + price * tax;
		end if;
	end;
$$;

call update_data ( 10, 0, 1);


CALL insert_data('product_1', 'producer_1', 3, 50);
CALL insert_data('product_3', 'producer_3', 8, 115);

CALL update_data(0, 0.2, 0);

Alter procedure update_data( u_count integer, tax float, u_id integer) rename to update;

delete from products;
alter sequence products_id_seq restart with 1;




create or replace function f_insert_data ( i_name varchar, prod varchar, i_count integer, i_price integer )
returns void
language 'plpgsql' as
$$
	begin
		insert into products (name, producer, count, price)
		values (i_name, prod, i_count, i_price);
	end;
$$;


select f_insert_data ('product_1', 'producer_1', 25,50);


create or replace function f_update_data (u_count integer, tax float, u_id integer)
returns integer language 'plpgsql' as
$$
	declare
		result integer;
	begin
		if u_count > 0 then
			update products
			set count = count - u_count
			where id = u_id;
			select into result count
			from products
			where id = u_id;
		end if;
		if tax > 0 then
			update products
			set price = price + price * tax;
			select into result SUM(price)
			from products;
		end if;
		return result;
	end;
$$;

select f_update_data(10,0,1);

select f_insert_data ('product_2', 'producer_2', 15, 32);
select f_insert_data ('product_3', 'producer_3', 8, 115);


select f_update_data(0,0.2,0);


drop function f_update_data(u_count, tax, u_id);


select * from products;

