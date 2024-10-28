package ku.cs.RPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;
import java.time.LocalDate;


@Data
@NoArgsConstructor
public class RouteProblem {
    // convince, district, road_name, reporter_id, problem_topic, problem_detail, reported_date
    private String route_problem_id;
    private String province;
    private String district;
    private String road_name;
    private String reporter_id;
    private String problem_topic;
    private String problem_detail;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate reported_date;

    public RouteProblem(String route_problem_id, String province, String district, String road_name, String reporter_id, String problem_topic, String problem_detail, LocalDate reported_date) {
        System.out.println("At Class RoutProblem" + reported_date);
        this.route_problem_id = route_problem_id;
        this.province = province;
        this.district = district;
        this.road_name = road_name;
        this.reporter_id = reporter_id;
        this.problem_topic = problem_topic;
        this.problem_detail = problem_detail;
        this.reported_date = reported_date;
    }
}
