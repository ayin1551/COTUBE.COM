package cotube.services;

import cotube.domain.Tag;

import java.util.List;

public interface TagService {
    Tag addTag(Tag tag); //add tags to db *C
    List<Tag> getAllTags(); //get all tags in db *R
    void deleteTag(Tag tag);
    List<Tag> getAllTagsInRegularComic(Integer regular_comic_id);
}