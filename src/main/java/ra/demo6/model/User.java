package ra.demo6.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class User {
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
    private String avatar;
    private int role = 2;
    private boolean status;
    private  float totalPurchase;

    public User(int id, String fullName, String userName, String passWord, String passWord2, String country, String city, String phone, String email, Date birthdate, String avatar, int role, boolean status, float totalPurchase) {
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

    public User() {
    }
    public User(String userName, String email, String passWord) {
        this.userName = userName;
        this.email = email;
        this.passWord = passWord;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", userName='" + userName + '\'' +
                ", passWord='" + passWord + '\'' +
                ", passWord2='" + passWord2 + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", birthdate=" + birthdate +
                ", avatar='" + avatar + '\'' +
                ", role=" + role +
                ", status=" + status +
                ", totalPurchase=" + totalPurchase +
                '}';
    }
}
