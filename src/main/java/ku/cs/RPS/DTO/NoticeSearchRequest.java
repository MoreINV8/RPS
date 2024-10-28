package ku.cs.RPS.DTO;

import ku.cs.RPS.validations.notice_id.ValidNoticeId;
import lombok.Data;

@Data
public class NoticeSearchRequest {
    @ValidNoticeId
    private String searchValue;
}
