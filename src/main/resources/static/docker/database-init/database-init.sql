CREATE DATABASE company;

CREATE USER 'company_user'@'%' identified by 'SuPeRsEcReTpWd';

GRANT ALL PRIVILEGES ON company.* TO 'company_user'@'%';

USE company

CREATE TABLE company (
	id INT(10) UNSIGNED AUTO_INCREMENT,
	uid VARCHAR(36) NOT NULL,
	name VARCHAR(256) NOT NULL,
	email VARCHAR(256) NOT NULL,
	phone VARCHAR(32),
	deleted TINYINT(1),
	PRIMARY KEY (id)
) ENGINE=InnoDB;