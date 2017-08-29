package fiftyfive.and_firebase_mcommerce;

import android.net.Uri;
import android.util.Log;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Enumeration;

import static android.R.attr.x;

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
        this.uid = uid;
        this.profilePicUrl= userProfilPic;
        this.name = userName;
        this.mail = userEmail;
        this.address = userAddress;
        this.paymentInfos = userPaymentInfos;
    }

    public static void writeNewUser(String uid) {
        User user = new User("noProfilePic", "Anonymous", "Unregistered", "Not defined", "Not defined");
        DatabaseReference usersRef  = Utils.getDatabaseNode("users");
        Log.i("DB", "userRefs:success");
        usersRef.child(uid).setValue(user);
        Log.i("DB", "Writing:success");
    }

}
