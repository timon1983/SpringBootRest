CREATE TABLE IF NOT EXISTS users(
                                    user_id   int auto_increment not null,
                                    primary key(user_id),
                                    firstName varchar(45) not null,
                                    lastName  varchar(45) not null);
CREATE TABLE IF NOT EXISTS events(
                                     event_id int auto_increment not null,
                                     primary key(event_id),
                                     user_id int references users(user_id),
                                     file_id int references files(file_id));
CREATE TABLE IF NOT EXISTS files(
                                    file_id  int auto_increment not null,
                                    primary key(file_id),
                                    path     varchar(45) not null,
                                    metaData varchar(45) not null);
CREATE TABLE IF NOT EXISTS usersauth(
                                    id       int auto_increment primary key,
                                    email    varchar(255) not null,
                                    name     varchar(50)  not null,
                                    surname  varchar(100) not null,
                                    password varchar(255) not null,
                                    role     varchar(20)  not null comment 'USER',
                                    status   varchar(20)  not null comment 'ACTIVE',
                                    constraint `USERS-AUTH_email_uindex`
                                        unique (email));
INSERT INTO usersauth(id,email,name,surname,password,role,status) VALUES
(1,'user@yandex.ru','user','userov','$2y$12$DZTnEPABdW7Yj6dvPpgUheg09ZPxpELyl1VyIEsypF9AfuJZ5ziQW','USER','ACTIVE'),
(2,'moder@mail.ru','moder','moderov','$2y$12$GOzPRBjQCK798lc7lntVWev3AK.AC46HzNHmHSyxVfdHEnufaCJt6','MODERATOR','ACTIVE'),
(3,'admin@gmail.com','admin','adminov','$2y$12$/K2yKTu6Qdq3l.st9dcK9u7s5ni8la5sRHAkXP2DM7mgZbFCPaJ8C','ADMIN','ACTIVE');
