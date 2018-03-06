package fiftyfive.and_firebase_mcommerce;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.firebase.analytics.FirebaseAnalytics;

/**
 * Created by Francois on 03/08/2017.
 */

public class Legal extends AppCompatActivity{



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_legal);

        FirebaseAnalytics mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle=new Bundle();
        bundle.putString("screenName","Legal");
        bundle.putString("userId", "1111111111");
        bundle.putString("pageTopCategory", "Legal");
        bundle.putString("pageCategory", "");
        bundle.putString("pageSubCategory", "");
        bundle.putString("pageType", "User");
        bundle.putString("loginStatus", "Not logged");
        bundle.putString("previousScreen", "HomePage");

        mFirebaseAnalytics.logEvent("screenView", bundle);
        mFirebaseAnalytics.setCurrentScreen(this, "Legal", null);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        WebView myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl("https://www.amazon.fr/gp/help/customer/display.html/ref=navm_ftr_cou?ie=UTF8&nodeId=548524");
        myWebView.setWebViewClient(new WebViewClient());
        myWebView.getSettings().setJavaScriptEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //ajoute les entrées de menu_test à l'ActionBar
        getMenuInflater().inflate(R.menu.menu_legal, menu);
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
                Intent i = new Intent(Legal.this, Informations.class);
                startActivity(i);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}
