use eventlister_db;

insert into users (username, email, password, admin)
values ('adiss0', 'dadamides0@rambler.ru', 'n8I1Xt8vJlKu', 0);
insert into users (username, email, password, admin)
values ('shynde1', 'hlednor1@umn.edu', 'n1RbqxWWj3Vx', 1);

use eventlister_db;

insert into events (title, user_id, description, is_approved)
values ('Ziegfeld Follies', 1,
        'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.',
        1);
insert into events (title, user_id, description, is_approved)
values ('Events Horizon', 1,
        'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.',
        1);
insert into events (title, user_id, description, is_approved)
values ('Red Cliff (Chi bi)', 2,
        'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 1);
insert into events (title, user_id, description, is_approved)
values ('Chandni Chowk to China', 2,
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.',
        1);
insert into events (title, user_id, description, is_approved)
values ('This is an unapproved post', 1,
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.',
        0);
insert into events (title, user_id, description, is_approved)
values ('This is a second unapproved post', 2,
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.',
        0);

insert into categories (category, parent_id)
VALUES ('Music', 0);
insert into categories (category, parent_id)
VALUES ('Movies', 0);
insert into categories (category, parent_id)
VALUES ('Arts', 0);
insert into categories (category, parent_id)
VALUES ('Family', 0);

insert into categories (category, parent_id)
VALUES ('18+', 1),
       ('21+', 1),
       ('All Ages', 1),
       ('Free', 2),
       ('Family-friendly', 2),
       ('Rated R', 2),
       ('Wrestling', 3),
       ('Comedy', 3),
       ('Theater', 3),
       ('Baby-Friendly', 4),
       ('Arts & Crafts', 4),
       ('Outdoors', 4);

insert into event_category(event_id, category_id)
values (1, 2),
       (1, 5),
       (2, 2),
       (2, 8),
       (3, 3),
       (3, 11),
       (4, 4),
       (4, 15)