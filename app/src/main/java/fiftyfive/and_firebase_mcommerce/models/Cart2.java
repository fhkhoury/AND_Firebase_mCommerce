package fiftyfive.and_firebase_mcommerce.models;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fiftyfive.and_firebase_mcommerce.Utils;

/**
 * Created by Francois on 15/09/2017.
 */

public class Cart2 {

    public String id;
    public HashMap<String, Object> productList;



    public Cart2(){
        // Constructor to get cart

    }

    public Cart2(String id, Map products){
        // Constructor to get cart

    }

    public HashMap<String, Object> getProductList(){
        return productList;
    }

    public  void setProductList(HashMap<String, Object> cartProducts){
        this.productList = cartProducts;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();


        return result;
    }

    public static void createNewCartInDB (String uid){
        DatabaseReference cartsNode = Utils.getDatabaseRoot().child("carts");
        cartsNode.push().setValue("user_id", uid);

        System.out.println("le panier de " + uid + " a bien été créé.");
    }

}
