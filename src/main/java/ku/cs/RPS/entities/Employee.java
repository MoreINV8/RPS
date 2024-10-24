package ku.cs.RPS.entities;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import ku.cs.RPS.validations.ValidEmail;
import ku.cs.RPS.validations.ValidPhoneNumber;
import ku.cs.RPS.validations.password.ValidPassword;
import lombok.Data;
import lombok.NoArgsConstructor;

import static ku.cs.RPS.utils.PasswordUtils.*;

@Data
@NoArgsConstructor
public class Employee {

    private String employeeId;

    @NotBlank(message = "โปรดใส่ชื่อพนักงาน")
    @Size(min = 2, message = "ชื่อสั้นเกินไป")
    private String employeeFirstName;

    @NotBlank(message = "โปรดใส่นามสกุลพนักงาน")
    @Size(min = 2, message = "นามสกุลสั้นเกินไป")
    private String employeeLastName;

    @NotBlank(message = "โปรดใส่เพศพนักงาน")
    private String employeeSex;

    @NotBlank(message = "โปรดใส่อีเมลพนักงาน")
    @ValidEmail
    private String employeeEmail;

    @NotBlank(message = "โปรดใส่เบอร์โทรติดต่อพนักงาน")
    @ValidPhoneNumber
    private String employeePhoneNumber;

    @NotBlank(message = "โปรดใส่แผนกของพนักงาน")
    private String employeeDepartment;

    @NotBlank(message = "โปรดใส่ที่อยู่พนักงาน")
    @Size(min = 2, message = "ที่อยู่สั้นเกินไป")
    private String employeeAddress;

    @ValidPassword
    private String employeePassword;

    public Employee(String employeeFirstName, String employeeLastName, String employeeSex ,String employeeEmail, String employeePhoneNumber, String employeeDepartment, String employeeAddress) {
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.employeeSex = employeeSex;
        this.employeeDepartment = employeeDepartment;
        this.employeeEmail = employeeEmail;
        this.employeePhoneNumber = employeePhoneNumber;
        this.employeeAddress = employeeAddress;
        this.employeePassword = generatePassword();  // generated first
    }
}
