CREATE TABLE users
(
    id        INT AUTO_INCREMENT PRIMARY KEY,
    email     VARCHAR(250) NOT NULL,
    password  VARCHAR(96)  NOT NULL,
    name      VARCHAR(96)  NOT NULL,
    surname   VARCHAR(96)  NOT NULL,
    parental  VARCHAR(96)  NOT NULL,
    role      VARCHAR(96)  NOT NULL,
    about     TEXT         NOT NULL,
    avatar_id LONG         NOT NULL
);

CREATE TABLE subject
(
    id         INT PRIMARY KEY,
    created    TIMESTAMP   NOT NULL,
    name       VARCHAR(96) NOT NULL,
    faculty    VARCHAR(96) NOT NULL,
    speciality VARCHAR(96) NOT NULL,
    course     INT         NOT NULL,
    code       INT         NOT NULL,
    trim       VARCHAR(32) NOT NULL
);

CREATE TABLE event
(
    id       INT PRIMARY KEY,
    created  TIMESTAMP   NOT NULL,
    groups   VARCHAR(96) NOT NULL,
    name     VARCHAR(96) NOT NULL,
    auditory VARCHAR(32) NOT NULL,
    request  BOOLEAN     NOT NULL
);

CREATE TABLE request
(
    id       INT PRIMARY KEY,
    created  TIMESTAMP NOT NULL,
    user_id  LONG      NOT NULL,
    event_id LONG      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES event (id)
);

CREATE TABLE atendee
(
    id       INT PRIMARY KEY,
    created  TIMESTAMP NOT NULL,
    user_id  LONG      NOT NULL,
    event_id LONG      NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (event_id) REFERENCES event (id)
);

-- USER --
insert into users(id, email, password, name, surname, parental, role, about, avatar_id)
values (1, 'mandy.dolton@ukma.edu.ua', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm', 'Mandy',
        'Dolton', 'Claire', 'STUDENT', 'about todo', 1);

insert into users(id, email, password, name, surname, parental, role, about, avatar_id)
values (2, 'paulina.vlencia@ukma.edu.ua', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm', 'Paulina',
        'Valencia', 'Timur', 'STUDENT', 'about todo', 1);

insert into users(id, name, email, password, surname, parental, role, about, avatar_id)
values (3, 'phebe.bowes@ukma.edu.ua', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm', 'Phebe', 'Bowes',
        'Hania', 'STUDENT', 'about todo', 2);

insert into users(id, email, password, name, surname, parental, role, about, avatar_id)
values (4, 'bradley.davine@ukma.edu.ua', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm', 'Bradley',
        'Devine', 'Zayan', 'TEACHER', 'about todo', 1);

insert into users(id, email, password, name, surname, parental, role, about, avatar_id)
values (5, 'kian.mullen@ukma.edu.ua', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm', 'Kian', 'Mullen',
        'Gunn', 'TEACHER', 'about todo', 1);

insert into users(id, email, password, name, surname, parental, role, about, avatar_id)
values (6, 'teagan.nicholson@ukma.edu.ua', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm', 'Teagan',
        'Nicholson', 'Benton', 'TEACHER', 'about todo', 1);

insert into users(id, email, password, name, surname, parental, role, about, avatar_id)
values (7, 'richard.gill@ukma.edu.ua', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm', 'Richard',
        'Gill', 'Fletcher', 'TEACHER', 'about todo', 1);

insert into users(id, email, password, name, surname, parental, role, about, avatar_id)
values (8, 'bently.avila@ukma.edu.ua', '$2a$10$JQor2Lkc5wAN4hWUQQq.wudA2kb/vXUsGevKkmD.xOmCyqLnM.UHm', 'Bentley',
        'Avila', 'Hull', 'ADMINISTRATOR', 'about todo', 1);

-- SUBJECT --
insert into subject(id, created, name, faculty, speciality, course, code, trim)
values (1, '1636092860', 'Computer Science', 'INFORMATICS', 'Computer Science', 1, 111111, 1);

insert into subject(id, created, name, faculty, speciality, course, code, trim)
values (2, '1636092860', 'Introduction to Computer Networks', 'INFORMATICS', 'Computer Science', 2, 111112, 1);

insert into subject(id, created, name, faculty, speciality, course, code, trim)
values (3, '1636092860', 'Databases', 'INFORMATICS', 'Software Engineering', 3, 111113, 1);

insert into subject(id, created, name, faculty, speciality, course, code, trim)
values (4, '1636092860', 'Algebra', 'INFORMATICS', 'Mathematics', 1, 111114, 1);

insert into subject(id, created, name, faculty, speciality, course, code, trim)
values (5, '1636092860', 'Computer Structure', 'INFORMATICS', 'Software Engineering', 2, 111115, 1);

insert into subject(id, created, name, faculty, speciality, course, code, trim)
values (6, '1636092860', 'Computer Architecture', 'INFORMATICS', 'Computer Science', 2, 111116, 2);

-- EVENT --
insert into event(id, created, groups, name, auditory, request)
values (1, '1636092860', 'gr1', 'Algebra lecture', '3-311', false);

insert into event(id, created, groups, name, auditory, request)
values (2, '1636092860', 'gr2', 'Algebra practice', '3-312', false);

insert into event(id, created, groups, name, auditory, request)
values (3, '1636092860', 'gr1', 'Computer Structure lecture', '1-311', false);

insert into event(id, created, groups, name, auditory, request)
values (4, '1636092860', 'gr1', 'Computer Structure practice', '1-311', false);

insert into event(id, created, groups, name, auditory, request)
values (5, '1636092860', 'gr1', 'Computer Architecture lecture', '1-215', false);


-- ATENDEE --
insert into atendee(id, created, user_id, event_id)
values (1, '1636092860', 1, 1);
insert into atendee(id, created, user_id, event_id)
values (2, '1636092860', 1, 2);
insert into atendee(id, created, user_id, event_id)
values (3, '1636092860', 2, 1);
insert into atendee(id, created, user_id, event_id)
values (4, '1636092860', 2, 2);
insert into atendee(id, created, user_id, event_id)
values (5, '1636092860', 3, 3);
insert into atendee(id, created, user_id, event_id)
values (6, '1636092860', 3, 3);


-- REQUEST --
-- insert into request values();