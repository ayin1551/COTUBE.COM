package cotube.services;

import cotube.domain.Comic;
import cotube.domain.Series;
import cotube.domain.Views;
import cotube.repositories.ComicRepository;
import cotube.repositories.SeriesRepository;
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

    @Autowired
    private SeriesRepository seriesRepository;

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
        List<Integer> comicIds = viewsRepository.getComicIdsOfMostViewedRegularComics();
        List<Comic> result = new ArrayList<>();
        System.out.print(comicIds);
        for (int i = 0; i < comicIds.size(); i++){
            System.out.print(comicIds.get(i));
            Comic comic = comicRepository.getComicByComic_id(comicIds.get(i));
            result.add(comic);
        }
        return result;
    }

    @Override
    public List<Series> getHighestViewedSeries() {
        List<Integer> seriesIds = viewsRepository.getSeriesIdsOfMostViewedSeries();
        List<Series> result = new ArrayList<>();
        System.out.print(seriesIds);
        for (int i = 0; i < seriesIds.size(); i++){
            System.out.print(seriesIds.get(i));
            Series series = seriesRepository.getSeriesBySeries_id(seriesIds.get(i));
            result.add(series);
        }
        return result;
    }

    @Override
    public List<Views> getAllViewsInComic(Integer comic_id) {
        return viewsRepository.getAllViewsInComic(comic_id);
    }

}