package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    final String PREFS_NAME = "MyPrefsFile";

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);

        //checke si connection internet
        if(Utils.isNetworkAvailable(this)) {
            Log.i("Connection : ", " Connextion is OK");
            mAuth = FirebaseAuth.getInstance();
            final FirebaseUser currentUser = mAuth.getCurrentUser();

            if (settings.getBoolean("my_first_time", true)) {
                //the app is being launched for first time, do something
                Log.i("Comments", "First time");
                // first time task
                mAuth.signInAnonymously()
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Log.i("SignIn", "signInAnonymously:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Log.i("UID = ", user.getUid());
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Log.i("SignIn", "signInAnonymously:failure", task.getException());
                                }
                            }
                        });
                // record the fact that the app has been started at least once
                settings.edit().putBoolean("my_first_time", false).commit();
            } else {
                Log.i("Comments", "Not the First time");
            }
            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);//Enable database persisitance
            //Retrieve and Keep synced categories and products
            DatabaseReference categoriesRef = database.getReference("categories");
            categoriesRef.keepSynced(true);
            DatabaseReference productsRef = database.getReference("products");
            productsRef.keepSynced(true);
            //TODO: Test de la BDD
            categoriesRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Log.i("database", dataSnapshot.child("Jewelry").toString());
                    Log.i("info", "Database is OK");
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    Log.i("info", "Database is KO");
                }
            });
        }
        else{
            Log.i("Connection : ", " Connection is KO");
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashScreen.this, HomePage.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
