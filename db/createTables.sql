-- noinspection SqlNoDataSourceInspectionForFile

CREATE SCHEMA IF NOT EXISTS my_schema;

CREATE TABLE IF NOT EXISTS my_schema.address
(
    address_id BIGINT NOT NULL,
    street     VARCHAR(255),
    number     VARCHAR(255),
    city       VARCHAR(255),
    post_code  VARCHAR(255),
    CONSTRAINT pk_address PRIMARY KEY (address_id)
);

CREATE TABLE IF NOT EXISTS my_schema.category
(
    category_id   BIGINT NOT NULL,
    category_name VARCHAR(255),
    code          VARCHAR(255),
    CONSTRAINT pk_category PRIMARY KEY (category_id)
);

CREATE TABLE IF NOT EXISTS my_schema.colour
(
    colour_id BIGINT NOT NULL,
    colour    VARCHAR(255),
    code      VARCHAR(255),
    CONSTRAINT pk_colour PRIMARY KEY (colour_id)
);

CREATE TABLE IF NOT EXISTS my_schema.customer
(
    customer_id  BIGINT NOT NULL,
    first_name   VARCHAR(255),
    last_name    VARCHAR(255),
    phone_number VARCHAR(255),
    email        VARCHAR(255),
    address_id   BIGINT,
    CONSTRAINT pk_customer PRIMARY KEY (customer_id)
);

ALTER TABLE my_schema.customer
    ADD CONSTRAINT FK_CUSTOMER_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES my_schema.address (address_id);

CREATE TABLE IF NOT EXISTS my_schema.garment_details
(
    garment_details_id  BIGINT NOT NULL,
    image               OID,
    size                VARCHAR(255),
    colour_id           BIGINT,
    discount            BOOLEAN,
    discount_percentage FLOAT,
    CONSTRAINT pk_garment_details PRIMARY KEY (garment_details_id)
);

ALTER TABLE my_schema.garment_details
    ADD CONSTRAINT FK_GARMENT_DETAILS_ON_COLOUR FOREIGN KEY (colour_id) REFERENCES my_schema.colour (colour_id);

CREATE TABLE IF NOT EXISTS my_schema.garment
(
    name               VARCHAR(255),
    garment_code       VARCHAR(255),
    description        VARCHAR(255),
    price              FLOAT,
    category_id        BIGINT,
    garment_details_id BIGINT,
    target_consumer    VARCHAR(255),
    barcode            BIGINT NOT NULL,
    sku_number         VARCHAR(255),
    is_active          BOOLEAN,
    CONSTRAINT pk_garment PRIMARY KEY (barcode)
);

ALTER TABLE my_schema.garment
    ADD CONSTRAINT FK_GARMENT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES my_schema.category (category_id);

ALTER TABLE my_schema.garment
    ADD CONSTRAINT FK_GARMENT_ON_GARMENT_DETAILS FOREIGN KEY (garment_details_id) REFERENCES my_schema.garment_details (garment_details_id);

CREATE TABLE IF NOT EXISTS my_schema.material_blend
(
    material_id  BIGINT NOT NULL,
    material     VARCHAR(255),
    percentage   INTEGER,
    garment_code VARCHAR(255),
    CONSTRAINT pk_material_blend PRIMARY KEY (material_id)
);

CREATE TABLE IF NOT EXISTS my_schema.size
(
    size_id BIGINT NOT NULL,
    size    VARCHAR(255),
    waist   VARCHAR(255),
    hips    VARCHAR(255),
    bust    VARCHAR(255),
    gender  VARCHAR(255),
    CONSTRAINT pk_size PRIMARY KEY (size_id)
);

CREATE TABLE IF NOT EXISTS my_schema.store
(
    store_id     BIGINT NOT NULL,
    store_name   VARCHAR(255),
    address_id   BIGINT,
    phone_number VARCHAR(255),
    email        VARCHAR(255),
    CONSTRAINT pk_store PRIMARY KEY (store_id)
);

ALTER TABLE my_schema.store
    ADD CONSTRAINT FK_STORE_ON_ADDRESS FOREIGN KEY (address_id) REFERENCES my_schema.address (address_id);

CREATE TABLE IF NOT EXISTS my_schema.store_inventory
(
    store_inventory_id BIGINT NOT NULL,
    store_id           BIGINT,
    barcode            BIGINT,
    CONSTRAINT pk_store_inventory PRIMARY KEY (store_inventory_id)
);

ALTER TABLE my_schema.store_inventory
    ADD CONSTRAINT FK_STORE_INVENTORY_ON_GARMENT FOREIGN KEY (barcode) REFERENCES my_schema.garment (barcode);

