package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Adresses extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adresses);
        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle=new Bundle();
        bundle.putString("screenName","Adresses");
        bundle.putString("userId", "1111111111");
        bundle.putString("pageTopCategory", "Profile");
        bundle.putString("pageCategory", "Adresses");
        bundle.putString("pageSubCategory", "");
        bundle.putString("pageType", "User");
        bundle.putString("loginStatus", "Logged");
        bundle.putString("previousScreen", "Login");
        mFirebaseAnalytics.logEvent("screenView", bundle);
        mFirebaseAnalytics.setCurrentScreen(this, "Adresses", null);

        final Button connect = (Button) findViewById(R.id.shipping);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    startActivity(new Intent(Adresses.this, Shipping.class));
                    finish();
                }
            });
    }
}

