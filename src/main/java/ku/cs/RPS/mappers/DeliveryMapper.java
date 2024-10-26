package ku.cs.RPS.mappers;

import ku.cs.RPS.entities.Delivery;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DeliveryMapper implements RowMapper<Delivery> {

    @Override
    public Delivery mapRow(ResultSet rs, int rowNum) throws SQLException {

//        (id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count)

        Delivery d = new Delivery();
        d.setId(rs.getString("id"));
        d.setCustomerId("customer_id");
        d.setCreatedDate(rs.getDate("created_date").toLocalDate().atStartOfDay());
        d.setDeliveredDate(rs.getDate("delivered_date").toLocalDate().atStartOfDay());
        d.setItemType(rs.getString("item_type"));
        d.setDestination(rs.getString("destination"));
        d.setSentDetailStatus(rs.getString("sent_detail_status"));
        d.setAllProductCount(rs.getInt("all_product_count"));

        return d;
    }
}
