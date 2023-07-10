package project.forums.domain.file;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileStore {
   @Value("${file.dir}")
   private String fileDir;

   //파일 전체 경로
   public String getFullPath(String filename){
       return fileDir+filename;
   }

   //여러 파일 올때
   public List<UploadFile> storeFiles(List<MultipartFile> multipartFiles)
           throws IOException {
       List<UploadFile> storeFileResult = new ArrayList<>();
       for (MultipartFile multipartFile : multipartFiles) {
           if (!multipartFile.isEmpty()) {
               storeFileResult.add(storeFile(multipartFile));
           }
       }
       return storeFileResult;
   }
   //파일 하나 올때
    public UploadFile storeFile(MultipartFile multipartFile)throws IOException{

       if(multipartFile.isEmpty()){
           return null;
       }

       String originalFilename = multipartFile.getOriginalFilename(); // 업로드시 파일 이름
       String storeFileName = createStoreFileName(originalFilename);  //db에 저장될 이미지 파일 이름
       multipartFile.transferTo(new File(getFullPath(storeFileName)));  // 파일 직접 생성

       return new UploadFile(originalFilename,storeFileName);
    }

    private String createStoreFileName(String originalFilename){
       String ext = extractExt(originalFilename);// 확장자 추출
       String uuid = UUID.randomUUID().toString();
       return uuid+"."+ext;
    }

    private String extractExt(String originalFilename){
       int pos = originalFilename.lastIndexOf(".");
       return originalFilename.substring(pos + 1);
    }



}
