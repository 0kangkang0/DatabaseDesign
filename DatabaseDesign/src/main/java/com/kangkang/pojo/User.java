package com.kangkang.pojo;

public class User {
    private int id;
    private String username;
    private String email;
    private String telephone;
    private byte status;
    private String role;
    private String password;

    public User() {
    }

    public User(int id, String username, String email, String telephone, byte status, String role, String password) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.telephone = telephone;
        this.status = status;
        this.role = role;
        this.password = password;
    }

    public String getStatusStr(){
        return status==1? "启用":"禁用";
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", status=" + status +
                ", password='" + password + '\'' +
                '}';
    }
}
