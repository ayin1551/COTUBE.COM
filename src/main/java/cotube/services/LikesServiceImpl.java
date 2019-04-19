package cotube.services;

import cotube.domain.Likes;
import cotube.repositories.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    public void setProductRepository(LikesRepository likesRepository) {
        this.likesRepository = likesRepository;
    }


    @Override
    public Likes addLike(Likes likes) {
        return likesRepository.save(likes);
    }

    @Override
    public List<Likes> getAllLikes() {

        List<Likes> likes = (List<Likes>) likesRepository.findAll();

        return likes;
    }

    @Override
    public void deleteLike(Likes likes) {
        likesRepository.delete(likes);
    }

    @Override
    public List<Likes> getAllLikesInComic(Integer comic_id) {
        return likesRepository.getAllLikesInComic(comic_id);
    }

}