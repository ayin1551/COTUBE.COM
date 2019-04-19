package cotube.services;

import cotube.domain.Comic;
import cotube.domain.Likes;
import cotube.repositories.ComicRepository;
import cotube.repositories.LikesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesRepository likesRepository;

    @Autowired
    private ComicRepository comicRepository;

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

    @Override
    public List<Comic> getMostLikedRegularComics() {
        List<Integer> comicIds = likesRepository.getComicIdsOfMostLikedRegularComics();
        List<Comic> result = new ArrayList<>();
        System.out.print(comicIds);
        for (int i = 0; i < comicIds.size(); i++){
            System.out.print(comicIds.get(i));
            Comic comic = comicRepository.getComicByComic_id(comicIds.get(i));
            result.add(comic);
        }
        return result;
    }

}