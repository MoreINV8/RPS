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
    // Employee details
    private String employeeFirstName;
    private String employeeLastName;

    // Delivery details
    private String deliveryItemType;
    private String deliveryDestination;

    public Notice(String id, String delivery_id, String driver_id, String car_registration, String start_work_date, String complete_status, String employeeFirstName, String employeeLastName, String deliveryItemType, String deliveryDestination) {
        this.id = id;
        this.deliveryId = deliveryId;
        this.driverId = driverId;
        this.carRegistration = carRegistration;
        this.startWorkDate = startWorkDate;
        this.completeStatus = completeStatus;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.deliveryItemType = deliveryItemType;
        this.deliveryDestination = deliveryDestination;
    }

    public String getDriverId() {
        return driverId;
    }

}
