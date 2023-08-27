package ra.demo6.service;

import com.sun.org.apache.xpath.internal.operations.Or;
import ra.demo6.model.CartItem;
import ra.demo6.model.Order;
import ra.demo6.model.Product;
import ra.demo6.util.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderService {

    public List<Order> findAll() {
        Connection conn = null;

        conn = ConnectDB.getConnection();

        List<Order> orderList = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call findAllOrder}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Order o = new Order();
                o.setId(rs.getInt("id"));
                o.setUserId(rs.getInt("user_id"));
                o.setOrderAt(rs.getDate("order_at"));
                o.setTotalPrice(rs.getInt("total_price"));
                o.setPhone(rs.getInt("phone"));
                o.setfullName(rs.getString("name"));
                o.setStatus(rs.getInt("status"));
                orderList.add(o);
            }
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
        return orderList;
    }


    public int save(Order order) {
        Connection conn = null;
        conn = ConnectDB.getConnection();
        int newOrderId = 0;
        try {
            if (order.getId() == 0) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call addOrder(?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, order.getUserId());
                callSt.setDate(2, new java.sql.Date(order.getOrderAt().getTime()));
                callSt.setInt(3, order.getTotalPrice());
                callSt.setInt(4, order.getPhone());
                callSt.setString(5, order.getAddress());
                callSt.setString(6, order.getfullName());
                callSt.setInt(7, order.isStatus());
                callSt.registerOutParameter(8,Types.INTEGER);
                callSt.executeUpdate();
                newOrderId = callSt.getInt(8);
            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call  updateOrder(?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, order.getId());
                callSt.setInt(2, order.getUserId());
                callSt.setDate(3, (Date) order.getOrderAt());
                callSt.setInt(4, order.getTotalPrice());
                callSt.setInt(5, order.getPhone());
                callSt.setString(6, order.getAddress());
                callSt.setString(7, order.getfullName());
                callSt.setInt(8, order.isStatus());
                callSt.executeUpdate();
            }
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
        return newOrderId;
    }


    public Order findByID(Integer id) {
        Connection conn = null;

        conn = ConnectDB.getConnection();

        Order o = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call  findOrderById(?)}");
            callSt.setInt(1, id);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                o = new Order();
                o.setId(rs.getInt("id"));
                o.setUserId(rs.getInt("user_id"));
                o.setOrderAt(rs.getDate("order_at"));
                o.setTotalPrice(rs.getInt("total_price"));
                o.setAddress(rs.getString("adrress"));
                o.setfullName(rs.getString("name"));
                o.setStatus(rs.getInt("status"));
            }

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
        return o;
    }


    public void deleteById(Integer id) {
        Connection conn = null;

        conn = ConnectDB.getConnection();

        try {
            // xóa ảnh phụ
            CallableStatement callSt = conn.prepareCall("{call  deleteOrderById(?)}");
            callSt.setInt(1, id);
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
}
