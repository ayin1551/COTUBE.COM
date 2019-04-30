package cotube.services;

import cotube.domain.Comic;

import java.util.List;

public interface ComicService {
    Comic addComic(Comic comic); //adds a comic to db *C
    List<Comic> getAllComics(); //get all followUser pairs in db *R
    void deleteComic(Comic comic);
    List<Comic> searchComicsByTitle(String title);
    List<Comic> searchComicsByTitlePublic(String title);
    Comic getComicByComic_Id(Integer integer);

}