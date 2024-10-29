package ku.cs.RPS.DTO;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
public class NoticeRequest {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate start_work_date;
}
