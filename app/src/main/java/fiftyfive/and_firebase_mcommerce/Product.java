package fiftyfive.and_firebase_mcommerce;

/**
 * Created by Francois on 28/07/2017.
 */

public class Product {

    private int color;
    private String name;
    private String desc;

    public Product(int color, String name, String desc) {
        this.color = color;
        this.name = name;
        this.desc = desc;
    }

    //...getters
    int getColor (){
        return color;
    }

    String getName(){
        return name;
    }

    String getDesc(){
        return desc;
    }
    //...setters

}
