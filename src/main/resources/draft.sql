------- CREATE ROLES
DROP TABLE IF EXISTS privileges;
CREATE TABLE privileges
(
    id   bigint  NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_roles_name (role_name)
);

DROP TABLE IF EXISTS roles;
CREATE TABLE roles
(
    id   bigint  NOT NULL AUTO_INCREMENT,
    name varchar(60) NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_roles_name (role_name)
);

------ CREATE USERS
DROP TABLE IF EXISTS users;
CREATE TABLE users
(
    id                bigint auto_increment,
    first_name        varchar(255) not null,
    last_name         varchar(255) not null,
    mail              varchar(255) not null,
    password          varchar(255) not null,
    creation_date     datetime(6)  null,
    modification_date datetime(6)  null,
    enabled           boolean null,
    provider          varchar(255) null,
    provider_id       bigint null,
    UNIQUE key uk_mail (mail)
);


------ CREATE JOINT TABLE ROLES AND USERS

DROP TABLE IF EXISTS user_roles;
create table user_roles
(
    user_id bigint not null,
    role_id bigint not null,
    CONSTRAINT fk_user_roles_role_id    foreign key (role_id) references roles (id),
    CONSTRAINT fk_user_roles_user_id   foreign key (user_id) references users (id)
);

------ CREATE JOINT TABLE ROLES AND PRIVILEGES

DROP TABLE IF EXISTS user_roles;
create table user_roles
(
    role_id bigint not null,
    privilege_id bigint not null,
    CONSTRAINT fk_user_roles_role_id    foreign key (role_id) references roles (id),
    CONSTRAINT fk_user_roles_privilege_id   foreign key (privilege_id) references privileges (id)
);