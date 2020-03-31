use eventlister_db;

insert into users (username, email, password, is_admin)
values ('adiss0', 'dadamides0@rambler.ru', 'n8I1Xt8vJlKu', 0);
insert into users (username, email, password, is_admin)
values ('shynde1', 'hlednor1@umn.edu', 'n1RbqxWWj3Vx', 1);

use eventlister_db;

insert into events (title, user_id, description, is_approved)
values ('Ziegfeld Follies', 2,
        'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.',
        1);
insert into events (title, user_id, description, is_approved)
values ('Events Horizon', 2,
        'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.',
        1);
insert into events (title, user_id, description, is_approved)
values ('Red Cliff (Chi bi)', 2,
        'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.', 1);
insert into events (title, user_id, description, is_approved)
values ('Chandni Chowk to China', 2,
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.',
        1);


# insert into categories (category, parent_id)
# VALUES ('music', 0);
# insert into categories (category, parent_id)
# VALUES ('movies', 0);
# insert into categories (category, parent_id)
# VALUES ('arts', 0);
# insert into categories (category, parent_id)
# VALUES ('family', 0);
