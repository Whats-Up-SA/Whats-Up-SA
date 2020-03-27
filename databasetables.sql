drop database if exists eventlister_db;

create database eventlister_db;

use eventlister_db;

Create table users
(
    id       int unsigned        not null auto_increment,
    username varchar(255) unique not null,
    email    varchar(255) unique not null,
    password varchar(255)        not null,
    primary key (id)
);

Create table relationship
(
    user_one_id    int unsigned not null,
    user_two_id    int unsigned not null,
    status         tinyint,
    action_user_id int unsigned not null,
    PRIMARY KEY (user_one_id, user_two_id, action_user_id),
    Foreign key (user_one_id) references users (id),
    Foreign key (user_two_id) references users (id),
    Foreign key (action_user_id) references users (id)
);

Create table events
(
    id          int unsigned not null auto_increment,
    user_id     int unsigned not null,
    title       varchar(50)  not null,
    description text         not null,
    primary key (id),
    foreign key (user_id) references users (id)
        on delete cascade
);

Create table joinerEvents
(
    user_id  int unsigned not null,
    event_id int unsigned not null,
    primary key (user_id, event_id),
    foreign key (user_id) references users (id),
    foreign key (event_id) references events (id)
);

Create table categories
(
    id        int unsigned not null,
    parent_id int unsigned not null,
    event_id  int unsigned not null,
    category  varchar(255) not null,
    primary key (id),
    foreign key (event_id) references events (id)
);

Create table joinerCat
(
    event_id int unsigned not null,
    cat_id   int unsigned not null,
    PRIMARY KEY (event_id, cat_id),
    foreign key (event_id) references events (id),
    foreign key (cat_id) references categories (id)
);
