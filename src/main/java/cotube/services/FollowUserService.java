package cotube.services;

import cotube.domain.FollowUser;

import java.util.List;

public interface FollowUserService {
    FollowUser addFollowUser(FollowUser followUser); //adds a followUser pair to db *C
    List<FollowUser> getAllFollowUsers(); //get all followUser pairs in db *R
    void deleteFollowerUser(FollowUser followUser);
    Integer getFollowerCount(String following_username);
}