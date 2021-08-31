SET
    FOREIGN_KEY_CHECKS=0;
TRUNCATE TABLE eshop.user cascade ;
TRUNCATE TABLE eshop.basket cascade ;
TRUNCATE TABLE eshop.order cascade ;
TRUNCATE TABLE eshop.product cascade ;
TRUNCATE TABLE eshop.product_category cascade ;
TRUNCATE TABLE eshop.line_of_basket cascade ;
SET
    FORIGN_KEY_CHECKS = 1;

ALTER SEQUENCE basket_basket_id_seq RESTART WITH 1;
ALTER SEQUENCE line_of_basket_lob_id_seq RESTART WITH 1;
ALTER SEQUENCE order_order_id_seq RESTART WITH 1;
ALTER SEQUENCE product_categoty_product_category_id_seq RESTART WITH 1;
ALTER SEQUENCE user_user_id_seq RESTART WITH 1;


-- drop table if exists eshop.user;
-- drop table if exists eshop.basket;
-- drop table if exists eshop.order;
-- drop table if exists eshop.product;
-- drop table if exists eshop.product_category;
-- drop table if exists eshop.line_of_basket;