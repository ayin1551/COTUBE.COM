package cotube.services;

import cotube.domain.Views;
import cotube.repositories.ViewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViewsServiceImpl implements ViewsService {

    @Autowired
    private ViewsRepository viewsRepository;

    @Autowired
    public void setProductRepository(ViewsRepository viewsRepository) {
        this.viewsRepository = viewsRepository;
    }


    @Override
    public Views addView(Views view) {
        return viewsRepository.save(view);
    }

    @Override
    public List<Views> getAllViews() {

        List<Views> views = (List<Views>) viewsRepository.findAll();

        return views;
    }

    @Override
    public void deleteView(Views view) {
        viewsRepository.delete(view);
    }

}