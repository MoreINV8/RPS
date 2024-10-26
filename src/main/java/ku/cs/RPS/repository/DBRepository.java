package ku.cs.RPS.repository;

import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.entities.Delivery;
import ku.cs.RPS.entities.Employee;
import ku.cs.RPS.entities.Product;
import ku.cs.RPS.mappers.CustomerMapper;
import ku.cs.RPS.mappers.DeliveryMapper;
import ku.cs.RPS.mappers.EmployeeMapper;
import ku.cs.RPS.mappers.ProductMapper;
import ku.cs.RPS.requests.DeliveryCreateRequest;
import ku.cs.RPS.utils.UtilityMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public class DBRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    ================================ Customer ================================

    public List<Customer> findCustomers() {
        String query = "SELECT id, first_name, last_name, email, phone_number, address FROM customer;";

        List<Customer> customers = jdbcTemplate.query(query, new CustomerMapper());

        return customers;
    }

    public Customer findCustomerById(String id) {
        String query = "SELECT id, first_name, last_name, email, phone_number, address FROM customer WHERE id = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new CustomerMapper());
    }

    public boolean isExistCustomerById(String id) {
        String query = "SELECT id, first_name, last_name, email, phone_number, address FROM customer WHERE id = ?;";

        try {
            jdbcTemplate.queryForObject(query, new Object[]{id}, new CustomerMapper());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean isExistCustomerByEmail(String email) {
        String query = "SELECT id, first_name, last_name, email, phone_number, address FROM customer WHERE email = ?;";

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

        String queryInsert = "INSERT INTO customer (id, first_name, last_name, email, phone_number, address) VALUES (?, ?, ?, ?, ?, ?);";
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
        String query = "UPDATE customer SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ? WHERE id = ?;";

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

    //    ================================ Delivery ================================

    public String save(DeliveryCreateRequest request) {

        String id = createId("delivery");

        int allProductCount = 0;
        for (Product p : request.getProducts())
            allProductCount += p.getProductCount();

        String queryInsert = "INSERT INTO delivery (id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(
                queryInsert,
                id,
                request.getCustomerId(),
                null,
                Date.valueOf(request.getDeliveredTime()),
                request.getItemType(),
                request.getDestination(),
                "TODO",
                allProductCount
        );

        return id;
    }

    public Delivery findDeliveryById(String id) {
        String query = "SELECT id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count FROM delivery WHERE id = ?;";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, new DeliveryMapper());
    }

    public void updateDeliveryCreatedDateById(String id) {
        String query = "UPDATE delivery SET created_date = ? WHERE id = ?;";
        jdbcTemplate.update(query, Date.valueOf(LocalDate.now()), id);
    }

    //    ================================ Product ================================

    public String save(Product product) {

        String id = createId("product");

        String queryInsert = "INSERT INTO product (id, notice_id, delivery_id, item_count, item_detail) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(
                queryInsert,
                id,
                null,
                product.getDeliveryId(),
                product.getProductCount(),
                product.getProductDetail()
        );

        return id;
    }

    public List<Product> findProductsByDeliveryId(String id) {
        String query = "SELECT id, notice_id, delivery_id, item_count, item_detail FROM product WHERE delivery_id = ?;";

        return jdbcTemplate.query(query, new Object[]{id}, new ProductMapper());
    }

    //    ================================ Employee ================================

    // For now this is for Employee
    public List<Employee> findEmployees() {
        // id, first_name, last_name, sex, email, phone_number, role, address, password
        String query = "SELECT id, first_name, last_name, sex, email, phone_number, role, address, password FROM employee;";

        List<Employee> employees = jdbcTemplate.query(query, new EmployeeMapper());

        return employees;
    }

    public Employee findEmployeeById(String id) {
        String query = "SELECT id, first_name, last_name, sex, email, phone_number, role, address, password FROM employee WHERE id = ?;";
        Employee employee = jdbcTemplate.queryForObject(query, new Object[]{id}, new EmployeeMapper());
//        System.out.println(employee.getEmployeeId());

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new EmployeeMapper());
    }

    public boolean isExistEmployeeByEmail(String email) {
        String query = "SELECT id, first_name, last_name, sex, email, phone_number, role, address, password FROM employee WHERE email = ?;";

        try {
            jdbcTemplate.queryForObject(query, new Object[]{email}, new EmployeeMapper());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public String save(Employee employee) {
        String id = createId("employee");

        String queryInsert = "INSERT INTO employee (id, first_name, last_name, sex, email, phone_number, role, address, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(queryInsert,
                id,
                employee.getEmployeeFirstName(),
                employee.getEmployeeLastName(),
                employee.getEmployeeSex(),
                employee.getEmployeeEmail(),
                employee.getEmployeePhoneNumber(),
                employee.getEmployeeDepartment(),
                employee.getEmployeeAddress(),
                employee.getEmployeePassword()
        );

        return id;
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
        String query = "UPDATE employee SET first_name = ?, last_name = ?, sex = ?, email = ?, phone_number = ?, role = ?, address = ?, password = ? WHERE id = ?;";

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

    //    ================================ Util ================================
    public String createId(String tableName) {
        String queryCount = "SELECT COUNT(id) FROM " + tableName + ";";

        int id = jdbcTemplate.queryForObject(queryCount, Integer.class) + 1;

        String encodedId = UtilityMethod.rjust(Integer.toString(id), 9, '0');
        encodedId = tableName.charAt(0) + encodedId;

        return encodedId;
    }
}


