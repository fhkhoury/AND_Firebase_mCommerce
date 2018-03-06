package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

import java.util.ArrayList;

public class Payment extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

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
        ecommerceBundle.putLong( FirebaseAnalytics.Param.CHECKOUT_STEP, 3 );
        ecommerceBundle.putString( FirebaseAnalytics.Param.CHECKOUT_OPTION, "" );
        ecommerceBundle.putString("screenName","Payment");
        ecommerceBundle.putString("userId", "1111111111");
        ecommerceBundle.putString("pageTopCategory", "Checkout");
        ecommerceBundle.putString("pageCategory", "Payment");
        ecommerceBundle.putString("pageSubCategory", "");
        ecommerceBundle.putString("pageType", "Checkout");
        ecommerceBundle.putString("loginStatus", "Logged");
        ecommerceBundle.putString("previousScreen", "Shipping");

        mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.CHECKOUT_PROGRESS, ecommerceBundle );
        mFirebaseAnalytics.setCurrentScreen(this, "Payment", null);

        final Button connect = (Button) findViewById(R.id.nextConfirmation);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle ecommerceBundle = new Bundle();
                ecommerceBundle.putString("screenName", "Payment");
                ecommerceBundle.putString( "eventCategory", "Enhanced Ecommerce" );
                ecommerceBundle.putString( "eventAction", "SET_CHECKOUT_OPTION" );
                ecommerceBundle.putString( "eventLabel", "Credit card" );
                ecommerceBundle.putLong( FirebaseAnalytics.Param.CHECKOUT_STEP, 3 );
                ecommerceBundle.putString( FirebaseAnalytics.Param.CHECKOUT_OPTION, "Credit card" );

                mFirebaseAnalytics2.logEvent( FirebaseAnalytics.Event.SET_CHECKOUT_OPTION, ecommerceBundle );

                startActivity(new Intent(Payment.this, Confirmation.class));
                finish();

            }
        });
    }
}
