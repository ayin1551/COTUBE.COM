package cotube.services;

import cotube.domain.Comments;

import java.util.List;

public interface CommentsService {
    Comments addComments(Comments comment); //adds a comic to db *C
    List<Comments> getAllComments(); //get all followUser pairs in db *R
    void deleteComment(Comments comment);
    List<Comments> getAllCommentsInComic(Integer comic_id);

}