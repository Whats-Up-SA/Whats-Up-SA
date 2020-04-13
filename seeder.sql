use eventlister_db;

insert into users (username, email, password, admin)
values ('adiss0', 'dadamides0@rambler.ru', 'n8I1Xt8vJlKu', 0);
insert into users (username, email, password, admin)
values ('shynde1', 'hlednor1@umn.edu', 'n1RbqxWWj3Vx', 1);
insert into users (username, email, password, admin)
values ('brahmin', 'smellymines@tumblr.ru', 'n8I1Xt8vJlKu', 1);
insert into users (username, email, password, admin)
values ('chelkster', 'chelkazaur@umn.edu', 'n1RbqxWWj3Vx', 0);

use eventlister_db;

insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Yellow Pincushion', 'Suspendisse accumsan tortor quis turpis. Sed ante. Vivamus tortor.', false,
        'http://dummyimage.com/141x143.bmp/5fa2dd/ffffff', '7:29', '17:56', '2020-03-24', '2020-01-23', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Columbian Onion',
        'Morbi odio odio, elementum eu, interdum eu, tincidunt in, leo. Maecenas pulvinar lobortis est. Phasellus sit amet erat.',
        false, 'http://dummyimage.com/212x207.jpg/5fa2dd/ffffff', '21:12', '5:19', '2020-02-15', '2019-11-05', 4);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Asian White Birch',
        'Nullam molestie nibh in lectus. Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus.',
        true, 'http://dummyimage.com/115x101.jpg/5fa2dd/ffffff', '1:50', '0:15', '2019-11-02', '2020-02-01', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Arctic Aspicilia',
        'Aliquam sit amet diam in magna bibendum imperdiet. Nullam orci pede, venenatis non, sodales sed, tincidunt eu, felis.',
        true, 'http://dummyimage.com/142x247.jpg/5fa2dd/ffffff', '9:40', '15:46', '2019-12-24', '2020-03-11', 2);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Nevada Agave',
        'Integer pede justo, lacinia eget, tincidunt eget, tempus vel, pede. Morbi porttitor lorem id ligula. Suspendisse ornare consequat lectus.',
        true, 'http://dummyimage.com/207x140.jpg/cc0000/ffffff', '14:34', '15:35', '2019-05-15', '2020-02-17', 4);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Cuming''s Lovegrass',
        'Pellentesque viverra pede ac diam. Cras pellentesque volutpat dui. Maecenas tristique, est et tempus semper, est quam pharetra magna, ac consequat metus sapien ut nunc.',
        true, 'http://dummyimage.com/217x209.jpg/5fa2dd/ffffff', '23:03', '23:37', '2019-10-31', '2019-05-06', 2);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Sorrie''s Heartleaf',
        'Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis. Ut at dolor quis odio consequat varius.',
        true, 'http://dummyimage.com/248x244.jpg/cc0000/ffffff', '10:29', '2:59', '2020-04-05', '2019-09-22', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Sanguinaria', 'Donec posuere metus vitae ipsum.', false, 'http://dummyimage.com/236x172.bmp/cc0000/ffffff',
        '18:28', '14:15', '2019-12-02', '2019-11-17', 3);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Drug Eyebright', 'Integer a nibh. In quis justo.', true, 'http://dummyimage.com/113x115.png/5fa2dd/ffffff',
        '14:03', '18:44', '2019-12-07', '2019-10-30', 4);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Spotted Gum', 'Nam tristique tortor eu pede.', true, 'http://dummyimage.com/167x147.jpg/cc0000/ffffff',
        '16:01', '11:03', '2019-05-09', '2019-09-11', 3);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Howell''s Fawnlily',
        'Proin risus. Praesent lectus. Vestibulum quam sapien, varius ut, blandit non, interdum in, ante.', false,
        'http://dummyimage.com/130x239.jpg/cc0000/ffffff', '19:34', '10:36', '2019-10-06', '2019-11-26', 2);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Panamint Mountain Lupine', 'Donec semper sapien a libero. Nam dui.', true,
        'http://dummyimage.com/149x196.bmp/dddddd/000000', '18:04', '0:32', '2019-10-19', '2019-07-28', 3);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Smallfruit Beggarticks',
        'Proin interdum mauris non ligula pellentesque ultrices. Phasellus id sapien in sapien iaculis congue. Vivamus metus arcu, adipiscing molestie, hendrerit at, vulputate vitae, nisl. Aenean lectus.',
        true, 'http://dummyimage.com/178x141.bmp/dddddd/000000', '16:35', '12:14', '2019-09-02', '2019-09-10', 4);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Ephemerum Moss', 'Duis at velit eu est congue elementum.', true,
        'http://dummyimage.com/240x138.png/5fa2dd/ffffff', '15:36', '2:22', '2019-12-31', '2019-06-12', 3);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Sugarcane', 'Nulla tellus.', true, 'http://dummyimage.com/224x192.jpg/ff4444/ffffff', '8:25', '17:27',
        '2019-04-25', '2019-10-02', 2);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Southwestern Rabbitbrush',
        'Pellentesque ultrices mattis odio. Donec vitae nisi. Nam ultrices, libero non mattis pulvinar, nulla pede ullamcorper augue, a suscipit nulla elit ac nulla.',
        true, 'http://dummyimage.com/101x170.png/ff4444/ffffff', '23:43', '21:56', '2020-04-04', '2020-01-19', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Whisk Fern',
        'Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Duis faucibus accumsan odio. Curabitur convallis. Duis consequat dui nec nisi volutpat eleifend.',
        false, 'http://dummyimage.com/234x105.bmp/cc0000/ffffff', '20:50', '1:00', '2020-04-19', '2019-06-10', 3);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Confusing Fescue', 'Nunc purus. Phasellus in felis.', true, 'http://dummyimage.com/242x159.bmp/ff4444/ffffff',
        '4:11', '9:30', '2020-02-17', '2020-01-19', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Dogbane', 'Duis bibendum. Morbi non quam nec dui luctus rutrum.', true,
        'http://dummyimage.com/147x244.png/cc0000/ffffff', '22:47', '13:21', '2020-04-08', '2019-04-25', 4);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Pore Lichen',
        'Nam congue, risus semper porta volutpat, quam pede lobortis ligula, sit amet eleifend pede libero quis orci.',
        false, 'http://dummyimage.com/154x176.bmp/5fa2dd/ffffff', '1:22', '17:30', '2019-08-18', '2020-04-14', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('False Buffalograss',
        'Vivamus vestibulum sagittis sapien. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Etiam vel augue. Vestibulum rutrum rutrum neque.',
        true, 'http://dummyimage.com/233x240.jpg/cc0000/ffffff', '0:24', '1:31', '2020-01-17', '2020-02-13', 4);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('American Century Plant', 'Suspendisse potenti.', true, 'http://dummyimage.com/144x246.jpg/5fa2dd/ffffff',
        '8:01', '19:29', '2020-04-23', '2020-04-21', 4);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Macoun''s Pore Lichen', 'Aliquam augue quam, sollicitudin vitae, consectetuer eget, rutrum at, lorem.', false,
        'http://dummyimage.com/170x184.png/cc0000/ffffff', '9:36', '7:46', '2019-08-04', '2020-03-01', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Mock Vervain', 'Nulla tempus. Vivamus in felis eu sapien cursus vestibulum. Proin eu mi.', true,
        'http://dummyimage.com/224x224.png/cc0000/ffffff', '9:09', '6:27', '2020-01-09', '2020-02-24', 4);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Blackthread Lichen',
        'Donec semper sapien a libero. Nam dui. Proin leo odio, porttitor id, consequat in, consequat ut, nulla. Sed accumsan felis.',
        false, 'http://dummyimage.com/210x145.png/dddddd/000000', '5:54', '1:37', '2019-07-09', '2020-04-24', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Erect Tropical Daisy',
        'Donec quis orci eget orci vehicula condimentum. Curabitur in libero ut massa volutpat convallis.', false,
        'http://dummyimage.com/223x200.bmp/dddddd/000000', '6:10', '6:14', '2019-11-24', '2019-08-11', 2);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Graceful Bedstraw',
        'Pellentesque at nulla. Suspendisse potenti. Cras in purus eu magna vulputate luctus. Cum sociis natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus.',
        false, 'http://dummyimage.com/228x209.bmp/ff4444/ffffff', '19:22', '6:35', '2020-03-22', '2020-02-25', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Celery', 'Etiam justo. Etiam pretium iaculis justo. In hac habitasse platea dictumst.', true,
        'http://dummyimage.com/220x123.png/ff4444/ffffff', '16:51', '18:03', '2019-08-31', '2019-10-26', 1);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Bogrush',
        'Quisque id justo sit amet sapien dignissim vestibulum. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Nulla dapibus dolor vel est.',
        false, 'http://dummyimage.com/161x177.jpg/ff4444/ffffff', '4:53', '3:57', '2019-10-31', '2020-04-17', 3);
