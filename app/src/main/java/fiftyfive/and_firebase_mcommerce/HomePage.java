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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomePage extends AppCompatActivity {

    //private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;


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



        ImageButton promoBanner = (ImageButton) findViewById(R.id.promoBanner);
        promoBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Goto Promo
                Intent i = new Intent(HomePage.this, Promo.class);
                startActivity(i);
                finish();
            }
        });


        ImageButton consoles = (ImageButton) findViewById(R.id.consoles);
        consoles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HomePage.this, Liste.class);
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
