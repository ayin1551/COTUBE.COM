
CREATE TABLE Account (
	id INTEGER AUTO_INCREMENT,
    username VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL ,
    security_question VARCHAR(1000) NOT NULL,
    security_answer VARCHAR(1000) NOT NULL,
    profile_pic_path VARCHAR(1000) NOT NULL,
    account_role VARCHAR(65) NOT NULL,
    PRIMARY KEY (id) );
    
CREATE TABLE FollowUser(
	follower_username VARCHAR(255),
    following_username VARCHAR(255),
    follow_time DATETIME,
    PRIMARY KEY (follower_username , following_username),
    FOREIGN KEY (follower_username) REFERENCES Account (username)
		ON DELETE NO ACTION
        ON UPDATE CASCADE,
	FOREIGN KEY (following_username) REFERENCES Account (username)
		ON DELETE NO ACTION
        ON UPDATE CASCADE);

    
	