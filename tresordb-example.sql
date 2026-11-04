CREATE DATABASE IF NOT EXISTS tresordb;
USE tresordb;

CREATE TABLE user (
    id INT NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(30) NOT NULL,
    last_name VARCHAR(30) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password LONGTEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE secret (
    id INT NOT NULL AUTO_INCREMENT,
    user_id INT NOT NULL,
    content JSON NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);
