package ku.cs.RPS.entities;

import jakarta.validation.constraints.NotBlank;
import ku.cs.RPS.validations.licensePlate.ValidLicensePlate;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Car {

    @NotBlank
    @ValidLicensePlate
    private String carId;

    @NotBlank(message = "โปรดใส่รหัสพนักงานขับรถ")
    private String driverId;

    @NotBlank(message = "โปรดใส่ประเภทของน้ำมัน")
    private String oilType;

    private Date endOfUseTime;

    @NotBlank(message = "โปรดใส่ชนิดของรถ")
    private String carType;

    public Car(String carId, String driverId, String oilType, String carType) {
        this.carId = carId;
        this.driverId = driverId;
        this.oilType = oilType;
        this.carType = carType;
        this.endOfUseTime = null;
    }
}
