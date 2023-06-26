package project.forums.domain.file;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ImageProfile {
    private int profileId;
    private String memberId;
    private String uploadFileName;
    private String storeFileName;
    private LocalDateTime regiDate;
}
