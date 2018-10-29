package com.saurabh.googlepaydemo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wallet.IsReadyToPayRequest;
import com.google.android.gms.wallet.PaymentsClient;

import org.json.JSONObject;

import java.util.Optional;

public class GetGooglePayAvailabilityTask {

    private PaymentsClient mPaymentsClient;
    private Context context;

    public GetGooglePayAvailabilityTask(PaymentsClient mPaymentsClient, Context context) {
        this.mPaymentsClient = mPaymentsClient;
        this.context = context;
    }

    final Optional<JSONObject> isReadyToPayJson = GooglePay.getIsReadyToPayRequest();

    public void showGooglePayButton() {

        if (!isReadyToPayJson.isPresent()) {
            ((MainActivity)context).showGooglePayButton = false;
        }

        IsReadyToPayRequest request = IsReadyToPayRequest.fromJson(isReadyToPayJson.get().toString());
        if (request == null) {
            ((MainActivity)context).showGooglePayButton = false;
        }

        Task<Boolean> task = mPaymentsClient.isReadyToPay(request);
        task.addOnCompleteListener(
                new OnCompleteListener<Boolean>() {
                    @Override
                    public void onComplete(@NonNull Task<Boolean> task) {
                        try {
                            boolean result = task.getResult(ApiException.class);
                            if (result) {
                                // show Google as a payment option
                                Log.d("Demo", "result: " + result);
                                ((MainActivity)context).showGooglePayButton = true;
                            }
                        } catch (ApiException exception) {
                            // handle developer errors
                            ((MainActivity)context).showGooglePayButton = false;
                        }
                    }
                });

    }

}
