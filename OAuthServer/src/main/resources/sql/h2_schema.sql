CREATE TABLE herb_order (
    id INT AUTO_INCREMENT PRIMARY KEY,
    consumer_id INT NOT NULL,
    consumer_name VARCHAR(255) NOT NULL,
    consumer_email VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    herb VARCHAR(255) NOT NULL,
    quantity INT NOT NULL,
    delivery_date DATE NOT NULL
);

CREATE TABLE app_user (
    id INT NOT NULL,
    username VARCHAR(255),
    password VARCHAR(255),
    role VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE herb (
    id INT AUTO_INCREMENT NOT NULL,
    name VARCHAR(255),
    features VARCHAR(255),
    usage VARCHAR(255),
    image_path VARCHAR(255),
    remains INTEGER,
    PRIMARY KEY (id)
);
