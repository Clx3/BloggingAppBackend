CREATE SCHEMA bloggingapp;

use bloggingapp;

CREATE TABLE blogPost (
	id BIGINT(20) NOT NULL,
	postDate DATETIME NOT NULL,
	author VARCHAR(20) NOT NULL,
	title VARCHAR(100),
	content VARCHAR(3000),
	PRIMARY KEY(id)
);

CREATE TABLE user (
	id BIGINT(20) NOT NULL,
    username VARCHAR(20) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    isAdmin BOOLEAN NOT NULL,
    PRIMARY KEY (id)
);

INSERT INTO user (id, username, password, isAdmin) VALUES (1, 'admin', 'admin', true);