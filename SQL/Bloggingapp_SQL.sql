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