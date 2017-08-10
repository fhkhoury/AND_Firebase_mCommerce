package fiftyfive.and_firebase_mcommerce;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import static android.R.attr.id;
import static fiftyfive.and_firebase_mcommerce.R.id.promoBanner;


public class HomePage extends AppCompatActivity {

    //private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    // Choose an arbitrary request code value
    //private static final int RC_SIGN_IN = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        //get current user
        final FirebaseUser user = auth.getCurrentUser();



        ImageButton promoBanner = (ImageButton) findViewById(R.id.promoBanner);
        promoBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goto Promo
                Intent i = new Intent(HomePage.this, Promo.class);
                startActivity(i);
            }
        });


        ImageButton consoles = (ImageButton) findViewById(R.id.consoles);
        consoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, Liste.class);
                startActivity(i);
            }
        });


        final Button connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("TAG;", "Bouton clickqué");
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    Log.i("TAG:", "User non connecté");
                    startActivity(new Intent(HomePage.this, Login.class));
                    finish();
                }
                else {
                    // user is already logged
                    // launch profile activity
                    Log.i("TAG:", "User connecté");
                    startActivity(new Intent(HomePage.this, Profile.class));
                    finish();
                }
            }
        });


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
                Intent i = new Intent(HomePage.this, Cart.class);
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
