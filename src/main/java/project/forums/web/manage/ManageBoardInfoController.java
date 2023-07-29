package project.forums.web.manage;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import project.forums.domain.board.Board;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/manage")
@Controller
public class ManageBoardInfoController {

    private final ManageService manageService;

    @GetMapping("/board/{boardUri}")
    public String manageCategoryHome(@PathVariable String boardUri, Model model){

        Board board = manageService.getBoardInfo(boardUri);
        log.info("{}",board);
        model.addAttribute("board",board);


        return "manage/board";
    }
}
