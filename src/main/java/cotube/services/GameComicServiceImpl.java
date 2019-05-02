package cotube.services;

import cotube.domain.GameComic;
import cotube.domain.Notification;
import cotube.repositories.GameComicRepository;
import cotube.repositories.NotificationRepository;
import cotube.repositories.PanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameComicServiceImpl implements GameComicService {

    @Autowired
    private GameComicRepository gameComicRepository;

    @Autowired
    private PanelRepository panelRepository;

    @Override
    public GameComic addGameComic(GameComic gameComic) {
        return gameComicRepository.save(gameComic);
    }

    @Override
    public List<GameComic> getAllGameComics() {
        List<GameComic> gameComics = (List<GameComic>) gameComicRepository.findAll();
        return gameComics;
    }

    @Override
    public void deleteGameComic(GameComic gameComic) {
        gameComicRepository.delete(gameComic);
    }

    @Override
    public Integer getGameComicIdByPanelId(Integer panelId) {
        return gameComicRepository.getGameComicIdFromPanelId(panelId);
    }

    @Override
    public String getGameComicTitle(Integer gameComicId) {
        GameComic gc = gameComicRepository.getGameComicByGameComic_id(gameComicId);
        String word1 = panelRepository.getPanelFromPanelId(gc.getPanel1_id()).getTitle_word();
        String word2 = panelRepository.getPanelFromPanelId(gc.getPanel2_id()).getTitle_word();
        String word3 = panelRepository.getPanelFromPanelId(gc.getPanel3_id()).getTitle_word();
        String word4 = panelRepository.getPanelFromPanelId(gc.getPanel4_id()).getTitle_word();
        return "" + word1 + word2 + word3 + word4;
    }

    @Override
    public GameComic getGameComicByGameComicId(Integer gameComicId) {
        return gameComicRepository.getGameComicByGameComic_id(gameComicId);
    }
}