package cotube.services;

import cotube.domain.Comic;
import cotube.domain.Series;
import cotube.domain.Views;

import java.util.List;

public interface ViewsService {
    Views addView(Views view); //add views to db *C
    List<Views> getAllViews(); //get all views in db *R
    void deleteView(Views view);
    List<Comic> getHighestViewedRegularComics();
    List<Series> getHighestViewedSeries();
    List<Views> getAllViewsInComic(Integer comic_id);
}