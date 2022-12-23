package com.kangkang.pojo;

public class Address {
    private int id;
    private String province;
    private String city;
    private String area;
    private String provinceCityArea;
    private String detailAddress;
    private String allAddress;
    private String telephone;
    private String name;
    private int customerId;



    public String getAllAddress() {
        return allAddress;
    }

    public void setAllAddress(String allAddress) {
        this.allAddress = allAddress;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getProvinceCityArea() {
        return provinceCityArea;
    }

    public void setProvinceCityArea(String provinceCityArea) {
        this.provinceCityArea = provinceCityArea;
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", area='" + area + '\'' +
                ", provinceCityArea='" + provinceCityArea + '\'' +
                ", detailAddress='" + detailAddress + '\'' +
                ", telephone='" + telephone + '\'' +
                ", name='" + name + '\'' +
                ", customerId=" + customerId +
                '}';
    }
}
