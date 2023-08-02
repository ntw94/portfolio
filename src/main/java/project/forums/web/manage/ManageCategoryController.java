package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.category.PostCategory;
import project.forums.web.manage.form.ManageCategorySaveForm;

import java.awt.geom.QuadCurve2D;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManageCategoryController {

    private final ManageService manageService;

    @GetMapping("/category/{boardUri}")
    public String manageCategoryHome(Model model, @PathVariable String boardUri){

        List<PostCategory> list = manageService.getPostCategoryListAll(boardUri);
        model.addAttribute("list",list);

        log.info("category id 값 체크 {}",list);

        return "manage/category";
    }

    @PostMapping("/category/{boardUri}/add")
    public String manageCategoryAdd(@ModelAttribute ManageCategorySaveForm form, @PathVariable String boardUri){

        log.info("category: {}",form);

        //1. 삭제된 category 목록들을 먼저 db에서 제거한다.
            manageService.deleteCategoryMenus(form);
        //2. 기존에 업데이트될 목록들을 db에 저장한다.
            manageService.categoryAdd(form);
        //3. 새로 추가된 카테고리를 목록에 저장한다.
            manageService.newCategoryAdd(form);

        //1. 기존 DB에 저장된 카테고리 리스트를 불러온다.
        //List<PostCategory> list = manageService.getPostCategoryListAll(boardUri);
        //2. 삭제로 되어있는 목록들을 카테고리 리스트에서 제거한다.
        //manageService.deleteCategoryMenus(form);
        //3. 카테고리 이름을 변경한 것을 업데이트를 한다.
        //manageService.updateCategoryMenus(form);

        //4. 새로 추가된 것을 업데이트한다.
        //manageService.categoryAdd(form);


        return "redirect:/manage/category/{boardUri}";
    }

}
