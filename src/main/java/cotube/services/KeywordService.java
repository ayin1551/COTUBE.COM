package cotube.services;

import cotube.domain.Keyword;

import java.util.List;

public interface KeywordService {
    Keyword addKeyword(Keyword keyword); //adds a comic to db *C
    List<Keyword> getAllKeywords(); //get all followUser pairs in db *R
    void deleteKeyword(Keyword keyword);
}
