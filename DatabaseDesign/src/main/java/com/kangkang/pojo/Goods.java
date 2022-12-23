package com.kangkang.pojo;

public class Goods {
    private int id;
    private String img;
    private String goodName;
    private double price;
    private int num;
    private String description;
    private String brand;
    private String statusStr;
    private String status;
    private String category;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Brand{" +
                "id=" + id +
                ", img='" + img + '\'' +
                ", goodName='" + goodName + '\'' +
                ", price=" + price +
                ", num=" + num +
                ", description='" + description + '\'' +
                ", brand='" + brand + '\'' +
                ", statusStr='" + statusStr + '\'' +
                ", status=" + status +
                ", category='" + category + '\'' +
                '}';
    }
}
