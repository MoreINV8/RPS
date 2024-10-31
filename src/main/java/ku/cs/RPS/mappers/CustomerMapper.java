package ku.cs.RPS.mappers;

import ku.cs.RPS.entities.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {

//        (id, first_name, last_name, email, phone_number, address)

        Customer c = new Customer();
        c.setId(rs.getString("id"));
        c.setFirstName(rs.getString("first_name"));
        c.setLastName(rs.getString("last_name"));
        c.setEmail(rs.getString("email"));
        c.setPhoneNumber(rs.getString("phone_number"));
        c.setAddress(rs.getString("address"));

        return c;
    }
}
