package cotube.services;

import cotube.domain.Series;
import cotube.repositories.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService {

    @Autowired
    private SeriesRepository seriesRepository;

    @Autowired
    public void setProductRepository(SeriesRepository seriesRepository) {
        this.seriesRepository = seriesRepository;
    }


    @Override
    public Series addSeries(Series series) {
        return seriesRepository.save(series);
    }

    @Override
    public List<Series> getAllSeries() {

        List<Series> series = (List<Series>) seriesRepository.findAll();

        return series;
    }

    @Override
    public void deleteSeries(Series series) {
        seriesRepository.delete(series);
    }

    @Override
    public Series getSeriesBySeriesId(Integer series_id) {
        return seriesRepository.getSeriesBySeries_id(series_id);
    }

}