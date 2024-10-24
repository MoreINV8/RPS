package ku.cs.RPS.mappers;

import ku.cs.RPS.entities.Employee;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {

        // id, first_name, last_name, sex, email, phone_number, role, address, password

        Employee employee = new Employee();
        employee.setEmployeeId(rs.getString("id"));
        employee.setEmployeeFirstName(rs.getString("first_name"));
        employee.setEmployeeLastName(rs.getString("last_name"));
        employee.setEmployeeSex(rs.getString("sex"));
        employee.setEmployeeEmail(rs.getString("email"));
        employee.setEmployeePhoneNumber(rs.getString("phone_number"));
        employee.setEmployeeDepartment(rs.getString("role"));
        employee.setEmployeeAddress(rs.getString("address"));
        employee.setEmployeePassword(rs.getString("password"));

        return employee;
    }
}
