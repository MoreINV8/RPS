package ku.cs.RPS.mappers;

import ku.cs.RPS.entities.RouteProblem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;


public class RouteProblemMapper implements RowMapper<RouteProblem> {

    @Override
    public RouteProblem mapRow(ResultSet rs, int rowNum) throws SQLException {

        //convince, district, road_name, reporter_id, problem_topic, problem_detail, reported_date
        RouteProblem routeProblem = new RouteProblem();
        routeProblem.setRoute_problem_id(rs.getString("route_problem_id"));
        routeProblem.setProvince(rs.getString("province"));
        routeProblem.setDistrict(rs.getString("district"));
        routeProblem.setRoad_name(rs.getString("road_name"));
        routeProblem.setReporter_id(rs.getString("reporter_id"));
        routeProblem.setReporterFirstName(rs.getString("reporter_first_name"));
        routeProblem.setReporterLastName(rs.getString("reporter_last_name"));
        routeProblem.setProblem_topic(rs.getString("problem_topic"));
        routeProblem.setProblem_detail(rs.getString("problem_detail"));
        System.out.println(rs.getDate("reported_date"));
        routeProblem.setReported_date(rs.getDate("reported_date").toLocalDate());

        return routeProblem;
    }
}
