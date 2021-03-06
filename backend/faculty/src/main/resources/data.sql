--USER --
insert into users(id, name, surname, parental, role, about, course, faculty, email, created_at, updated_at, password, username)
values ('6314099e-4c88-11ec-81d3-0242ac130003', 'Mandy', 'Dolton', 'Claire', 'TEACHER', 'about todo', 1, 'INFORMATICS',
        'teacher@gmail.com', 0, 0, '$2a$12$6szy.C0uMpGqBWreK.0i.OPx4.ngPZcqtUgdpUeey9I1DMlQTh/5e', 'teacher');

insert into users(id, name, surname, parental, role, about, course, faculty, email, created_at, updated_at, password, username)
values ('88f38f3e-4c8a-11ec-81d3-0242ac130003', 'Valeria', 'Shartz', 'Dorian', 'STUDENT', 'about todo', 1,
        'INFORMATICS', 'student@gmail.com', 0, 0, '$2a$12$6szy.C0uMpGqBWreK.0i.OPx4.ngPZcqtUgdpUeey9I1DMlQTh/5e', 'student');

insert into users(id, name, surname, parental, role, about, course, faculty, email, created_at, updated_at, password, username)
values ('88f38f3e-4c8a-11ec-81d3-2042ac130003', 'Test', 'Test', 'Test', 'TEACHER', 'about todo', 1,
        'INFORMATICS', 'student2@gmail.com', 0, 0, '$2a$12$6szy.C0uMpGqBWreK.0i.OPx4.ngPZcqtUgdpUeey9I1DMlQTh/5e', 'student2');
--USER --
insert into users(id, name, surname, parental, role, about, course, faculty, email, created_at, updated_at, password, username)
values ('6314099e-4c88-11ec-12d3-0242ac130003', 'ADMIN', 'ADMIN', 'ADMIN', 'ADMINISTRATOR', 'about todo', 1, 'INFORMATICS',
        'admin@gmail.com', 0, 0, '$2a$12$6szy.C0uMpGqBWreK.0i.OPx4.ngPZcqtUgdpUeey9I1DMlQTh/5e', 'admin');


-- SUBJECT --
insert into subject(id, name, faculty, speciality, course, code, trim, created_at, updated_at)
values ('9abf6df0-4c8a-11ec-81d3-0242ac130003', 'Algebra', 'INFORMATICS', 'Computer Science', 1, 111111, 1, 0,
        0);
insert into subject(id, name, faculty, speciality, course, code, trim, created_at, updated_at)
values ('9abf6da0-4c8a-11ec-81d3-0242ac130003', 'C++', 'INFORMATICS', 'Computer Science', 1, 111112, 1, 0,
        0);
insert into subject(id, name, faculty, speciality, course, code, trim, created_at, updated_at)
values ('9abf6db0-4c8a-11ec-81d3-0242ac130003', 'AI', 'INFORMATICS', 'Computer Science', 1, 111113, 1, 0,
        0);


-- EVENT --
insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('b75d57c4-4c8a-11ec-81d3-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-12-01 08:30:00.591', 'all', 'lecture', '3-311', 0, 0);

insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('b75d57c4-4c8a-11ec-81d4-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-12-01 10:00:00.591', 'gr1', 'practice', '3-311', 0, 0);

insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('b75d57c4-4c8a-10ec-81d5-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-12-01 11:40:00.591', 'gr2', 'practice', '3-311', 0, 0);

insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('b75d58c4-4c8a-12ec-81d5-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-12-07 11:40:00.591', 'gr2', 'practice', '3-311', 0, 0);


-- ATTENDEE --
insert into attendee(id, event_id, user_id, created_at, updated_at)
values ('fe3f9a8a-4c8a-11ec-81d3-0242ac130003', 'b75d57c4-4c8a-11ec-81d3-0242ac130003',
        '88f38f3e-4c8a-11ec-81d3-0242ac130003', 0, 0);

insert into attendee(id, event_id, user_id, created_at, updated_at)
values ('fe3f9a8a-4c8a-111c-81d3-0242ac130003', 'b75d57c4-4c8a-11ec-81d4-0242ac130003',
        '88f38f3e-4c8a-11ec-81d3-0242ac130003', 0, 0);


--- REQUEST ---
insert into request(id, created_at, updated_at, auditory, group_name, name, datetime, time, subject_id, event_id,
                    user_id)
values ('25e69c33-79de-4741-8dc2-f4815d999585', 0, 0, '3-777', 'all', 'lecture', '	2021-12-01 08:30:00',
        '2021-12-05 18:39:56.681', '9abf6df0-4c8a-11ec-81d3-0242ac130003',
        'b75d57c4-4c8a-11ec-81d3-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003');


