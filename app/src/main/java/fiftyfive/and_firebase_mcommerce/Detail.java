package fiftyfive.and_firebase_mcommerce;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import fiftyfive.and_firebase_mcommerce.R;

import static fiftyfive.and_firebase_mcommerce.R.id.productImage;

public class Detail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Context context = getApplicationContext();

        //Get value information about selected product in the list
        int productColor = getIntent().getIntExtra("SELECTED_PRODUCT_COLOR", Color.TRANSPARENT);
        String productName = getIntent().getStringExtra("SELECTED_PRODUCT_NAME");
        String productDesc = getIntent().getStringExtra("SELECTED_PRODUCT_DESC");

        ImageView ImageOfProduct = (ImageView) findViewById(productImage);
        ImageOfProduct.setBackgroundColor(productColor);

        TextView NameOfProduct= (TextView) findViewById(R.id.productName);
        NameOfProduct.setText(productName);

        TextView DescriptionOfProduct= (TextView) findViewById(R.id.productDescription);
        DescriptionOfProduct.setText(productDesc);


        //Définition du toast
        CharSequence text = "Added to cart !";
        int duration = Toast.LENGTH_LONG;
        final Toast toast = Toast.makeText(context, text, duration);





        Button call = (Button) findViewById(R.id.addToCart);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO: Mettre ici le code d'ajout du produit à Firebase RTDB
                toast.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent i = new Intent(Detail.this, Cart.class);
                startActivity(i);
                return true;
            case R.id.action_informations:
                Intent j = new Intent(Detail.this, Informations.class);
                startActivity(j);
                return true;
            case R.id.action_legal:
                Intent k = new Intent(Detail.this, Legal.class);
                startActivity(k);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
