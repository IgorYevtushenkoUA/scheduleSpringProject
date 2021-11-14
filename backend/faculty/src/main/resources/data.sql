--USER --
insert into users(id, name, surname, parental, role, about, course, faculty, email, created_at, updated_at)
values ('3fa85f64-5717-4562-b3fc-2c963f66afa6', 'Mandy', 'Dolton', 'Claire', 'STUDENT', 'about todo', 1, 'INFORMATICS', 'fsad@gmail.com', 0, 0);

-- SUBJECT --
insert into subject(id, name, faculty, speciality, course, code, trim, created_at, updated_at)
values ('3fa85f64-5717-4562-b3fc-2c963f66afa6', 'Computer Science', 'INFORMATICS', 'Computer Science', 1, 111111, 1, 0, 0);

-- EVENT --
insert into event(id, user_id, subject_id, datetime, group_name, name, auditory, created_at, updated_at)
values ('3fa85f64-5717-4562-b3fc-2c963f66afa6', '3fa85f64-5717-4562-b3fc-2c963f66afa6', '3fa85f64-5717-4562-b3fc-2c963f66afa6', '2021-09-01 08:30:00.591', 'gr1', 'Algebra lecture', '3-311', 0, 0);
