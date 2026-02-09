CREATE TABLE IF NOT EXISTS item (
    item_id BIGSERIAL PRIMARY KEY,
    item_name TEXT UNIQUE NOT NULL,
    item_description TEXT
);