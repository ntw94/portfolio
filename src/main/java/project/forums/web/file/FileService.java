package project.forums.web.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import project.forums.domain.file.FileBoardImage;
import project.forums.domain.file.FileImageMapper;
import project.forums.domain.file.FileStore;
import project.forums.domain.file.UploadFile;
import project.forums.web.board.form.BoardSaveForm;

import java.io.IOException;
import java.net.MalformedURLException;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileService {

    private final FileStore fileStore;
    private final FileImageMapper fileImageMapper;

    public void saveBoardImage(BoardSaveForm boardSaveForm) throws IOException {


        UploadFile uploadFile = fileStore.storeFile(boardSaveForm.getBoardImageFile());

        if(uploadFile == null){
            return;
        }
        FileBoardImage fileBoardImage = new FileBoardImage();
        fileBoardImage.setBoardUri(boardSaveForm.getBoardUri());
        fileBoardImage.setStoreFileName(uploadFile.getStoreFileName());
        fileBoardImage.setUploadFileName(uploadFile.getUploadFileName());

        fileImageMapper.saveBoardImage(fileBoardImage);
    }
    public UrlResource loadBoardImage(String boardUri) throws MalformedURLException {
        FileBoardImage fileBoardImage= fileImageMapper.getBoardImageOne(boardUri);
        if(fileBoardImage == null){
            return new UrlResource("file:"+"C:/file/default_image.png");
        }
        return new UrlResource("file:"+fileStore.getFullPath(fileBoardImage.getStoreFileName()));
    }



}
