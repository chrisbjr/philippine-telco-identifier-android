package com.chrisbjr.android.philippinetelcoidentifier.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chrisbjr.android.philippinetelcoidentifier.R;
import com.chrisbjr.android.philippinetelcoidentifier.models.Prefix;
import com.chrisbjr.android.philippinetelcoidentifier.models.Telco;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;


public class MainActivity extends ActionBarActivity {

    private TextView mDialTextView;
    private final String TAG = "MainActivity";

    /** The view to show the ad. */
    private AdView adView;

    /* Your ad unit id. Replace with your actual ad unit id. */
    private static final String AD_UNIT_ID = "ca-app-pub-2596844701687081/7357271658";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDialTextView = (TextView) findViewById(R.id.dialTextView);

        RelativeLayout dialPad1 = (RelativeLayout) findViewById(R.id.dialPad1);
        RelativeLayout dialPad2 = (RelativeLayout) findViewById(R.id.dialPad2);
        RelativeLayout dialPad3 = (RelativeLayout) findViewById(R.id.dialPad3);
        RelativeLayout dialPad4 = (RelativeLayout) findViewById(R.id.dialPad4);
        RelativeLayout dialPad5 = (RelativeLayout) findViewById(R.id.dialPad5);
        RelativeLayout dialPad6 = (RelativeLayout) findViewById(R.id.dialPad6);
        RelativeLayout dialPad7 = (RelativeLayout) findViewById(R.id.dialPad7);
        RelativeLayout dialPad8 = (RelativeLayout) findViewById(R.id.dialPad8);
        RelativeLayout dialPad9 = (RelativeLayout) findViewById(R.id.dialPad9);
        RelativeLayout dialPad0 = (RelativeLayout) findViewById(R.id.dialPad0);

        dialPad1.setOnClickListener(new DailPadClickListener("1"));
        dialPad2.setOnClickListener(new DailPadClickListener("2"));
        dialPad3.setOnClickListener(new DailPadClickListener("3"));
        dialPad4.setOnClickListener(new DailPadClickListener("4"));
        dialPad5.setOnClickListener(new DailPadClickListener("5"));
        dialPad6.setOnClickListener(new DailPadClickListener("6"));
        dialPad7.setOnClickListener(new DailPadClickListener("7"));
        dialPad8.setOnClickListener(new DailPadClickListener("8"));
        dialPad9.setOnClickListener(new DailPadClickListener("9"));
        dialPad0.setOnClickListener(new DailPadClickListener("0"));

        LinearLayout backspaceLinearLayout = (LinearLayout) findViewById(R.id.backspaceLinearLayout);
        backspaceLinearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String dialText = mDialTextView.getText().toString();
                if (dialText.length() > 0) {
                    dialText = dialText.substring(0, (dialText.length() - 1));
                    mDialTextView.setText(dialText);
                    getOperator(dialText);
                }
            }
        });

        // Create an ad.
        adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId(AD_UNIT_ID);



    }

    public void getOperator(String dialText) {

        if (dialText.length() < 3) {
            return;
        }

        // clean the string
        String regex = "\"/[^0-9]/\"";
        dialText = dialText.replaceAll(regex, "");

        // Take out the 0 at the start
        String start = dialText.substring(0, 1);

        if (start.equals("0")) {
            dialText = dialText.substring(1, dialText.length());
        }

        // Take out 63
        start = dialText.substring(0, 2);

        if (start.equals("63")) {
            dialText = dialText.substring(2, dialText.length());
        }

        if (dialText.length() < 3) {
            return;
        }

        String dialTextPrefix = dialText.substring(0, 3);

        Log.i(TAG, "dialtext is: " + dialText);

        // Search for it in DB
        Prefix prefixQuery = new Prefix();
        Telco telco = prefixQuery.getTelcoFromPrefix(dialTextPrefix);

        if (telco != null) {
            Log.i(TAG, "Telco is: " + telco.name);
        }


    }

    public class DailPadClickListener implements View.OnClickListener {

        private final String number;

        public DailPadClickListener(String number) {
            this.number = number;
        }

        @Override
        public void onClick(View view) {
            String dialText = mDialTextView.getText().toString();
            dialText = dialText + number;
            mDialTextView.setText(dialText);
            // Check if there is a matching prefix
            getOperator(dialText);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
