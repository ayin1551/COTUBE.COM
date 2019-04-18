package cotube.services;

import cotube.domain.Folder;
import cotube.repositories.FolderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderServiceImpl implements FolderService {

    @Autowired
    private FolderRepository folderRepository;

    @Autowired
    public void setProductRepository(FolderRepository folderRepository) {
        this.folderRepository = folderRepository;
    }


    @Override
    public Folder addFolder(Folder folder) {
        return folderRepository.save(folder);
    }

    @Override
    public List<Folder> getAllFolders() {

        List<Folder> folders = (List<Folder>) folderRepository.findAll();
        return folders;
    }

    @Override
    public void deleteFolder(Folder folder) {
        folderRepository.delete(folder);
    }

}