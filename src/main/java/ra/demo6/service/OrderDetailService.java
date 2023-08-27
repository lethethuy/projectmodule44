package ra.demo6.service;

import org.springframework.stereotype.Service;
import ra.demo6.model.Order;
import ra.demo6.model.OrderDetail;
import ra.demo6.util.ConnectDB;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
@Service

public class OrderDetailService implements IGenericService<OrderDetail, Integer> {

    @Override
    public List<OrderDetail> findAll() {
        return null;
    }

    @Override
    public void save(OrderDetail orderDetail) {
        Connection conn = null;
        conn = ConnectDB.getConnection();
        try {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call addOrderDetail(?,?,?,?)}");
                callSt.setInt(1, orderDetail.getProductId());
                callSt.setDouble(2, orderDetail.getPrice());
                callSt.setInt(3, orderDetail.getQuantity());
                callSt.setInt(4, orderDetail.getOrderId());;
                callSt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }


    }

    @Override
    public OrderDetail findByID(Integer id) {
        return null;
    }

    @Override
    public void deleteById(Integer id) {

    }
}
