package fiftyfive.and_firebase_mcommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class Confirmation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);

        final FirebaseAnalytics mFirebaseAnalytics2 = FirebaseAnalytics.getInstance(this);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle product1 = new Bundle();
        product1.putString(FirebaseAnalytics.Param.ITEM_ID, "ProductId 123");
        product1.putString(FirebaseAnalytics.Param.ITEM_NAME, "ProductName 123");
        product1.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "ProductCategory 123");
        product1.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "ProductVariant 123");
        product1.putString(FirebaseAnalytics.Param.ITEM_BRAND, "ProductBrand 123");
        product1.putDouble(FirebaseAnalytics.Param.PRICE, 12.34 );
        product1.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
        product1.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

        ArrayList items = new ArrayList();
        items.add(product1);

        Bundle ecommerceBundle = new Bundle();
        ecommerceBundle.putParcelableArrayList( "items", items );
        ecommerceBundle.putString( FirebaseAnalytics.Param.TRANSACTION_ID, "1A2B3C4D" );
        ecommerceBundle.putString( FirebaseAnalytics.Param.AFFILIATION, "Acme Clothing" );
        ecommerceBundle.putDouble( FirebaseAnalytics.Param.VALUE, 20.49 );
        ecommerceBundle.putDouble( FirebaseAnalytics.Param.TAX, 3.65 );
        ecommerceBundle.putDouble( FirebaseAnalytics.Param.SHIPPING, 4.50);
        ecommerceBundle.putString( FirebaseAnalytics.Param.CURRENCY, "EUR" );
        ecommerceBundle.putString( FirebaseAnalytics.Param.COUPON, "Fifty Fifty" );
        ecommerceBundle.putString("screenName", "Confirmation");
        ecommerceBundle.putString("userId", "1111111111");
        ecommerceBundle.putString("pageTopCategory", "Checkout");
        ecommerceBundle.putString("pageCategory", "Confirmation");
        ecommerceBundle.putString("pageSubCategory", "");
        ecommerceBundle.putString("pageType", "Checkout");
        ecommerceBundle.putString("loginStatus", "Logged");
        ecommerceBundle.putString("previousScreen", "Payment");

        mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.ECOMMERCE_PURCHASE, ecommerceBundle );
        mFirebaseAnalytics.setCurrentScreen(this, "Confirmation", null);

        final Button purchaseRefund = (Button) findViewById(R.id.refund);
        purchaseRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle ecommerceBundle = new Bundle();
                ecommerceBundle.putString("screenName", "Confirmation");
                ecommerceBundle.putString( FirebaseAnalytics.Param.TRANSACTION_ID, "1A2B3C4D" );
                ecommerceBundle.putDouble( FirebaseAnalytics.Param.VALUE, 20.49 );

                // (OPTIONAL) For partial refunds, define the item IDs and quantities of products being refunded
                Bundle refundedProduct = new Bundle();
                refundedProduct.putString(FirebaseAnalytics.Param.ITEM_ID, "ProductId 123"); // Required for partial refund
                refundedProduct.putLong(FirebaseAnalytics.Param.QUANTITY, 1); // Required for partial refund

                ArrayList items = new ArrayList();
                items.add(refundedProduct);
                ecommerceBundle.putParcelableArrayList( "items", items );

                mFirebaseAnalytics2.logEvent( FirebaseAnalytics.Event.PURCHASE_REFUND, ecommerceBundle );
            }
        });
    }
}
