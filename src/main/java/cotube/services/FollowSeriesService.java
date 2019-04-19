package cotube.services;

import cotube.domain.FollowSeries;

import java.util.List;

public interface FollowSeriesService {
    FollowSeries addFollowSeries(FollowSeries followSeries); //adds a comic to db *C
    List<FollowSeries> getAllFollowSeries(); //get all followUser pairs in db *R
    void deleteFollowSeries(FollowSeries followSeries);
    List<FollowSeries> getAllFollowSeriesInSeries(Integer series_id);
}