CREATE DATABASE IF NOT EXISTS saga CHARACTER SET utf8 COLLATE utf8_general_ci;
USE saga;

DROP TABLE IF EXISTS S_TEAM;

CREATE TABLE S_TEAM
(
  ID         INT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(50)
);


DROP TABLE IF EXISTS S_PLAYER;

CREATE TABLE S_PLAYER
(
  ID         INT AUTO_INCREMENT PRIMARY KEY,
  FIRST_NAME VARCHAR(50),
  USERNAME   VARCHAR(50) NOT NULL,
  LAST_NAME  VARCHAR(50),
  PASSWORD   VARCHAR(50) NOT NULL,
  ADDRESS    VARCHAR(100),
  EMAIL      VARCHAR(50) NOT NULL/*,
  UNIQUE (EMAIL),
  UNIQUE (USERNAME)*/
);
