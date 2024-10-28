package ku.cs.RPS.mappers.join;

import ku.cs.RPS.entities.join.DeliveryCustomer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryCustomerMapper implements RowMapper<DeliveryCustomer> {
    @Override
    public DeliveryCustomer mapRow(ResultSet rs, int rowNum) throws SQLException {
        DeliveryCustomer dc = new DeliveryCustomer();

        dc.setDeliveryId(rs.getString("delivery_id"));
        dc.setDestination(rs.getString("destination"));
        dc.setItemType(rs.getString("item_type"));
        dc.setDeliveredTime(rs.getDate("delivered_date"));

        dc.setCustomerId(rs.getString("customer_id"));
        dc.setCustomerFirstName(rs.getString("customer_first_name"));
        dc.setCustomerLastName(rs.getString("customer_last_name"));

        return dc;
    }
}
