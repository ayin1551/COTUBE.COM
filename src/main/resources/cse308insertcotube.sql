/*---------------ACCOUNT: 15 accounts, 2 admins ----------------*/
INSERT INTO account
VALUES('user1', 'password1', 1, 'user1-1answer', '/img/test/oldpp.jpg', 0);

INSERT INTO account
VALUES('user2', 'password2', 1, 'user2-1answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHQntteTtybk0MOF9-EYgY7SFP0YWmHuVdreBsoh92pkTZ-lWo7Q', 0);

INSERT INTO account
VALUES('user3', 'password3', 2, 'user3-2answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQWndT7s3BIRtpxlioKkWs7YJt3cTOiNZB5-j8WFZSE66XHilD8', 0);

INSERT INTO account
VALUES('user4', 'password4', 4, 'user4-4answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR1ZfeNiYElYzJAnqdptGfJN8zudvhsEqO2vUYoGmoJ3gZmuMAjDg', 0);

INSERT INTO account
VALUES('user5', 'password5', 3, 'user5-3answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRsxrPT1OoRp-meImiXdgSZdX9aJNzOrhvd6oEEZdvukIF2YMrQ', 0);

INSERT INTO account
VALUES('user6', 'password6', 3, 'user6-3answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOSfyAGiqHSU4YelfhLGA08EwcIZtpWux0nDBvjTUhUDPKoH6LEQ', 0);

INSERT INTO account
VALUES('user7', 'password7', 4, 'user7-4answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTiws39PFYFulVMfjdp3npy3geYcv_F_dTPHBHznrenSfaL19Wcbg', 0);

INSERT INTO account
VALUES('user8', 'password8', 3, 'user8-3answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQjDIDx1983415GCiq5eVli_qkK-z2OVD9f0mLlkIW6IXT2-09Z2g', 0);

INSERT INTO account
VALUES('user9', 'password9', 2, 'user9-2answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQBB54ojCXh4wrLygl355ZF7oqSxzRkbVs7viQG7DR6bt_v9jQO', 0);

INSERT INTO account
VALUES('user10', 'password10', 2, 'user10-2answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTef30plTwjVco7_6sYT-1MGDCo13ArTTkrRDKgdS5RY7nHabQ9ZA', 0);

INSERT INTO account
VALUES('user11', 'password11', 2, 'user11-2answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT94L7TgYdyziUg7Y4DsSIYkaQccjuk7RaOAO0VoE3AkWaeLenBmg', 0);

INSERT INTO account
VALUES('user12', 'password12', 1, 'user12-1answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQc8qliofx0-jxOkYLZAgHR_VfyJCA_LZWxzFDBnMwjHuY1gcP71Q', 0);

INSERT INTO account
VALUES('user13', 'password13', 2, 'user13-2answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTIE3cxEayX_H8PTQzJWhMAi0XyvNFjcmozmCxcZml5BKCowM8OkQ', 0);

INSERT INTO account
VALUES('user14', 'password14', 1, 'user14-1answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQJU8oMU4IAht1vm5TF35X2SX-y9Pik9y0w6teCFZVXIZvbZNwR', 0);

INSERT INTO account
VALUES('user15', 'password15', 2, 'user15-2answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkLeIzI1O-w_nVXR9nivJHXfn5CZf_2stQQlTbjT8AS-UFahJ4kQ', 0);

INSERT INTO account
VALUES('admin1', 'pass1', 1, 'admin1-1answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSRwWG4TnLQdKpOBgDZ3u8Ywf5p8kKfQSeFUPu-O0d0DICkjTNBvQ', 1);

