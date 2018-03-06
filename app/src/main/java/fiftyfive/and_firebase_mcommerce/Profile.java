package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


/**
 * Created by Francois on 07/08/2017.
 */

public class Profile extends AppCompatActivity {

    private FirebaseAuth.AuthStateListener authListener;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        final FirebaseAnalytics mFirebaseAnalytics2 = FirebaseAnalytics.getInstance(this);
        Bundle bundle=new Bundle();
        bundle.putString("screenName","Profile");
        bundle.putString("userId", "1111111111");
        bundle.putString("pageTopCategory", "Profile");
        bundle.putString("pageCategory", "");
        bundle.putString("pageSubCategory", "");
        bundle.putString("pageType", "User");
        bundle.putString("loginStatus", "Logged");
        bundle.putString("previousScreen", "Login");

        mFirebaseAnalytics.logEvent("screenView",bundle);
        mFirebaseAnalytics.setCurrentScreen(this, "Profile", null);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        //get current user
        final FirebaseUser user = auth.getCurrentUser();

        //ImageView userPic = (ImageView) findViewById(R.id.userPicture);
        TextView userName = (TextView) findViewById(R.id.userName);
        TextView userEmail = (TextView) findViewById(R.id.userEmail);

        if (user != null) {
            // User is signed in
            String name = user.getDisplayName();
            String mail = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            userName.setText(name);
            userEmail.setText(mail);
            //userPic.setImageURI(photoUrl);

        } else {
            // No user is signed in
        }


        //TODO: Mettre ici le code pour récupérer la photo de l'utilisateur
        //TODO: Mettre ici le code pour récupérer le nom de l'utilisateur


        //TODO: Mettre ici le code pour récupérer le mail de l'utilisateur

        //ListView userOrders = (ListView) findViewById(R.id.userOrderList);
        //TODO: Mettre ici le code pour récupérer le nom de l'utilisateur

        Button disconnectButton = (Button) findViewById(R.id.disconnect);
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Déconnecter l'utilisateur et revenir à la HP après
                if (view.getId() == R.id.disconnect) {
                    auth.signOut();
                    Bundle params = new Bundle();
                    params.putString("screenName", "Profile");
                    params.putString("eventCategory", "User");
                    params.putString("eventAction", "Logout");
                    params.putString("eventLabel", "");
                    mFirebaseAnalytics2.logEvent("LOGOUT", params );
                    startActivity(new Intent(Profile.this, HomePage.class));
                    finish();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_profile, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_cart:
                Intent i = new Intent(Profile.this, Basket.class);
                startActivity(i);
                return true;
            case R.id.action_informations:
                Intent j = new Intent(Profile.this, Informations.class);
                startActivity(j);
                return true;
            case R.id.action_legal:
                Intent k = new Intent(Profile.this, Legal.class);
                startActivity(k);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
