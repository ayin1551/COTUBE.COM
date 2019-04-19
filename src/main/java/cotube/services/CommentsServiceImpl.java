package cotube.services;

import cotube.domain.Comments;
import cotube.repositories.CommentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {

    @Autowired
    private CommentsRepository commentRepository;

    @Autowired
    public void setProductRepository(CommentsRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Override
    public Comments addComments(Comments comment) {
        return commentRepository.save(comment);
    }

    @Override
    public List<Comments> getAllComments() {

        List<Comments> comments = (List<Comments>) commentRepository.findAll();
        return comments;
    }

    @Override
    public void deleteComment(Comments comment) {
        commentRepository.delete(comment);
    }

    @Override
    public List<Comments> getAllCommentsInComic(Integer comic_id) {
        return commentRepository.getAllCommentsInComic(comic_id);
    }

}