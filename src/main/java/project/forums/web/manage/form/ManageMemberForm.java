package project.forums.web.manage.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class ManageMemberForm {

    private String keyword;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date;
    private int page= 1;
    private int perPageSize = 10;

    private String boardUri;

}
