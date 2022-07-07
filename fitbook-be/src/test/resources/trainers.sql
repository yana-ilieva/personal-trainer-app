insert into test.roles (id, name) values (1, 'ROLE_CLIENT'), (2, 'ROLE_TRAINER');

insert into test.users (id, deleted, email, password, role_id)
values (1, false, 'client@mail.com', '12345678', 1),
 (2, false, 'trainer@mail.com', '12345678', 2),
 (3, false, 'trainer2@mail.com', '12345678', 2),
 (4, false, 'trainer4@mail.com', '12345678', 2);

insert into test.trainers(id, user_id, gender, first_name, last_name, city)
values (10, 2, 'MALE', 'John', 'Smith', 'London'),
(20, 3, 'FEMALE', 'Jane', 'Doe', 'Reading');

insert into test.clients(id, user_id, trainer_id)
values (10, 1, 10);

insert into test.programs(id) values (10);

insert into test.trainers_programs(trainer_id, programs_id)
values (10, 10);
