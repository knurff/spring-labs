drop table if exists products;

drop table if exists categories;

CREATE TABLE categories (
                            id BIGSERIAL PRIMARY KEY,
                            name VARCHAR(55) NOT NULL,
                            parent_category_id BIGINT,
                            FOREIGN KEY (parent_category_id) REFERENCES categories(id) ON DELETE CASCADE
);

CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(55) NOT NULL,
                          price DOUBLE PRECISION NOT NULL,
                          category_id BIGINT not null,
                          FOREIGN KEY (category_id) REFERENCES categories(id) ON DELETE CASCADE
);