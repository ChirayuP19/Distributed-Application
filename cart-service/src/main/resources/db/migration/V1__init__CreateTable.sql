CREATE TABLE cart_items (
                            id BIGSERIAL PRIMARY KEY,
                            user_id VARCHAR(255),
                            product_id BIGINT,
                            quantity INTEGER,
                            price NUMERIC(19, 2),
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);