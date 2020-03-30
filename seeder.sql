use eventlister_db;

insert into users (username, email, password)
values ('adiss0', 'dadamides0@rambler.ru', 'n8I1Xt8vJlKu');
insert into users (username, email, password)
values ('shynde1', 'hlednor1@umn.edu', 'n1RbqxWWj3Vx');

use eventlister_db;

insert into events (title, user_id, description)
values ('Ziegfeld Follies', 1,
        'Aenean fermentum. Donec ut mauris eget massa tempor convallis. Nulla neque libero, convallis eget, eleifend luctus, ultricies eu, nibh.');
insert into events (title, user_id, description)
values ('Events Horizon', 1,
        'In hac habitasse platea dictumst. Morbi vestibulum, velit id pretium iaculis, diam erat fermentum justo, nec condimentum neque sapien placerat ante. Nulla justo.');
insert into events (title, user_id, description)
values ('Red Cliff (Chi bi)', 2,
        'In quis justo. Maecenas rhoncus aliquam lacus. Morbi quis tortor id nulla ultrices aliquet.');
insert into events (title, user_id, description)
values ('Chandni Chowk to China', 2,
        'Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla. Sed vel enim sit amet nunc viverra dapibus. Nulla suscipit ligula in lacus.');
