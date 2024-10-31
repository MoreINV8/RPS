package ku.cs.RPS.mappers;

import ku.cs.RPS.entities.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

//        (id, notice_id, delivery_id, item_count, item_detail)

        Product product = new Product();
        product.setId(rs.getString("id"));
        product.setNoticeId(rs.getString("notice_id"));
        product.setDeliveryId(rs.getString("delivery_id"));
        product.setProductCount(rs.getInt("item_count"));
        product.setProductDetail(rs.getString("item_detail"));

        return product;
    }
}
