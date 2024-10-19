package ku.cs.RPS.entities;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import ku.cs.RPS.validation.ValidPhoneNumber;
import lombok.Data;

@Data
public class Customer {
    @Size(min = 10, max = 10, message = "เกิดข้อผิดพลาดโปรดแจ้งเจ้าหน้าที่")
    private String id;

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
}
