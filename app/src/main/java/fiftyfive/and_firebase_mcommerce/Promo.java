package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fiftyfive.and_firebase_mcommerce.models.Product;

import static fiftyfive.and_firebase_mcommerce.R.id.listView;

/**
 * Created by Francois on 03/08/2017.
 */

public class Promo extends AppCompatActivity{

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ListView bijouxPromoListView = (ListView) findViewById(listView);

        List<Product> bijouxPromoList = generateBijouxPromoList();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_promo, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent i = new Intent(Promo.this, Basket.class);
                startActivity(i);
                return true;
            case R.id.action_informations:
                Intent j = new Intent(Promo.this, Informations.class);
                startActivity(j);
                return true;
            case R.id.action_legal:
                Intent k = new Intent(Promo.this, Legal.class);
                startActivity(k);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Product> generateBijouxPromoList(){
        List<Product> promoBijouxList = new ArrayList<Product>();
        /*promoBijouxList.add(new Product(Color.YELLOW, "MAUBOUSSIN - Pendentif Sentiments", "Bla bla bla"));
        promoBijouxList.add(new Product(Color.YELLOW, "CARTIER - Bague Panthère", "Bla bla bla"));
        promoBijouxList.add(new Product(Color.YELLOW, "POIRAY - Monte intemporelle", "Bla bla bla"));*/
        return promoBijouxList;
    }
}
