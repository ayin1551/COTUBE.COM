package cotube.services;

import cotube.domain.Comic;
import cotube.domain.Views;
import cotube.repositories.ComicRepository;
import cotube.repositories.ViewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ViewsServiceImpl implements ViewsService {

    @Autowired
    private ViewsRepository viewsRepository;

    @Autowired
    private ComicRepository comicRepository;

    @Override
    public Views addView(Views view) {
        return viewsRepository.save(view);
    }

    @Override
    public List<Views> getAllViews() {

        List<Views> views = (List<Views>) viewsRepository.findAll();

        return views;
    }

    @Override
    public void deleteView(Views view) {
        viewsRepository.delete(view);
    }

    @Override
    public List<Comic> getHighestViewedRegularComics() {
        List<Integer> comicIds = viewsRepository.getComicIdsOfMostViewedComics();
        List<Comic> result = new ArrayList<>();
        for (Integer i: comicIds) {
            Comic comic = comicRepository.getComicByComic_id(i);
            result.add(comic);
        }
        return result;
    }

}