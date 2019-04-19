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

INSERT INTO Folder
VALUES(1, 'user3', 'favfolder1', 0, 1);

INSERT INTO Folder
VALUES(2, 'user3', 'favfolder2', 0, 1);

INSERT INTO Folder
VALUES(3, 'user1', 'garfieldseriesfolder1', 1, 1);

INSERT INTO Panel
VALUES(1, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4TyAbTgJtsWlUB3SMaytFi9XsiniH1r2dX0n8jR_lDZthOKW3-A', NULL, curdate());

INSERT INTO Panel
VALUES(2, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAeysQYdLE3qDyfid4nXi0iLxvJ6vtwc3U4Ju50zl1nQfaICbN', NULL, curdate());

INSERT INTO Panel
VALUES(3, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0LyCEImuityCx-giYKJl5qBN2mszqrOjIuI7k4hfC0kJdu8lhWA', NULL, curdate());

INSERT INTO Comic
VALUES(1, 0, 'garfield1', 1, curdate());

INSERT INTO Comic
VALUES(2, 0, 'garfield2', 1, curdate());

INSERT INTO Comic
VALUES(3, 0, 'nonseries', 1, curdate());

INSERT INTO Series
VALUES(1, 'garfield', 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4TyAbTgJtsWlUB3SMaytFi9XsiniH1r2dX0n8jR_lDZthOKW3-A');

INSERT INTO RegularComic
VALUES(1, 1,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4TyAbTgJtsWlUB3SMaytFi9XsiniH1r2dX0n8jR_lDZthOKW3-A', 'part1 garfield series', 1);

INSERT INTO RegularComic
VALUES(2, 1,'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAeysQYdLE3qDyfid4nXi0iLxvJ6vtwc3U4Ju50zl1nQfaICbN','part2 garfield series', 2);

INSERT INTO RegularComic
VALUES(3, NULL,  'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0LyCEImuityCx-giYKJl5qBN2mszqrOjIuI7k4hfC0kJdu8lhWA','nonseries comic', 3);

INSERT INTO FollowSeries
VALUES('user3', 1, curDate());

INSERT INTO Views
VALUES(1, 'user3', curDate());

INSERT INTO Views
VALUES(2, 'user3', curDate());

INSERT INTO Views
VALUES(2, 'user2', curDate());

INSERT INTO Likes
VALUES(1, 'user3', curDate());

INSERT INTO Likes
VALUES(2, 'user3', curDate());

INSERT INTO Comments
VALUES(1, 1, 'user3', curDate(), 'awesome!', 0);

INSERT INTO Tag
VALUES(1, 'garfieldtag');

INSERT INTO Favorite
VALUES(1, 'user3', curDate(), 1);

INSERT INTO Favorite
VALUES(2, 'user3', curDate(), 2);

