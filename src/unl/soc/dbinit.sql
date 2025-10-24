use user;

drop table if exists User;
drop table if exists Post;
drop table if exists Visibility;


create table User (
	userID int not null auto_increment,
    username varchar(255) not null,
    password varchar(255) not null,
    primary key (userID)
    );

create table Post (
	postID int not null auto_increment,
    postText varchar(255) not null,
    postTime varchar(255) not null,
    author varchar(255) not null,
    userID int not null,
    primary key (postID),
	foreign key (userID) references User(userID)
    );
    
create table Visibility (
    userID int not null,
    userID2 int not null,
    primary key(userID, userID2),
    foreign key (userID) references User(userID),
	foreign key (userID2) references User(userID)
    );
    
    insert into User (userId, username, password)
		values (1, "Alice", "Alice123"),
			   (2, "Bob", "Bob123"),
               (3, "Crystal", "Crystal123"),
               (4, "David", "David123");
               
	insert into Post (postText, postTime, author, userID)
		values ("Project deadline extended?", "19:00:00, 10/12/2023", "Alice", 1),
			   ("Yep", "19:01:00, 10/12/2023", "Bob", 2),
               ("Fall break", "09:00:00, 10/16/2023", "David", 4),
               ("Lab due tonight?", "23:30:00, 10/27/2023", "Alice", 1),
               ("No, it's due next week", "23:35:00, 10/27/2023", "Crystal", 3);
						
    insert into Visibility (userID, userID2)
		values (1, 2),
			   (1, 3),
               (2, 1),
               (2, 3),
               (3, 1);
