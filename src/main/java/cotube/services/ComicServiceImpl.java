package cotube.services;

import cotube.domain.Comic;
import cotube.repositories.ComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ComicServiceImpl implements ComicService {

    @Autowired
    private ComicRepository comicRepository;

    @Autowired
    public void setProductRepository(ComicRepository comicRepository) {
        this.comicRepository = comicRepository;
    }


    @Override
    public Comic addComic(Comic comic) {
        return comicRepository.save(comic);
    }

    @Override
    public List<Comic> getAllComics() {

        List<Comic> comics = (List<Comic>) comicRepository.findAll();
        return comics;
    }

    @Override
    public void deleteComic(Comic comic) {
        comicRepository.delete(comic);
    }

}