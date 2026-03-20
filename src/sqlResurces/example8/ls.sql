create table students (
	id serial PRIMARY KEY,
	name varchar(50)
);

insert into students (name) values
('Иван Иванов'),
('Петр Петров');

create table authors (
	id serial PRIMARY KEY,
	name varchar(59)
);

insert into authors(name) values
('Александр Пушин'),
('Николай Гоголь');



create table books(
	id serial PRIMARY KEY,
	name VARCHAR(200),
	author_id INTEGER REFERENCES authors (id)
);


insert into books (name, author_id) values
('Евгений Онегин', 1 ),
('Капитанская дочка', 1 ),
('Дубровский', 1 ),
('Мертвые души', 2),
('Вий', 2 );



create table orders (
	id serial PRIMARY KEY,
	active BOOLEAN DEFAULT true,
	book_id INTEGER REFERENCES books (id),
	student_id INTEGER REFERENCES students (id)
);

insert into orders  (book_id, student_id) values
(1,1),
(3,1),
(5,2),
(4,1),
(2,2);



select
s.name,
COUNT (a.name),
a.name
from
students as s
	join orders o on s.id = o.student_id
	join books b on o.book_id = b.id
	join authors a on b.author_id = a.id
group by
(s.name, a.name)
HAVING COUNT(a.name) >= 2;


create view show_students_with_2_or_more_books as
select
	s.name as student,
	count(a.name),
	a.name as author
from students as s
	join orders o on s.id = o.student_id
	join books b on o.book_id = b.id
	join authors a on b.author_id = a.id
group by (s.name, a.name)
having count(a.name) >= 2;


select
*
from
show_students_with_2_or_more_books

alter view show_students_with_2_or_more_books rename to newName;


