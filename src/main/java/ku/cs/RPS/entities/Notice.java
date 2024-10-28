package ku.cs.RPS.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Notice {

    private String id;  // Renamed from noticeId to id
    private String deliveryId;
    private String driverId;
    private String carRegistration;
    private String startWorkDate;
    private String completeStatus;
    private String employeeFirstName;
    private String employeeLastName;

    public Notice(String id, String deliveryId, String driverId, String carRegistration, String startWorkDate, String completeStatus, String employeeFirstName, String employeeLastName) {
        this.id = id;
        this.deliveryId = deliveryId;
        this.driverId = driverId;
        this.carRegistration = carRegistration;
        this.startWorkDate = startWorkDate;
        this.completeStatus = completeStatus;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
    }

    public String getDriverId() {
        return driverId;
    }

}
