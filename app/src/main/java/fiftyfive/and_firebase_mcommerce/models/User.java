package fiftyfive.and_firebase_mcommerce.models;

import android.util.Log;

import com.google.firebase.database.DatabaseReference;

import fiftyfive.and_firebase_mcommerce.Utils;

/**
 * Created by Francois on 29/08/2017.
 */

public class User {

    public String uid;
    public String profilePicUrl;
    public String mail;
    public String name;
    public String address;
    public String paymentInfos;
    public String[] orders;


    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String userProfilPic, String userName, String userEmail, String userAddress, String userPaymentInfos) {
        // Constructeur required to write on db Anonymous User
        this.profilePicUrl= userProfilPic;
        this.name = userName;
        this.mail = userEmail;
        this.address = userAddress;
        this.paymentInfos = userPaymentInfos;
    }

    public static void createAnonymousUser(String uid) {
        User user = new User("noProfilePic", "Anonymous", "Unregistered", "Not defined", "Not defined");
        DatabaseReference usersRef  = Utils.getDatabaseNode("users");
        Log.i("DB", "userRefs:success");
        usersRef.child(uid).setValue(user);
        Log.i("DB", "Writing:success");
    }

    public static void createNewUser(String uid, String mail){
        User user = new User("noProfilePic", "Not defined", mail, "Not defined", "Not defined");
        DatabaseReference usersRef  = Utils.getDatabaseNode("users");
        Log.i("DB", "userRefs:success");
        usersRef.child(uid).setValue(user);
        Log.i("DB", "Writing:success");
    }

}
