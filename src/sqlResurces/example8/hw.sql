create view demoView as
SELECT
    s.name AS student_name,
    a.name AS author_name,
    COUNT(b.id) AS books_count,
    STRING_AGG(b.name, ', ') AS book_titles
FROM students s
JOIN orders o ON s.id = o.student_id
JOIN books b ON o.book_id = b.id
JOIN authors a ON b.author_id = a.id
GROUP BY s.name, a.name
HAVING COUNT(b.id) >= 2
ORDER BY s.name, a.name;