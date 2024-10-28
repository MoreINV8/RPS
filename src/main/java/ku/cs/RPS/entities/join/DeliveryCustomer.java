package ku.cs.RPS.entities.join;

import lombok.Data;

import java.sql.Date;

@Data
public class DeliveryCustomer {
    private String deliveryId;
    private String destination;
    private String itemType;
    private Date deliveredTime;
    private String customerId;
    private String customerFirstName;
    private String customerLastName;
}
