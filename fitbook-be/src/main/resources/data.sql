insert into roles (id, name)
values (1000000, 'ROLE_CLIENT'), (1000001, 'ROLE_TRAINER'), (1000002, 'ROLE_ADMIN')
on conflict (id) do nothing;

insert into users (id, deleted, email, password, role_id)
values (1000000000, false, 'admin@mail.com', '$2a$10$GAy6t9gbzGDv39NHH.S.hO5Ugt3fy8qgBoeWMU8PLuRXxXFl7qF9.', 1000002)
on conflict (id) do nothing;

insert into exercises (id, name)
values (1, 'Push Ups'),
(2, 'Barbell or Dumbbell Bench Press'),
(3, 'Incline Dumbbell'),
(4, 'Dumbbell Fly'),
(5, 'Pull ups'),
(6, 'Wide Grip Lat Pull Downs'),
(7, 'Seated Row Machine'),
(8, 'Bent Over Row (dumbbells or barbell)'),
(9, 'Air Squat'),
(10, 'Barbell Squat'),
(11, 'Leg Press Machine'),
(12, 'Walking Lunges'),
(13, 'Leg Extension Machine'),
(14, 'Leg Curl Machine'),
(15, 'Straight Leg Deadlifts'),
(16, 'Weighted Calf Raises'),
(17, 'Handstand Push Up'),
(18, 'Military Press with Barbell'),
(20, 'Shoulder Press Machine'),
(21, 'Arnold Presses'),
(22, 'Upright Rows'),
(23, 'Arnold Presses'),
(24, 'Lateral Raises'),
(25, 'Dips'),
(26, 'Tricep Pullovers'),
(27, 'Tricep Press'),
(28, 'Tricep Kickbacks'),
(29, 'Straight Bar Curl'),
(30, 'Preacher Curl'),
(31, 'Dumbbell Curl'),
(32, 'Concentration Curls'),
(33, 'Traditional Sit Ups'),
(34, 'Crunches'),
(35, 'Weighted Crunches'),
(36, 'Bicycle Crunches'),
(37, 'Leg Raises'),
(38, 'Hanging Knee Raise'),
(39, 'Oblique Crunch'),
(40, 'Plank'),
(41, 'Side Plank'),
(42, 'Back Extension')
on conflict (id) do nothing;