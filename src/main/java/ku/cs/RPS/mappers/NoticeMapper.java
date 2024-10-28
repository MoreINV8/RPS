package ku.cs.RPS.mappers;


import ku.cs.RPS.entities.Notice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NoticeMapper implements RowMapper<Notice> {
    @Override
    public Notice mapRow(ResultSet rs, int rowNum) throws SQLException {

        // id, delivery_id, driver_id, car_registration, start_work_date, complete_status
        Notice notice = new Notice();
        notice.setId(rs.getString("id"));
        notice.setDeliveryId(rs.getString("delivery_id"));
        notice.setDriverId(rs.getString("driver_id"));
        notice.setCarRegistration(rs.getString("car_registration"));
        notice.setStartWorkDate(rs.getString("start_work_date"));
        notice.setCompleteStatus(rs.getString("complete_status"));
        notice.setEmployeeFirstName(rs.getString("employee_first_name"));  // Set first name
        notice.setEmployeeLastName(rs.getString("employee_last_name"));    // Set last name
        notice.setDeliveryItemType(rs.getString("delivery_item_type"));
        notice.setDeliveryDestination(rs.getString("delivery_destination"));

        return notice;
    }
}