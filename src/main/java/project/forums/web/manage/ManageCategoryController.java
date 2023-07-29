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

        //카테고리 뷰단에서 값을 다르게 받아와서 여기서 처리해주면 될듯
        //input value값으로 delete인 값은 따로 빼고
        //update된 값은 따로 또 여기서 수정해줘서 마지막에 add하면 될듯
        //결국 앞에서 조금 값을 다르게 주면 됨


        manageService.categoryAdd(form);


        return "redirect:/manage/category/{boardUri}";
    }

}
