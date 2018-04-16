set FOREIGN_KEY_CHECKS = 0;

INSERT INTO S_TEAM(NAME) VALUES ( 'Crvena Zvezda');
INSERT INTO S_TEAM(NAME) VALUES ( 'Partizan');
INSERT INTO S_TEAM(NAME) VALUES ( 'Buducnost');

INSERT INTO S_PLAYER(FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, TEAM_ID) VALUES ( 'Milos', 'Badjevic', 'Novi Beograd', 'milos@saga.rs', 25, 1);
INSERT INTO S_PLAYER(FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, TEAM_ID) VALUES ( 'Ana', 'Dimitrijevic', 'Novi Beograd', 'ana@saga.rs', 26, 1);
INSERT INTO S_PLAYER(FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, TEAM_ID) VALUES ( 'Manja', 'Miljevic', 'Novi Beograd', 'manja@saga.rs', 27, 1);
INSERT INTO S_PLAYER(FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, TEAM_ID) VALUES ( 'Nikola', 'Mijailovic', 'Novi Beograd','nikola.m@saga.rs', 28,2);
INSERT INTO S_PLAYER(FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, TEAM_ID) VALUES ( 'Nikola', 'Stankovic', 'Novi Beograd', 'nikola.s@saga.rs', 29,2);
INSERT INTO S_PLAYER(FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, TEAM_ID) VALUES ( 'Radenko', 'Jovicic', 'Novi Beograd', 'radenko@saga.rs', 30,2 );
INSERT INTO S_PLAYER(FIRST_NAME, LAST_NAME, ADDRESS, EMAIL, AGE, TEAM_ID) VALUES ( 'Rados', 'Scepanovic', 'Novi Beograd', 'rados@saga.rs', 31, 3);

set FOREIGN_KEY_CHECKS = 1;