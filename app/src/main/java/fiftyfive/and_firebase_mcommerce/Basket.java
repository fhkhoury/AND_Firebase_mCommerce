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

import com.google.firebase.analytics.FirebaseAnalytics;
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

        final FirebaseAnalytics mFirebaseAnalytics2 = FirebaseAnalytics.getInstance(this);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);

        Bundle product1 = new Bundle();
        product1.putString(FirebaseAnalytics.Param.ITEM_ID, "ProductId 123");
        product1.putString(FirebaseAnalytics.Param.ITEM_NAME, "ProductName 123");
        product1.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "ProductCategory 123");
        product1.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "ProductVariant 123");
        product1.putString(FirebaseAnalytics.Param.ITEM_BRAND, "ProductBrand 123");
        product1.putDouble(FirebaseAnalytics.Param.PRICE, 12.34 );
        product1.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
        product1.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

        ArrayList items = new ArrayList();
        items.add(product1);

        Bundle ecommerceBundle = new Bundle();
        ecommerceBundle.putParcelableArrayList( "items", items );
        ecommerceBundle.putLong( FirebaseAnalytics.Param.CHECKOUT_STEP, 1 );
        ecommerceBundle.putString( FirebaseAnalytics.Param.CHECKOUT_OPTION, "" );
        ecommerceBundle.putString("screenName","Basket");
        ecommerceBundle.putString("userId", "1111111111");
        ecommerceBundle.putString("pageTopCategory", "Checkout");
        ecommerceBundle.putString("pageCategory", "Basket");
        ecommerceBundle.putString("pageSubCategory", "");
        ecommerceBundle.putString("pageType", "Checkout");
        ecommerceBundle.putString("loginStatus", "Logged");
        ecommerceBundle.putString("previousScreen", "Login");

        mFirebaseAnalytics.logEvent( FirebaseAnalytics.Event.BEGIN_CHECKOUT, ecommerceBundle );
        mFirebaseAnalytics.setCurrentScreen(this, "Adresses", null);

        //get firebase auth instance
        auth = FirebaseAuth.getInstance();
        final FirebaseUser user = auth.getCurrentUser();

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ListView cartListView = (ListView) findViewById(listView);

        final HashMap<String, Product> cartList = generateCartList();

        ProductCartAdapter adapter = new ProductCartAdapter(cartList);
        cartListView.setAdapter(adapter);

        final Button removeFromCart = (Button) findViewById(R.id.removeFromCart);
        removeFromCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle product1 = new Bundle();
                product1.putString(FirebaseAnalytics.Param.ITEM_ID, "ProductId 123");
                product1.putString(FirebaseAnalytics.Param.ITEM_NAME, "ProductName 123");
                product1.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, "ProductCategory 123");
                product1.putString(FirebaseAnalytics.Param.ITEM_VARIANT, "ProductVariant 123");
                product1.putString(FirebaseAnalytics.Param.ITEM_BRAND, "ProductBrand 123");
                product1.putDouble(FirebaseAnalytics.Param.PRICE, 12.34 );
                product1.putString(FirebaseAnalytics.Param.CURRENCY, "EUR");
                product1.putLong(FirebaseAnalytics.Param.QUANTITY, 1);

                ArrayList items = new ArrayList();
                items.add(product1);

                Bundle ecommerceBundle = new Bundle();
                ecommerceBundle.putParcelableArrayList( "items", items );
                ecommerceBundle.putString("screenName","Detail");
                ecommerceBundle.putString("eventCategory", "Enhanced Ecommerce");
                ecommerceBundle.putString("eventAction", "REMOVE_FROM_CART");
                ecommerceBundle.putString("eventLabel", "More information in ecommerce reports");

                mFirebaseAnalytics2.logEvent( FirebaseAnalytics.Event.REMOVE_FROM_CART, ecommerceBundle );
            }
        });

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
