package ku.cs.RPS.DTO;

import jakarta.validation.constraints.Min;
import lombok.Data;

@Data
public class EditPasswordRequest {
    private String oldPassword;

    @Min(value = 6, message = "รหัสผ่านสั้นเกินไป")
    private String newPassword;

    @Min(value = 6, message = "รหัสผ่านสั้นเกินไป")
    private String confirmPassword;
}
