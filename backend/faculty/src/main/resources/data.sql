--USER --
insert into users(id, name, surname, parental, role, about, course, faculty, email, created_at, updated_at, password, username)
values ('6314099e-4c88-11ec-81d3-0242ac130003', 'Mandy', 'Dolton', 'Claire', 'TEACHER', 'about todo', 1, 'INFORMATICS',
        'teacher@gmail.com', 0, 0, '$2a$12$6szy.C0uMpGqBWreK.0i.OPx4.ngPZcqtUgdpUeey9I1DMlQTh/5e', 'mem');

insert into users(id, name, surname, parental, role, about, course, faculty, email, created_at, updated_at, password, username)
values ('88f38f3e-4c8a-11ec-81d3-0242ac130003', 'Valeria', 'Shartz', 'Dorian', 'STUDENT', 'about todo', 1,
        'INFORMATICS', 'student@gmail.com', 0, 0, '$2a$12$6szy.C0uMpGqBWreK.0i.OPx4.ngPZcqtUgdpUeey9I1DMlQTh/5e', 'igo_ory');


-- SUBJECT --
insert into subject(id, name, faculty, speciality, course, code, trim, created_at, updated_at)
values ('9abf6df0-4c8a-11ec-81d3-0242ac130003', 'Computer Science', 'INFORMATICS', 'Computer Science', 1, 111111, 1, 0,
        0);

-- EVENT --
insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('b75d57c4-4c8a-11ec-81d3-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-11-01 08:30:00.591', 'gr1', 'Algebra lecture', '3-311', 0, 0);

insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('cf2dc1a4-4c8a-11ec-81d3-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-11-07 08:30:00.591', 'gr1', 'Algebra lecture', '3-311', 0, 0);

insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('d96b2a44-4c8a-11ec-81d3-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-11-14 08:30:00.591', 'gr1', 'Algebra lecture', '3-311', 0, 0);

insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('e4bae02e-4c8a-11ec-81d3-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-11-28 10:00:00.591', 'gr1', 'Algebra practice', '3-311', 0, 0);

insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('ed32d0b8-4c8a-11ec-81d3-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-11-28 08:30:00.591', 'gr1', 'Algebra lecture', '3-311', 0, 0);

insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('f5e90006-4c8a-11ec-81d3-0242ac130003', '6314099e-4c88-11ec-81d3-0242ac130003',
        '9abf6df0-4c8a-11ec-81d3-0242ac130003', '2021-12-05 08:30:00.591', 'gr1', 'Algebra lecture', '3-311', 0, 0);


-- ATTENDEE --
insert into attendee(id, event_id, user_id, created_at, updated_at)
values ('fe3f9a8a-4c8a-11ec-81d3-0242ac130003', 'b75d57c4-4c8a-11ec-81d3-0242ac130003',
        '88f38f3e-4c8a-11ec-81d3-0242ac130003', 0, 0);

insert into attendee(id, event_id, user_id, created_at, updated_at)
values ('04089732-4c8b-11ec-81d3-0242ac130003', 'cf2dc1a4-4c8a-11ec-81d3-0242ac130003',
        '88f38f3e-4c8a-11ec-81d3-0242ac130003', 0, 0);

insert into attendee(id, event_id, user_id, created_at, updated_at)
values ('07c9f410-4c8b-11ec-81d3-0242ac130003', 'd96b2a44-4c8a-11ec-81d3-0242ac130003',
        '88f38f3e-4c8a-11ec-81d3-0242ac130003', 0, 0);

insert into attendee(id, event_id, user_id, created_at, updated_at)
values ('0b8fef3c-4c8b-11ec-81d3-0242ac130003', 'e4bae02e-4c8a-11ec-81d3-0242ac130003',
        '88f38f3e-4c8a-11ec-81d3-0242ac130003', 0, 0);

insert into attendee(id, event_id, user_id, created_at, updated_at)
values ('0f2e77bc-4c8b-11ec-81d3-0242ac130003', 'ed32d0b8-4c8a-11ec-81d3-0242ac130003',
        '88f38f3e-4c8a-11ec-81d3-0242ac130003', 0, 0);

insert into attendee(id, event_id, user_id, created_at, updated_at)
values ('122380a2-4c8b-11ec-81d3-0242ac130003', 'f5e90006-4c8a-11ec-81d3-0242ac130003',
        '88f38f3e-4c8a-11ec-81d3-0242ac130003', 0, 0);


