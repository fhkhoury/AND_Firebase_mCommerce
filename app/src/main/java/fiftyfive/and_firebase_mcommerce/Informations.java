package fiftyfive.and_firebase_mcommerce;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fiftyfive.and_firebase_mcommerce.R;

public class Informations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        Button crash = (Button) findViewById(R.id.crash);
        crash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // PUT HERE THE FIREBASE CRASH CODE
            }
        });

        //App Name
        TextView appName = (TextView) findViewById(R.id.appName);
        appName.setText(appName.getText() + " " + getResources().getString(R.string.app_name) );

        TextView udid = (TextView) findViewById(R.id.udid);
        //appName.setText(appName.getText() + " " + Context.getSystemService(Context.TELEPHONY_SERVICE).getDeviceID() );
    }
}
