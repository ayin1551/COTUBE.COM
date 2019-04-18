package cotube.services;

import cotube.domain.Folder;

import java.util.List;

public interface FolderService {
    Folder addFolder(Folder folder); //adds a comic to db *C
    List<Folder> getAllFolders(); //get all followUser pairs in db *R
    void deleteFolder(Folder folder);
}