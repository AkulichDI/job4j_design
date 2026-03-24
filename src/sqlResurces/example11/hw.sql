
create or replace function find_delete_data_counter()
	returns integer
	language 'plpgsql' as $$
	declare
		result integer;
		u_count integer;
	begin
		result := 0;
		u_count := (select count(*) from products where count = 0  );
			if u_count > 0 then
				result := u_count;
			end if;
		return result;
	end;
$$;



create or replace procedure f_delete_data_count_null ()
	language 'plpgsql' as $$
	declare
		u_counter integer;
	begin
		u_counter := find_delete_data_counter();
		if u_counter > 0 then
			delete from products
			where count <= 0 ;
		end if;
	end;
$$;

create or replace function f_del_data (u_id integer )
	returns void
	language 'plpgsql' as
	$$
		begin
			delete from products
			where id = u_id;
		end;
	$$;



select f_del_data (1);

call f_delete_data_count_null();

select * from products