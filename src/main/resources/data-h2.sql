-- Initialize the privileges
INSERT INTO privilege(name)
VALUES ('READ_PRIVILEGE');

INSERT INTO privilege(name)
VALUES ('WRITE_PRIVILEGE');

INSERT INTO privilege(name)
VALUES ('CHANGE_PASSWORD_PRIVILEGE');

-- Initialize the roles
INSERT INTO role(name)
VALUES ('ADMIN_ROLE');

INSERT INTO role(name)
VALUES ('USER_ROLE');

-- Give the admin/roles privileges
INSERT INTO role_privilege(role_id, privilege_id)
VALUES (1,1);

INSERT INTO role_privilege(role_id, privilege_id)
VALUES (1,2);

INSERT INTO role_privilege(role_id, privilege_id)
VALUES (1,3);

-- Give the user/roles privileges
INSERT INTO role_privilege(role_id, privilege_id)
VALUES (2,1);

INSERT INTO role_privilege(role_id, privilege_id)
VALUES (2,3);

-- Create level

INSERT INTO level (name)
VALUES ('AMATEUR');
INSERT INTO level (name)
VALUES ('PROFESSIONNEL');

-- Create Profile

INSERT INTO profile (gender, age, level_id)
VALUES ('M',
        18,
        2);

INSERT INTO profile (gender, age, level_id)
VALUES ('F',
        31,
        1);

INSERT INTO profile (gender, age, level_id)
VALUES ('F',
        25,
        2);

INSERT INTO profile (gender, age, level_id)
VALUES ('M',
        44,
        1);

INSERT INTO profile (gender, age, level_id)
VALUES ('M',
        77,
        2);

INSERT INTO profile (gender, age, level_id)
VALUES ('F',
        65,
        2);

-- Initialize the users

INSERT INTO user (first_name, last_name, mail, password, profile_id, creation_date, modification_date, enabled, connected)
VALUES ( 'Jean', 'Luc', 'jean.luc@test.com',
         '$2a$10$GJmv93WiauIGqpDmw/D.T.WGO6r.T3oI6CgC.cuvnkHGpKosh.v0m',
         1,
         '2019-12-14',
         '2019-12-14 18:42:00.996000000',
          true,
          false);

INSERT INTO user (first_name, last_name, mail, password, profile_id, creation_date, modification_date, enabled, connected)
VALUES ( 'Micheal', 'Marie', 'micheal.marie@test.com',
         '$2a$04$5zLXcpOeuKyZZPbDa9CZ6uFloNQi3RjKD30l2rQuz8M/.wEl5D9fS',
         2,
         '2019-12-14',
         '2019-12-14 18:43:00.996000000',
          true,
          false);

INSERT INTO user (first_name, last_name, mail, password, profile_id, creation_date, modification_date, enabled, connected)
VALUES ( 'Foe', 'Foe', 'foe.foe@test.com',
         '$2a$10$GJmv93WiauIGqpDmw/D.T.WGO6r.T3oI6CgC.cuvnkHGpKosh.v0m',
         3,
         '2019-12-14',
         '2019-12-14 18:42:00.996000000',
          true,
          false);

INSERT INTO user (first_name, last_name, mail, password, profile_id, creation_date, modification_date, enabled, connected)
VALUES ( 'Alphonse', 'Truc', 'alphonse.truc@test.com',
         '$2a$10$GJmv93WiauIGqpDmw/D.T.WGO6r.T3oI6CgC.cuvnkHGpKosh.v0m',
         4,
         '2019-12-14',
         '2019-12-14 18:42:00.996000000',
          true,
          false);

INSERT INTO user (first_name, last_name, mail, password, profile_id, creation_date, modification_date, enabled, connected)
VALUES ( 'Paul', 'Dumanche', 'paul.dumanche@test.com',
         '$2a$10$GJmv93WiauIGqpDmw/D.T.WGO6r.T3oI6CgC.cuvnkHGpKosh.v0m',
         5,
         '2019-12-14',
         '2019-12-14 18:42:00.996000000',
          true,
          false);

INSERT INTO user (first_name, last_name, mail, password, profile_id, creation_date, modification_date, enabled, connected)
VALUES ( 'Marc', 'Henry', 'marc.henry@test.com',
         '$2a$10$GJmv93WiauIGqpDmw/D.T.WGO6r.T3oI6CgC.cuvnkHGpKosh.v0m',
         6,
         '2019-12-14',
         '2019-12-14 18:42:00.996000000',
          true,
          false);

-- Give the admin/user role

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1);
INSERT INTO user_role (user_id, role_id)
VALUES (2, 2);
INSERT INTO user_role (user_id, role_id)
VALUES (3, 2);
INSERT INTO user_role (user_id, role_id)
VALUES (4, 2);
INSERT INTO user_role (user_id, role_id)
VALUES (5, 2);
INSERT INTO user_role (user_id, role_id)
VALUES (6, 2);

-- Create instrument

