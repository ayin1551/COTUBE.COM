package cotube.services;

import cotube.domain.GameComic;

import java.util.List;

public interface GameComicService {
    GameComic addGameComic(GameComic gameComic); //add regularComics to db *C
    List<GameComic> getAllGameComics(); //get all regularComics in db *R
    void deleteGameComic(GameComic gameComic);
    Integer getGameComicIdByPanelId(Integer panelId);
    String getGameComicTitle(Integer gameComicId);
    GameComic getGameComicByGameComicId(Integer gameComicId);
}
