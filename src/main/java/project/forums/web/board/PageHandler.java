package project.forums.web.board;

import lombok.Data;

@Data
public class PageHandler {
    private int page; // 현재 페이지 정보
    private int perPageSize; // 한페이지에 보이는 수
    private int totalPage; // 총 전체 페이지 수
    private int totalRecord; // 총 전체 글 수
    private int naviSize = 10;
    private int beginPage;
    private int endPage;
    private boolean showPrev;
    private boolean showNext;

    public PageHandler(int page, int perPageSize, int totalRecord) {
        this.page = page;
        this.perPageSize = perPageSize;
        this.totalRecord = totalRecord;

        totalPage = (int)(Math.ceil(totalRecord/(double)perPageSize)); // 토탈page수
        beginPage = (page/naviSize) * 10 + 1;
        endPage = beginPage + naviSize -1;
        if(endPage > totalPage)
            endPage = totalPage;

        showPrev = (beginPage == 1) ? false : true;
        showNext = (endPage >= totalPage) ? false : true;
    }

    public void print(){
        System.out.println(showPrev?"<":"");
        for(int i= beginPage; i <=endPage; i++)
            System.out.print(i+ " ");
        System.out.println(showNext?">":"");
    }
}
