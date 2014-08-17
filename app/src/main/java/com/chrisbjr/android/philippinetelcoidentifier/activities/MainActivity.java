package com.chrisbjr.android.philippinetelcoidentifier.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chrisbjr.android.philippinetelcoidentifier.R;


public class MainActivity extends ActionBarActivity {

    private TextView mDialTextView;

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
                }
            }
        });

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
