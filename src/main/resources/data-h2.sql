-- Initialize the privileges
INSERT INTO privileges(name)
VALUES ('READ_PRIVILEGE');

INSERT INTO privileges(name)
VALUES ('WRITE_PRIVILEGE');

INSERT INTO privileges(name)
VALUES ('CHANGE_PASSWORD_PRIVILEGE');

-- Initialize the roles
INSERT INTO roles(name)
VALUES ('ADMIN_ROLE');

INSERT INTO roles(name)
VALUES ('USER_ROLE');

-- Give the admin/roles privileges
INSERT INTO roles_privileges(role_id, privilege_id)
VALUES (1,1);

INSERT INTO roles_privileges(role_id, privilege_id)
VALUES (1,2);

INSERT INTO roles_privileges(role_id, privilege_id)
VALUES (1,3);

-- Give the user/roles privileges
INSERT INTO roles_privileges(role_id, privilege_id)
VALUES (2,1);

INSERT INTO roles_privileges(role_id, privilege_id)
VALUES (2,3);

-- Initialize the user

INSERT INTO users (
    first_name,
    last_name,
    mail,
    password,
    creation_date,
    modification_date,
    enabled,
    provider,
    provider_id)
VALUES ( 'Jean', 'Luc', 'jean.luc@test.com',
         '$2a$04$HwelVsNMMDXP4Kc/R9uI4ehDBTBTaPRvVcvryBJIM6jneJ64eM2E6',
         '2019-12-14',
         '2019-12-14 18:42:00.996000000',
          true,
         'local',
         'local_ID');

INSERT INTO users (
    first_name,
    last_name,
    mail,
    password,
    creation_date,
    modification_date,
    enabled,
    provider,
    provider_id)
VALUES ( 'Micheal', 'Marie', 'micheal.marie@test.com',
         '$2a$04$5zLXcpOeuKyZZPbDa9CZ6uFloNQi3RjKD30l2rQuz8M/.wEl5D9fS',
         '2019-12-14',
         '2019-12-14 18:43:00.996000000',
          true,
         'local',
         'local_ID');

-- Give the admin/user role

INSERT INTO users_roles (user_id, role_id)
VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id)
VALUES (2, 2);