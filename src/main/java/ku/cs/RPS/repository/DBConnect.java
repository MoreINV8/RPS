package ku.cs.RPS.repository;

import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.mappers.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBConnect {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> findCustomers() {
        String query = "SELECT id, first_name, last_name, email, phone_number, address FROM customer;";

        List<Customer> customers = jdbcTemplate.query(query, new CustomerMapper());

        return customers;
    }

    public Customer findCustomerById(String id) {
        String query = "SELECT id, first_name, last_name, email, phone_number, address FROM customer WHERE id = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new CustomerMapper());
    }

    public void update(Customer customer) {
        String query = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ? WHERE id = ?";

        jdbcTemplate.update(
                query,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress(),
                customer.getId());
    }
}
