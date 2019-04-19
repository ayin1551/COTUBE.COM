CREATE TABLE Favorite(
	comic_id INTEGER,
    favoriter_username VARCHAR(255),
    favorite_time DATETIME,
    favorite_folder_id INTEGER,
    PRIMARY KEY(comic_id, favoriter_username, favorite_folder_id),
    FOREIGN KEY (comic_id) REFERENCES Comic (comic_id)
		ON DELETE NO ACTION
        ON UPDATE CASCADE,
	FOREIGN KEY (favorite_folder_id) REFERENCES Folder (folder_id)
		ON DELETE NO ACTION
        ON UPDATE CASCADE,
	FOREIGN KEY (favoriter_username) REFERENCES Account (username)
		ON DELETE NO ACTION
        ON UPDATE CASCADE);
CREATE TABLE Favorite(
	comic_id INTEGER,
    favoriter_username VARCHAR(255),
    favorite_time DATETIME,
    favorite_folder_id INTEGER,
    PRIMARY KEY(comic_id, favoriter_username),
    FOREIGN KEY (comic_id) REFERENCES Comic (comic_id)
		ON DELETE NO ACTION
        ON UPDATE CASCADE,
	FOREIGN KEY (favorite_folder_id) REFERENCES Folder (folder_id)
		ON DELETE NO ACTION
        ON UPDATE CASCADE,
	FOREIGN KEY (favoriter_username) REFERENCES Account (username)
		ON DELETE NO ACTION
        ON UPDATE CASCADE);
/*
CREATE TABLE GameComic(

CREATE TABLE Notification(
	notification_id INTEGER,
	username VARCHAR(255),
    notification VARCHAR(1000),
    notification_time DATETIME,
    PRIMARY KEY(notification_id),
    FOREIGN KEY (username) REFERENCES Account (username)
		ON DELETE NO ACTION
        ON UPDATE CASCADE);

#Folder table - Username, FolderName, type(series, favorite)