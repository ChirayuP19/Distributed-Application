CREATE TABLE products (
                          id BIGSERIAL PRIMARY KEY,
                          name VARCHAR(255),
                          description TEXT,
                          price NUMERIC(10,2),
                          stock INT,
                          create_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          update_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);