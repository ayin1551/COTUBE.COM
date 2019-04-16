package cotube.services;

import cotube.domain.Likes;

import java.util.List;

public interface LikesService {
    Likes addLike(Likes like); //add likes to db *C
    List<Likes> getAllLikes(); //get all likes in db *R
    void deleteLike(Likes like);
}