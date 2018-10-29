package com.saurabh.googlepaydemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {


    private final List<ProductsModel> productsList;
    private final Context context;
    private boolean showGooglePayButton = false;

    public ProductsAdapter(Context context, List<ProductsModel> productsList, boolean showGooglePayButton) {
        this.context = context;
        this.productsList = productsList;
        this.showGooglePayButton = showGooglePayButton;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_product, parent, false);
        return new ProductsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {

        final ProductsModel item = productsList.get(position);

        holder.ivProductImage.setImageDrawable(item.getImage_url());
        holder.tvProductName.setText(item.getProduct_name());
        holder.tvProductPrice.setText("₹" + item.getPrice());

        holder.btnPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!((MainActivity)context).showGooglePayButton) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setMessage("Google pay not allowed on your device.");
                    alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    alertDialogBuilder.show();
                } else {
                    ((MainActivity) context).requestPayment(item.getPrice());
                }

//                Toast.makeText(context, "You purchased " + item.getProduct_name() + ".", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return productsList != null ? productsList.size() : 0;
    }

    public class ProductsViewHolder extends RecyclerView.ViewHolder {
        private final Button btnPurchase;
        private final ImageView ivProductImage;
        private final TextView tvProductName, tvProductPrice;

        public ProductsViewHolder(View itemView) {
            super(itemView);

             ivProductImage = itemView.findViewById(R.id.img_row_product_img);
             tvProductName = itemView.findViewById(R.id.txt_row_product_name);
             tvProductPrice = itemView.findViewById(R.id.txt_row_product_price);
             btnPurchase = itemView.findViewById(R.id.btn_row_product_purchase);
        }
    }
}
