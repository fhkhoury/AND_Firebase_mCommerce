package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;

public class Informations extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        final FirebaseAnalytics mFirebaseAnalytics2 = FirebaseAnalytics.getInstance(this);
        Bundle bundle=new Bundle();
        bundle.putString("screenName","Informations");
        bundle.putString("userId", "11111111111");
        bundle.putString("pageTopCategory", "Informations");
        bundle.putString("pageCategory", "");
        bundle.putString("pageSubCategory", "");
        bundle.putString("pageType", "Informations");
        bundle.putString("loginStatus", "Not logged");
        bundle.putString("previousScreen", "Menu bar");

        mFirebaseAnalytics.logEvent("screenView",bundle);
        mFirebaseAnalytics.setCurrentScreen(this, "Informations", null);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        Button crash = (Button) findViewById(R.id.crash);
        crash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Bundle params = new Bundle();
                params.putString("screenName", "Informations");
                params.putString("eventCategory", "App");
                params.putString("eventAction", "Crash");
                params.putString("eventLabel", "Crash the app");
                mFirebaseAnalytics2.logEvent("CRASH", params);

                        throw new  RuntimeException("Crash Activity ");

            }
        });

        //App Name
        TextView appName = (TextView) findViewById(R.id.appName);
        appName.setText(appName.getText() + " " + getResources().getString(R.string.app_name) );

        TextView udid = (TextView) findViewById(R.id.udid);
        //appName.setText(appName.getText() + " " + Context.getSystemService(Context.TELEPHONY_SERVICE).getDeviceID() );
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_informations, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent i = new Intent(Informations.this, Basket.class);
                startActivity(i);
                return true;
            case R.id.action_legal:
                Intent j = new Intent(Informations.this, Legal.class);
                startActivity(j);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
