package com.saurabh.googlepaydemo;

import android.graphics.drawable.Drawable;

public class ProductsModel {


    /**
     * product_id :
     * product_name :
     * image_url :
     * price :
     * offer_price :
     */

    private String product_id;
    private String product_name;
    private Drawable image_url;
    private String price;
    private String offer_price;

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Drawable getImage_url() {
        return image_url;
    }

    public void setImage_url(Drawable image_url) {
        this.image_url = image_url;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOffer_price() {
        return offer_price;
    }

    public void setOffer_price(String offer_price) {
        this.offer_price = offer_price;
    }
}
