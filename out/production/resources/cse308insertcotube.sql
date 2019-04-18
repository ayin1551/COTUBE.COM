INSERT INTO account
VALUES('user1', 'password1', 1, 'user1answer', 'profile_pic_path', 0);

INSERT INTO account
VALUES('user2', 'password2', 1, 'user2answer', 'profile_pic_path2', 0);

INSERT INTO account
VALUES('user3', 'password3', 2, 'user3answer', 'profile_pic_path3', 0);

INSERT INTO account
VALUES('admin1', 'pass1', 1, 'admin1answer', 'profile_pic_path4', 1);

INSERT INTO followUser
VALUES('user1', 'user2', curDate());

INSERT INTO followUser
VALUES('user1', 'user3', curDate());

INSERT INTO followUser
VALUES('user2', 'user3', curDate());

INSERT INTO followUser
VALUES('user3', 'user1', curDate());