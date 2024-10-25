package ku.cs.RPS.repository;

import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.entities.Employee;
import ku.cs.RPS.mappers.CustomerMapper;
import ku.cs.RPS.mappers.EmployeeMapper;
import ku.cs.RPS.utils.UtilityMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DBRepository {

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

    public boolean isExistCustomerByEmail(String email) {
        String query = "SELECT id, first_name, last_name, email, phone_number, address FROM customer WHERE email = ?";

        try {
            jdbcTemplate.queryForObject(query, new Object[]{email}, new CustomerMapper());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public String save(Customer customer) {
        String queryCount = "SELECT COUNT(id) FROM customer;";

        int id = jdbcTemplate.queryForObject(queryCount, Integer.class) + 1;

        String encodedId = UtilityMethod.rjust(Integer.toString(id), 9, '0');
        encodedId = "c" + encodedId;

        String queryInsert = "INSERT INTO customer (id, first_name, last_name, email, phone_number, address) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(queryInsert,
                encodedId,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress()
        );

        return encodedId;
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
                customer.getId()
        );
    }

    // For now this is for Employee
    public List<Employee> findEmployees() {
    // id, first_name, last_name, sex, email, phone_number, role, address, password
        String query = "SELECT id, first_name, last_name, sex, email, phone_number, role, address, password FROM employee;";

        List<Employee> employees = jdbcTemplate.query(query, new EmployeeMapper());

        return employees;
    }

    public Employee findEmployeeById(String id) {
        String query = "SELECT id, first_name, last_name, sex, email, phone_number, role, address, password FROM employee WHERE id = ?";
        Employee employee = jdbcTemplate.queryForObject(query, new Object[]{id}, new EmployeeMapper());
//        System.out.println(employee.getEmployeeId());

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new EmployeeMapper());
    }

    public boolean isExistEmployeeByEmail(String email) {
        String query = "SELECT id, first_name, last_name, sex, email, phone_number, role, address, password FROM employee WHERE email = ?";

        try {
            jdbcTemplate.queryForObject(query, new Object[]{email}, new EmployeeMapper());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public String save(Employee employee) {
        String queryCount = "SELECT COUNT(id) FROM employee;";

        int id = jdbcTemplate.queryForObject(queryCount, Integer.class) + 1;

        String encodedId = UtilityMethod.rjust(Integer.toString(id), 9, '0');
        encodedId = "c" + encodedId;

        String queryInsert = "INSERT INTO employee (id, first_name, last_name, sex, email, phone_number, role, address, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(queryInsert,
                encodedId,
                employee.getEmployeeFirstName(),
                employee.getEmployeeLastName(),
                employee.getEmployeeSex(),
                employee.getEmployeeEmail(),
                employee.getEmployeePhoneNumber(),
                employee.getEmployeeDepartment(),
                employee.getEmployeeAddress(),
                employee.getEmployeePassword()
        );

        return encodedId;
    }

    public void update(Employee employee) {
//        System.out.println("Hi, update?");
//        System.out.println(employee.getEmployeeId());
//        System.out.println(employee.getEmployeeFirstName());
//        System.out.println(employee.getEmployeeLastName());
//        System.out.println(employee.getEmployeeSex());
//        System.out.println(employee.getEmployeeEmail());
//        System.out.println(employee.getEmployeePhoneNumber());
//        System.out.println(employee.getEmployeeDepartment());
//        System.out.println(employee.getEmployeeAddress());
//        System.out.println(employee.getEmployeePassword());
        // first_name, last_name, sex, email, phone_number, role, address, password
        String query = "UPDATE employee SET first_name = ?, last_name = ?, sex = ?, email = ?, phone_number = ?, role = ?, address = ?, password = ? WHERE id = ?";

        jdbcTemplate.update(
                query,
                employee.getEmployeeFirstName(),
                employee.getEmployeeLastName(),
                employee.getEmployeeSex(),
                employee.getEmployeeEmail(),
                employee.getEmployeePhoneNumber(),
                employee.getEmployeeDepartment(),
                employee.getEmployeeAddress(),
                employee.getEmployeePassword(),
                employee.getEmployeeId()
        );
    }
}


