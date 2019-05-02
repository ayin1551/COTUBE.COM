package cotube.services;

import cotube.domain.Folder;
import cotube.domain.Keyword;
import cotube.repositories.FolderRepository;
import cotube.repositories.KeywordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeywordServiceImpl implements KeywordService {

    @Autowired
    private KeywordRepository keywordRepository;

    @Autowired
    public void setProductRepository(KeywordRepository keywordRepository) {
        this.keywordRepository = keywordRepository;
    }


    @Override
    public Keyword addKeyword(Keyword keyword) {
        return keywordRepository.save(keyword);
    }

    @Override
    public List<Keyword> getAllKeywords() {

        List<Keyword> keywords = (List<Keyword>) keywordRepository.findAll();
        return keywords;
    }

    @Override
    public void deleteKeyword(Keyword keyword) {
        keywordRepository.delete(keyword);
    }

}