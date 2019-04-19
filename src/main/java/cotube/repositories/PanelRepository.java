package cotube.repositories;

import cotube.domain.Panel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PanelRepository extends CrudRepository<Panel, Integer> {
    @Query(value = "SELECT * from Panel p where p.panel_id = :panel_id", nativeQuery = true)
    Panel getPanelFromPanelId(@Param("panel_id") Integer panel_id);
}