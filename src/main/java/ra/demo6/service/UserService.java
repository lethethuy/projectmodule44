package ra.demo6.service;


import org.springframework.stereotype.Service;
import ra.demo6.DTO.request.FormLoginDTO;
import ra.demo6.DTO.request.FormRegisterDTO;

import ra.demo6.model.User;
import ra.demo6.util.ConnectDB;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class UserService implements IGenericService<User,Integer>{


    @Override
    public List<User> findAll() {
        Connection conn = null;
        List<User> userList = new ArrayList<>();
        try {
            conn = ConnectDB.getConnection();
            CallableStatement callSt = conn.prepareCall("{call findAllUser}");
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                User u = new User();
                u.setId(rs.getInt(1));
                u.setRole(rs.getInt(2));
                u.setFullName(rs.getString(3));
                u.setPassWord(rs.getString(4));
                u.setPassWord2(rs.getString(5));
                u.setCountry(rs.getString(6));
                u.setCity(rs.getString(7));
                u.setPhone(rs.getString(8));
                u.setEmail(rs.getString(9));
                u.setBirthdate(rs.getDate(10));
                u.setAvatar(rs.getString(11));
                u.setStatus(rs.getBoolean(12));
                userList.add(u);
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
        return userList;
    }


    @Override
    public void save(User user) {
        Connection conn = null;

            conn = ConnectDB.getConnection();


        try {
            if (user.getId() == 0) {
                // thêm moi
                CallableStatement callSt = conn.prepareCall("{call addUser(?,?,?,?)}");
                callSt.setString(1, user.getFullName());
                callSt.setString(2, user.getPassWord());
                callSt.setString(3, user.getPhone());
                callSt.setString(4, user.getEmail());
                callSt.executeUpdate();
            } else {
                // cap nhat
                CallableStatement callSt = conn.prepareCall("{call  updateUser(?,?,?,?,?,?,?,?,?,?,?)}");
                callSt.setInt(1, user.getId());
                callSt.setInt(2, user.getRole());
                callSt.setString(3, user.getFullName());
                callSt.setString(4, user.getPassWord());
                callSt.setString(5, user.getPassWord2());
                callSt.setString(6, user.getCountry());
                callSt.setString(7, user.getCity());
                callSt.setString(8, user.getPhone());
                callSt.setString(9, user.getEmail());
                callSt.setString(10, user.getAvatar());
                callSt.setBoolean(11, user.isStatus());
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
    public User findByID(Integer integer) {
        Connection conn = null;

            conn = ConnectDB.getConnection();

        User user = null;
        try {
            CallableStatement callSt = conn.prepareCall("{call  findById(?)}");
            callSt.setInt(1, integer);
            ResultSet rs = callSt.executeQuery();
            while (rs.next()) {
                user = new User();
                user.setId(rs.getInt(1));
                user.setRole(rs.getInt(2));
                user.setFullName(rs.getString(3));
                user.setPassWord(rs.getString(4));
                user.setPassWord2(rs.getString(5));
                user.setCountry(rs.getString(6));
                user.setCity(rs.getString(7));
                user.setPhone(rs.getString(8));
                user.setEmail(rs.getString(9));
                user.setBirthdate(rs.getDate(10));
                user.setAvatar(rs.getString(11));
                user.setStatus(rs.getBoolean(12));

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
        return user;
    }

    @Override
    public void deleteById(Integer integer) {
        Connection conn = null;

            conn = ConnectDB.getConnection();

        try {
            // xóa ảnh phụ
            CallableStatement callSt = conn.prepareCall("{call  deleteUserById(?)}");
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
    public User login(FormLoginDTO formLoginDTO) {
        User user = null;
        Connection con = null;
        try {
            con = ConnectDB.getConnection();
            CallableStatement callst = con.prepareCall("{call login(?,?)}");
            callst.setString(1, formLoginDTO.getEmail());
            callst.setString(2, formLoginDTO.getPassword());
            ResultSet rs = callst.executeQuery();
            if(rs.next()){
                user = new User();
                user.setId(rs.getInt("id"));
                user.setRole(rs.getInt("role_id"));
                user.setFullName(rs.getString("fullname"));
                user.setEmail(rs.getString("email"));
                user.setStatus(rs.getBoolean("status"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return user;
    }

    public void formToModel(FormRegisterDTO formRegisterDTO) {
// chuyen doi dto sang model
        User user = new User();
        user.setFullName(formRegisterDTO.getUsername());
        user.setEmail(formRegisterDTO.getEmail());
        user.setPassWord(formRegisterDTO.getPassword());
        user.setPhone(formRegisterDTO.getPhoneNumber());
        save(user);
    }

    public void lock(int id){
        Connection conn = null;
        conn = ConnectDB.getConnection();
        try {
            // xóa ảnh phụ
            CallableStatement callSt = conn.prepareCall("{call lockUser(?)}");
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
        }}


}
