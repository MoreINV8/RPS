package ku.cs.RPS.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@NoArgsConstructor
public class RouteProblem {
    // convince, district, road_name, reporter_id, problem_topic, problem_detail, reported_date
    private String route_problem_id;
    private String province;

    @NotBlank(message = "โปรดใส่ชื่อตำบล หรือ เขต")
    @Size(min = 2, message = "ชื่อตำบล หรือ เขตสั้นเกินไป")
    private String district;

    @NotBlank(message = "โปรดชื่อหมายเลขทางหลวง, ชื่อถนนที่ชาวบ้านรู้จัก หรือชื่อซอย")
    @Size(min = 2, message = "ชื่อที่กรอกสั้นเกินไป")
    private String road_name;

    @NotBlank(message = "โปรดใส่รหัสพนักงานของตนเอง")
    @Size(min = 2, message = "รหัสพนักงานมีทั้งหมด 10 ตำแหน่ง")
    private String reporter_id;

    @NotBlank(message = "โปรดใส่หัวข้อปัญหา")
    @Size(min = 2, message = "หัวข้อสั้นเกินไป")
    private String problem_topic;

    @NotBlank(message = "โปรดใส่รายละเอียดของปัญหา")
    @Size(min = 2, message = "รายละเอียดของปัญหาน้อยเกินไป")
    private String problem_detail;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate reported_date;
    private String reporterFirstName;
    private String reporterLastName;

    public RouteProblem(String route_problem_id, String province, String district, String road_name, String reporter_id, String problem_topic, String problem_detail,
                        LocalDate reported_date, String reporterFirstName, String reporterLastName) {

        System.out.println("At Class RoutProblem" + reported_date);
        this.route_problem_id = route_problem_id;
        this.province = province;
        this.district = district;
        this.road_name = road_name;
        this.reporter_id = reporter_id;
        this.problem_topic = problem_topic;
        this.problem_detail = problem_detail;
        this.reported_date = reported_date;
        this.reporterFirstName = reporterFirstName;
        this.reporterLastName = reporterLastName;
    }
}
