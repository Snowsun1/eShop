-- SET
--     FOREIGN_KEY_CHECKS=0;
-- TRUNCATE TABLE eshop.user;
-- TRUNCATE TABLE eshop.basket;
-- TRUNCATE TABLE eshop.order;
-- TRUNCATE TABLE eshop.product;
-- TRUNCATE TABLE eshop.product_category;
-- TRUNCATE TABLE eshop.line_of_basket;
-- SET
--     FORIGN_KEY_CHECKS = 1;

drop table if exists eshop.user;
drop table if exists eshop.basket;
drop table if exists eshop.order;
drop table if exists eshop.product;
drop table if exists eshop.product_category;
drop table if exists eshop.line_of_basket;