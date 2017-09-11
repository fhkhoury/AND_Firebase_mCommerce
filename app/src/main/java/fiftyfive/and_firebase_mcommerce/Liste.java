package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fiftyfive.and_firebase_mcommerce.models.Product;
import fiftyfive.and_firebase_mcommerce.adapters.ProductListAdapter;


public class Liste extends AppCompatActivity {


    private FirebaseAnalytics mFirebaseAnalytics;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ListView listView = (ListView) findViewById(R.id.listView);

        //Get value information about selected product in the list
        final String category = getIntent().getStringExtra("SELECTED_CATEGORY_ID");

        // Obtain the FirebaseAnalytics instance.
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);


        final List<String> productIdList = getProductIdListFromCategory(category);

        final ArrayList<Product> productList = new ArrayList<>();

        final DatabaseReference categoryNode = Utils.getDatabaseRoot().child("products").getRef();
        categoryNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {

                    Product product = postSnapshot.getValue(Product.class);

                    // Boucle pour chaque id produit de la categore, je récupère les détails du produit dans la base pour remplr les produits de ma liste
                    for(int i=0; i<productIdList.size(); i++){
                        if(productIdList.get(i).equals(postSnapshot.getKey())){
                            productList.add(product);
                            System.out.println(postSnapshot.getKey());
                            System.out.println(product.getName());
                        }

                    }
                }

                //Send a view_item_list event with pomotional info only if user selected Jewelry category

                    Bundle bundle = new Bundle();
                    bundle.putString(FirebaseAnalytics.Param.ITEM_CATEGORY, category);
                    Bundle ecommerce = new Bundle();
                    ecommerce.putString("currencyCode", "EUR");

                    Bundle impressions = new Bundle();
                    Bundle impression = new Bundle();

                    for(int i=0; i<productList.size(); i++){
                        impression.putString("id", String.valueOf(i+1));
                        impression.putString("name", productList.get(i).getName());
                        impression.putString("category", productList.get(i).getCategory());
                        impression.putString("brand", productList.get(i).getBrand() );
                        impression.putString("variant", productList.get(i).getVariant());
                        //impression.putDouble("price", productList.get(i).getPrice());
                        impression.putString("list", "bla");
                        impression.putInt("position", i+1);


                        impressions.putBundle(String.valueOf(i+1), impression);
                    }

                    ecommerce.putBundle("impressions", impressions);
                    bundle.putBundle("ecommerce", ecommerce);
                    mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.VIEW_ITEM_LIST, bundle);

                ProductListAdapter adapter = new ProductListAdapter(Liste.this, productList);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
                    {
                        Product selectedProduct = (Product) listView.getItemAtPosition(itemPosition);

                        Intent i = new Intent(Liste.this, Detail.class);
                        i.putExtra("SELECTED_PRODUCT_MINIATURE", selectedProduct.getProductMiniature());
                        i.putExtra("SELECTED_PRODUCT_NAME", selectedProduct.getName());
                        i.putExtra("SELECTED_PRODUCT_BRAND", selectedProduct.getBrand());
                        i.putExtra("SELECTED_PRODUCT_PRICE", selectedProduct.getPrice());
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_liste, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                Intent i = new Intent(Liste.this, Basket.class);
                startActivity(i);
                return true;
            case R.id.action_informations:
                Intent j = new Intent(Liste.this, Informations.class);
                startActivity(j);
                return true;
            case R.id.action_legal:
                Intent k = new Intent(Liste.this, Legal.class);
                startActivity(k);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //récupération de la liste des id produits de la categorie dans le noeud "categories" de la base
    List<String> getProductIdListFromCategory(String categoryName){
        final List<String> productIdList = new ArrayList<String>();

        DatabaseReference categoryNode = Utils.getDatabaseRoot().child("categories").child(categoryName).getRef();
        categoryNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot productSnapshot: dataSnapshot.getChildren()){
                    Log.i("productValue :", productSnapshot.getValue().toString());
                    productIdList.add(productSnapshot.getValue().toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        return productIdList;
    }


    Query getProductViaSku (String sku){
        DatabaseReference productsNode = Utils.getDatabaseRoot().child("products").getRef();
        Query queryRef = productsNode.orderByKey().equalTo(sku);
        ValueEventListener productEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Product product = dataSnapshot.getValue(Product.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        queryRef.addValueEventListener(productEventListener);
        return queryRef;
    }

}
