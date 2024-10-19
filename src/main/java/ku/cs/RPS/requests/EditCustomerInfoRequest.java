package ku.cs.RPS.requests;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import ku.cs.RPS.entities.Customer;
import lombok.Data;
import lombok.NonNull;

@Data
public class EditCustomerInfoRequest {
    @NonNull
    private String firstName;
    @NonNull
    private String lastName;
    @NonNull
    @Email(message = "รูปแบบอีเมลไม่ถูกตต้อง")
    private String email;
    @NonNull
    @Size(min = 10, message = "เบอร์โทรศัพท์มือถือต้องมีขนาด 10 ตัวอักษร")
    private String phoneNumber;
    @NonNull
    private String address;

    public EditCustomerInfoRequest(Customer customer) {
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.email = customer.getEmail();
        this.phoneNumber = customer.getPhoneNumber();
        this.address = customer.getAddress();
    }
}
