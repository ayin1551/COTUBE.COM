package cotube.services;

import cotube.domain.FollowUser;
import cotube.repositories.FollowUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowUserServiceImpl implements FollowUserService {

    @Autowired
    private FollowUserRepository followUserRepository;

    @Autowired
    public void setProductRepository(FollowUserRepository followUserRepository) {
        this.followUserRepository = followUserRepository;
    }


    @Override
    public FollowUser addFollowUser(FollowUser followUser) {
        return followUserRepository.save(followUser);
    }

    @Override
    public List<FollowUser> getAllFollowUsers() {

        List<FollowUser> followUsers = (List<FollowUser>) followUserRepository.findAll();
        return followUsers;
    }

    @Override
    public void deleteFollowerUser(FollowUser followUser){
        followUserRepository.delete(followUser);
    }

    @Override
    public Integer getFollowerCount(String following_username) {
        return followUserRepository.getFollowerCount(following_username);
    }

}