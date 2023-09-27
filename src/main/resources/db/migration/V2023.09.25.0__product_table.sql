CREATE TABLE product
(
    id          UUID                        NOT NULL,
    created_at  TIMESTAMP WITH TIME ZONE    NOT NULL,
    updated_at  TIMESTAMP WITH TIME ZONE,
    deleted     BOOLEAN,
    name        VARCHAR(255),
    description VARCHAR(255),
    price       DECIMAL(10, 2),
    quantity    INTEGER,
    CONSTRAINT pk_product PRIMARY KEY (id)
);