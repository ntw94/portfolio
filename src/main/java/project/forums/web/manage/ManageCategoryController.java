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

        //1. 기존 DB에 저장된 카테고리 리스트를 불러온다.
        List<PostCategory> list = manageService.getPostCategoryListAll(boardUri);
        //2. 삭제로 되어있는 목록들을 카테고리 리스트에서 제거한다.

        //3. 수정이 예정된 목록들을 리스트에서 바꾼다. (id값을 활용) 기존에 있는건 update하고 새로 추가되는 얘들은 그냥 그대로 쓰면되기 떄문에

        //카테고리 뷰단에서 값을 다르게 받아와서 여기서 처리해주면 될듯
        //input value값으로 delete인 값은 따로 빼고
        //update된 값은 따로 또 여기서 수정해줘서 마지막에 add하면 될듯
        //결국 앞에서 조금 값을 다르게 주면 됨


        manageService.categoryAdd(form);


        return "redirect:/manage/category/{boardUri}";
    }

}
