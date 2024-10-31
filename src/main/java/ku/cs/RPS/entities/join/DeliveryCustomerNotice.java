package ku.cs.RPS.entities.join;

import lombok.Data;

import java.sql.Date;

@Data
public class DeliveryCustomerNotice {
    private String noticeId;
    private String completeStatus;

    private String deliveryId;
    private Date deliveryCreatedDate;
    private String destination;
    private String itemType;
    private Date deliverDateTime;
    private int productCount;

    private String customerId;
    private String customerFirstName;
    private String customerLastName;
}
