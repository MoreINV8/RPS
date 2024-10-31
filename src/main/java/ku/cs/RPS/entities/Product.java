package ku.cs.RPS.entities;

import lombok.Data;

@Data
public class Product {
    private String id;
    private String deliveryId;
    private String noticeId;
    private int productCount;
    private String productDetail;
}
