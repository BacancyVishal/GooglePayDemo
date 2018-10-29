package com.saurabh.googlepaydemo;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivityView {

    private boolean showGooglePayButton = false;
    private Context context;
    private final RecyclerView rvProducts;
    private List<ProductsModel> productsList = new ArrayList<>();
    private ProductsAdapter productsListAdapter;

    public MainActivityView(Context context, RecyclerView rvProducts, boolean b) {
        this.rvProducts = rvProducts;
        this.context = context;
        this.showGooglePayButton = b;
    }

    public void initView() {

        // To get the list of all the products...
        GetProductListTask getProductListTask = new GetProductListTask(productsList, context);
        productsList = getProductListTask.getProductList();

        // To set the layout and adapter for product list...
        rvProducts.setLayoutManager(new GridLayoutManager(context, 2));
        productsListAdapter = new ProductsAdapter(context, productsList, showGooglePayButton);
        rvProducts.setAdapter(productsListAdapter);

    }

}
