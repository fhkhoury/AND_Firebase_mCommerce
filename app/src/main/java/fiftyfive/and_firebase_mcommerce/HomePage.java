package fiftyfive.and_firebase_mcommerce;

import android.app.Activity;
import android.content.Intent;
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

import static android.R.attr.id;


public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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


        Button connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Authenticate authenticate = new Authenticate();
                boolean authenticationStatus = authenticate.checkAuthenticationStatus();
                if (authenticationStatus){
                    Log.i("INFO CONNEXION", "Connexion OK");
                    Intent i = new Intent(HomePage.this, Profile.class);
                    startActivity(i);
                }
                else{
                    Log.i("INFO CONNEXION", "Connexion KO");
                    // Lance le process d'authentification
                    startActivityForResult(
                            // Get an instance of AuthUI based on the default app
                            AuthUI.getInstance().createSignInIntentBuilder().build(),
                            123);
                    //Redirection to Profile
                    startActivity(new Intent (HomePage.this, Profile.class));
                }
                //Intent j = new Intent(HomePage.this, Liste.class);
                //startActivity(j);
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