ALTER TABLE my_schema.store_inventory
    ADD CONSTRAINT FK_STORE_INVENTORY_ON_STORE FOREIGN KEY (store_id) REFERENCES my_schema.store (store_id);

CREATE TABLE IF NOT EXISTS my_schema.tryon
(
    tryon_id     BIGINT NOT NULL,
    "timestamp" TIMESTAMP WITHOUT TIME ZONE,
    store_id     BIGINT,
    customer_id  BIGINT,
    CONSTRAINT pk_tryon PRIMARY KEY (tryon_id)
);

ALTER TABLE my_schema.tryon
    ADD CONSTRAINT FK_TRYON_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES my_schema.customer (customer_id);

ALTER TABLE my_schema.tryon
    ADD CONSTRAINT FK_TRYON_ON_STORE FOREIGN KEY (store_id) REFERENCES my_schema.store (store_id);

CREATE TABLE IF NOT EXISTS my_schema.tryon_garments
(
    try_on_tryon_id BIGINT NOT NULL,
    garments_barcode BIGINT NOT NULL
);

ALTER TABLE my_schema.tryon_garments
    ADD CONSTRAINT FK_TRYON_ON_TRYON FOREIGN KEY (try_on_tryon_id) REFERENCES my_schema.tryon (tryon_id);
ALTER TABLE my_schema.tryon_garments
    ADD CONSTRAINT FK_TRYON_ON_GARMENT FOREIGN KEY (garments_barcode) REFERENCES my_schema.garment (barcode);

CREATE TABLE IF NOT EXISTS my_schema.recommendation
(
    recommendation_id BIGINT NOT NULL,
    outfit            VARCHAR(255),
    garment_sku       VARCHAR(255),
    CONSTRAINT pk_recommendation PRIMARY KEY (recommendation_id)
);

CREATE TABLE IF NOT EXISTS my_schema.purchase_details
(
    purchase_id        BIGINT NOT NULL,
    barcode BIGINT,
    current_price      FLOAT,
    CONSTRAINT pk_purchase_details PRIMARY KEY (purchase_id)
);

ALTER TABLE my_schema.purchase_details
    ADD CONSTRAINT FK_PURCHASE_DETAILS_ON_PRODUCT_GARMENT FOREIGN KEY (barcode) REFERENCES my_schema.garment (barcode);

CREATE TABLE IF NOT EXISTS my_schema.point_of_sale
(
    pos_id          BIGINT NOT NULL,
    date            TIMESTAMP WITHOUT TIME ZONE,
    customer_id     BIGINT,
    store_id        BIGINT,
    purchase_status VARCHAR(255),
    CONSTRAINT pk_point_of_sale PRIMARY KEY (pos_id)
);

ALTER TABLE my_schema.point_of_sale
    ADD CONSTRAINT FK_POINT_OF_SALE_ON_CUSTOMER FOREIGN KEY (customer_id) REFERENCES my_schema.customer (customer_id);

ALTER TABLE my_schema.point_of_sale
    ADD CONSTRAINT FK_POINT_OF_SALE_ON_STORE FOREIGN KEY (store_id) REFERENCES my_schema.store (store_id);

CREATE TABLE IF NOT EXISTS my_schema.basket
(
    point_of_sale_pos_id          BIGINT NOT NULL,
    details_purchase_id        BIGINT NOT NULL
);

ALTER TABLE my_schema.basket
    ADD CONSTRAINT FK_BASKET_ON_POS FOREIGN KEY (point_of_sale_pos_id) REFERENCES my_schema.point_of_sale (pos_id);
ALTER TABLE my_schema.basket
    ADD CONSTRAINT FK_BASKET_ON_POS_DETAILS FOREIGN KEY (details_purchase_id) REFERENCES my_schema.purchase_details (purchase_id);

CREATE SEQUENCE
    IF NOT EXISTS my_schema.recommendation_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.recommendation.recommendation_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.customer_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.customer.customer_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.colour_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.colour.colour_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.category_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.category.category_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.address_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.address.address_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.storeInventory_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.store_inventory.store_inventory_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.store_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.store.store_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.size_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.size.size_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.material_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.material_blend.material_id;


CREATE SEQUENCE
    IF NOT EXISTS my_schema.pointOfSale_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.point_of_sale.pos_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.purchase_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.purchase_details.purchase_id;


CREATE SEQUENCE
    IF NOT EXISTS my_schema.tryon_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.tryon.tryon_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.garment_details_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.garment_details.garment_details_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.store_inventory_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.store_inventory.store_inventory_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.point_of_sale_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1
    OWNED BY my_schema.point_of_sale.pos_id;

CREATE SEQUENCE
    IF NOT EXISTS my_schema.hibernate_sequence
    AS BIGINT
    INCREMENT BY 1
    START WITH 1;