package ku.cs.RPS.mappers;

import ku.cs.RPS.entities.Delivery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryMapper implements RowMapper<Delivery> {

    @Override
    public Delivery mapRow(ResultSet rs, int rowNum) throws SQLException {

        // id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status

        Delivery delivery = new Delivery();
        delivery.setDeliveryId(rs.getString("id"));
        delivery.setCustomerId(rs.getString("customer_id"));
        delivery.setDeliveryCreatedDate(rs.getDate("created_date"));
        delivery.setDeliverDateTime(rs.getDate("delivered_date"));
        delivery.setItem_type(rs.getString("item_type"));
        delivery.setDestination(rs.getString("destination"));
        delivery.setSentDetailStatus(rs.getString("sent_detail_status"));

        return delivery;
    }
}
