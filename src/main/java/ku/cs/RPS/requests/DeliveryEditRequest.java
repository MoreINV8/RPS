package ku.cs.RPS.requests;

import jakarta.validation.constraints.Size;
import ku.cs.RPS.entities.Delivery;
import ku.cs.RPS.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class DeliveryEditRequest {

    private String deliveryId;
    private String customerId;

    @Size(min = 1, message = "สถานที่ปลายทางสั้นเกินไป")
    private String destination;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate deliveredTime;

    @Size(min = 1, message = "ชนิดสินค้าสั้นเกินไป")
    private String itemType;

    private List<Product> products = new ArrayList<>();

    private Delivery initialDelivery;
    private List<Product> initialProducts;
    private int allProductCountInitial;

    public DeliveryEditRequest(Delivery initialDelivery, List<Product> initialProducts) {
        this.initialDelivery = initialDelivery;
        this.initialProducts = initialProducts;
        customerId = initialDelivery.getCustomerId();
        destination = initialDelivery.getDestination();
        deliveredTime = initialDelivery.getDeliverDateTime().toLocalDate();
        itemType = initialDelivery.getItemType();
        deliveryId = initialDelivery.getDeliveryId();
        allProductCountInitial = initialDelivery.getAllProductsCount();
    }

    public void resetInput() {
        destination = initialDelivery.getDestination();
        deliveredTime = initialDelivery.getDeliverDateTime().toLocalDate();
        itemType = initialDelivery.getItemType();
    }
}
