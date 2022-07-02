insert into test.roles (id, name) values (1, 'ROLE_CLIENT'), (2, 'ROLE_TRAINER');

insert into test.users (id, deleted, email, password, role_id)
values (1, false, 'client@mail.com', '12345678', 1),
 (2, false, 'trainer@mail.com', '12345678', 2),
 (3, false, 'client2@mail.com', '12345678', 1),
 (4, false, 'client3@mail.com', '12345678', 1),
 (5, false, 'client4@mail.com', '12345678', 1),
 (6, false, 'client5@mail.com', '12345678', 1);

insert into test.clients (id, user_id) values (1, 1), (2, 3), (3, 4), (4, 5), (5, 6);
insert into test.trainers (id, user_id) values (1, 2);

insert into test.notifications (id, created_timestamp, notification_type, resolved, client_id, trainer_id)
values (10, '2022-06-01T10:00', 'REQUEST_SENT', true, 1, 1),
(20, '2022-06-02T10:00', 'REQUEST_ACCEPTED', false, 1, 1),
(30, '2022-06-03T10:00', 'REQUEST_SENT', false, 2, 1),
(40, '2022-06-04T10:00', 'REQUEST_SENT', false, 3, 1),
(50, '2022-06-05T10:00', 'REQUEST_SENT', true, 4,  1),
(60, '2022-06-06T10:00', 'REQUEST_ACCEPTED', false, 4, 1);