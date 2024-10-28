package ku.cs.RPS.mappers;

import ku.cs.RPS.entities.Bill;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class BillMapper implements RowMapper<Bill> {
    @Override
    public Bill mapRow(ResultSet rs, int rowNum) throws SQLException {
        Bill b = new Bill();
        b.setBillId(rs.getString("id"));
        b.setDeliveryId(rs.getString("delivery_id"));
        b.setCreatedDate(rs.getDate("created_date"));

        return b;
    }
}
