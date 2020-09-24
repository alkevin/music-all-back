------- CREATE PRIVILEGE
DROP TABLE IF EXISTS privilege;
CREATE TABLE privilege
(
    id   bigint  NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_privilege_name (name)
);

------- CREATE ROLE
DROP TABLE IF EXISTS role;
CREATE TABLE role
(
    id   bigint  NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_role_name (name)
);

------- CREATE INSTRUMENT
DROP TABLE IF EXISTS instrument;
CREATE TABLE instrument
(
    id   bigint  NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_instrument_name (name)
);

------- CREATE LEVEL
DROP TABLE IF EXISTS level;
CREATE TABLE level
(
    id   bigint  NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_level_name (name)
);

------ CREATE PROFILE
DROP TABLE IF EXISTS profile;
CREATE TABLE profile
(
    id                bigint NOT NULL auto_increment,
    gender            char(1) null,
    age               int null,
    level_id          bigint null,
    PRIMARY KEY (id),
    CONSTRAINT fk_profile_level_id    foreign key (level_id) references level (id)
);

------ CREATE USER
DROP TABLE IF EXISTS user;
CREATE TABLE user
(
    id                bigint NOT NULL auto_increment,
    first_name        varchar(255) not null,
    last_name         varchar(255) not null,
    mail              varchar(255) not null,
    password          varchar(255) not null,
    profile_id        bigint null,
    creation_date     datetime(6)  null,
    modification_date datetime(6)  null,
    enabled           boolean null,
    connected         boolean null,
    PRIMARY KEY (id),
    UNIQUE key uk_mail (mail),
    CONSTRAINT fk_user_profile_id    foreign key (profile_id) references profile (id)

);

------ CREATE JOINT TABLE ROLES AND USERS
DROP TABLE IF EXISTS user_role;
create table user_role
(
    user_id bigint not null,
    role_id bigint not null,
    CONSTRAINT fk_user_role_role_id    foreign key (role_id) references role (id),
    CONSTRAINT fk_user_role_user_id   foreign key (user_id) references user (id)
);

------ CREATE JOINT TABLE ROLES AND PRIVILEGES
DROP TABLE IF EXISTS role_privilege;
create table role_privilege
(
    role_id bigint not null,
    privilege_id bigint not null,
    CONSTRAINT fk_role_privilege_role_id    foreign key (role_id) references role (id),
    CONSTRAINT fk_role_privilege_privilege_id   foreign key (privilege_id) references privilege (id)
);

------ CREATE JOINT TABLE PROFILE AND INSTRUMENT
DROP TABLE IF EXISTS profile_instrument;
create table profile_instrument
(
    profile_id bigint not null,
    instrument_id bigint not null,
    CONSTRAINT fk_profile_instrument_profile_id    foreign key (profile_id) references profile (id),
    CONSTRAINT fk_profile_instrument_instrument_id   foreign key (instrument_id) references instrument (id)
);

------ CREATE JOINT TABLE USER AND USER AS FRIEND
DROP TABLE IF EXISTS user_friend;
create table user_friend
(
    user_id bigint not null,
    friend_id bigint not null,
    status varchar(255) not null,
    creation_date     datetime(6)  null,
    modification_date datetime(6)  null,
    CONSTRAINT fk_user_friend_user_id    foreign key (user_id) references user (id),
    CONSTRAINT fk_user_friend_friend_id   foreign key (friend_id) references user (id)
);

------ CREATE CONVERSATION
DROP TABLE IF EXISTS conversation;
create table conversation
(
    id bigint not null auto_increment,
    user_id bigint not null,
    creation_date     datetime(6)  null,
    modification_date datetime(6)  null,
    PRIMARY KEY (id),
    CONSTRAINT fk_conversation_user_id foreign key (user_id) references user (id)
);

------ CREATE MESSAGES
DROP TABLE IF EXISTS message;
create table message
(
    id bigint not null auto_increment,
    conversation_id bigint not null,
    sender_id bigint not null,
    content varchar(255) not null,
    status varchar(255) not null,
    creation_date     datetime(6)  null,
    modification_date datetime(6)  null,
    PRIMARY KEY (id),
    CONSTRAINT fk_message_conversation_id foreign key (conversation_id) references conversation (id),
    CONSTRAINT fk_message_sender_id foreign key (sender_id) references user (id)
);
