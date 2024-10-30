package ku.cs.RPS.mappers.join;

import ku.cs.RPS.entities.join.NoticeEmployeeCar;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoticeEmployeeCarMapper implements RowMapper<NoticeEmployeeCar> {
    @Override
    public NoticeEmployeeCar mapRow(ResultSet rs, int rowNum) throws SQLException {
        NoticeEmployeeCar d = new NoticeEmployeeCar();
        d.setEmployeeId(rs.getString("employee_id"));
        d.setEmployeeFirstName(rs.getString("employee_first_name"));
        d.setEmployeeLastName(rs.getString("employee_last_name"));
        d.setCarId(rs.getString("car_id"));
        d.setOilType(rs.getString("car_type"));
        d.setNoticeId(rs.getString("notice_id"));

        return d;
    }
}
