insert into eshop.user(name, surname, email, balance, address) values ( 'Alex', 'Petrov', 'alex@mail.ru', 100.0, 'sadovaya 1' );
insert into eshop.user(name, surname, email, balance, address) values ( 'Victor', 'Ivanov', 'victor@mail.ru', 200.0, 'sadovaya 24' );
insert into eshop.product_category(name) values ( 'Молочные продукты' );
insert into eshop.product(name, count, price, product_category_id) values ( 'Молоко', 10, 4.0, 1 );
insert into eshop.product(name, count, price, product_category_id) values ( 'Творог', 8, 5.0, 1 );
insert into eshop.basket(delivery, paid, user_id, total_cost) values ( false, false, 1, 0 );
insert into eshop.basket(delivery, paid, user_id, total_cost) values ( false, false, 2, 0 );
insert into eshop.line_of_basket(count, product_id, basket_id, position_cost) values ( 3, 1, 1, 0);
insert into eshop.line_of_basket(count, product_id, basket_id, position_cost) values ( 2, 2, 2, 0);