SELECT
	prod_no,
	prod_name,
	price,
	caption,
	created_at,
	updated_at 
FROM
	product 
WHERE
	prod_name LIKE :keyWord 
OR
	caption LIKE :keyWord