package com.example.vasilis.smsreceiver;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Created by Vasilis on 10/6/2015.
 */
public class ReceiverActivity extends Activity {

    private EditText etSender, etMessage, etCompany;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receive);

        //initialise
        etSender = (EditText)findViewById(R.id.ed_sender);
        etMessage = (EditText)findViewById(R.id.eT_message);
        etCompany = (EditText)findViewById(R.id.eT_company);

        savedInstanceState = getIntent().getExtras();

        if(savedInstanceState != null) {
            etSender.setText(savedInstanceState.getString("Sender"));
            etMessage.setText(savedInstanceState.getString("Message Body"));
            etCompany.setText(savedInstanceState.getString("Provider"));
        }
    }
}
