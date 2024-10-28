package ku.cs.RPS.DTO;

import ku.cs.RPS.entities.Bill;
import ku.cs.RPS.entities.join.DeliveryCustomer;
import ku.cs.RPS.entities.join.NoticeEmployeeCar;
import lombok.Data;

import java.util.List;

@Data
public class BillResponse {
    private Bill bill;
    private DeliveryCustomer dc;
    private List<NoticeEmployeeCar> necs;
}
