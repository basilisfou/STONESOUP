package com.example.vasilis.smsreceiver;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends Activity {

    private EditText mEtSender, mEtReceiver, mEtText;
    private SmsManager mSmsManager;
    private String mSender, mReceiver , mText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        mEtSender = (EditText)findViewById(R.id.eT_sender);
        mEtReceiver = (EditText)findViewById(R.id.et_receiver);
        mEtText = (EditText)findViewById(R.id.et_text);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void send(View v) {
        mSmsManager = SmsManager.getDefault();
        fillEditText();
        if (mReceiver != null)
            mSmsManager.sendTextMessage(mReceiver, null, mText, null, null);

        Toast toast = Toast.makeText(this,"Message Sent", Toast.LENGTH_LONG);
        toast.show();
    }

    public void fillEditText () {
        mSender = mEtSender.getText().toString();
        mReceiver = mEtReceiver.getText().toString();
        mText = mEtText.getText().toString();
    }


}
