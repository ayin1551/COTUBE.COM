
CREATE TABLE Notification(
	notification_id INTEGER auto_increment,
    notification_type INTEGER, #1 = Admin censors comic, 2 = Admin deletes comment, 3 = Admin passes comic,
							#4 = Single comic in favorite folder - censored/deleted, 5 = Entire series in series following -censored/deleted
						    #6 = Invitation to join gamecomic, 7 =Gamecomic finished
	username VARCHAR(255),
    notification VARCHAR(1000),
    notification_time DATETIME,
    PRIMARY KEY(notification_id),
    FOREIGN KEY (username) REFERENCES Account (username)
		ON DELETE NO ACTION
        ON UPDATE CASCADE);
