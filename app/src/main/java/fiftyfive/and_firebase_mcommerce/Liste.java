package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fiftyfive.and_firebase_mcommerce.R;

public class Liste extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        Button goToDetail = (Button) findViewById(R.id.goToDetail);
        goToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Liste.this, Detail.class);
                startActivity(i);
            }
        });

        Button infos = (Button) findViewById(R.id.informations);
        infos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Liste.this, Informations.class);
                startActivity(i);
            }
        });
    }

}
