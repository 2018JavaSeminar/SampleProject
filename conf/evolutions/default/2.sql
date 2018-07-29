# --- !Ups
insert into product (prod_no, prod_name, price, caption, created_at, updated_at) values ('000001','商品名',10000L,'説明文',TIMESTAMP'1900-01-01 00:00:00', TIMESTAMP'1900-01-01 00:00:00');
insert into product (prod_no, prod_name, price, caption, created_at, updated_at) values ('000002','商品名２',15000L,'説明文４',TIMESTAMP'1900-01-01 00:00:00', TIMESTAMP'1900-01-31 23:59:59');

# --- !Downs
delete from product;