insert into events (title, description, is_approved, event_image, start_time, end_time, start_date, end_date, user_id)
values ('Rare Clubmoss',
        'Maecenas tincidunt lacus at velit. Vivamus vel nulla eget eros elementum pellentesque. Quisque porta volutpat erat.',
        true, 'http://dummyimage.com/141x153.png/5fa2dd/ffffff', '5:29', '3:00', '2020-03-02', '2019-08-13', 2);

insert into categories (category, parent_id)
VALUES ('Music', 0);
insert into categories (category, parent_id)
VALUES ('Movies', 0);
insert into categories (category, parent_id)
VALUES ('Arts', 0);
insert into categories (category, parent_id)
VALUES ('Family', 0);

insert into event_category (event_id, category_id)
values (1, 4);
insert into event_category (event_id, category_id)
values (2, 4);
insert into event_category (event_id, category_id)
values (3, 1);
insert into event_category (event_id, category_id)
values (4, 4);
insert into event_category (event_id, category_id)
values (5, 1);
insert into event_category (event_id, category_id)
values (6, 1);
insert into event_category (event_id, category_id)
values (7, 2);
insert into event_category (event_id, category_id)
values (8, 1);
insert into event_category (event_id, category_id)
values (9, 3);
insert into event_category (event_id, category_id)
values (10, 4);
insert into event_category (event_id, category_id)
values (11, 1);
insert into event_category (event_id, category_id)
values (12, 2);
insert into event_category (event_id, category_id)
values (13, 3);
insert into event_category (event_id, category_id)
values (14, 4);
insert into event_category (event_id, category_id)
values (15, 2);
insert into event_category (event_id, category_id)
values (16, 4);
insert into event_category (event_id, category_id)
values (17, 2);
insert into event_category (event_id, category_id)
values (18, 2);
insert into event_category (event_id, category_id)
values (19, 1);
insert into event_category (event_id, category_id)
values (20, 4);
insert into event_category (event_id, category_id)
values (21, 1);
insert into event_category (event_id, category_id)
values (22, 1);
insert into event_category (event_id, category_id)
values (23, 2);
insert into event_category (event_id, category_id)
values (24, 2);
insert into event_category (event_id, category_id)
values (25, 4);
insert into event_category (event_id, category_id)
values (26, 4);
insert into event_category (event_id, category_id)
values (27, 4);
insert into event_category (event_id, category_id)
values (28, 3);
insert into event_category (event_id, category_id)
values (29, 1);
insert into event_category (event_id, category_id)
values (30, 4);

DELETE
from users
where id = 1;