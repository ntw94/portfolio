package project.forums.domain.file;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class FileBoardImage {
    private int id;
    private String boardUri;
    private String uploadFileName;
    private String storeFileName;
    private LocalDateTime regiDate;
}
