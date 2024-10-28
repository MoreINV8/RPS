package ku.cs.RPS.mappers.join;

import ku.cs.RPS.entities.join.DeliveryCustomerNotice;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryCustomerNoticeMapper implements RowMapper<DeliveryCustomerNotice> {

    @Override
    public DeliveryCustomerNotice mapRow(ResultSet rs, int rowNum) throws SQLException {

        DeliveryCustomerNotice data = new DeliveryCustomerNotice();
        data.setNoticeId(rs.getString("notice_id"));
        data.setCompleteStatus(rs.getString("complete_status"));

        data.setDeliveryId(rs.getString("delivery_id"));
        data.setDeliveryCreatedDate(rs.getDate("created_date"));
        data.setDestination(rs.getString("destination"));
        data.setDeliverDateTime(rs.getDate("delivered_date"));
        data.setProductCount(rs.getInt("all_product_count"));

        data.setCustomerId(rs.getString("customer_id"));
        data.setCustomerFirstName(rs.getString("customer_first_name"));
        data.setCustomerLastName(rs.getString("customer_last_name"));

        return data;
    }
}
