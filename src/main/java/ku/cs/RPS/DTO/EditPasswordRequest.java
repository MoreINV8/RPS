package ku.cs.RPS.DTO;

import jakarta.validation.constraints.Min;
import ku.cs.RPS.validations.password.ValidPassword;
import lombok.Data;

@Data
public class EditPasswordRequest {
    private String oldPassword;

    @ValidPassword
    private String newPassword;

    @ValidPassword
    private String confirmPassword;
}
