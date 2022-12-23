package com.kangkang.pojo;

public class Orders {
    private int id;
    private int goodsId;
    private int customerId;
    private int addressId;
    private String customerName;
    private String shName;
    private String  goodsName;
    private double totalPrice;
    private int num;
    private double price;
    private String status;
    private String address;
    private String telephone;
    private String buyTime;
    private String finishTime;
    private String fhTime;

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShName() {
        return shName;
    }

    public void setShName(String shName) {
        this.shName = shName;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getBuyTime() {
        return buyTime;
    }

    public void setBuyTime(String buyTime) {
        this.buyTime = buyTime;
    }

    public String getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(String finishTime) {
        this.finishTime = finishTime;
    }

    public String getFhTime() {
        return fhTime;
    }

    public void setFhTime(String fhTime) {
        this.fhTime = fhTime;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", goodsId=" + goodsId +
                ", customerId=" + customerId +
                ", addressId=" + addressId +
                ", customerName='" + customerName + '\'' +
                ", shName='" + shName + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", totalPrice=" + totalPrice +
                ", num=" + num +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", buyTime='" + buyTime + '\'' +
                ", finishTime='" + finishTime + '\'' +
                ", fhTime='" + fhTime + '\'' +
                '}';
    }
}
