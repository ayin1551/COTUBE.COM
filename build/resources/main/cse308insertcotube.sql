INSERT INTO account
VALUES('1', 'user1', 'password1', 'security_question_1', 'user1answer', 'profile_pic_path', 'user');

INSERT INTO account
VALUES('2', 'user2', 'password2', 'security_question_1', 'user2answer', 'profile_pic_path2', 'user');

INSERT INTO account
VALUES('3', 'user3', 'password3', 'security_question_2', 'user3answer', 'profile_pic_path3', 'user');

INSERT INTO account
VALUES('4', 'admin1', 'pass1', 'security_question_1', 'admin1answer', 'profile_pic_path4', 'admin');

INSERT INTO followUser
VALUES('user1', 'user2', curDate());

INSERT INTO followUser
VALUES('user1', 'user3', curDate());

INSERT INTO followUser
VALUES('user2', 'user3', curDate());

INSERT INTO followUser
VALUES('user3', 'user1', curDate());