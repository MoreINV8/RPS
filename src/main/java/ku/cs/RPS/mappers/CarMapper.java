package ku.cs.RPS.mappers;

import ku.cs.RPS.entities.Car;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CarMapper implements RowMapper<Car> {

    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {

        // car_registration, driver_id, oil_type, finish_used, car_type

        Car car = new Car();
        car.setCarId(rs.getString("car_registration"));
        car.setDriverId(rs.getString("driver_id"));
        car.setOilType(rs.getString("oil_type"));
        car.setCarType(rs.getString("car_type"));

        Timestamp timestamp = rs.getTimestamp("finish_used");
        if (timestamp != null) {
            car.setEndOfUseTime(timestamp.toLocalDateTime()); // Convert Timestamp to LocalDateTime
        } else {
            car.setEndOfUseTime(null); // Handle null appropriately
        }

        return car;
    }
}
