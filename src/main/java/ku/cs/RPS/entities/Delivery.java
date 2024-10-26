package ku.cs.RPS.entities;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Delivery {
    private String id;
    private String customerId;
    private LocalDateTime createdDate;
    private LocalDateTime deliveredDate;
    private String itemType;
    private String destination;
    private String sentDetailStatus;
    private int allProductCount;
}
