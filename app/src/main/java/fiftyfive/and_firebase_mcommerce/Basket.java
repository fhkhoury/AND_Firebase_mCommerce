package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import fiftyfive.and_firebase_mcommerce.models.Product;
import fiftyfive.and_firebase_mcommerce.adapters.ProductCartAdapter;

import static fiftyfive.and_firebase_mcommerce.R.id.listView;

/**
 * Created by Francois on 06/08/2017.
 */

public class Basket extends AppCompatActivity {

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);


        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ListView cartListView = (ListView) findViewById(listView);

        final HashMap<String, Product> cartList = generateCartList();

        ProductCartAdapter adapter = new ProductCartAdapter(cartList);
        cartListView.setAdapter(adapter);
        final Button connect = (Button) findViewById(R.id.checkoout);
        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(user==null){
                    startActivity(new Intent(Basket.this, Login.class));
                    finish();
                }
                else{
                    if(user.isAnonymous()){
                        startActivity(new Intent(Basket.this, Signup.class));
                        finish();
                    }
                    else{
                        startActivity(new Intent(Basket.this, Adresses.class));
                        finish();
                    }
                }
            }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_informations:
                Intent i = new Intent(Basket.this, Informations.class);
                startActivity(i);
                return true;
            case R.id.action_legal:
                Intent j = new Intent(Basket.this, Legal.class);
                startActivity(j);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private HashMap<String, Product> generateCartList() {
        //TODO: Load articles form Firebase Real-Time Database
        HashMap<String, Product> cartList = new HashMap<>();
        //cartList.add(new Product(Color.BLUE, "SONY Playstation 4 - 500 Go Slim", "- Plate-forme : PlayStation 4\n" +
        //        "- Edition : Slim 500Go\n" +
        //        "- Des couleurs riches et éclatantes avec les graphismes HDR d’une qualité exceptionnelle."));
        return cartList;
    }
}
