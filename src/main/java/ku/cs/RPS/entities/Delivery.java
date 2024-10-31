package ku.cs.RPS.entities;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@NoArgsConstructor
public class Delivery {

    private String deliveryId;

    @NotBlank(message = "โปรดใส่รหัสลูกค้า")
    private String customerId;

    private Date deliveryCreatedDate;

    @NotBlank(message = "โปรดใส่เวลาส่งมอบสินค้า")
    private Date deliverDateTime;

    @NotBlank(message = "โปรดใส่ประเภทสินค้า")
    private String itemType;

    @NotBlank(message = "โปรดใส่สถานที่ปลายทาง")
    private String destination;

    private String sentDetailStatus;

    private int allProductsCount;

    public Delivery(String customerId, Date deliverDateTime, String itemType, String destination) {
        this.customerId = customerId;
        this.deliverDateTime = deliverDateTime;
        this.itemType = itemType;
        this.destination = destination;
        this.sentDetailStatus = "TODO";
        this.deliveryId = null;
        this.deliveryCreatedDate = null;
    }
}
