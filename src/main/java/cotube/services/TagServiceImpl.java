package cotube.services;

import cotube.domain.Tag;
import cotube.repositories.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    public void setProductRepository(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }


    @Override
    public Tag addTag(Tag tag) {
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> getAllTags() {

        List<Tag> tags = (List<Tag>) tagRepository.findAll();

        return tags;
    }

    @Override
    public void deleteTag(Tag tag) {
        tagRepository.delete(tag);
    }

}