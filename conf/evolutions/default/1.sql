# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table product (
  prod_no                       varchar(255),
  prod_name                     varchar(255),
  price                         bigint,
  caption                       varchar(255),
  created_at                    timestamp not null,
  updated_at                    timestamp not null
);


# --- !Downs

drop table if exists product;

