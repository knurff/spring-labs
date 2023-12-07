-- Categories
INSERT INTO categories (name, parent_category_id) VALUES ('Electronics', NULL);
INSERT INTO categories (name, parent_category_id) VALUES ('Laptops', 1);
INSERT INTO categories (name, parent_category_id) VALUES ('Smartphones', 1);
INSERT INTO categories (name, parent_category_id) VALUES ('Household Appliances', NULL);
INSERT INTO categories (name, parent_category_id) VALUES ('Kitchen Appliances', 4);
INSERT INTO categories (name, parent_category_id) VALUES ('Vacuums', 4);

-- Products
INSERT INTO products (name, price, category_id) VALUES ('Smart Fridge', 899.99, 4);
INSERT INTO products (name, price, category_id) VALUES ('Coffee Maker', 49.99, 5);
INSERT INTO products (name, price, category_id) VALUES ('Robotic Vacuum', 199.99, 6);
INSERT INTO products (name, price, category_id) VALUES ('Toaster', 29.99, 5);
INSERT INTO products (name, price, category_id) VALUES ('Blender', 69.99, 5);
INSERT INTO products (name, price, category_id) VALUES ('Handheld Vacuum', 79.99, 6);
INSERT INTO products (name, price, category_id) VALUES ('Microwave', 129.99, 5);