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

INSERT INTO s_credentials (PASSWORD, USERNAME, PLAYER_ID) VALUES ( 'pass', 'badjevic.m', 1);

INSERT INTO s_role (ROLE_NAME, PLAYER_ID) VALUES ( 'ROLE_ADMIN', 1);
INSERT INTO s_role (ROLE_NAME, PLAYER_ID) VALUES ( 'ROLE_USER', 1);


set FOREIGN_KEY_CHECKS = 1;