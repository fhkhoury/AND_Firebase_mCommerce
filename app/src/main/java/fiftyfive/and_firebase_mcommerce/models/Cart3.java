package fiftyfive.and_firebase_mcommerce.models;

import java.util.List;
import java.util.Map;

/**
 * Created by Francois on 20/09/2017.
 */

public class Cart3 {

    //tring userId;
    int nbOfArticles;
    //Double subTotal;
    List productList;

    public Cart3(){

    }

    public Cart3(String id, List products){
        //this.userId = id;
        this.productList = products;
        this.nbOfArticles = products.size();

    }

    public List getProductList(){
        return this.productList;
    }

    public int getNbOfArticles(){
        return this.nbOfArticles;
    }

}
