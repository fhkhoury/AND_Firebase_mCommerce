package fiftyfive.and_firebase_mcommerce;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Francois on 07/08/2017.
 */

public class Authenticate {


    public boolean checkAuthenticationStatus (){
        FirebaseAuth auth = FirebaseAuth.getInstance();
        boolean authenticationStatus;
        if (auth.getCurrentUser() != null) {
            authenticationStatus = true;
        } else {
            authenticationStatus = false;

        }
        return authenticationStatus;
    }




}


