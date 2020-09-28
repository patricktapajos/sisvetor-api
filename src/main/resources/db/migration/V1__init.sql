CREATE SEQUENCE seq_item;
CREATE SEQUENCE seq_sub_item;

CREATE TABLE item
(
    id BIGINT DEFAULT nextval('seq_item'),
    nome varchar(100),
    PRIMARY KEY(id)
);

CREATE TABLE sub_item
(
    id BIGINT DEFAULT nextval('seq_sub_item'),
    nome varchar(100),
    PRIMARY KEY(id)
);

CREATE TABLE item_sub_item
(
    id BIGINT,
    item_id BIGINT,
    sub_item_id BIGINT,
    PRIMARY KEY(id),
    CONSTRAINT fk_item FOREIGN KEY(item_id) REFERENCES item(id),
    CONSTRAINT fk_sub_item_id FOREIGN KEY(sub_item_id) REFERENCES sub_item(id)
);