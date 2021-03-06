package com.saurabh.googlepaydemo;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Optional;

public class GooglePay {

    /**
     * Create a Google Pay API base request object with properties used in all requests
     *
     * @return Google Pay API base request object
     * @throws JSONException
     */
    private static JSONObject getBaseRequest() throws JSONException {
        return new JSONObject()
                .put("apiVersion", 2)
                .put("apiVersionMinor", 0);
    }

    /**
     * Identify your gateway and your app's gateway merchant identifier
     *
     * <p>The Google Pay API response will return an encrypted payment method capable of being charged
     * by a supported gateway after payer authorization
     *
     * <p>TODO: check with your gateway on the parameters to pass
     *
     * @return payment data tokenization for the CARD payment method
     * @throws JSONException
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#PaymentMethodTokenizationSpecification">PaymentMethodTokenizationSpecification</a>
     */
    private static JSONObject getTokenizationSpecification() throws JSONException {
        JSONObject tokenizationSpecification = new JSONObject();
        tokenizationSpecification.put("type", "DIRECT");
        tokenizationSpecification.put(
                "parameters",
                new JSONObject()
                        .put("protocolVersion", "ECv1")
                        .put("publicKey", "MIGHAgEAMBMGByqGSM49AgEGCCqGSM49AwEHBG0wawIBAQQgWJh/YAvVAFZLWvKMozgJEOVRcFYyi6V8YRqLm5fY6s+hRANCAAQ96Pr7xvoDusMShI68AIptxtrs89qPYrXqD+XtP83SbzBvMWgLfEBcMy6TCI49FdN+b4J/CJeV7Dnd6iwE0bbk"));


        return tokenizationSpecification;
    }

    /**
     * Card networks supported by your app and your gateway
     *
     * <p>TODO: confirm card networks supported by your app and gateway
     *
     * @return allowed card networks
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#CardParameters">CardParameters</a>
     */
    private static JSONArray getAllowedCardNetworks() {
        return new JSONArray()
                .put("AMEX")
                .put("DISCOVER")
                .put("JCB")
                .put("MASTERCARD")
                .put("VISA");
    }

    /**
     * Card authentication methods supported by your app and your gateway
     *
     * <p>TODO: confirm your processor supports Android device tokens on your supported card networks
     *
     * @return allowed card authentication methods
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#CardParameters">CardParameters</a>
     */
    private static JSONArray getAllowedCardAuthMethods() {
        return new JSONArray()
                .put("PAN_ONLY")
                .put("CRYPTOGRAM_3DS");
    }

    /**
     * Describe your app's support for the CARD payment method
     *
     * <p>The provided properties are applicable to both an IsReadyToPayRequest and a
     * PaymentDataRequest
     *
     * @return a CARD PaymentMethod object describing accepted cards
     * @throws JSONException
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#PaymentMethod">PaymentMethod</a>
     */
    private static JSONObject getBaseCardPaymentMethod() throws JSONException {
        JSONObject cardPaymentMethod = new JSONObject();
        cardPaymentMethod.put("type", "CARD");
        cardPaymentMethod.put(
                "parameters",
                new JSONObject()
                        .put("allowedAuthMethods", GooglePay.getAllowedCardAuthMethods())
                        .put("allowedCardNetworks", GooglePay.getAllowedCardNetworks()));

        return cardPaymentMethod;
    }

    /**
     * Describe the expected returned payment data for the CARD payment method
     *
     * @return a CARD PaymentMethod describing accepted cards and optional fields
     * @throws JSONException
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#PaymentMethod">PaymentMethod</a>
     */
    private static JSONObject getCardPaymentMethod() throws JSONException {
        JSONObject cardPaymentMethod = GooglePay.getBaseCardPaymentMethod();
        cardPaymentMethod.put("tokenizationSpecification", GooglePay.getTokenizationSpecification());

        return cardPaymentMethod;
    }

    /**
     * Provide Google Pay API with a payment amount, currency, and amount status
     *
     * @return information about the requested payment
     * @throws JSONException
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#TransactionInfo">TransactionInfo</a>
     * @param price
     */
    public static JSONObject getTransactionInfo(String price) throws JSONException {
        JSONObject transactionInfo = new JSONObject();
        transactionInfo.put("totalPrice", price);
        transactionInfo.put("totalPriceStatus", "FINAL");
        transactionInfo.put("currencyCode", "INR");

        Log.d("Demo", "Transaction info: "+ transactionInfo.toString());
        return transactionInfo;
    }

    /**
     * Information about the merchant requesting payment information
     *
     * @return information about the merchant
     * @throws JSONException
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#MerchantInfo">MerchantInfo</a>
     */
    private static JSONObject getMerchantInfo() throws JSONException {
        return new JSONObject()
                .put("merchantName", "Hind market");
    }

    /**
     * An object describing accepted forms of payment by your app, used to determine a viewer's
     * readiness to pay
     *
     * @return API version and payment methods supported by the app
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#IsReadyToPayRequest">IsReadyToPayRequest</a>
     */
    public static Optional<JSONObject> getIsReadyToPayRequest() {
        try {
            JSONObject isReadyToPayRequest = GooglePay.getBaseRequest();
            isReadyToPayRequest.put(
                    "allowedPaymentMethods", new JSONArray().put(getBaseCardPaymentMethod()));
            return Optional.of(isReadyToPayRequest);
        } catch (JSONException e) {
            return Optional.empty();
        }
    }

    /**
     * An object describing information requested in a Google Pay payment sheet
     *
     * @return payment data expected by your app
     * @see <a
     *     href="https://developers.google.com/pay/api/android/reference/object#PaymentDataRequest">PaymentDataRequest</a>
     * @param price
     */
    public static Optional<JSONObject> getPaymentDataRequest(String price) {
        try {
            JSONObject paymentDataRequest = GooglePay.getBaseRequest();
            paymentDataRequest.put(
                    "allowedPaymentMethods", new JSONArray().put(GooglePay.getCardPaymentMethod()));
            paymentDataRequest.put("transactionInfo", GooglePay.getTransactionInfo(price));
            paymentDataRequest.put("merchantInfo", GooglePay.getMerchantInfo());
//            paymentDataRequest.put("emailRequired", true); // Optional
//            paymentDataRequest.put("shippingAddressRequired", true); // Optional
            return Optional.of(paymentDataRequest);
        } catch (JSONException e) {
            return Optional.empty();
        }
    }

}
