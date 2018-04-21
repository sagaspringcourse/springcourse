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

create table s_game
(
  DTYPE varchar(31) not null,
  ID bigint not null auto_increment
    primary key,
  DATE_PLAYED datetime null,
  PLAYER_ID bigint null,
  AWAY_TEAM_ID bigint null,
  HOME_TEAM_ID bigint null,
  TEAM_ID bigint null
)
;

create index FK1ewi1nqvkdmjda96e765sd4qi
  on s_game (AWAY_TEAM_ID)
;

create index FK7ayyk513fdwmbimq9kwfkvgne
  on s_game (HOME_TEAM_ID)
;

create index FKjqdomg0ucqj0hgx76kh9p93bb
  on s_game (PLAYER_ID)
;

create index FKn9w8pwge38ontol40gd3v6qbi
  on s_game (TEAM_ID)
;

create table s_game_s_player
(
  MultiPlayerGame_ID bigint not null,
  players_ID bigint not null,
  constraint UK_l99dxg9lla65i7qg1r17wqih6
  unique (players_ID),
  constraint FKruyjfbyjfrtmxxu7kw2vimich
  foreign key (MultiPlayerGame_ID) references saga.s_game (ID)
)
;

create index FKruyjfbyjfrtmxxu7kw2vimich
  on s_game_s_player (MultiPlayerGame_ID)
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

alter table s_credentials
  add constraint FKf28acj2cixu7tgy7ktg0sx8q7
foreign key (PLAYER_ID) references saga.s_player (ID)
;

alter table s_game
  add constraint FKjqdomg0ucqj0hgx76kh9p93bb
foreign key (PLAYER_ID) references saga.s_player (ID)
;

alter table s_game_s_player
  add constraint FKqgee4fvt1ncuwgtisn09gnmwk
foreign key (players_ID) references saga.s_player (ID)
;

create table s_role
(
  id int not null auto_increment
    primary key,
  ROLE_NAME varchar(255) not null,
  PLAYER_ID bigint not null,
  constraint FKgbsqvl8j3m2e77atccttmequ4
  foreign key (PLAYER_ID) references saga.s_player (ID)
)
;

create index FKgbsqvl8j3m2e77atccttmequ4
  on s_role (PLAYER_ID)
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

alter table s_game
  add constraint FK1ewi1nqvkdmjda96e765sd4qi
foreign key (AWAY_TEAM_ID) references saga.s_team (ID)
;

alter table s_game
  add constraint FK7ayyk513fdwmbimq9kwfkvgne
foreign key (HOME_TEAM_ID) references saga.s_team (ID)
;

alter table s_game
  add constraint FKn9w8pwge38ontol40gd3v6qbi
foreign key (TEAM_ID) references saga.s_team (ID)
;

alter table s_player
  add constraint FKi134gbv702vjgpkdowsooh4mi
foreign key (TEAM_ID) references saga.s_team (ID)
;

