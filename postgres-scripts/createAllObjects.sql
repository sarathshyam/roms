CREATE TABLE roms_menu_fooditem
(
   item_id bigint NOT NULL, 
   item_code character varying(10),
   item_name character varying(500),
   category_name character varying(20), 
   price double precision,   
   status character varying(15),   
   CONSTRAINT roms_menu_fooditem_pk PRIMARY KEY (item_id)
);


CREATE TABLE roms_live_order
(
   order_id bigint NOT NULL, 
   order_taken_time timestamp with time zone,
   table_id character varying(10),
   waiter_id character varying(20),
   order_ready_time timestamp with time zone,
   order_billed_time timestamp with time zone,
   order_status character varying(15),
   total_actual_price double precision,
   discount double precision,
   total_final_price double precision,
   remarks character varying(100), 
   CONSTRAINT roms_live_order_pk PRIMARY KEY (order_id)
);


CREATE SEQUENCE roms_order_pk_seq
   INCREMENT 1
   START 1;

 CREATE TABLE roms_live_order_dtls
(
   order_item_id bigint NOT NULL, 
   food_item_id bigint NOT NULL,
   item_price double precision,
   item_quantity integer,
   item_remarks character varying(100),
   item_name character varying(500),
   discount double precision,
   item_total_price double precision,
   order_id bigint NOT NULL,    
   CONSTRAINT roms_live_order_dtls_pk PRIMARY KEY (order_item_id)
);


CREATE SEQUENCE roms_order_dtls_pk_seq
   INCREMENT 1
   START 1;

   ALTER TABLE roms_live_order_dtls
  ADD CONSTRAINT roms_live_order_dtls_fk FOREIGN KEY (order_id) REFERENCES roms_live_order (order_id);
