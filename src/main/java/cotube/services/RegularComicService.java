package cotube.services;

import cotube.domain.RegularComic;

import java.util.List;

public interface RegularComicService {
    RegularComic addRegularComic(RegularComic regularComic); //add regularComics to db *C
    List<RegularComic> getAllRegularComics(); //get all regularComics in db *R
    void deleteRegularComic(RegularComic regularComic);
}