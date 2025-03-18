CREATE TABLE herb_order (
    id SERIAL PRIMARY KEY,
    consumer_id INT NOT NULL,
    consumer_name VARCHAR(255) NOT NULL,
    consumer_email VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    herb VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    delivery_date DATE NOT NULL
);
ALTER TABLE herb_order ALTER COLUMN id SET DEFAULT nextval('herb_order_id_seq');


CREATE TABLE app_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255)
);
ALTER TABLE app_user ALTER COLUMN id SET DEFAULT nextval('app_user_id_seq');


CREATE TABLE herb (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    features VARCHAR(255),
    usage VARCHAR(255),
    image_path VARCHAR(255),
    remains INT
);
ALTER TABLE herb ALTER COLUMN id SET DEFAULT nextval('herb_id_seq');

