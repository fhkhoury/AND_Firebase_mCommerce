package fiftyfive.and_firebase_mcommerce;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import fiftyfive.and_firebase_mcommerce.R;

public class Detail extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        Context context = getApplicationContext();



        //Définition du toast
        CharSequence text = "Added to cart !";
        int duration = Toast.LENGTH_LONG;
        final Toast toast = Toast.makeText(context, text, duration);





        Button call = (Button) findViewById(R.id.addToCart);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toast.show();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }

    //gère le click sur une action de l'ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_cart:
                //Goto action cart
                return true;
            case R.id.action_informations:
                Intent i = new Intent(Detail.this, Informations.class);
                startActivity(i);
                return true;
            case R.id.action_legal:
                Intent j = new Intent(Detail.this, Legal.class);
                startActivity(j);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
