package ku.cs.RPS.entities;

import ku.cs.RPS.entities.join.DeliveryCustomerNotice;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Bill {
    private String billId;
    private String deliveryId;
    private Date createdDate;

    public Bill(DeliveryCustomerNotice data) {
        deliveryId = data.getDeliveryId();
    }
}
