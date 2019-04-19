package cotube.services;

import cotube.domain.Panel;
import cotube.repositories.PanelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanelServiceImpl implements PanelService {

    @Autowired
    private PanelRepository panelRepository;

    @Autowired
    public void setProductRepository(PanelRepository panelRepository) {
        this.panelRepository = panelRepository;
    }


    @Override
    public Panel addPanel(Panel panel) {
        return panelRepository.save(panel);
    }

    @Override
    public List<Panel> getAllPanels() {

        List<Panel> panels = (List<Panel>) panelRepository.findAll();

        return panels;
    }

    @Override
    public void deletePanel(Panel panel) {
        panelRepository.delete(panel);
    }

    @Override
    public Panel getPanelFromPanelId(Integer panel_id) {
        return panelRepository.getPanelFromPanelId(panel_id);
    }

}