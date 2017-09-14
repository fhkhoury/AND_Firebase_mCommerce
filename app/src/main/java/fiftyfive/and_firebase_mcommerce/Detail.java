package fiftyfive.and_firebase_mcommerce;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.net.URL;

import fiftyfive.and_firebase_mcommerce.models.Cart;
import fiftyfive.and_firebase_mcommerce.models.Product;

import static fiftyfive.and_firebase_mcommerce.R.id.parent;
import static fiftyfive.and_firebase_mcommerce.R.id.productBrand;
import static fiftyfive.and_firebase_mcommerce.R.id.productImage;
import static fiftyfive.and_firebase_mcommerce.R.id.productName;
import static fiftyfive.and_firebase_mcommerce.R.id.productPrice;
import static fiftyfive.and_firebase_mcommerce.R.id.udid;

public class Detail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final Context context = getApplicationContext();

        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        //Get value information about selected product in the list
        final String productSku = getIntent().getStringExtra("SELECTED_PRODUCT_SKU");

        DatabaseReference productNode = Utils.getDatabaseRoot().child("products").child(productSku);
        ValueEventListener productEventLister = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Product product = dataSnapshot.getValue(Product.class);

                TextView brandOfProduct = (TextView) findViewById(productBrand);
                brandOfProduct.setText(product.getBrand());

                TextView NameOfProduct= (TextView) findViewById(productName);
                NameOfProduct.setText(product.getName());

                TextView PriceOfProduct = (TextView) findViewById(productPrice);
                PriceOfProduct.setText(String.valueOf(product.getPrice())+" €");

                ImageView ImageOfProduct = (ImageView) findViewById(productImage);
                Picasso.with(context).load(product.getProductMiniature()).into(ImageOfProduct);

                //TextView DescriptionOfProduct= (TextView) findViewById(R.id.productDescription);
                //DescriptionOfProduct.setText(product.getDesc().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        };
        productNode.addListenerForSingleValueEvent(productEventLister);





        //Définition du toast
        CharSequence text = "Added to cart !";
        int duration = Toast.LENGTH_LONG;
        final Toast toast = Toast.makeText(context, text, duration);



        final Button addToCart = (Button) findViewById(R.id.addToCart);
        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check cart existe
                Cart myCart = Cart.checkIfCartExists(userId);
                System.out.println("Currrency = " + myCart.getCurrency());
                    //Si oui, check si produit déjà dans panier
                    /*if(myCart.checkIfProductExistInTheCart(userId, productSku)){
                        //Si oui incrémenter la quantité de 1

                    }
                    else{
                        //Si non ajoutet un produit enfant dans le noeud article
                    }*/



                    //Si non, créer un noeud 'uid' dans le noeud "carts" de la bdd
                    //myCart.createNewCartInDB(userId);
                    //Créer le prodle produit au panier
                    //myCart.createProductInCart(userId, productSku);




                //Incrémenter le nbOfArticles de 1
                //myCart.incrementNbOfArticles(userId);


                //Update le sub-total
                //myCart.updateSubTotal(product.getPrice());




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
                Intent i = new Intent(Detail.this, Basket.class);
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





    // Create the product in the cart
    public static void createProductInCart(String uid, String sku){
        //Product newProduct = new Product (sku);
        DatabaseReference articlesNode = Utils.getDatabaseRoot().child("carts").child(uid).child("Articles").getRef();
        //articlesNode.setValue(newProduct);
        DatabaseReference productNode = Utils.getDatabaseRoot().child("carts").child(uid).child("Articles").child(sku).getRef();
        //Map<String, Object> quantityValue = this.toMap();
        //Map<String, Object> childUpdates = new HashMap<>();

        //childUpdates.
        //productNode.updateChildren()
        //productNode.setValue()
        //TODO : A FAIRE
    }

}
