ALTER TABLE order_history
    ADD COLUMN created_at TIMESTAMP;

UPDATE order_history
    SET created_at = CURRENT_TIMESTAMP
    WHERE created_at IS NULL;

ALTER TABLE order_history
    ALTER COLUMN created_at SET NOT NULL;

ALTER TABLE tables_order
    DROP COLUMN created_at;
