package cotube.repositories;

import cotube.domain.GameComic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameComicRepository extends CrudRepository<GameComic, Integer> {
    @Query(value = "SELECT gc.game_comic_id from GameComic gc where gc.panel1_id = :panel_id OR gc.panel2_id = :panel_id OR gc.panel3_id = :panel_id OR gc.panel4_id = :panel_id", nativeQuery = true)
    Integer getGameComicIdFromPanelId(@Param("panel_id") Integer panel_id);

    @Query(value = "SELECT * from GameComic gc where gc.game_comic_id = :game_comic_id", nativeQuery = true)
    GameComic getGameComicByGameComic_id(@Param("game_comic_id") Integer game_comic_id);

}