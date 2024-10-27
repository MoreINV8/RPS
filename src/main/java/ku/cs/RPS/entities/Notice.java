package ku.cs.RPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notice {

    private String id;  // Renamed from noticeId to id
    private String delivery_id;
    private String driver_id;
    private String car_registration;
    private String start_work_date;
    private String complete_status;
    private String employeeFirstName;
    private String employeeLastName;

    public Notice (String id, String delivery_id, String driver_id, String car_registration, String start_work_date, String complete_status, String employeeFirstName, String employeeLastName){
        this.id = id;
        this.delivery_id = delivery_id;
        this.driver_id = driver_id;
        this.car_registration = car_registration;
        this.start_work_date = start_work_date;
        this.complete_status = complete_status;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
    }

    public String getDriverId() {
        return driver_id;
    }

}