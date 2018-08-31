package com.example.mlk.myapplication.bean;

/**
 * 作者：idmin on 2018/8/31 10:09
 */
public class ProductBean {

    private String name;//产品名称
    private String price;//价格
    private int businessCode;//业务编码

    public ProductBean(String name, String price, int businessCode) {
        this.name = name;
        this.price = price;
        this.businessCode = businessCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getBusinessCode() {
        return businessCode;
    }

    public void setBusinessCode(int businessCode) {
        this.businessCode = businessCode;
    }
}
