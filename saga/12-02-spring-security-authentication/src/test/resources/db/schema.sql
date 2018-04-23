DROP DATABASE IF EXISTS saga;
CREATE DATABASE IF NOT EXISTS saga CHARACTER SET utf8 COLLATE utf8_general_ci;
USE saga;


create table player_skill
(
  PLAYER_ID bigint not null,
  SKILL_ID bigint not null
)
;

create index FK2052i5ac152a306b9s4pcebpm
  on player_skill (SKILL_ID)
;

create index FK5kbdrfq7q5glgjv2isul6p65u
  on player_skill (PLAYER_ID)
;

create table role_player
(
  ROLE_ID int not null,
  PLAYER_ID bigint not null
)
;

create index FKh4pt3qllu20e05vbjedmm6ngt
  on role_player (PLAYER_ID)
;

create index FKoyty8ncus4bmltht8hvmougkw
  on role_player (ROLE_ID)
;

create table s_credentials
(
  ID bigint not null auto_increment
    primary key,
  PASSWORD varchar(255) null,
  USERNAME varchar(255) null,
  PLAYER_ID bigint not null
)
;

create index FKf28acj2cixu7tgy7ktg0sx8q7
  on s_credentials (PLAYER_ID)
;

create table s_player
(
  ID bigint not null auto_increment
    primary key,
  ADDRESS varchar(255) null,
  AGE int null,
  EMAIL varchar(255) null,
  FIRST_NAME varchar(255) null,
  GENDER varchar(255) null,
  LAST_NAME varchar(255) null,
  TEAM_ID bigint null

)
;

create index FKi134gbv702vjgpkdowsooh4mi
  on s_player (TEAM_ID)
;

alter table player_skill
  add constraint FK5kbdrfq7q5glgjv2isul6p65u
foreign key (PLAYER_ID) references saga.s_player (ID)
;

alter table role_player
  add constraint FKh4pt3qllu20e05vbjedmm6ngt
foreign key (PLAYER_ID) references saga.s_player (ID)
;

alter table s_credentials
  add constraint FKf28acj2cixu7tgy7ktg0sx8q7
foreign key (PLAYER_ID) references saga.s_player (ID)
;

create table s_role
(
  id int not null auto_increment
    primary key,
  ROLE_NAME varchar(255) not null
)
;

alter table role_player
  add constraint FKoyty8ncus4bmltht8hvmougkw
foreign key (ROLE_ID) references saga.s_role (id)
;

create table s_skill
(
  id bigint not null auto_increment
    primary key,
  TYPE varchar(255) null,
  VALUE int null
)
;

alter table player_skill
  add constraint FK2052i5ac152a306b9s4pcebpm
foreign key (SKILL_ID) references saga.s_skill (id)
;

create table s_team
(
  ID bigint not null auto_increment
    primary key,
  NAME varchar(255) null
)
;

alter table s_player
  add constraint FKi134gbv702vjgpkdowsooh4mi
foreign key (TEAM_ID) references saga.s_team (ID)
;

