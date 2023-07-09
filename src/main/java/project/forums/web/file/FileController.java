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

    @ResponseBody
    @RequestMapping("/images/ckeditor5Upload")
    public void fileUpload() throws MalformedURLException {

    }



    @ResponseBody
    @PostMapping("/api/image/upload")
    public String imageUpload(
            HttpServletRequest request,
            @RequestParam MultipartFile upload
    ) throws IOException {
        String url = null;

        UploadFile uploadFile = fileStore.storeFile(upload);
        url = "/api/images/"+uploadFile.getStoreFileName();

        log.info("{} {} {}",uploadFile.getUploadFileName(),uploadFile.getStoreFileName(),url);


        return "{ \"uploaded\" : false, \"url\" : \"" + url + "\" }";

//        return "{ \"uploaded\" : false, \"error\": { \"message\": \"업로드 중 에러가 발생했습니다.\" } }";
    }

    @ResponseBody
    @GetMapping("/api/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {

        return new UrlResource("file:" + fileStore.getFullPath(filename));
    }



}
