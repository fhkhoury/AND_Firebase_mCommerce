package fiftyfive.and_firebase_mcommerce.models;

import android.provider.ContactsContract;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

import fiftyfive.and_firebase_mcommerce.Utils;


/**
 * Created by Francois on 31/08/2017.
 */

public class Cart {


    public Map<String, Map> articles;
    public Map<String, Map> productSku;
    public Map<String, Integer> quantity;
    public String currency;
    public int nbOfArticles;
    public double subTotal;

    public Cart (){

        // Constructor to get cart
    }

    public Cart(String currency, int nbOfArticles, double subTotal) {
        //Constructor to create new cart entry in the DB
        this.articles.put("articles", new HashMap());
        this.currency = currency;
        this.nbOfArticles = nbOfArticles;
        this.subTotal = subTotal;
    }


    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("articles", articles);
        //result.put(p, productSku)
        result.put("quantity", quantity);
        result.put("currency", currency);
        result.put("nbOfArticles", nbOfArticles);
        result.put("subTotal", subTotal);


        return result;
    }

    public String getCurrency(){
        return currency;
    }


    public void decrementNbOfArticles(){
        this.nbOfArticles = nbOfArticles - 1;
    }

    public void incrementNbOfArticles(String uid){
        this.nbOfArticles = nbOfArticles  + 1;
        final DatabaseReference cartNode = Utils.getDatabaseRoot().child("carts").child(uid);
        cartNode.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Long actualQuantity = ((Long) dataSnapshot.child("nbOfArticles").getValue());
                cartNode.child("nbOfArticles").setValue(actualQuantity + 1);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }

    public void updateSubTotal(double value){
        this.subTotal = this.subTotal + value;
    }



    public static void addToCart(final String uid, final String sku, final Double value){

        //Check if cart already exists
        final DatabaseReference cartNode = Utils.getDatabaseRoot().child("carts").child(uid);
        final ValueEventListener cartEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (!dataSnapshot.exists()) {
                    Log.i("TAG", "Cart does not exist!");
                    //createCart
                    //createNewCart(uid);

                } else {
                    Log.i("TAG", "Cart exists!");
                    //Check if product is not already in the cart
                    if (!dataSnapshot.child("articles").child(sku).exists()){
                        //product is not in the cart, we will create it
                        //create the product in the cart and update the subTotal
                        //TODO : Create the product in the cart
                        //updateSubTotal(value);

                    }
                    else{
                        //Product is in the cart. Increment quantity and upate the subTOtal
                        Integer previousQuantity = Integer.parseInt(dataSnapshot.child("articles").child(sku).child("quantity").getValue().toString());
                        cartNode.child("articles").child(sku).child("quantity").setValue((previousQuantity + 1));
                        //updateSubTotal(value);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        cartNode.addListenerForSingleValueEvent(cartEventListener);
    }


    public static Cart checkIfCartExists(final String uid){
        final Cart[] cart = {new Cart()};
        DatabaseReference cartsNode = Utils.getDatabaseRoot().child("carts").child(uid);
        cartsNode.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(!dataSnapshot.exists()){
                    //Si le panier n'existe pas alors on le créé en base
                    createNewCartInDB(uid);
                    System.out.println("le panier de " + uid + " a bien été créé.");
                    cart[0] = dataSnapshot.getValue(Cart.class);
                    return;

                }
                else {
                    //if cart exist alors on le récupère
                    System.out.println("Le panier existe");
                    cart[0] = dataSnapshot.getValue(Cart.class);
                    System.out.println(cart[0].getCurrency());
                    return;

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {


            }
        });

        return cart[0];

    }


    /*public boolean checkIfCartExists(String uid){
        final boolean[] result = {false};
        Query cartCheck = Utils.getDatabaseRoot().child("carts").equalTo(uid);
        cartCheck.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                result[0] = true;
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                result[0] = false;
            }
        });

        return result[0];
    }*/

    // Check if product exists in the cart
    public  boolean checkIfProductExistInTheCart(String uid, String sku){
        DatabaseReference productNode = Utils.getDatabaseRoot().child("carts").child(uid).child("Articles").child(sku).getRef();
        Log.i("productNode: ", productNode.toString());
        if(productNode.toString() == null){
            Log.i("Info : ", "Le produit "+sku+" n'existe pas dans le panier de "+uid+".");
            return false;
        }
        else{
            Log.i("Info : ", "Le produit "+sku+" existe dans le panier de "+uid+".");
            return true;
        }
    }

    public static void createNewCartInDB (String uid){
        DatabaseReference cartsNode = Utils.getDatabaseRoot().child("carts");
        cartsNode.child(uid).child("currency").setValue("EUR");
        cartsNode.child(uid).child("nbOfArticles").setValue(0);
        cartsNode.child(uid).child("subTotal").setValue(0.00);
        Log.i("Cart: ", "Cart of user "+ uid +" created in DB");
    }

    // Create the product in the cart
    public void createProductInCart(String uid, String sku){

        //this.articles.put(sku, this.productSku);
        //this.nbOfArticles = this.nbOfArticles+1;
        //this.subTotal=this.subTotal;

        //Update DB
        DatabaseReference cartNode = Utils.getDatabaseRoot().child("carts").child(uid);
        cartNode.child("articles").child(sku).child("quantity").setValue(1);
    }



}
