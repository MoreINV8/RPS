package ku.cs.RPS.repository;

import ku.cs.RPS.DTO.DeliveryCreateRequest;
import ku.cs.RPS.DTO.DeliveryEditRequest;
import ku.cs.RPS.entities.*;
import ku.cs.RPS.entities.join.DeliveryCustomer;
import ku.cs.RPS.entities.join.DeliveryCustomerNotice;
import ku.cs.RPS.entities.join.NoticeEmployeeCar;
import ku.cs.RPS.mappers.*;
import ku.cs.RPS.mappers.join.DeliveryCustomerMapper;
import ku.cs.RPS.mappers.join.DeliveryCustomerNoticeMapper;
import ku.cs.RPS.mappers.join.NoticeEmployeeCarMapper;
import ku.cs.RPS.utils.UtilityMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DBRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    //    ================================ Customer ================================

    public List<Customer> findCustomers() {
        String query = "SELECT id, first_name, last_name, email, phone_number, address FROM customer;";

        return jdbcTemplate.query(query, new CustomerMapper());
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

    public String save(Customer customer) {
        String queryCount = "SELECT COUNT(id) FROM customer;";

        String id = createId("customer");

        String queryInsert = "INSERT INTO customer (id, first_name, last_name, email, phone_number, address) VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(queryInsert,
                id,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getPhoneNumber(),
                customer.getAddress()
        );

        return id;
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

        String queryInsert = "INSERT INTO delivery (id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ";

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

    public List<Delivery> findUncreatedDeliveries() {
        String query = "SELECT id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count " +
                "FROM delivery WHERE created_date IS NULL " +
                "ORDER BY delivered_date;";

        return jdbcTemplate.query(query, new DeliveryMapper());
    }

    public List<Delivery> findUnsentDeliveries() {
        String query = "SELECT id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count" +
                " FROM delivery WHERE sent_detail_status = 'TODO' AND all_product_count = 0 " +
                "ORDER BY delivered_date ,created_date;";

        return jdbcTemplate.query(query, new DeliveryMapper());
    }

    public void update(DeliveryEditRequest request) {

        int allNewProductCount = 0;
        for (Product p : request.getProducts())
            allNewProductCount += p.getProductCount();

        String query = "UPDATE delivery SET delivered_date = ?, item_type = ?, destination = ?, all_product_count = ? WHERE id = ?;";

        jdbcTemplate.update(
                query,
                request.getDeliveredTime(),
                request.getItemType(),
                request.getDestination(),
                allNewProductCount + request.getAllProductCountInitial(),
                request.getDeliveryId()
        );
    }

    public void updateDeliveryCreatedDateById(String id) {
        String query = "UPDATE delivery SET created_date = ? WHERE id = ?;";
        jdbcTemplate.update(query, Date.valueOf(LocalDate.now()), id);
    }

    public void updateDeliverySentDetailById(String id) {
        String query = "UPDATE delivery SET sent_detail_status = 'FIN' WHERE id = ?;";
        jdbcTemplate.update(query, id);
    }

    public void updateDeliveryFKToNullById(String id) {
        String query = "UPDATE delivery SET customer_id = null WHERE id = ?;";

        jdbcTemplate.update(
                query,
                id
        );
    }

    public void updateAllProductCount(String deliveryId, int remainingProductCount) {
        String query = "UPDATE delivery SET all_product_count = ? WHERE id = ?";
        jdbcTemplate.update(query, remainingProductCount, deliveryId);
    }

    public void deleteDeliveryById(String id) {
        String query = "DELETE FROM delivery WHERE id = ?;";

        jdbcTemplate.update(
                query,
                id
        );
    }

    public List<Delivery> getDeliveriesDistinctByNotAssignAmount() {
        String query = "SELECT id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count " +
                " FROM delivery WHERE all_product_count > 0 AND created_date IS NOT NULL ORDER BY delivered_date, created_date, all_product_count;";

        List<Delivery> deliveries = jdbcTemplate.query(query, new DeliveryMapper());

        return deliveries;
    }

    public List<Delivery> getDeliveriesDistinctByAlreadyAssignAmount() {
        String query = "SELECT id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count " +
                " FROM delivery WHERE all_product_count = 0 AND created_date IS NOT NULL ORDER BY delivered_date, created_date, all_product_count;";

        List<Delivery> deliveries = jdbcTemplate.query(query, new DeliveryMapper());

        return deliveries;
    }

    public Delivery findDeliveryByDeliveryId(String deliveryId) {
        String query = "SELECT id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count FROM delivery WHERE id = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{deliveryId}, new DeliveryMapper());
    }

    public Delivery findDeliveryByCustomerId(String customerId) {
        String query = "SELECT id, customer_id, created_date, delivered_date, item_type, destination, sent_detail_status, all_product_count FROM delivery WHERE customer_id = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{customerId}, new DeliveryMapper());
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

    public Product findProductByProductId(String id) {
        String query = "SELECT id, notice_id, delivery_id, item_count, item_detail FROM product WHERE id = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new ProductMapper());
    }

    public void updateRemainingProductCount(String productId, int remainingProductCount) {
        String query = "UPDATE product SET item_count = ? WHERE id = ?";
        jdbcTemplate.update(query, remainingProductCount, productId);
    }

    public List<Product> findProductsByNoticeId(String id) {
        String query = "SELECT id, notice_id, delivery_id, item_count, item_detail FROM product WHERE notice_id = ?;";

        return jdbcTemplate.query(query, new Object[]{id}, new ProductMapper());
    }

    public void updateProductFKToNullById(String id) {
        String query = "UPDATE product SET delivery_id = null, notice_id = null WHERE id = ?;";

        jdbcTemplate.update(
                query,
                id
        );
    }

    public void deleteProductById(String id) {
        String query = "DELETE FROM product WHERE id = ?;";

        jdbcTemplate.update(
                query,
                id
        );
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

    public Employee findEmployeeByEmail(String email) {
        String query = "SELECT id, first_name, last_name, sex, email, phone_number, role, address, password FROM employee WHERE email = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{email}, new EmployeeMapper());
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

    public void updateEmployeePassword(String id, String newPassword) {
        String query = "UPDATE employee SET password = ? WHERE id = ?;";

        jdbcTemplate.update(
                query,
                newPassword,
                id
        );
    }

    //    ================================ Notice ================================
    public boolean isExistNoticeById(String id) {
        String query = "SELECT COUNT(id) FROM notice WHERE id = ?;";

        int result = jdbcTemplate.queryForObject(query, new Object[]{id}, Integer.class);

        return result != 0;
    }

    public DeliveryCustomerNotice findDeliveryCustomerNoticeByNoticeId(String id) {
        String query = "SELECT n.id AS notice_id, n.delivery_id, n.complete_status, " +
                "d.created_date, d.destination, d.delivered_date, d.all_product_count, " +
                "c.id AS customer_id, c.first_name AS customer_first_name, c.last_name AS customer_last_name " +
                "FROM notice n " +
                "JOIN delivery d ON n.delivery_id = d.id " +
                "JOIN customer c ON d.customer_id = c.id " +
                "WHERE n.id = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new DeliveryCustomerNoticeMapper());
    }

    public DeliveryCustomer findDeliveryCustomerByDeliveryId(String id) {
        String query = "SELECT d.id AS delivery_id, d.destination, d.item_type, d.delivered_date, " +
                "c.id AS customer_id, c.first_name AS customer_first_name, c.last_name AS customer_last_name " +
                "FROM delivery d " +
                "JOIN customer c ON d.customer_id = c.id " +
                "WHERE d.id = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new DeliveryCustomerMapper());
    }

    public List<NoticeEmployeeCar> findNoticeEmployeeCarByDeliveryId(String id) {
        String query = "SELECT e.first_name AS employee_first_name, e.last_name AS employee_last_name, e.id AS employee_id, " +
                "c.car_registration AS car_id, c.car_type, n.id AS notice_id " +
                "FROM notice n " +
                "JOIN car c ON n.car_registration = c.car_registration " +
                "JOIN employee e ON n.driver_id = e.id " +
                "WHERE n.delivery_id = ? AND n.complete_status = 'COMPLETE';";

        return jdbcTemplate.query(query, new Object[]{id}, new NoticeEmployeeCarMapper());
    }

    // Find List Jobs(Notices) for each Driver
    public List<Notice> findJobListByEmployeeId(String id) {
        String query = "SELECT n.id AS notice_id, n.delivery_id, n.driver_id, n.car_registration, " +
                "n.start_work_date, n.complete_status, " +
                "e.first_name AS employee_first_name, e.last_name AS employee_last_name, " +
                "d.item_type AS delivery_item_type, d.destination AS delivery_destination " +
                "FROM notice n " +
                "JOIN employee e ON n.driver_id = e.id " +
                "JOIN delivery d ON n.delivery_id = d.id " +
                "WHERE n.driver_id = ? AND n.complete_status = 'INCOMPLETE'";

        return jdbcTemplate.query(query, new Object[]{id}, new NoticeMapper());
    }

    // Show Data Information of each Notice. When you click in
    public Notice findNoticeById(String id) {
        String query = "SELECT n.id AS notice_id, n.delivery_id, n.driver_id, n.car_registration, " +
                "n.start_work_date, n.complete_status, " +
                "e.first_name AS employee_first_name, e.last_name AS employee_last_name, " +
                "d.item_type AS delivery_item_type, d.destination AS delivery_destination " +
                "FROM notice n " +
                "JOIN employee e ON n.driver_id = e.id " +
                "JOIN delivery d ON n.delivery_id = d.id " +
                "WHERE n.id = ?";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new NoticeMapper());
    }

    public void update(Notice callNotice) {
        // id, delivery_id, driver_id, car_registration, start_work_date, complete_status
        String query = "UPDATE notice SET id = ?, delivery_id = ?, driver_id = ?, car_registration = ?, start_work_date = ?, complete_status = ? WHERE id = ?;";

        jdbcTemplate.update(
                query,
                callNotice.getId(),
                callNotice.getDelivery_id(),
                callNotice.getDriverId(),
                callNotice.getCar_registration(),
                callNotice.getStart_work_date(),
                callNotice.getComplete_status(),
                callNotice.getId()
        );
    }


    public String save(Notice notice) {
        String id = createId("notice");

        String queryInsert = "INSERT INTO notice (id, delivery_id, driver_id, car_registration, start_work_date, complete_status) VALUES (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(queryInsert,
                id,
                notice.getDelivery_id(),
                notice.getDriver_id(),
                notice.getCar_registration(),
                notice.getStart_work_date(),
                "INCOMPLETE"
        );

        return notice.getId();
    }

    public void updateNoticeCompleteStatusById(String id) {
        String query = "UPDATE notice SET complete_status = 'COMPLETE' WHERE id = ?;";

        jdbcTemplate.update(query, id);
    }

    //    ================================ Car ================================
    public List<Car> findCars() {
        String query = "SELECT car_registration, driver_id, oil_type, finish_used, car_type FROM car;";

        List<Car> cars = jdbcTemplate.query(query, new CarMapper());

        return cars;
    }

    public boolean isExistCarByLicensePlate(String licensePlate) {
        String query = "SELECT car_registration, driver_id, oil_type, finish_used, car_type FROM car WHERE car_registration = ?;";

        try {
            jdbcTemplate.queryForObject(query, new Object[]{licensePlate}, new CarMapper());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public boolean isExistCarByDriverId(String driverId) {
        String query = "SELECT car_registration, driver_id, oil_type, finish_used, car_type FROM car WHERE driver_id = ?;";

        try {
            jdbcTemplate.queryForObject(query, new Object[]{driverId}, new CarMapper());
            return true;
        } catch (DataAccessException e) {
            return false;
        }
    }

    public Car findCarByCarId(String carId) {
        String query = "SELECT car_registration, driver_id, oil_type, finish_used, car_type FROM car WHERE car_registration = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{carId}, new CarMapper());
    }

    public List<Car> findAvailableCars() {
        String query = "SELECT car_registration, driver_id, oil_type, finish_used, car_type FROM car " +
                "WHERE finish_used IS NULL";

        List<Car> cars = jdbcTemplate.query(query, new CarMapper());

        return cars;
    }

    public List<Car> filterCarByEstimatedStartTime(String estimatedStartTime) {
        String query = "SELECT car_registration, driver_id, oil_type, finish_used, car_type FROM car WHERE finish_used < ?;";

        List<Car> filteredCars = jdbcTemplate.query(query, new Object[]{estimatedStartTime}, new CarMapper());

        return filteredCars;
    }

    public List<Car> findAssignedCarsByDeliveryId(String deliveryId) {
        String query = "SELECT car_registration, driver_id, oil_type, finish_used, car_type FROM car WHERE car_registration IN " +
                " (SELECT car_registration FROM notice WHERE delivery_id = ?);";

        try {
            List<Car> cars = jdbcTemplate.query(query, new Object[]{deliveryId}, new CarMapper());
            return cars;
        } catch (DataAccessException e) {
            return null;
        }
    }

    public void updateFinishUsed(String carId, LocalDateTime finishUsed) {
        String query = "UPDATE car SET finish_used = ? WHERE car_registration = ?;";

        jdbcTemplate.update(query, finishUsed, carId);
    }

    public String save(Car car) {
        String queryInsert = "INSERT INTO car (car_registration, driver_id, oil_type, finish_used, car_type) VALUES (?, ?, ?, ?, ?);";
        jdbcTemplate.update(queryInsert,
                car.getCarId(),
                car.getDriverId(),
                car.getOilType(),
                car.getEndOfUseTime(),
                car.getCarType()
        );

        return car.getCarId();
    }

    //    ================================ Route Problem ================================
    // Method to retrieve unique list of provinces (convince) from route_problem
    public List<String> findAllProvinces() {
        String query = "SELECT DISTINCT province FROM route_problem;";
        return jdbcTemplate.queryForList(query, String.class);
    }

    // Method to retrieve route problems by province
    public List<RouteProblem> showAllProblemRoute(String province) {
        String query = """
                SELECT rp.route_problem_id, rp.province, rp.district, rp.road_name, 
                       rp.reporter_id, rp.problem_topic, rp.problem_detail, rp.reported_date,
                       e.first_name AS reporter_first_name, e.last_name AS reporter_last_name
                FROM route_problem rp
                JOIN employee e ON rp.reporter_id = e.id
                WHERE rp.province = ?;
                """;

        // Pass the province parameter to the query
        return jdbcTemplate.query(query, new Object[]{province}, new RouteProblemMapper());
    }


    public String save(RouteProblem routeProblem) {
        String queryInsert = "INSERT INTO route_problem (route_problem_id, province, district, road_name, reporter_id, problem_topic, problem_detail, reported_date) VALUES (?, ?, ?, ?, ?, ? ,?, ?);";
        String id = createId("route_problem");
        jdbcTemplate.update(queryInsert,
                id,
                routeProblem.getProvince(),
                routeProblem.getDistrict(),
                routeProblem.getRoad_name(),
                routeProblem.getReporter_id(),
                routeProblem.getProblem_topic(),
                routeProblem.getProblem_detail(),
                routeProblem.getReported_date()
        );

        return id;
    }

    //    ================================ Bill ================================
    public boolean isExistBillByDeliveryId(String id) {
        String query = "SELECT COUNT(id) FROM bill WHERE delivery_id = ?;";

        int result = jdbcTemplate.queryForObject(query, new Object[]{id}, Integer.class);

        return result != 0;
    }

    public Bill findBillByDeliveryId(String id) {
        String query = "SELECT id, delivery_id, created_date FROM bill WHERE delivery_id = ?;";

        return jdbcTemplate.queryForObject(query, new Object[]{id}, new BillMapper());
    }

    public String save(Bill bill) {
        String query = "INSERT INTO bill (id, delivery_id, created_date) VALUES (?, ?, ?);";

        String id = createId("bill");

        jdbcTemplate.update(
                query,
                id,
                bill.getDeliveryId(),
                Date.valueOf(LocalDate.now())
        );

        return id;
    }

    //    ================================ Util ================================
    private String createId(String tableName) {
        String queryCount = "SELECT counter FROM counter WHERE table_name = ?;";

        int id = jdbcTemplate.queryForObject(queryCount, new Object[]{tableName}, Integer.class) + 1;

        String encodedId = UtilityMethod.rjust(Integer.toString(id), 9, '0');
        encodedId = tableName.charAt(0) + encodedId;

        updateCounter(tableName, id);

        return encodedId;
    }

    private void updateCounter(String tableName, int counter) {
        String query = "UPDATE counter SET counter = ? WHERE table_name = ?;";

        jdbcTemplate.update(query, counter, tableName);
    }

}
