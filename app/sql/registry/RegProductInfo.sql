INSERT INTO product
	(
		prod_no,
		prod_name,
		price,
		caption,
		created_at,
		updated_at
	)
VALUES
	(
		:prodNo,
		:prodName,
		:price,
		:caption,
		:sysDate,
		:sysDate
	)