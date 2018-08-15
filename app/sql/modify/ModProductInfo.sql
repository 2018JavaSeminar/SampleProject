UPDATE FROM product
SET
	prod_name = :prodName,
	price = :price,
	caption = :caption,
	updated_at = :sysDate
WHERE
	prod_no = :prodNo