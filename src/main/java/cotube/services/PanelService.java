package cotube.services;

import cotube.domain.Panel;

import java.util.List;

public interface PanelService {
    Panel addPanel(Panel panel); //add panels to db *C
    List<Panel> getAllPanels(); //get all panels in db *R
    void deletePanel(Panel panel);
    Panel getPanelFromPanelId(Integer panel_id);
}