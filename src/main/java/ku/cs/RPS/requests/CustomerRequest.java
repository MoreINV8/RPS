package ku.cs.RPS.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import ku.cs.RPS.entities.Customer;
import ku.cs.RPS.validations.ValidPhoneNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomerRequest {
    @Size(min = 4, message = "ชื่อสั้นเกินไป")
    private String firstName;

    @Size(min = 4, message = "นามสกุลสั้นเกินไป")
    private String lastName;

    @Email(message = "ไม่ตรงกับรูปแบบที่อยู่อีเมล")
    private String email;

    @ValidPhoneNumber
    private String phoneNumber;

    @Size(min = 1, message = "ที่อยู่สั้นเกินไป")
    private String address;

    public CustomerRequest(Customer customer) {
        firstName = customer.getFirstName();
        lastName = customer.getLastName();
        email = customer.getEmail();
        phoneNumber = customer.getPhoneNumber();
        address = customer.getAddress();
    }
}
