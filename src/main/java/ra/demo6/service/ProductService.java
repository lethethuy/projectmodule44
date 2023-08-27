package ra.demo6.service;


import org.springframework.stereotype.Service;
import ra.demo6.model.Product;
import ra.demo6.util.ConnectDB;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
@Service
public class ProductService implements IGenericService<Product,Integer> {


    @Override
    public List<Product> findAll() {
        Connection conn = null;

            conn = ConnectDB.getConnection();

        List<Product> productList = new ArrayList<>();
        try {
            CallableStatement callSt = conn.prepareCall("{call products}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("product_name"));
                p.setImgUrl(rs.getString("image_url"));
                p.setDescription(rs.getString("description"));
                p.setStock(rs.getInt("stock"));
                p.setCatalogId(rs.getInt("catalog_id"));
                p.setPrice(rs.getDouble("price"));
                p.setStatus(rs.getBoolean("status"));
                productList.add(p);
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
        return productList;
    }

    @Override
    public void save(Product product) {
        Connection conn = null;
            conn = ConnectDB.getConnection();
        try {
            if (product.getId() == 0) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call addProducts(?,?,?,?,?,?,?)}");
                callSt.setString(1, product.getProductName());
                callSt.setString(2, product.getImgUrl());
                callSt.setString(3, product.getDescription());
                callSt.setInt(4, product.getStock());
                callSt.setInt(5, product.getCatalogId());
                callSt.setDouble(6, product.getPrice());
                callSt.setBoolean(7, product.isStatus());
                callSt.executeUpdate();
            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call  updateProducts(?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, product.getId());
                callSt.setString(2, product.getProductName());
                callSt.setString(3, product.getImgUrl());
                callSt.setString(4, product.getDescription());
                callSt.setInt(5, product.getStock());
                callSt.setInt(6, product.getCatalogId());
                callSt.setDouble(7, product.getPrice());
                callSt.setBoolean(8,product.isStatus());
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
    }

    @Override
    public Product findByID(Integer integer) {
        Connection conn = null;

            conn = ConnectDB.getConnection();

        Product p = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call  findById(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                p = new Product();
                p.setId(rs.getInt("id"));
                p.setProductName(rs.getString("product_name"));
                p.setImgUrl(rs.getString("image_url"));
                p.setDescription(rs.getString("description"));
                p.setStock(rs.getInt("stock"));
                p.setCatalogId(rs.getInt("catalog_id"));
                p.setPrice(rs.getDouble("price"));
                p.setStatus(rs.getBoolean("status"));
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
        return p;
    }

    @Override
    public void deleteById(Integer integer) {
        Connection conn = null;

            conn = ConnectDB.getConnection();

        try {
            // xóa ảnh phụ
            CallableStatement callSt = conn.prepareCall("{call  deleteById(?)}");
            callSt.setInt(1, integer);
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
