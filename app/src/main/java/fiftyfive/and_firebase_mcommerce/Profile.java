package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Francois on 07/08/2017.
 */

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        ImageView userPic = (ImageView) findViewById(R.id.userPicture);
        //TODO: Mettre ici le code pour récupérer la photo de l'utilisateur

        TextView userName = (TextView) findViewById(R.id.userName);
        //TODO: Mettre ici le code pour récupérer le nom de l'utilisateur

        TextView userEmail = (TextView) findViewById(R.id.userEmail);
        //TODO: Mettre ici le code pour récupérer le mail de l'utilisateur

        ListView userOrders = (ListView) findViewById(R.id.userOrderList);
        //TODO: Mettre ici le code pour récupérer le nom de l'utilisateur

        Button disconnectButton = (Button) findViewById(R.id.disconnect);
        disconnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Mettre ici le code pour déconnecter l'utilisateur

                //Revenir à la HP
                Intent i = new Intent(Profile.this, HomePage.class);
                startActivity(i);
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
                Intent i = new Intent(Profile.this, Cart.class);
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
