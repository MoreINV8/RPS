package ku.cs.RPS.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ku.cs.RPS.validations.email.ValidEmail;
import ku.cs.RPS.validations.phone_number.ValidPhoneNumber;
import lombok.Data;

@Data
public class RegisterEmployeeRequest {
    @NotBlank(message = "โปรดใส่ชื่อ")
    @Size(min = 2, message = "ชื่อสั้นเกินไป")
    private String employeeFirstName;

    @NotBlank(message = "โปรดใส่นามสกุล")
    @Size(min = 2, message = "นามสกุลสั้นเกินไป")
    private String employeeLastName;

    @NotBlank(message = "โปรดใส่เพศ")
    private String employeeSex;

    @NotBlank(message = "โปรดใส่อีเมล")
    @ValidEmail
    private String employeeEmail;

    @NotBlank(message = "โปรดใส่เบอร์โทรติดต่อ")
    @ValidPhoneNumber
    private String employeePhoneNumber;

    @NotBlank(message = "โปรดใส่แผนก")
    private String employeeDepartment;

    @NotBlank(message = "โปรดใส่ที่อยู่")
    @Size(min = 2, message = "ที่อยู่สั้นเกินไป")
    private String employeeAddress;
}
