package project.forums.web.manage.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Data
public class ManageStopMemberForm {
    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int page= 1;
    private int perPageSize = 10;

    private String boardUri;
    private List<String> chkMember;
}
