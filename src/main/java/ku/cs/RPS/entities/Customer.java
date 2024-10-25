package ku.cs.RPS.entities;

import jakarta.validation.constraints.Size;
import ku.cs.RPS.validations.email.ValidEmail;
import ku.cs.RPS.validations.phone_number.ValidPhoneNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Customer {
    //    @Size(min = 10, max = 10, message = "เกิดข้อผิดพลาดโปรดแจ้งเจ้าหน้าที่")
    private String id;

    @Size(min = 4, message = "ชื่อสั้นเกินไป")
    private String firstName;

    @Size(min = 4, message = "นามสกุลสั้นเกินไป")
    private String lastName;

    @ValidEmail
    private String email;

    @ValidPhoneNumber
    private String phoneNumber;

    @Size(min = 1, message = "ที่อยู่สั้นเกินไป")
    private String address;

    public Customer(String firstName, String lastName, String email, String phoneNumber, String address) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }
}
