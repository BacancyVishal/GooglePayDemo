package com.saurabh.googlepaydemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.wallet.AutoResolveHelper;
import com.google.android.gms.wallet.PaymentData;
import com.google.android.gms.wallet.PaymentDataRequest;
import com.google.android.gms.wallet.PaymentsClient;
import com.google.android.gms.wallet.Wallet;
import com.google.android.gms.wallet.WalletConstants;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvProducts;
    public boolean showGooglePayButton = false;

    /**
     * A client for interacting with the Google Pay API
     */

    private PaymentsClient mPaymentsClient;

    /**
     * A constant integer you define to track a request for payment data activity
     */

    private static final int LOAD_PAYMENT_DATA_REQUEST_CODE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initProductListView(); // To initialise the view
    }

    /**
     * To initialise all the variables...
     */

    private void initProductListView() {
        rvProducts = findViewById(R.id.recyc_products);

        // initialize a Google Pay API client for an environment suitable for testing
        Wallet.WalletOptions googleWalletOptions = new Wallet.WalletOptions
                .Builder()
                .setEnvironment(WalletConstants.ENVIRONMENT_TEST)
                .build();

        mPaymentsClient = Wallet.getPaymentsClient(MainActivity.this, googleWalletOptions);
        new GetGooglePayAvailabilityTask(mPaymentsClient, MainActivity.this).showGooglePayButton();

        MainActivityView mainActivityView = new MainActivityView(MainActivity.this, rvProducts, showGooglePayButton);
        mainActivityView.initView();

    }

    public void requestPayment(String price) {
        Optional<JSONObject> paymentDataRequestJson = GooglePay.getPaymentDataRequest(price);
        if (!paymentDataRequestJson.isPresent()) {
            return;
        }
        PaymentDataRequest request =
                PaymentDataRequest.fromJson(paymentDataRequestJson.get().toString());
        if (request != null) {
            AutoResolveHelper.resolveTask(
                    mPaymentsClient.loadPaymentData(request), this, LOAD_PAYMENT_DATA_REQUEST_CODE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {
            // value passed in AutoResolveHelper
            case LOAD_PAYMENT_DATA_REQUEST_CODE:
                switch (resultCode) {
                    case Activity.RESULT_OK:
                        PaymentData paymentData = PaymentData.getFromIntent(data);
                        String json = paymentData.toJson();
                        Log.d(getString(R.string.app_name) + "_LOG", "SUCCESS: " + json != null ? json : "Payment response is null.");
                        break;
                    case Activity.RESULT_CANCELED:
                        break;
                    case AutoResolveHelper.RESULT_ERROR:
                        Status status = AutoResolveHelper.getStatusFromIntent(data);
                        // Log the status for debugging.
                        // Generally, there is no need to show an error to the user.
                        // The Google Pay payment sheet will present any account errors.
                        Log.d(getString(R.string.app_name) + "_LOG", "ERROR: " + status.getStatusMessage() != null ? status.getStatusMessage().toString() : "Status is null.");
                        break;
                    default:
                        // Do nothing.
                }
                break;
            default:
                // Do nothing.
        }
    }
}
