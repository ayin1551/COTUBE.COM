package cotube.services;

import cotube.domain.Series;

import java.util.List;

public interface SeriesService {
    Series addSeries(Series series); //add series to db *C
    List<Series> getAllSeries(); //get all series in db *R
    void deleteSeries(Series series);
    Series getSeriesBySeriesId(Integer series_id);
}