package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import fiftyfive.and_firebase_mcommerce.R;

import static fiftyfive.and_firebase_mcommerce.R.id.listView;

public class Liste extends AppCompatActivity {

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        final ListView consolesListView = (ListView) findViewById(listView);

        final List<Product> consolesList = generateConsolesList();

        ProductAdapter adapter = new ProductAdapter(Liste.this, consolesList);
        consolesListView.setAdapter(adapter);
        consolesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> listView, View itemView, int itemPosition, long itemId)
            {
                Product selectedProduct = (Product) consolesListView.getItemAtPosition(itemPosition);
                Intent i = new Intent(Liste.this, Detail.class);
                i.putExtra("SELECTED_PRODUCT_COLOR", selectedProduct.getColor());
                i.putExtra("SELECTED_PRODUCT_NAME", selectedProduct.getName());
                i.putExtra("SELECTED_PRODUCT_DESC", selectedProduct.getDesc());
                startActivity(i);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_liste, menu);
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
                Intent i = new Intent(Liste.this, Informations.class);
                startActivity(i);
                return true;
            case R.id.action_legal:
                Intent j = new Intent(Liste.this, Legal.class);
                startActivity(j);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private List<Product> generateConsolesList(){
        List<Product> consolesList = new ArrayList<Product>();
        consolesList.add(new Product(Color.BLUE, "SONY Playstation 4 - 500 Go Slim", "- Plate-forme : PlayStation 4\n" +
                "- Edition : Slim 500Go\n" +
                "- Des couleurs riches et éclatantes avec les graphismes HDR d’une qualité exceptionnelle."));
        consolesList.add(new Product(Color.BLUE, "SONY Playstation 4 Pro", "- Plate-forme: PlayStation 4\n" +
                "- Edition: Pro\n" +
                "- La PlayStation la plus puissante jamais conçue."));
        consolesList.add(new Product(Color.GREEN, "MICROSOFT - Console Xbox One (ancien modèle)", "Inclus avec le produit :\n" +
                "- 1 manette sans fil\n" +
                "- 1 micro casque filaire\n" +
                "- 1 bloc d'alimentation"));
        consolesList.add(new Product(Color.GREEN, "MICROSOFT - Xbox One X", "La XBOX One X sortira le 7 novembre 2017\n" +
                "- Plongez dans des univers de jeux en qualité 4K et laissez-vous entraîner par des images ultra-réalistes en 2160p\n" +
                "- Ne ratez pas une image grâce à 326 Go/s de bande passante.\n" +
                "- 6 téraflops de puissance de traitement graphique, pour des jeux plus performants que jamais."));
        consolesList.add(new Product(Color.RED, "NINTENDO - Switch avec Joy-Con", "Important : du fait des quantités limitées nous ne pouvons autoriser qu'une précommande par client et par adresse.\n" +
                "Contenu :\n" +
                "- Console Nintendo Switch\n" +
                "- Manette Joy-Con droite rouge néon et manette Joy-Con gauche bleu néon\n" +
                "- support Joy-Con"));
        consolesList.add(new Product(Color.RED, "NINTENDO - Switch avec paire de Joy-Con", "Important : du fait des quantités limitées nous ne pouvons autoriser qu'une précommande par client et par adresse.\n" +
                "Contenu :\n" +
                "- Console Nintendo Switch\n" +
                "- Manette Joy-Con droite grise et manette Joy-Con gauche grise"));
        return consolesList;
    }

}
