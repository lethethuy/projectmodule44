package ra.demo6.model;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

public class UserDTO {
    private int id;
    private String fullName;

    private String userName;

    private String passWord;

    private String passWord2;
    private String country;
    private String city;
    private String phone;
    private String email;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthdate;
    private MultipartFile avatar;
    private int role = 2;
    private boolean status;
    private  float totalPurchase;

    public UserDTO(int id, String fullName, String userName, String passWord, String passWord2, String country, String city, String phone, String email, Date birthdate, MultipartFile avatar, int role, boolean status, float totalPurchase) {
        this.id = id;
        this.fullName = fullName;
        this.userName = userName;
        this.passWord = passWord;
        this.passWord2 = passWord2;
        this.country = country;
        this.city = city;
        this.phone = phone;
        this.email = email;
        this.birthdate = birthdate;
        this.avatar = avatar;
        this.role = role;
        this.status = status;
        this.totalPurchase = totalPurchase;
    }

    public UserDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getPassWord2() {
        return passWord2;
    }

    public void setPassWord2(String passWord2) {
        this.passWord2 = passWord2;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public MultipartFile getAvatar() {
        return avatar;
    }

    public void setAvatar(MultipartFile avatar) {
        this.avatar = avatar;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public float getTotalPurchase() {
        return totalPurchase;
    }

    public void setTotalPurchase(float totalPurchase) {
        this.totalPurchase = totalPurchase;
    }
}
