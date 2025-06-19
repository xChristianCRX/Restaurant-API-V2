CREATE TABLE public.additions (
    id uuid NOT NULL,
    name character varying(255),
    price double precision
);

ALTER TABLE public.additions OWNER TO postgres;

CREATE TABLE public.menu (
    id uuid NOT NULL,
    name character varying(255),
    price double precision,
    type character varying(255),
    CONSTRAINT menu_type_check CHECK (((type)::text = ANY ((ARRAY['BURGUER'::character varying, 'DRINK'::character varying, 'PORTION'::character varying])::text[])))
);

ALTER TABLE public.menu OWNER TO postgres;

CREATE TABLE public.order_history (
    id uuid NOT NULL,
    active boolean,
    table_number integer NOT NULL
);

ALTER TABLE public.order_history OWNER TO postgres;

CREATE TABLE public.people (
    id uuid NOT NULL,
    email character varying(255),
    name character varying(255),
    password character varying(255),
    role character varying(255),
    username character varying(255),
    CONSTRAINT people_role_check CHECK (((role)::text = ANY ((ARRAY['ADMIN'::character varying, 'CASHIER'::character varying, 'WAITER'::character varying])::text[])))
);

ALTER TABLE public.people OWNER TO postgres;

CREATE TABLE public.rl_item_additions (
    fk_table_order uuid NOT NULL,
    fk_addition uuid NOT NULL
);

ALTER TABLE public.rl_item_additions OWNER TO postgres;

CREATE TABLE public.rl_orderhistory_tableorders (
    history_id uuid NOT NULL,
    table_order_id uuid NOT NULL
);

ALTER TABLE public.rl_orderhistory_tableorders OWNER TO postgres;

CREATE TABLE public.tables (
    table_number integer NOT NULL
);

ALTER TABLE public.tables OWNER TO postgres;

CREATE TABLE public.tables_order (
    id uuid NOT NULL,
    created_at timestamp(6) without time zone,
    observations character varying(255),
    item_id uuid,
    waiter_id uuid
);

ALTER TABLE public.tables_order OWNER TO postgres;

ALTER TABLE ONLY public.additions
    ADD CONSTRAINT additions_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.menu
    ADD CONSTRAINT menu_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.order_history
    ADD CONSTRAINT order_history_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.people
    ADD CONSTRAINT people_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.tables_order
    ADD CONSTRAINT tables_order_pkey PRIMARY KEY (id);

ALTER TABLE ONLY public.tables
    ADD CONSTRAINT tables_pkey PRIMARY KEY (table_number);

ALTER TABLE ONLY public.rl_orderhistory_tableorders
    ADD CONSTRAINT uk9936qbph37x2rc900599p52e8 UNIQUE (table_order_id);

ALTER TABLE ONLY public.rl_orderhistory_tableorders
    ADD CONSTRAINT fk40l4t7dv3ekymtsgeep9apo3f FOREIGN KEY (history_id) REFERENCES public.order_history(id);

ALTER TABLE ONLY public.tables_order
    ADD CONSTRAINT fk40wn7wh196w7v6454yj0ey4ad FOREIGN KEY (item_id) REFERENCES public.menu(id);

ALTER TABLE ONLY public.tables_order
    ADD CONSTRAINT fk8na7wky5uv8fvmv61e38mk7ja FOREIGN KEY (waiter_id) REFERENCES public.people(id);

ALTER TABLE ONLY public.order_history
    ADD CONSTRAINT fkafqea4sjshlelybwdrjkne54b FOREIGN KEY (table_number) REFERENCES public.tables(table_number);

ALTER TABLE ONLY public.rl_orderhistory_tableorders
    ADD CONSTRAINT fkotg9qvqi98u5inxrcaenw0g6i FOREIGN KEY (table_order_id) REFERENCES public.tables_order(id);

ALTER TABLE ONLY public.rl_item_additions
    ADD CONSTRAINT fkphcn0fg3ih7w4w50j7vay0joy FOREIGN KEY (fk_addition) REFERENCES public.additions(id);

ALTER TABLE ONLY public.rl_item_additions
    ADD CONSTRAINT fkt850ktn6oeswg02fxqwnkdse2 FOREIGN KEY (fk_table_order) REFERENCES public.tables_order(id);