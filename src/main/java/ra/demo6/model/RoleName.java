package ra.demo6.model;

import lombok.*;

public class RoleName {
    private int id;
    private String roleName;

    public RoleName(int id, String roleName) {
        this.id = id;
        this.roleName = roleName;
    }

    public RoleName() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
