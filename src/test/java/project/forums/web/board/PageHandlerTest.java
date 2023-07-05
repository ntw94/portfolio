package project.forums.web.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PageHandlerTest {

    @Test
    void page(){
        PageHandler pageHandler = new PageHandler(21,10,255);

        pageHandler.print();
        System.out.println(pageHandler.getTotalPage());

    }
}