INSERT INTO roles(name) VALUES('ROLE_USER');
INSERT INTO roles(name) VALUES('ROLE_MODERATOR');
INSERT INTO roles(name) VALUES('ROLE_ADMIN');


INSERT INTO tags( name) VALUES('Furniture');
INSERT INTO products(title, description, published, price) VALUES('char', 'Amazing vintage chair', TRUE, 1.0);
INSERT INTO products(title, description, published, price) VALUES('1800 Sofa', 'A true masterpiece of historical', TRUE, 91.0);
INSERT INTO products(title, description, published, price) VALUES('Oldskool desk', 'A Desk for the crafstman of the past', TRUE, 321.0);
INSERT INTO product_tags(product_id, tag_id) VALUES(1 ,1);
INSERT INTO product_tags(product_id, tag_id) VALUES(2 ,1);
INSERT INTO product_tags(product_id, tag_id) VALUES(3 ,1);

INSERT INTO tags(name) VALUES('Books');
INSERT INTO products(title, description, published, price) VALUES('Lord of the rings', 'Will always be good', TRUE, 31.0);
INSERT INTO products(title, description, published, price) VALUES('Pokemon', 'A  book of novi memories', TRUE, 3.0);
INSERT INTO products(title, description, published, price) VALUES('The  great gatsby', 'Wat life can be great', TRUE,  1.0);
INSERT INTO product_tags(product_id, tag_id) VALUES(4 ,2);
INSERT INTO product_tags(product_id, tag_id) VALUES(5 ,2);
INSERT INTO product_tags(product_id, tag_id) VALUES(6 ,2);


INSERT INTO tags(name) VALUES('Art');
INSERT INTO products(title, description, published, price) VALUES('Video recorder', 'Do you remember those', TRUE, 11.0);
INSERT INTO products(title, description, published, price) VALUES('Retro painting', 'Turn your environment back to the future', TRUE, 211.0);
INSERT INTO product_tags(product_id, tag_id) VALUES(7 ,3);
INSERT INTO product_tags(product_id, tag_id) VALUES(8 ,3);


INSERT INTO users(id, username, email , password) VALUES(99, 'sqlscript', 'isadmin@ex.com', '$2a$12$vEtG7Tru.6KeZc3jYhhsl.QfxX8MS7OVAprULHF8xmk2UoBC2wd/q');


-- INSERT INTO users(id ,username, email, password) VALUES (99,'wijnie', 'admintest@test.com', '$2a$12$vEtG7Tru.6KeZc3jYhhsl.QfxX8MS7OVAprULHF8xmk2UoBC2wd/q')
INSERT INTO user_roles(user_id, role_id) VALUES(99, 3);

