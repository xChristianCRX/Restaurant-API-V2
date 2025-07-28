-- Remove a constraint antiga (substitua o nome da constraint caso esteja diferente)
ALTER TABLE tables_order
DROP CONSTRAINT IF EXISTS fk40wn7wh196w7v6454yj0ey4ad;

-- Cria nova constraint com ON DELETE CASCADE
ALTER TABLE tables_order
    ADD CONSTRAINT fk_tables_order_menu
        FOREIGN KEY (item_id) REFERENCES menu(id)
            ON DELETE CASCADE;
