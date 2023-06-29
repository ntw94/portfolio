package project.forums.web.file;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import project.forums.domain.member.Member;
import project.forums.web.member.MemberService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.net.MalformedURLException;
import java.util.Map;
import java.util.Random;

@RequiredArgsConstructor
@Controller
public class FileController {

    private final MemberService memService;

    @ResponseBody
    @RequestMapping("/images/ckeditor5Upload")
    public void fileUpload() throws MalformedURLException {

    }

    @ResponseBody
    @GetMapping("/images/{memberId}/ckeditor5Upload")
    public Resource downloadImage(@PathVariable String memberId) throws MalformedURLException {

        Member member = memService.getMemberOne(memberId);
        String imgFilePath = memService.profileImgFullPath(member.getMemberId());

        return new UrlResource("file:"+imgFilePath);
    }


}
