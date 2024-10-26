package ku.cs.RPS.requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import ku.cs.RPS.entities.Product;
import ku.cs.RPS.validations.customer_id.ValidCustomerId;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryCreateRequest {
    @ValidCustomerId
    private String customerId;

    @Size(min = 1, message = "สถานที่ปลายทางสั้นเกินไป")
    private String destination;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveredTime;

    @Size(min = 1, message = "ชนิดสินค้าสั้นเกินไป")
    private String itemType;

    @NotEmpty(message = "ต้องมีสินค้าอย่างน้อย 1 ชิ้น")
    private List<Product> products = new ArrayList<>();
}
