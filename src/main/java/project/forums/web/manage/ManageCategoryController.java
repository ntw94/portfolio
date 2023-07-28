package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.category.PostCategory;
import project.forums.web.manage.form.ManageCategorySaveForm;

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


        manageService.categoryAdd(form);


        return "redirect:/manage/category/{boardUri}";
    }

}
