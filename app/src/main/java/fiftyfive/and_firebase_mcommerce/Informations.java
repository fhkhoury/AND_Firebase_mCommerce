package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import fiftyfive.and_firebase_mcommerce.R;

public class Informations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informations);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_informations, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                //Goto action cart
                return true;
            case R.id.action_legal:
                //Open a webview with CGU
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
