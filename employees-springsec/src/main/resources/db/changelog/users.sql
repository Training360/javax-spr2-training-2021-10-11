create table users(username varchar(50) not null primary key,password varchar(500) not null,enabled boolean not null);
create table authorities (username varchar(50) not null,authority varchar(50) not null,constraint fk_authorities_users foreign key(username) references users(username));
create unique index ix_auth_username on authorities (username,authority);

insert into users(username, password, enabled) values ('johndoe', '$2a$10$6.x6aeuVLZ3wJG5Cq0dMQeCM4ixs7PUKHEUe11v4RfcVdM.e3osPa', true);
insert into authorities(username, authority) values ('johndoe', 'ROLE_USER');

insert into users(username, password, enabled) values ('jackdoe', '$2a$10$qT2fe.OjddFKanXGy5DR2escurA2DW/xk8aZO9o4V0QqzzLO3PmTq', true);
insert into authorities(username, authority) values ('jackdoe', 'ROLE_ADMIN')
