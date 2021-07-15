CREATE TABLE IF NOT EXISTS users(
                                    user_id int auto_increment not null,
                                    primary key(user_id),
                                    firstName varchar(45) not null,
                                    lastName varchar(45) not null);
CREATE TABLE IF NOT EXISTS events(
                                     event_id int auto_increment not null,
                                     primary key(event_id),
                                     user_id int references users(user_id),
                                     file_id int references files(file_id));
CREATE TABLE IF NOT EXISTS files(
                                    file_id int auto_increment not null,
                                    primary key(file_id),
                                    path varchar(45) not null,
                                    metaData varchar(45) not null);