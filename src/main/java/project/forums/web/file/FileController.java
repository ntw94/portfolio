package project.forums.web.file;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import project.forums.domain.file.FileStore;
import project.forums.domain.file.UploadFile;
import project.forums.domain.member.Member;
import project.forums.web.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Slf4j
@RequiredArgsConstructor
@Controller
public class FileController {

    private final MemberService memService;
    private final FileStore fileStore;



    //프리뷰될때 임시 테이블에 저장해야한다.
    @ResponseBody
    @PostMapping("/api/image/upload")
    public String imageUpload(
            HttpServletRequest request,
            @RequestParam MultipartFile upload,
            @RequestParam String uuid
    ) throws IOException {
        String url = null;

        UploadFile uploadFile = fileStore.storeFile(upload);
        url = "/api/images/"+uploadFile.getStoreFileName();

        //임시테이블에 저장

        log.info("{} {} {}",uploadFile.getUploadFileName(),uploadFile.getStoreFileName(),url);
        log.info("{}",uuid);

        return "{ \"uploaded\" : false, \"url\" : \"" + url + "\" }";

        //return "{ \"uploaded\" : false, \"error\": { \"message\": \"업로드 중 에러가 발생했습니다.\" } }";
    }

    //ckeditor에서 api 이미지 요청이 왔을때
    @ResponseBody
    @GetMapping("/api/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {

        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }



}
