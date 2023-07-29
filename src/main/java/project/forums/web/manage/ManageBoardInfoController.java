package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import project.forums.domain.board.Board;
import project.forums.web.manage.form.ManageUpdateBoardInfoForm;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManageBoardInfoController {

    private final ManageService manageService;

    @GetMapping("/boards/{boardUri}")
    public String manageCategoryHome(@PathVariable String boardUri, Model model){

        Board board = manageService.getBoardInfo(boardUri);
        log.info("{}",board);
        model.addAttribute("board",board);


        return "manage/board";
    }

    @PostMapping("/boards/{boardUri}/edit")
    public String manageCategoryEdit(@ModelAttribute ManageUpdateBoardInfoForm form, @PathVariable String boardUri){

        log.info("{}",form);
        manageService.setUpdateBoardInfo(form);

        return "redirect:/manage/boards/{boardUri}";
    }
}