INSERT INTO instrument (name)
VALUES ('Alto');
INSERT INTO instrument (name)
VALUES ('Banjo');
INSERT INTO instrument (name)
VALUES ('Basse');
INSERT INTO instrument (name)
VALUES ('Basson');
INSERT INTO instrument (name)
VALUES ('Baryton');
INSERT INTO instrument (name)
VALUES ('Batterie');
INSERT INTO instrument (name)
VALUES ('Bois');
INSERT INTO instrument (name)
VALUES ('Caisse claire');
INSERT INTO instrument (name)
VALUES ('Caixa');
INSERT INTO instrument (name)
VALUES ('Castaniettes');
INSERT INTO instrument (name)
VALUES ('Clarinette');
INSERT INTO instrument (name)
VALUES ('Claves');
INSERT INTO instrument (name)
VALUES ('Clavecin');
INSERT INTO instrument (name)
VALUES ('Contralto');
INSERT INTO instrument (name)
VALUES ('Contre basse');
INSERT INTO instrument (name)
VALUES ('Contrebassine');
INSERT INTO instrument (name)
VALUES ('Contre-ténor');
INSERT INTO instrument (name)
VALUES ('Cor anglais');
INSERT INTO instrument (name)
VALUES ('Cor d''harmonie');
INSERT INTO instrument (name)
VALUES ('Cymbales');
INSERT INTO instrument (name)
VALUES ('Cymbalum');
INSERT INTO instrument (name)
VALUES ('Epinette');
INSERT INTO instrument (name)
VALUES ('Flûte à bec');
INSERT INTO instrument (name)
VALUES ('Flûte traversière');
INSERT INTO instrument (name)
VALUES ('Gong');
INSERT INTO instrument (name)
VALUES ('Guitare');
INSERT INTO instrument (name)
VALUES ('Gouroumi');
INSERT INTO instrument (name)
VALUES ('Grosse caisse');
INSERT INTO instrument (name)
VALUES ('Harpe');
INSERT INTO instrument (name)
VALUES ('Hautbois');
INSERT INTO instrument (name)
VALUES ('Haute-contre');
INSERT INTO instrument (name)
VALUES ('Hukulele');
INSERT INTO instrument (name)
VALUES ('Luth');
INSERT INTO instrument (name)
VALUES ('Lyre');
INSERT INTO instrument (name)
VALUES ('Orgue');
INSERT INTO instrument (name)
VALUES ('Mandoline');
INSERT INTO instrument (name)
VALUES ('Marimba');
INSERT INTO instrument (name)
VALUES ('Méso-soprano');
INSERT INTO instrument (name)
VALUES ('Piano');
INSERT INTO instrument (name)
VALUES ('Saxophone');
INSERT INTO instrument (name)
VALUES ('Soprano');
INSERT INTO instrument (name)
VALUES ('Tamborim');
INSERT INTO instrument (name)
VALUES ('Tambour');
INSERT INTO instrument (name)
VALUES ('Tambourin');
INSERT INTO instrument (name)
VALUES ('Ténor');
INSERT INTO instrument (name)
VALUES ('Timbales');
INSERT INTO instrument (name)
VALUES ('Tongue drum');
INSERT INTO instrument (name)
VALUES ('Toms');
INSERT INTO instrument (name)
VALUES ('Triangle');
INSERT INTO instrument (name)
VALUES ('Trombone');
INSERT INTO instrument (name)
VALUES ('Trompette');
INSERT INTO instrument (name)
VALUES ('Tuba');
INSERT INTO instrument (name)
VALUES ('Voix');
INSERT INTO instrument (name)
VALUES ('Vibrophone');
INSERT INTO instrument (name)
VALUES ('Viole de Gambe');
INSERT INTO instrument (name)
VALUES ('Violon');
INSERT INTO instrument (name)
VALUES ('Violoncelle');
INSERT INTO instrument (name)
VALUES ('Xylophone');

-- Create profiles instruments association

INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (1,2);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (1,4);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (1,6);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (2,9);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (3,6);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (3,7);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (3,10);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (3,12);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (4,40);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (4,50);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (5,2);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (5,44);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (5,27);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (6,3);
INSERT INTO profile_instrument (profile_id, instrument_id)
VALUES (6,54);


-- Create user friend list

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (1,
        2,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (1,
        3,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (1,
        4,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (1,
        5,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (1,
        6,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (2,
        1,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (2,
        3,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (2,
        4,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (2,
        5,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (2,
        6,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (3,
        1,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (3,
        2,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (3,
        4,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (3,
        5,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (3,
        6,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (4,
        1,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (4,
        2,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (4,
        3,
        'ACCEPTED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (4,
        5,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (4,
        6,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (5,
        1,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (5,
        2,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (5,
        3,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (5,
        4,
        'PENDING',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (5,
        6,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (6,
        1,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (6,
        2,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (6,
        3,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (6,
        4,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO user_friend (user_id, friend_id, status, creation_date, modification_date)
VALUES (6,
        5,
        'BLOCKED',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

-- Create conversations

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (1,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (2,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (3,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (4,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (5,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (6,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (1,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (2,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (3,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (4,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (5,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (6,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (1,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (2,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (3,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (4,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (5,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO conversation (user_id, creation_date, modification_date)
VALUES (6,
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

-- Create messages

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (1,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (1,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (1,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (1,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (2,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (2,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (2,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (2,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (3,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (3,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (3,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (3,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (4,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (4,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (4,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (4,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (5,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (5,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (5,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (5,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (6,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (6,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (6,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (6,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (7,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (7,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (7,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (7,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (8,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (8,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (8,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (8,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (9,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (9,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (9,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (9,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (10,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (10,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (10,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (10,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (11,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (11,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (11,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (11,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (12,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (12,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (12,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (12,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (13,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (13,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (13,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (13,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (14,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (14,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (14,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (14,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (15,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (15,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (15,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (15,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (16,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (16,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (16,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (16,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (17,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (17,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (17,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (17,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (18,
        1,
        'Salut comment tu vas ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:42:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (18,
        2,
        'ça va et toi ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:43:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (18,
        1,
        'bien, comment s''est passé le concert ?',
        'READ',
        '2019-12-14',
        '2019-12-14 18:44:00.996000000');

INSERT INTO message (conversation_id, sender_id, content, status, creation_date, modification_date)
VALUES (18,
        2,
        'très bien !',
        'SEND',
        '2019-12-14',
        '2019-12-14 18:45:00.996000000');