INSERT INTO account
VALUES('admin2', 'pass2', 3, 'admin2-3answer', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQM2guCTRo_igFii5aiLzgKUQ_CPtmSKjH1STEBy6vJw4koiHT2', 1);

/* -------------------------------FollowUser pairs---------------------------------*/

INSERT INTO followUser
VALUES('user1', 'user2', curDate());

INSERT INTO followUser
VALUES('user1', 'user3', curDate());

INSERT INTO followUser
VALUES('user1', 'user11', curDate());

INSERT INTO followUser
VALUES('user1', 'user8', curDate());

INSERT INTO followUser
VALUES('user1', 'user14', curDate());

INSERT INTO followUser
VALUES('user2', 'user3', curDate());

INSERT INTO followUser
VALUES('user2', 'user7', curDate());

INSERT INTO followUser
VALUES('user2', 'user1', curDate());

INSERT INTO followUser
VALUES('user2', 'user12', curDate());

INSERT INTO followUser
VALUES('user2', 'user8', curDate());

INSERT INTO followUser
VALUES('user2', 'user6', curDate());

INSERT INTO followUser
VALUES('user3', 'user9', curDate());

INSERT INTO followUser
VALUES('user3', 'user1', curDate());

INSERT INTO followUser
VALUES('user3', 'user5', curDate());

INSERT INTO followUser
VALUES('user3', 'user2', curDate());

INSERT INTO followUser
VALUES('user3', 'user12', curDate());

INSERT INTO followUser
VALUES('user4', 'user1', curDate());

INSERT INTO followUser
VALUES('user4', 'user7', curDate());

INSERT INTO followUser
VALUES('user5', 'user6', curDate());

INSERT INTO followUser
VALUES('user5', 'user1', curDate());

INSERT INTO followUser
VALUES('user5', 'user9', curDate());

INSERT INTO followUser
VALUES('user6', 'user1', curDate());

INSERT INTO followUser
VALUES('user6', 'user12', curDate());

INSERT INTO followUser
VALUES('user6', 'user2', curDate());

INSERT INTO followUser
VALUES('user7', 'user1', curDate());

INSERT INTO followUser
VALUES('user7', 'user14', curDate());

INSERT INTO followUser
VALUES('user7', 'user11', curDate());

INSERT INTO followUser
VALUES('user8', 'user1', curDate());

INSERT INTO followUser
VALUES('user8', 'user12', curDate());

INSERT INTO followUser
VALUES('user8', 'user10', curDate());

INSERT INTO followUser
VALUES('user9', 'user1', curDate());

INSERT INTO followUser
VALUES('user11', 'user10', curDate());

INSERT INTO followUser
VALUES('user13', 'user15', curDate());

INSERT INTO followUser
VALUES('user12', 'user14', curDate());

/*--------------------------FOLDERS----------------*/

INSERT INTO Folder
VALUES(1, 'user3', 'user3favfolder1', 0, 1);

INSERT INTO Folder
VALUES(2, 'user3', 'user3favfolder2', 0, 1);

INSERT INTO Folder
VALUES(3, 'user1', 'user1garfieldseriesfolder1', 1, 1);

INSERT INTO Folder
VALUES(4, 'user1', 'user1natureseriesfolder', 1, 1);

INSERT INTO Folder
VALUES(5, 'user2', 'user2spidermanseriesfolder', 1, 1);

INSERT INTO Folder
VALUES(6, 'user2', 'user2favfolder1', 0, 1);

INSERT INTO Folder
VALUES(7, 'user7', 'user7favfolder1', 0, 1);

INSERT INTO Folder
VALUES(8, 'user5', 'user5favfolder1', 0, 1);

INSERT INTO Folder
VALUES(9, 'user4', 'user4thorseriesfolder', 1, 1);

INSERT INTO Folder
VALUES(10, 'user5', 'user5batmanseriesfolder', 1, 1);

INSERT INTO Folder
VALUES(11, 'user6', 'user6hulkseriesfolder', 1, 1);

INSERT INTO Folder
VALUES(12, 'user1', 'user1supermanseriesfolder', 1, 1);

INSERT INTO Folder
VALUES(13, 'user1', 'user1wonderwomanseriesfolder', 1, 1);

INSERT INTO Folder
VALUES(14, 'user1', 'user1favfolder1', 0, 1);

/*----------------------------------40 Panels---------------------------*/

INSERT INTO Panel
VALUES(1, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4TyAbTgJtsWlUB3SMaytFi9XsiniH1r2dX0n8jR_lDZthOKW3-A', NULL, curdate());

INSERT INTO Panel
VALUES(2, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAeysQYdLE3qDyfid4nXi0iLxvJ6vtwc3U4Ju50zl1nQfaICbN', NULL, curdate());

INSERT INTO Panel
VALUES(3, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6HyLNHsaUJBdzmsHcK-Z7_urZIWskmVJeBIL4DiaZok9ev95V', NULL, curdate());

INSERT INTO Panel
VALUES(4, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0LyCEImuityCx-giYKJl5qBN2mszqrOjIuI7k4hfC0kJdu8lhWA', NULL, curdate());

INSERT INTO Panel
VALUES(5, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_XqFEJ73-BWqMxfMf90OUYhvLk0ZqyjGlfoby8BW9uC8iW4fM', NULL, curdate());

INSERT INTO Panel
VALUES(6, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTw9_JrDTICymeilTg5dAXxg1sDVUk61ljui0qgRfs4p9EL-lyV', NULL, curdate());

INSERT INTO Panel
VALUES(7, 'user2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSclR-L-AbjkJ_UDNDRY-cG3xRMuoic1Lgj9AQbgMXDRr2kjbnO', NULL, curdate());

INSERT INTO Panel
VALUES(8, 'user2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9xnHi0etZ9yJ5eJxuCQ5X507vmcxwmkLGBfOmgtA0XMHujzdP', NULL, curdate());

INSERT INTO Panel
VALUES(9, 'user2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6eguliC1Yz-YBi_9vHfZBgGEbUBOff2fhW6UnFg8qp5QjCWm4', NULL, curdate());

INSERT INTO Panel
VALUES(10, 'user4', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoMYPqplQELS-cfYQefHHKRwKe5vu3sLl73crTnwlV-0y33Jqm', NULL, curdate());

INSERT INTO Panel
VALUES(11, 'user4', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWF1QAgjaOjRLSPCFFDxj9PruaDdNKOFxhbvYv9kFI92UOesGn1Q', NULL, curdate());

INSERT INTO Panel
VALUES(12, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcyEwxsyo-TV2QohDuGZ4uiDIQ5uvejTDikoGH0R3IdtVt6rFq', NULL, curdate());

INSERT INTO Panel
VALUES(13, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOu6XuvHuu95ELaRT-74CktGnyaHNvFkxWvH_j4Q0RtVjq1-oK', NULL, curdate());

INSERT INTO Panel
VALUES(14, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgW_nPTjUgnaj-z_z6c271V2HPdB3t1G1OjuqHv0YkjLHj5GFHDQ', NULL, curdate());

INSERT INTO Panel
VALUES(15, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRw5Lc1dRgSM7F9SgLiuoLKot-Q3uAkY6yq-J_r9K8kxTYHN9u', NULL, curdate());

INSERT INTO Panel
VALUES(16, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5nVVsLlaI7TKeBWXk-UmwuwvnS3WH0D3qkRTM3MdOvSOO1q0z_w', NULL, curdate());

INSERT INTO Panel
VALUES(17, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUKlC_q6vUhcOWIHRPlvVF06ihKEdSlvMZ-_tfqqS3FweKseyI', NULL, curdate());

INSERT INTO Panel
VALUES(18, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQt0s9Lz1WIauXYP3B_7teYIp-NoKut1N_r3eRztvwDMuNDJq5C', NULL, curdate());

INSERT INTO Panel
VALUES(19, 'user6', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtoKqZeZ1pIqX-F1e49mutn6t_E1ibg-bWIlsvnuc43PwTJAC01A', NULL, curdate());

INSERT INTO Panel
VALUES(20, 'user6', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4TJbmSkJ9gFy3xgvhp29UIeh1ylDsgPuZixX497QJerICMoM4', NULL, curdate());

INSERT INTO Panel
VALUES(21, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpl8Aye2ROTAKxf1mMLKdO7pD0QH-iqLmfIS1KIkp3i-kbRjLATQ', NULL, curdate());

INSERT INTO Panel
VALUES(22, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5mCmqvXwb4mkyJoNN_ozwE5k4o1t4pKtcD6yQkphTBMWrHfxm', NULL, curdate());

INSERT INTO Panel
VALUES(23, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvSMZv5rrnvm44lVwIU2dq2iwgp3Dpxv1hhh4YmatUacF2Ry5F', NULL, curdate());

INSERT INTO Panel
VALUES(24, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2nEr3SHRNKIhAreT5X1P84XGpC9VXPIpnmCy8bZ_NmNnq3v4C', NULL, curdate());

INSERT INTO Panel
VALUES(25, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-Lpw3azww_Pi70u0UjmsgiVjtksAVrifNlIG00WY2ESsr4VivOw', NULL, curdate());

INSERT INTO Panel
VALUES(26, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRE-8nITID2CG2qB4Yo9jbQQndtLjxkZ_1Rj4OjYUSflr88f1vqrQ', NULL, curdate());

INSERT INTO Panel
VALUES(27, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiG451MkAQhExh4-e-bohno90sPgCR7F1YVhw9w2ksxOgK-aVT', NULL, curdate());

INSERT INTO Panel
VALUES(28, 'user6', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUDZZX_xmNlbqXTfb1_R4vHUl69bRo9cSA71-9-cIAXlFMPsHY', NULL, curdate());

INSERT INTO Panel
VALUES(29, 'user6', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXM-jkpVlfa82NeC5_qpecxi1faEBdc1NEOFWTeYfXVxZRFK5P', NULL, curdate());

INSERT INTO Panel
VALUES(30, 'user2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0d35w9yoLAgfHLzWDCaw8qprxHEeVwSajDi_EZw9mCiZvUFao1g', NULL, curdate());

INSERT INTO Panel
VALUES(31, 'user2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROBVz9SU_OavYxBxAAXVJIE9RMWLiJIWjQULxHDmeMPCwK2nay', NULL, curdate());

INSERT INTO Panel
VALUES(32, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBEaWjlG7TJdFR8TYBmnp-RuNDZVkz4wNKKHG-gAvtR9O2KNRK', NULL, curdate());

INSERT INTO Panel
VALUES(33, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdqdtWgxEekUUwEDFUbkKwqmu8ZXeX4_eof6AfHpEOvT_zINThVQ', NULL, curdate());

INSERT INTO Panel
VALUES(34, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTE1xQvEO5tpymf8mPg92xefRLEjvQGMBAATDqN1pOijIf98HSB', NULL, curdate());

INSERT INTO Panel
VALUES(35, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTM7nj21mQhQr2xJcUvCql21VMfF5ERO3zOvYnWMHc2YdKMkwdBdA', NULL, curdate());

INSERT INTO Panel
VALUES(36, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQIIMxnP4Z2DjYs1ZPKU4d06H47hfbNLj9LE2H54snQbFg9d8HMA', NULL, curdate());

INSERT INTO Panel
VALUES(37, 'user2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTP5NpDzZ5-ZIqcGXEOfdPdOhLBLfd4AEl0w7N_EskAp7iCJavw', NULL, curdate());

INSERT INTO Panel
VALUES(38, 'user3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7lYy_yphB9NUEPgS4mdi3Rzy9zApc-PNi4RtwocfvIcEWJCuODg', NULL, curdate());

INSERT INTO Panel
VALUES(39, 'user4', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQizBLv1hWBCvcZAIn979TqrVIIvNGblI2kALT2MJpmcB7VGKZZgw', NULL, curdate());

INSERT INTO Panel
VALUES(40, 'user4', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVY9OlTqOrWNhLOpTbVsf0IOZTBb45JFjA3QPUpxkyWA1-qmV-OQ', NULL, curdate());
/*--------------------------------------------40 comics---------------------------------*/

INSERT INTO Comic
VALUES(1, 0, 'garfield1', 1, curdate());

INSERT INTO Comic
VALUES(2, 0, 'garfield2', 3, curdate());

INSERT INTO Comic
VALUES(3, 0, 'garfield3', 0, curdate());

INSERT INTO Comic
VALUES(4, 0, 'nature1', 1, curdate());

INSERT INTO Comic
VALUES(5, 0, 'noseriesbirdfish1', 1, curdate());

INSERT INTO Comic
VALUES(6, 0, 'nature2', 1, curdate());

INSERT INTO Comic
VALUES(7, 0, 'spiderman1', 1, curdate());

INSERT INTO Comic
VALUES(8, 0, 'spiderman2', 1, curdate());

INSERT INTO Comic
VALUES(9, 0, 'spiderman3', 1, curdate());

INSERT INTO Comic
VALUES(10, 0, 'thor1', 1, curdate());

INSERT INTO Comic
VALUES(11, 0, 'thor2', 1, curdate());

INSERT INTO Comic
VALUES(12, 0, 'batman1', 1, curdate());

INSERT INTO Comic
VALUES(13, 0, 'batman2', 1, curdate());

INSERT INTO Comic
VALUES(14, 0, 'batman3', 1, curdate());

INSERT INTO Comic
VALUES(15, 0, 'batman4', 1, curdate());

INSERT INTO Comic
VALUES(16, 0, 'batman5', 1, curdate());

INSERT INTO Comic
VALUES(17, 0, 'batman6', 1, curdate());

INSERT INTO Comic
VALUES(18, 0, 'batman7', 1, curdate());

INSERT INTO Comic
VALUES(19, 0, 'hulk1', 1, curdate());

INSERT INTO Comic
VALUES(20, 0, 'hulk2', 1, curdate());

INSERT INTO Comic
VALUES(21, 0, 'superman1', 1, curdate());

INSERT INTO Comic
VALUES(22, 0, 'superman2', 1, curdate());

INSERT INTO Comic
VALUES(23, 0, 'superman3', 1, curdate());

INSERT INTO Comic
VALUES(24, 0, 'superman4', 1, curdate());

INSERT INTO Comic
VALUES(25, 0, 'superman5', 1, curdate());

INSERT INTO Comic
VALUES(26, 0, 'superman6', 1, curdate());

INSERT INTO Comic
VALUES(27, 0, 'wonderwoman1', 1, curdate());

INSERT INTO Comic
VALUES(28, 0, 'noseriesaesthetic2', 0, curdate());

INSERT INTO Comic
VALUES(29, 0, 'noseriespokellama3', 0, curdate());

INSERT INTO Comic
VALUES(30, 0, 'spiderman4', 1, curdate());

INSERT INTO Comic
VALUES(31, 0, 'noseriessquidsuck4', 1, curdate());

INSERT INTO Comic
VALUES(32, 0, 'noseriesdonkey5', 0, curdate());

INSERT INTO Comic
VALUES(33, 0, 'noseriesboop6', 3, curdate());

INSERT INTO Comic
VALUES(34, 0, 'noseriesshoes7', 1, curdate());

INSERT INTO Comic
VALUES(35, 0, 'noserieshexagon8', 1, curdate());

INSERT INTO Comic
VALUES(36, 0, 'noseriesburger9', 1, curdate());

INSERT INTO Comic
VALUES(37, 0, 'noseriesoutline10', 1, curdate());

INSERT INTO Comic
VALUES(38, 0, 'noserieswall11', 1, curdate());

INSERT INTO Comic
VALUES(39, 0, 'noserieswalk12', 1, curdate());

INSERT INTO Comic
VALUES(40, 0, 'noseriesheart13', 1, curdate());

/* ------------------- 8 SERIES ----------------------*/
INSERT INTO Series
VALUES(1, 'garfield', 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRD-bJsCkSwTZTtZ6Mc3oauYEdRbE0RNNPdcNLTsPgD765RzriQ');

INSERT INTO Series
VALUES(2, 'nature', 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRB3HWCG9ipZeM9oq16jQIS6bLSa0NR2dGfszLkFxYA-QWn1KOKpQ');

INSERT INTO Series
VALUES(3, 'wonder woman', 13, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTPNWPVSLk-Tjlmvwv9L9-oWd_A6He46HR1zsjRU9bhdVsJrDoD');

INSERT INTO Series
VALUES(4, 'batman', 10, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRA0ZuKyLD29qOH-y06AeQ3et04d_8_wniH3d0rUy7p0Isw_hQm6g');

INSERT INTO Series
VALUES(5, 'thor', 9, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSHqzbFA9B1N6yZrNzQ4LVRRSIrARyfTx2XnjM-PrY9a-TqMIbN');

INSERT INTO Series
VALUES(6, 'spiderman', 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSQgE3BrffuNueQK8bdDbucE0AwU4hvJfM4_OWbubVc_Y6hhzugNA');

INSERT INTO Series
VALUES(7, 'hulk', 11, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTwJirJ9qveKqZBOt4MjsAW08mIA4lKrSL5pmtov5Ax1nbIpsaHRg');

INSERT INTO Series
VALUES(8, 'superman', 12, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ9jkGRtLk4ti1xWYiml1NE8DOJuXqZ6fNLAcZqwRxca33YlD3mDw');

/*------------------------------------40 Regular Comics -----------------*/

INSERT INTO RegularComic
VALUES(1, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4TyAbTgJtsWlUB3SMaytFi9XsiniH1r2dX0n8jR_lDZthOKW3-A', 'garf1', 1);

INSERT INTO RegularComic
VALUES(2, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAeysQYdLE3qDyfid4nXi0iLxvJ6vtwc3U4Ju50zl1nQfaICbN', 'garf2', 2);

INSERT INTO RegularComic
VALUES(3, 1, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6HyLNHsaUJBdzmsHcK-Z7_urZIWskmVJeBIL4DiaZok9ev95V', 'garf3', 3);

INSERT INTO RegularComic
VALUES(4, 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0LyCEImuityCx-giYKJl5qBN2mszqrOjIuI7k4hfC0kJdu8lhWA', 'nat1', 4);

INSERT INTO RegularComic
VALUES(5, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS_XqFEJ73-BWqMxfMf90OUYhvLk0ZqyjGlfoby8BW9uC8iW4fM', 'birdfish', 5);

INSERT INTO RegularComic
VALUES(6, 2, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTw9_JrDTICymeilTg5dAXxg1sDVUk61ljui0qgRfs4p9EL-lyV', 'nat2', 6);

INSERT INTO RegularComic
VALUES(7, 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSclR-L-AbjkJ_UDNDRY-cG3xRMuoic1Lgj9AQbgMXDRr2kjbnO', 'spidey1', 7);

INSERT INTO RegularComic
VALUES(8, 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcT9xnHi0etZ9yJ5eJxuCQ5X507vmcxwmkLGBfOmgtA0XMHujzdP', 'spidey2', 8);

INSERT INTO RegularComic
VALUES(9, 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS6eguliC1Yz-YBi_9vHfZBgGEbUBOff2fhW6UnFg8qp5QjCWm4', 'spidey3', 9);

INSERT INTO RegularComic
VALUES(10, 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoMYPqplQELS-cfYQefHHKRwKe5vu3sLl73crTnwlV-0y33Jqm', 'th1', 10);

INSERT INTO RegularComic
VALUES(11, 5, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRWF1QAgjaOjRLSPCFFDxj9PruaDdNKOFxhbvYv9kFI92UOesGn1Q', 'th2', 11);

INSERT INTO RegularComic
VALUES(12, 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRcyEwxsyo-TV2QohDuGZ4uiDIQ5uvejTDikoGH0R3IdtVt6rFq', 'bat1', 12);

INSERT INTO RegularComic
VALUES(13, 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSOu6XuvHuu95ELaRT-74CktGnyaHNvFkxWvH_j4Q0RtVjq1-oK', 'bat2', 13);

INSERT INTO RegularComic
VALUES(14, 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTgW_nPTjUgnaj-z_z6c271V2HPdB3t1G1OjuqHv0YkjLHj5GFHDQ', 'bat3', 14);

INSERT INTO RegularComic
VALUES(15, 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTRw5Lc1dRgSM7F9SgLiuoLKot-Q3uAkY6yq-J_r9K8kxTYHN9u', 'bat4', 15);

INSERT INTO RegularComic
VALUES(16, 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5nVVsLlaI7TKeBWXk-UmwuwvnS3WH0D3qkRTM3MdOvSOO1q0z_w', 'bat5', 16);

INSERT INTO RegularComic
VALUES(17, 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSUKlC_q6vUhcOWIHRPlvVF06ihKEdSlvMZ-_tfqqS3FweKseyI', 'bat6', 17);

INSERT INTO RegularComic
VALUES(18, 4, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQt0s9Lz1WIauXYP3B_7teYIp-NoKut1N_r3eRztvwDMuNDJq5C', 'bat7', 18);

INSERT INTO RegularComic
VALUES(19, 7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtoKqZeZ1pIqX-F1e49mutn6t_E1ibg-bWIlsvnuc43PwTJAC01A', 'hk1', 19);

INSERT INTO RegularComic
VALUES(20, 7, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR4TJbmSkJ9gFy3xgvhp29UIeh1ylDsgPuZixX497QJerICMoM4', 'hk2', 20);

INSERT INTO RegularComic
VALUES(21, 8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpl8Aye2ROTAKxf1mMLKdO7pD0QH-iqLmfIS1KIkp3i-kbRjLATQ', 'super1', 21);

INSERT INTO RegularComic
VALUES(22, 8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR5mCmqvXwb4mkyJoNN_ozwE5k4o1t4pKtcD6yQkphTBMWrHfxm', 'super2', 22);

INSERT INTO RegularComic
VALUES(23, 8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQvSMZv5rrnvm44lVwIU2dq2iwgp3Dpxv1hhh4YmatUacF2Ry5F', 'super3', 23);

INSERT INTO RegularComic
VALUES(24, 8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR2nEr3SHRNKIhAreT5X1P84XGpC9VXPIpnmCy8bZ_NmNnq3v4C', 'super4', 24);

INSERT INTO RegularComic
VALUES(25, 8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR-Lpw3azww_Pi70u0UjmsgiVjtksAVrifNlIG00WY2ESsr4VivOw', 'super5', 25);

INSERT INTO RegularComic
VALUES(26, 8, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRE-8nITID2CG2qB4Yo9jbQQndtLjxkZ_1Rj4OjYUSflr88f1vqrQ', 'super6', 26);

INSERT INTO RegularComic
VALUES(27, 3, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSiG451MkAQhExh4-e-bohno90sPgCR7F1YVhw9w2ksxOgK-aVT', 'ww1', 27);

INSERT INTO RegularComic
VALUES(28, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRUDZZX_xmNlbqXTfb1_R4vHUl69bRo9cSA71-9-cIAXlFMPsHY', 'aesthetic', 28);

INSERT INTO RegularComic
VALUES(29, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTXM-jkpVlfa82NeC5_qpecxi1faEBdc1NEOFWTeYfXVxZRFK5P', 'llama', 29);

INSERT INTO RegularComic
VALUES(30, 6, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0d35w9yoLAgfHLzWDCaw8qprxHEeVwSajDi_EZw9mCiZvUFao1g', 'spidey4', 30);

INSERT INTO RegularComic
VALUES(31, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcROBVz9SU_OavYxBxAAXVJIE9RMWLiJIWjQULxHDmeMPCwK2nay', 'squid', 31);

INSERT INTO RegularComic
VALUES(32, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSBEaWjlG7TJdFR8TYBmnp-RuNDZVkz4wNKKHG-gAvtR9O2KNRK', 'donkey', 32);

INSERT INTO RegularComic
VALUES(33, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTdqdtWgxEekUUwEDFUbkKwqmu8ZXeX4_eof6AfHpEOvT_zINThVQ', 'boop', 33);

INSERT INTO RegularComic
VALUES(34, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTE1xQvEO5tpymf8mPg92xefRLEjvQGMBAATDqN1pOijIf98HSB', 'shoes', 34);

INSERT INTO RegularComic
VALUES(35, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTM7nj21mQhQr2xJcUvCql21VMfF5ERO3zOvYnWMHc2YdKMkwdBdA', 'hexagon', 35);

INSERT INTO RegularComic
VALUES(36, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTQIIMxnP4Z2DjYs1ZPKU4d06H47hfbNLj9LE2H54snQbFg9d8HMA', 'burger', 36);

INSERT INTO RegularComic
VALUES(37, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTP5NpDzZ5-ZIqcGXEOfdPdOhLBLfd4AEl0w7N_EskAp7iCJavw', 'outline', 37);

INSERT INTO RegularComic
VALUES(38, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR7lYy_yphB9NUEPgS4mdi3Rzy9zApc-PNi4RtwocfvIcEWJCuODg', 'wall', 38);

INSERT INTO RegularComic
VALUES(39, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQizBLv1hWBCvcZAIn979TqrVIIvNGblI2kALT2MJpmcB7VGKZZgw', 'walk', 39);

INSERT INTO RegularComic
VALUES(40, NULL, 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTVY9OlTqOrWNhLOpTbVsf0IOZTBb45JFjA3QPUpxkyWA1-qmV-OQ', 'heart', 40);

/*****------------------------------------FollowSeries------------------*/
INSERT INTO FollowSeries
VALUES('user3', 6, curDate());

INSERT INTO FollowSeries
VALUES('user1', 6, curDate());

INSERT INTO FollowSeries
VALUES('user4', 6, curDate());

INSERT INTO FollowSeries
VALUES('user9', 6, curDate());

INSERT INTO FollowSeries
VALUES('user9', 8, curDate());

INSERT INTO FollowSeries
VALUES('user4', 8, curDate());

INSERT INTO FollowSeries
VALUES('user12', 6, curDate());

INSERT INTO FollowSeries
VALUES('user15', 6, curDate());

INSERT INTO FollowSeries
VALUES('user15', 1, curDate());

INSERT INTO FollowSeries
VALUES('user3', 1, curDate());

INSERT INTO FollowSeries
VALUES('user2', 1, curDate());

INSERT INTO FollowSeries
VALUES('user7', 1, curDate());

INSERT INTO FollowSeries
VALUES('user8', 1, curDate());

INSERT INTO FollowSeries
VALUES('user4', 1, curDate());

INSERT INTO FollowSeries
VALUES('user10', 1, curDate());

INSERT INTO FollowSeries
VALUES('user12', 1, curDate());

INSERT INTO FollowSeries
VALUES('user12', 2, curDate());

INSERT INTO FollowSeries
VALUES('user14', 3, curDate());

INSERT INTO FollowSeries
VALUES('user10', 5, curDate());

INSERT INTO FollowSeries
VALUES('user1', 5, curDate());

INSERT INTO FollowSeries
VALUES('user14', 4, curDate());

INSERT INTO FollowSeries
VALUES('user8', 4, curDate());

INSERT INTO FollowSeries
VALUES('user7', 5, curDate());

INSERT INTO FollowSeries
VALUES('user8', 7, curDate());

INSERT INTO FollowSeries
VALUES('user8', 3, curDate());

/*-----------------------------------VIEWS----------------*/

INSERT INTO Views
VALUES(1, 'user3', curDate());

INSERT INTO Views
VALUES(1, 'user4', curDate());

INSERT INTO Views
VALUES(1, 'user5', curDate());

INSERT INTO Views
VALUES(1, 'user6', curDate());

INSERT INTO Views
VALUES(1, 'user7', curDate());

INSERT INTO Views
VALUES(1, 'user9', curDate());

INSERT INTO Views
VALUES(1, 'user13', curDate());

INSERT INTO Views
VALUES(1, 'user11', curDate());

INSERT INTO Views
VALUES(1, 'user12', curDate());

INSERT INTO Views
VALUES(1, 'user15', curDate());

INSERT INTO Views
VALUES(2, 'user3', curDate());

INSERT INTO Views
VALUES(2, 'user6', curDate());

INSERT INTO Views
VALUES(2, 'user9', curDate());

INSERT INTO Views
VALUES(2, 'user12', curDate());

INSERT INTO Views
VALUES(2, 'admin1', curDate());

INSERT INTO Views
VALUES(3, 'user6', curDate());

INSERT INTO Views
VALUES(3, 'user8', curDate());

INSERT INTO Views
VALUES(3, 'user10', curDate());

INSERT INTO Views
VALUES(4, 'user9', curDate());

INSERT INTO Views
VALUES(4, 'user14', curDate());

INSERT INTO Views
VALUES(4, 'user6', curDate());

INSERT INTO Views
VALUES(4, 'user5', curDate());

INSERT INTO Views
VALUES(4, 'user12', curDate());

INSERT INTO Views
VALUES(4, 'user10', curDate());

INSERT INTO Views
VALUES(4, 'user8', curDate());

INSERT INTO Views
VALUES(5, 'user1', curDate());

INSERT INTO Views
VALUES(5, 'user15', curDate());

INSERT INTO Views
VALUES(6, 'user1', curDate());

INSERT INTO Views
VALUES(6, 'user12', curDate());

INSERT INTO Views
VALUES(6, 'user5', curDate());

INSERT INTO Views
VALUES(6, 'user14', curDate());

INSERT INTO Views
VALUES(6, 'user13', curDate());

INSERT INTO Views
VALUES(6, 'user15', curDate());

INSERT INTO Views
VALUES(7, 'user9', curDate());

INSERT INTO Views
VALUES(9, 'user14', curDate());

INSERT INTO Views
VALUES(10, 'user1', curDate());

INSERT INTO Views
VALUES(11, 'user1', curDate());

INSERT INTO Views
VALUES(12, 'user9', curDate());

INSERT INTO Views
VALUES(12, 'user10', curDate());

INSERT INTO Views
VALUES(12, 'user11', curDate());

INSERT INTO Views
VALUES(12, 'user12', curDate());

INSERT INTO Views
VALUES(12, 'user13', curDate());

INSERT INTO Views
VALUES(12, 'user14', curDate());

INSERT INTO Views
VALUES(13, 'user1', curDate());

INSERT INTO Views
VALUES(16, 'user2', curDate());

INSERT INTO Views
VALUES(17, 'user1', curDate());

INSERT INTO Views
VALUES(17, 'user2', curDate());

INSERT INTO Views
VALUES(18, 'user9', curDate());

INSERT INTO Views
VALUES(18, 'user14', curDate());

INSERT INTO Views
VALUES(18, 'user15', curDate());

INSERT INTO Views
VALUES(18, 'user13', curDate());

INSERT INTO Views
VALUES(21, 'user3', curDate());

INSERT INTO Views
VALUES(20, 'user4', curDate());

INSERT INTO Views
VALUES(23, 'user6', curDate());

INSERT INTO Views
VALUES(25, 'user15', curDate());

INSERT INTO Views
VALUES(27, 'user14', curDate());

INSERT INTO Views
VALUES(28, 'user12', curDate());

INSERT INTO Views
VALUES(27, 'user3', curDate());

INSERT INTO Views
VALUES(30, 'user9', curDate());

INSERT INTO Views
VALUES(31, 'user2', curDate());

INSERT INTO Views
VALUES(32, 'user2', curDate());

INSERT INTO Views
VALUES(33, 'user14', curDate());

INSERT INTO Views
VALUES(34, 'user15', curDate());

INSERT INTO Views
VALUES(37, 'user14', curDate());

INSERT INTO Views
VALUES(40, 'user7', curDate());
/*----------------------------LIKES----------------------------*/

INSERT INTO Likes
VALUES(1, 'user3', curDate());

INSERT INTO Likes
VALUES(1, 'user4', curDate());

INSERT INTO Likes
VALUES(1, 'user5', curDate());

INSERT INTO Likes
VALUES(1, 'user6', curDate());

INSERT INTO Likes
VALUES(1, 'user7', curDate());

INSERT INTO Likes
VALUES(1, 'user13', curDate());

INSERT INTO Likes
VALUES(1, 'user11', curDate());

INSERT INTO Likes
VALUES(1, 'user12', curDate());

INSERT INTO Likes
VALUES(1, 'user15', curDate());

INSERT INTO Likes
VALUES(2, 'user3', curDate());

INSERT INTO Likes
VALUES(2, 'user6', curDate());

INSERT INTO Likes
VALUES(2, 'user9', curDate());

INSERT INTO Likes
VALUES(2, 'user12', curDate());

INSERT INTO Likes
VALUES(2, 'admin1', curDate());

INSERT INTO Likes
VALUES(3, 'user6', curDate());

INSERT INTO Likes
VALUES(3, 'user8', curDate());

INSERT INTO Likes
VALUES(3, 'user10', curDate());

INSERT INTO Likes
VALUES(4, 'user5', curDate());

INSERT INTO Likes
VALUES(4, 'user12', curDate());

INSERT INTO Likes
VALUES(4, 'user10', curDate());

INSERT INTO Likes
VALUES(4, 'user8', curDate());

INSERT INTO Likes
VALUES(5, 'user1', curDate());

INSERT INTO Likes
VALUES(5, 'user15', curDate());

INSERT INTO Likes
VALUES(6, 'user1', curDate());

INSERT INTO Likes
VALUES(6, 'user12', curDate());

INSERT INTO Likes
VALUES(6, 'user5', curDate());

INSERT INTO Likes
VALUES(6, 'user13', curDate());

INSERT INTO Likes
VALUES(6, 'user15', curDate());

INSERT INTO Likes
VALUES(7, 'user9', curDate());

INSERT INTO Likes
VALUES(9, 'user14', curDate());

INSERT INTO Likes
VALUES(10, 'user1', curDate());

INSERT INTO Likes
VALUES(11, 'user1', curDate());

INSERT INTO Likes
VALUES(12, 'user9', curDate());

INSERT INTO Likes
VALUES(12, 'user10', curDate());

INSERT INTO Likes
VALUES(12, 'user12', curDate());

INSERT INTO Likes
VALUES(12, 'user13', curDate());

INSERT INTO Likes
VALUES(12, 'user14', curDate());

INSERT INTO Likes
VALUES(13, 'user1', curDate());

INSERT INTO Likes
VALUES(16, 'user2', curDate());

INSERT INTO Likes
VALUES(17, 'user1', curDate());

INSERT INTO Likes
VALUES(18, 'user9', curDate());

INSERT INTO Likes
VALUES(18, 'user14', curDate());

INSERT INTO Likes
VALUES(18, 'user15', curDate());

INSERT INTO Likes
VALUES(20, 'user4', curDate());

INSERT INTO Likes
VALUES(28, 'user12', curDate());

INSERT INTO Likes
VALUES(27, 'user3', curDate());

INSERT INTO Likes
VALUES(30, 'user9', curDate());

INSERT INTO Likes
VALUES(40, 'user7', curDate());

/* ------------------------- COMMENTS --------------------------*/
INSERT INTO Comments
VALUES(1, 1, 'user3', curDate(), 'awesome!', 0);

INSERT INTO Comments
VALUES(1, 2, 'user3', curDate(), 'me again!', 0);

INSERT INTO Comments
VALUES(1, 3, 'user3', curDate(), 'once more!', 0);

INSERT INTO Comments
VALUES(1, 4, 'user14', curDate(), 'stop', 0);

INSERT INTO Comments
VALUES(2, 1, 'user8', curDate(), 'cool', 0);

INSERT INTO Comments
VALUES(3, 1, 'user14', curDate(), 'my comment', 0);

INSERT INTO Comments
VALUES(13, 1, 'user5', curDate(), 'wow', 0);

INSERT INTO Tag
VALUES(1, 'garfield1tag');

INSERT INTO Tag
VALUES(1, 'cattag');

INSERT INTO Tag
VALUES(13, 'battag');

INSERT INTO Favorite
VALUES(1, 'user3', curDate(), 1);

INSERT INTO Favorite
VALUES(2, 'user3', curDate(), 2);

INSERT INTO Favorite
VALUES(7, 'user3', curDate(), 1);

INSERT INTO Favorite
VALUES(1, 'user3', curDate(), 2);

INSERT INTO Favorite
VALUES(6, 'user3', curDate(), 1);

INSERT INTO Favorite
VALUES(8, 'user3', curDate(), 1);

INSERT INTO Favorite
VALUES(12, 'user3', curDate(), 1);

INSERT INTO Favorite
VALUES(15, 'user3', curDate(), 1);

INSERT INTO Favorite
VALUES(17, 'user1', curDate(), 14);

INSERT INTO Favorite
VALUES(18, 'user1', curDate(), 14);

INSERT INTO Favorite
VALUES(2, 'user2', curDate(), 6);

INSERT INTO Favorite
VALUES(25, 'user7', curDate(), 7);

INSERT INTO Favorite
VALUES(30, 'user5', curDate(), 8);

/* ---------------------- Game Comic -------------------------*/

INSERT INTO Panel
VALUES(41, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4TyAbTgJtsWlUB3SMaytFi9XsiniH1r2dX0n8jR_lDZthOKW3-A', 'first', curdate());

INSERT INTO Panel
VALUES(42, 'user2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAeysQYdLE3qDyfid4nXi0iLxvJ6vtwc3U4Ju50zl1nQfaICbN', 'second', curdate());

INSERT INTO Panel
VALUES(43, 'user3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6HyLNHsaUJBdzmsHcK-Z7_urZIWskmVJeBIL4DiaZok9ev95V', 'third', curdate());

INSERT INTO Panel
VALUES(44, 'user4', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0LyCEImuityCx-giYKJl5qBN2mszqrOjIuI7k4hfC0kJdu8lhWA', 'fourth', curdate());

INSERT INTO Panel
VALUES(47, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ4TyAbTgJtsWlUB3SMaytFi9XsiniH1r2dX0n8jR_lDZthOKW3-A', '3', curdate());

INSERT INTO Panel
VALUES(46, 'user2', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRAeysQYdLE3qDyfid4nXi0iLxvJ6vtwc3U4Ju50zl1nQfaICbN', '2', curdate());

INSERT INTO Panel
VALUES(45, 'user3', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcR6HyLNHsaUJBdzmsHcK-Z7_urZIWskmVJeBIL4DiaZok9ev95V', '1', curdate());

INSERT INTO Panel
VALUES(48, 'user4', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ0LyCEImuityCx-giYKJl5qBN2mszqrOjIuI7k4hfC0kJdu8lhWA', '4', curdate());

INSERT INTO Panel
VALUES(49, 'user1', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQt0s9Lz1WIauXYP3B_7teYIp-NoKut1N_r3eRztvwDMuNDJq5C', 'I', curdate());

INSERT INTO Panel
VALUES(50, 'user6', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQtoKqZeZ1pIqX-F1e49mutn6t_E1ibg-bWIlsvnuc43PwTJAC01A', 'II', curdate());

INSERT INTO Panel
VALUES(51, 'user5', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRpl8Aye2ROTAKxf1mMLKdO7pD0QH-iqLmfIS1KIkp3i-kbRjLATQ', 'III', curdate());

INSERT INTO Comic
VALUES(41, 1, 'first second third fourth', 1, curdate());

INSERT INTO Comic
VALUES(42, 1, '1 2 3 4', 3, curdate());

INSERT INTO Comic
VALUES(43, 1, 'I II III', 0, curdate());

INSERT INTO GameComic
VALUES(41, 'apple', 0, 41, 42, 43, 44);

INSERT INTO GameComic
VALUES(42, 'banana', 2, 45, 46, 47, 48);

INSERT INTO GameComic
VALUES(43, 'cranberry', 1, 49, 50, 51, null);

INSERT INTO Keyword
VALUES(1, 'apple');

INSERT INTO Keyword
VALUES(2, 'banana');

INSERT INTO Keyword
VALUES(3, 'cranberry');

INSERT INTO Keyword
VALUES(4, 'testkeyword');

INSERT INTO Keyword
VALUES(5, 'testkeyword2');

INSERT INTO Keyword
VALUES(6, 'testkeyword3');