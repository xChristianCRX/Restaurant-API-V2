ALTER TABLE tables_order
DROP CONSTRAINT IF EXISTS fk_tables_order_item;

ALTER TABLE tables_order
    ADD CONSTRAINT fk_tables_order_item
        FOREIGN KEY (item_id) REFERENCES menu(id)
            ON DELETE SET NULL;