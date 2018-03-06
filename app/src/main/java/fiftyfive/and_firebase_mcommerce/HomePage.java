package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    //private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;
    private FirebaseAnalytics mFirebaseAnalytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();

        final Intent j;

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        final FirebaseAnalytics mFirebaseAnalytics2 = FirebaseAnalytics.getInstance(this);

        ImageButton promoBanner = (ImageButton) findViewById(R.id.promoBanner);
        promoBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goto Promo
                Bundle promotion1 = new Bundle();
                promotion1.putString(FirebaseAnalytics.Param.ITEM_ID, "123");
                promotion1.putString(FirebaseAnalytics.Param.ITEM_NAME, "Solde sur les bijoux");
                promotion1.putString(FirebaseAnalytics.Param.CREATIVE_NAME, "HP_banner_solde_bijoux.jpg");
                promotion1.putString(FirebaseAnalytics.Param.CREATIVE_SLOT, "1");

                ArrayList promotions = new ArrayList();
                promotions.add(promotion1);

                Bundle ecommerceBundle = new Bundle();
                ecommerceBundle.putParcelableArrayList("promotions", promotions);
                ecommerceBundle.putString("screenName", "HomePage");
                ecommerceBundle.putString("eventCategory", "Enhanced Ecommerce");
                ecommerceBundle.putString("eventAction", "SELECT_PROMO");
                ecommerceBundle.putString("eventLabel", "More information in ecommerce reports");
                ecommerceBundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "HP Banner Solde Bijoux");
                ecommerceBundle.putString(FirebaseAnalytics.Param.ITEM_ID, "123");

                mFirebaseAnalytics2.logEvent("SELECT_PROMO", ecommerceBundle);

                Intent i = new Intent(HomePage.this, Liste.class);
                i.putExtra("SELECTED_CATEGORY_ID", "Jewelry");
                startActivity(i);
                finish();
            }
        });


        ImageButton consoles = (ImageButton) findViewById(R.id.consoles);
        consoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, Liste.class);
                i.putExtra("SELECTED_CATEGORY_ID", "Video Games");
                startActivity(i);
                finish();
            }
        });


        final Button connect = (Button) findViewById(R.id.connect);
        if(user == null){
            Log.i("TAG:", "User non connecté");
            connect.setText("Login");
        }
        else{
            if(user.isAnonymous()){
                Log.i("TAG:", "User Aonymous");
                Log.i("UID: ", user.getUid());
                connect.setText("Signup");
            }
            else{
                Log.i("TAG:", "User Connecté");
                Log.i("UID: ", user.getUid());
                connect.setText("Profile");
            }
        }

        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG;", "Bouton clickqué");

                Bundle params = new Bundle();
                params.putString("screenName", "HomePage");
                params.putString("eventCategory", "User");
                params.putString("eventAction", "Sign up");
                params.putString("eventLabel", "");
                params.putString(FirebaseAnalytics.Param.SIGN_UP_METHOD, "Email");

                mFirebaseAnalytics2.logEvent(FirebaseAnalytics.Event.SIGN_UP, params );

                if(user==null){
                    startActivity(new Intent(HomePage.this, Login.class));
                    finish();
                }
                else{
                    if(user.isAnonymous()){
                        startActivity(new Intent(HomePage.this, Signup.class));
                        finish();
                    }
                    else{
                        startActivity(new Intent(HomePage.this, Profile.class));
                        finish();
                    }
                }
            }
        });

        //initialisation firebaseAnalytics
        Bundle promotion1 = new Bundle();
        promotion1.putString(FirebaseAnalytics.Param.ITEM_ID, "123");
        promotion1.putString(FirebaseAnalytics.Param.ITEM_NAME, "Solde sur les bijoux");
        promotion1.putString(FirebaseAnalytics.Param.CREATIVE_NAME, "HP_banner_solde_bijoux.jpg");
        promotion1.putString(FirebaseAnalytics.Param.CREATIVE_SLOT, "1");

        ArrayList promotions = new ArrayList();
        promotions.add(promotion1);

        Bundle ecommerceBundle = new Bundle();
        ecommerceBundle.putParcelableArrayList("promotions", promotions);
        ecommerceBundle.putString("screenName","HomePage");
        ecommerceBundle.putString("userId", "1111111111");
        ecommerceBundle.putString("pageTopCategory", "HomePage");
        ecommerceBundle.putString("pageCategory", "");
        ecommerceBundle.putString("pageSubCategory", "");
        ecommerceBundle.putString("pageType", "HomePage");
        ecommerceBundle.putString("loginStatus", "Logged");
        ecommerceBundle.putString("previousScreen", "SplashScreen");

        mFirebaseAnalytics.logEvent("VIEW_PROMO_LIST", ecommerceBundle);
        mFirebaseAnalytics.setCurrentScreen(this, "HomePage", null);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_home_page, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent i = new Intent(HomePage.this, Basket.class);
                startActivity(i);
                return true;
            case R.id.action_informations:
                Intent j = new Intent(HomePage.this, Informations.class);
                startActivity(j);
                return true;
            case R.id.action_legal:
                Intent k = new Intent(HomePage.this, Legal.class);
                startActivity(k);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
