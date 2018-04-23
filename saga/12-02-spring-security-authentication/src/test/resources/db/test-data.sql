set FOREIGN_KEY_CHECKS = 0;

INSERT INTO S_TEAM(NAME) VALUES ( 'Crvena Zvezda');
INSERT INTO S_TEAM(NAME) VALUES ( 'Partizan');
INSERT INTO S_TEAM(NAME) VALUES ( 'Buducnost');

INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (1, 'Milos', 'Badjevic', 'Novi Beograd', 'milos@saga.rs', 25, 'MALE', 1);
INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (2, 'Ana', 'Dimitrijevic', 'Novi Beograd', 'ana@saga.rs', 26,  'FEMALE',1);
INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (3, 'Manja', 'Miljevic', 'Novi Beograd', 'manja@saga.rs', 27,  'FEMALE',1);
INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (4, 'Nikola', 'Mijailovic', 'Novi Beograd','nikola.m@saga.rs', 28, 'MALE',2);
INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (5, 'Nikola', 'Stankovic', 'Novi Beograd', 'nikola.s@saga.rs', 29, 'MALE',2);
INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (6, 'Radenko', 'Jovicic', 'Novi Beograd', 'radenko@saga.rs', 30, 'MALE',2);
INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (7, 'Rados', 'Scepanovic', 'Novi Beograd', 'rados@saga.rs', 31,  'MALE',3);
INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (8, 'Milos', 'Jelicic', 'Novi Beograd', 'jelicic.m@saga.rs', 32,  'MALE',2);
INSERT INTO S_PLAYER(ID, FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, GENDER,  TEAM_ID) VALUES (9, 'Nikola', 'Ninovic', 'Novi Beograd', 'nikol.n@saga.rs', 33,  'MALE',1);

INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'badjevic.m', 1);
INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'dimitrijevic.a', 2);
INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'miljevic.m', 3);
INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'mijailovic.n', 4);
INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'stankovic.n', 5);
INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'jovicic.r', 6);
INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'scepanovic.r', 7);
INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'jelicic.m', 8);
INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'ninovic.n', 9);

INSERT INTO s_role (id, ROLE_NAME) VALUES (1, 'ROLE_ADMIN');
INSERT INTO s_role (id, ROLE_NAME) VALUES ( 2, 'ROLE_USER');

INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 1, 9);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 9);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 1);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 2);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 3);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 4);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 5);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 6);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 7);
INSERT INTO role_player (ROLE_ID, PLAYER_ID) VALUES ( 2, 8);


set FOREIGN_KEY_CHECKS = 1;