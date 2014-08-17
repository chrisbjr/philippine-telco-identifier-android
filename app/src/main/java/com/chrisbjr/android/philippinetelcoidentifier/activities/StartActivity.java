package com.chrisbjr.android.philippinetelcoidentifier.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.chrisbjr.android.philippinetelcoidentifier.R;
import com.chrisbjr.android.philippinetelcoidentifier.libs.SeedTelcoPrefixes;
import com.chrisbjr.android.philippinetelcoidentifier.models.Prefix;

public class StartActivity extends ActionBarActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setCancelable(false);
        mProgressDialog.setMessage("Initializing the application ...");


        Prefix prefix = new Prefix();
        if (prefix.getCount() <= 0) {
            // Import the telco prefixes
            SeedTelcoPrefixes seedTelcoPrefixes = new SeedTelcoPrefixes(this, new SeedTelcoPrefixes.SeedTelcoPrefixesInterface() {
                @Override
                public void onStart() {
                    mProgressDialog.show();
                }

                @Override
                public void onFinish() {
                    mProgressDialog.dismiss();
                    startApp();
                }
            });
            seedTelcoPrefixes.execute();
        } else {
            startApp();
        }
    }

    public void startApp() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
