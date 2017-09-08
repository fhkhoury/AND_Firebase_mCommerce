package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import fiftyfive.and_firebase_mcommerce.models.Product;
import fiftyfive.and_firebase_mcommerce.views.ProductsListAdapter;

import static fiftyfive.and_firebase_mcommerce.R.id.listView;


public class Liste extends AppCompatActivity {

    String category = "Game";

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ListView listView = (ListView) findViewById(R.id.listView);



        final List<String> productIdList = getProductIdListFromCategory(category);

        final ArrayList<Product> productList = new ArrayList<>();
        DatabaseReference categoryNode = Utils.getDatabaseRoot().child("products").getRef();
        categoryNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                productList.clear();
                for (DataSnapshot postSnapshot: dataSnapshot.getChildren()) {
                    System.out.println(postSnapshot.getKey());

                    Product product = postSnapshot.getValue(Product.class);

                    for(int i=0; i<productIdList.size(); i++){
                        if(productIdList.get(i).equals(postSnapshot.getKey())){
                            productList.add(product);
                            System.out.println(product.getProductMiniature());
                            System.out.println(product.getName());
                            System.out.println(product.getBrand());
                            System.out.println(product.getPrice());
                        }

                    }
                    // here you can access to name property like university.name
                }
                ProductsListAdapter adapter = new ProductsListAdapter(Liste.this, productList);
                listView.setAdapter(adapter);
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

    /*ArrayList<Product> buildProductList(final List<String> productIdList){
        final ArrayList<Product> productList = new ArrayList<Product>();
        Query query;
                // Boucle pour chaque id produit de la categore, je récupère les détails du produit dans la base pour remplr le roduit

        for (int i = 0; i < productIdList.size(); i++) {
            query = getProductViaSku(productIdList.get(i))
            productList.add(getProductViaSku(productIdList.get(i)));

        }

        return productList;

    }*/


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

    /*private void generateProductList(String categoryName, final List<Product> productList){
        //final List<Product> productList = new ArrayList<Product>();

        //récupération de la liste des id produits de la categorie dans la basse
        final List<String> productIdList = new ArrayList<>();
        DatabaseReference categoryNode = Utils.getDatabaseRoot().child("categories").child(categoryName).getRef();

        ValueEventListener productFromCategoryListener = new ValueEventListener() {
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
        };
        categoryNode.addListenerForSingleValueEvent(productFromCategoryListener);

        // Boucle pour chaque id produit de la categore, je récupère les détails du produit dans la base pour remplr le roduit
        DatabaseReference productsNode = Utils.getDatabaseRoot().child("products").getRef();

        ValueEventListener productListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot productSnapshot: dataSnapshot.getChildren()){
                    Log.i("Product key : ", productSnapshot.getKey());
                    Product product = productSnapshot.getValue(Product.class);
                    for(int i= 0; i<productIdList.size(); i ++) {
                        Log.i("Product Id : ", productIdList.get(i));

                        if (productIdList.get(i).equals(productSnapshot.getKey()) ){
                            System.out.println("blabla");
                            productList.add(product);
                        }

                    }

                }
                //System.out.println(productList.size());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i("TAG", "loadPost:onCancelled", databaseError.toException());
            }
        };
        productsNode.addValueEventListener(productListener);


       //for (int j=0; j<productList.size(); f++)

        //return productList;

        /*productList.add(new Product(Color.BLUE, "SONY Playstation 4 - 500 Go Slim", "- Plate-forme : PlayStation 4\n" +
                "- Edition : Slim 500Go\n" +
                "- Des couleurs riches et éclatantes avec les graphismes HDR d’une qualité exceptionnelle."));
        //productList.add(Product.getProductFromSku(sku));
        productList.add(new Product(Color.BLUE, "SONY Playstation 4 Pro", "- Plate-forme: PlayStation 4\n" +
                "- Edition: Pro\n" +
                "- La PlayStation la plus puissante jamais conçue."));
        productList.add(new Product(Color.GREEN, "MICROSOFT - Console Xbox One (ancien modèle)", "Inclus avec le produit :\n" +
                "- 1 manette sans fil\n" +
                "- 1 micro casque filaire\n" +
                "- 1 bloc d'alimentation"));
        productList.add(new Product(Color.GREEN, "MICROSOFT - Xbox One X", "La XBOX One X sortira le 7 novembre 2017\n" +
                "- Plongez dans des univers de jeux en qualité 4K et laissez-vous entraîner par des images ultra-réalistes en 2160p\n" +
                "- Ne ratez pas une image grâce à 326 Go/s de bande passante.\n" +
                "- 6 téraflops de puissance de traitement graphique, pour des jeux plus performants que jamais."));
        productList.add(new Product(Color.RED, "NINTENDO - Switch avec Joy-Con", "Important : du fait des quantités limitées nous ne pouvons autoriser qu'une précommande par client et par adresse.\n" +
                "Contenu :\n" +
                "- Console Nintendo Switch\n" +
                "- Manette Joy-Con droite rouge néon et manette Joy-Con gauche bleu néon\n" +
                "- support Joy-Con"));
        productList.add(new Product(Color.RED, "NINTENDO - Switch avec paire de Joy-Con", "Important : du fait des quantités limitées nous ne pouvons autoriser qu'une précommande par client et par adresse.\n" +
                "Contenu :\n" +
                "- Console Nintendo Switch\n" +
                "- Manette Joy-Con droite grise et manette Joy-Con gauche grise"));
        return productList;
    }*/

}
