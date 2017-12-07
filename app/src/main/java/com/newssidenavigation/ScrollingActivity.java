package com.newssidenavigation;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.squareup.picasso.Picasso;

public class ScrollingActivity extends AppCompatActivity {
   // InterstitialAd mInterstitialAd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolling);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        /*mInterstitialAd = new InterstitialAd(this);

        // set the ad unit ID
        mInterstitialAd.setAdUnitId(getString(R.string.interstitial_full_screen));

        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("")
                .build();

        // Load ads into Interstitial Ads
        mInterstitialAd.loadAd(adRequest);

        mInterstitialAd.setAdListener(new AdListener() {
            public void onAdLoaded() {
                showInterstitial();
            }
        });*/


        final TextView des = (TextView) findViewById(R.id.description);
        final TextView title = (TextView) findViewById(R.id.title);
        ImageView img = (ImageView) findViewById(R.id.imageofscroll);


        String d=   getIntent().getExtras().getString("des");
        String t=   getIntent().getExtras().getString("title");
        String i = getIntent().getExtras().getString("img");


        des.setText(d);
        Picasso.with(this).load(i).fit().centerCrop().into(img);
        title.setText(t);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.wtsapp);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PackageManager pm=getPackageManager();
                try {

                    Intent waIntent = new Intent(Intent.ACTION_SEND);
                    waIntent.setType("text/plain");
                    String text =" Hindi News App\n\n\n\n "+ title.getText().toString()+ " \n\n"+des.getText().toString();

                    PackageInfo info=pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA);
                    //Check if package exists or not. If not then code
                    //in catch block will be called
                    waIntent.setPackage("com.whatsapp");

                    waIntent.putExtra(Intent.EXTRA_TEXT, text);
                    startActivity(Intent.createChooser(waIntent, "Share with"));

                } catch (PackageManager.NameNotFoundException e) {
                    Toast.makeText(ScrollingActivity.this, "WhatsApp not Installed", Toast.LENGTH_SHORT).show();
                }
            }
        });





    }

    private void showInterstitial() {
        /*if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }*/
    }
}


