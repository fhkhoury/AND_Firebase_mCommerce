package fiftyfive.and_firebase_mcommerce.models;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;

import java.sql.Ref;
import java.util.HashMap;
import java.util.Map;

import fiftyfive.and_firebase_mcommerce.Utils;

import static android.R.attr.author;
import static android.R.attr.key;
import static android.content.ContentValues.TAG;
import static fiftyfive.and_firebase_mcommerce.R.id.email;
import static fiftyfive.and_firebase_mcommerce.R.id.userAdress;
import static fiftyfive.and_firebase_mcommerce.R.id.userEmail;
import static fiftyfive.and_firebase_mcommerce.R.id.userName;

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

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        //result.put("uid", uid);
        result.put("profilePicUrl", profilePicUrl);
        result.put("name", name);
        result.put("mail", email);
        result.put("address", address);
        result.put("paymentInfos", paymentInfos);


        return result;
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
        DatabaseReference usersRef  = Utils.getDatabaseRoot().child("users").getRef();
        Log.i("DB", "userRefs:success");
        usersRef.child(uid).setValue(user);
        Log.i("DB", "Writing:success");
    }

    public static void updateAnonymouswithNewData(String uid, String mail){
        DatabaseReference usersRef  = Utils.getDatabaseNode(uid);
        String key = usersRef.push().getKey();
        Log.i("key; ", key);
        User user = new User("noProfilePic", "Not defined", mail, "Not defined", "Not defined");
        Map<String, Object> userValues = user.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, userValues);
        usersRef.updateChildren(childUpdates);
        Log.i("DB", "Update:success");
    }


    public static void updateAnonymoustoSignedUpAfterLinking(String uid, String mail){
        //Ecriture du mail renseigné dans le profil en BDD
        DatabaseReference userMailRef = Utils.getDatabaseRoot().child("users").child(uid).child("mail");
        userMailRef.setValue(mail);
        Log.i("Cool : ", "j'ai pu édire");


    }




}
