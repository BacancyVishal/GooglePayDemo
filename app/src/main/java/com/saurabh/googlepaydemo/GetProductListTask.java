package com.saurabh.googlepaydemo;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class GetProductListTask {

    public List<ProductsModel> productsList = new ArrayList<>();
    private Context context;

    public GetProductListTask(List<ProductsModel> productsList, Context context) {
        this.productsList = productsList;
        this.context = context;
    }

    /**
     * To get the list of all products...
     */

    public List<ProductsModel> getProductList() {

        ProductsModel productsModel = new ProductsModel();

        productsModel.setProduct_id("001");
        productsModel.setProduct_name("Polo Shirt");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.polo_shirt));
        productsModel.setPrice("180.00");
        productsModel.setOffer_price("00.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("002");
        productsModel.setProduct_name("Polo T-Shirt");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.polo));
        productsModel.setPrice("150.00");
        productsModel.setOffer_price("00.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("003");
        productsModel.setProduct_name("Nike T-shirt");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.shirt_01));
        productsModel.setPrice("210.00");
        productsModel.setOffer_price("10.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("004");
        productsModel.setProduct_name("Sneakers Sport Shoes");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.sneakers));
        productsModel.setPrice("300.00");
        productsModel.setOffer_price("00.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("005");
        productsModel.setProduct_name("Red Tie");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.tie));
        productsModel.setPrice("100.00");
        productsModel.setOffer_price("00.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("006");
        productsModel.setProduct_name("Sports T-Shirt");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.shirt));
        productsModel.setPrice("220.00");
        productsModel.setOffer_price("9.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("007");
        productsModel.setProduct_name("Sneakers Shoes");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.sneakers_01));
        productsModel.setPrice("399.99");
        productsModel.setOffer_price("00.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("008");
        productsModel.setProduct_name("Polo Shoes");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.shoe));
        productsModel.setPrice("310.00");
        productsModel.setOffer_price("00.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("009");
        productsModel.setProduct_name("Fishing Vest");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.fishing_vest));
        productsModel.setPrice("110.00");
        productsModel.setOffer_price("00.00");
        productsList.add(productsModel);

        productsModel = new ProductsModel();
        productsModel.setProduct_id("010");
        productsModel.setProduct_name("Nike Sports T-Shirt");
        productsModel.setImage_url(context.getResources().getDrawable(R.mipmap.polo_shirt));
        productsModel.setPrice("300.00");
        productsModel.setOffer_price("15.00");
        productsList.add(productsModel);

        return productsList;

    }

}
