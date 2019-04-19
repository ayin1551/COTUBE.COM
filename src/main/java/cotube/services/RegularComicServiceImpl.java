package cotube.services;

import cotube.domain.RegularComic;
import cotube.repositories.RegularComicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegularComicServiceImpl implements RegularComicService {

    @Autowired
    private RegularComicRepository regularComicRepository;

    @Autowired
    public void setProductRepository(RegularComicRepository regularComicRepository) {
        this.regularComicRepository = regularComicRepository;
    }


    @Override
    public RegularComic addRegularComic(RegularComic regularComic) {
        return regularComicRepository.save(regularComic);
    }

    @Override
    public List<RegularComic> getAllRegularComics() {

        List<RegularComic> regularComics = (List<RegularComic>) regularComicRepository.findAll();

        return regularComics;
    }

    @Override
    public void deleteRegularComic(RegularComic regularComic) {
        regularComicRepository.delete(regularComic);
    }

    @Override
    public RegularComic getRegularComicByRegular_Comic_Id(Integer integer) {
        return regularComicRepository.getRegularComicByRegular_Comic_Id(integer);
    }

    @Override
    public List<RegularComic> getAllRegularComicsInSeries(Integer series_id) {
        return regularComicRepository.getAllRegularComicsInSeries(series_id);
    }

}