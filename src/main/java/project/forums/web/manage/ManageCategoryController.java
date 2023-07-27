package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import project.forums.web.manage.form.ManageCategorySaveForm;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManageCategoryController {

    @GetMapping("/category/{boardUri}")
    public String manageCategoryHome( @PathVariable String boardUri){


        return "manage/category";
    }

    @PostMapping("/category/{boardUri}/add")
    public String manageCategoryAdd(@ModelAttribute ManageCategorySaveForm form, @PathVariable String boardUri){

        log.info("category: {}",form);

        return "redirect:/manage/category/{boardUri}";
    }

}
