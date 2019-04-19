package cotube.services;

import cotube.domain.FollowSeries;
import cotube.repositories.FollowSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FollowSeriesServiceImpl implements FollowSeriesService {

    @Autowired
    private FollowSeriesRepository followSeriesRepository;

    @Autowired
    public void setProductRepository(FollowSeriesRepository followSeriesRepository) {
        this.followSeriesRepository = followSeriesRepository;
    }


    @Override
    public FollowSeries addFollowSeries(FollowSeries followSeries) {
        return followSeriesRepository.save(followSeries);
    }

    @Override
    public List<FollowSeries> getAllFollowSeries() {

        List<FollowSeries> followSeries = (List<FollowSeries>) followSeriesRepository.findAll();
        return followSeries;
    }

    @Override
    public void deleteFollowSeries(FollowSeries followSeries) {
        followSeriesRepository.delete(followSeries);
    }

    @Override
    public List<FollowSeries> getAllFollowSeriesInSeries(Integer series_id) {
        return followSeriesRepository.getAllFollowSeriesInSeries(series_id);
    }

}