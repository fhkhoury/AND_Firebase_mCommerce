package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

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

import fiftyfive.and_firebase_mcommerce.R;

public class SplashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 1500;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Anonymous Authentication by default to get database informations
                mAuth.signInAnonymously()
                        .addOnCompleteListener(SplashScreen.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Log.i("TAG OK", "signInAnonymously:success");
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                                    database.setPersistenceEnabled(true);//Enable database persisitance
                                    //Retrieve and Keep synced categories and products
                                    DatabaseReference categoriesRef = database.getReference("categories");
                                    categoriesRef.keepSynced(true);
                                    DatabaseReference productsRef = database.getReference("products");
                                    productsRef.keepSynced(true);
                                    //TODO: Test de la BDD
                                    DatabaseReference userendPoint = database.getReference("users");
                                    userendPoint.addValueEventListener(new ValueEventListener() {
                                                                           @Override
                                                                           public void onDataChange(DataSnapshot dataSnapshot) {
                                                                               Log.i("database", dataSnapshot.child("D9ZD4Z8BUkcsHQucH0Iwv18L1zo2").child("mail").toString());
                                                                           }
                                                                           @Override
                                                                           public void onCancelled(DatabaseError databaseError) {

                                                                           }
                                                                       });
                                    Log.i("info", "it's ok");
                                } else {
                                   Log.i("TAG KO", "signInAnonymously:failure", task.getException());
                                    //Toast.makeText(AnonymousAuthActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
                Intent i = new Intent(SplashScreen.this, HomePage.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
