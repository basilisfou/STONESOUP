package com.example.vasilis.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

import java.util.Objects;

/**
 * Created by Vasilis on 10/6/2015. - Binary SMS Receiver
 */
public class SmsReceiver extends BroadcastReceiver {

    private static final String ACTION_SMS_RECEIVED = "android.provider.Telephony.SMS_RECEIVED";
    private String Sender = null;
    private String MessageBody = null;
    private String Telephone_Company = null;

    public String getSender() {
        return Sender;
    }

    public String getMessageBody() {
        return MessageBody;
    }

    public String getTelephone_Company() {
        return Telephone_Company;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String action = intent.getAction();
        SmsMessage[] lSmsMessages = null;
        Bundle lBundle = intent.getExtras();

        if (action.equals(ACTION_SMS_RECEIVED   )) {
            if(lBundle != null) {
                //retrieving the sms
                Object [] pdus = (Object[])lBundle.get("pdus");
                lSmsMessages = new SmsMessage[pdus.length];
                try {
                    for(int i = 0 ; i < lSmsMessages.length ; i ++) {
                        lSmsMessages[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
                        Sender = lSmsMessages[i].getOriginatingAddress();

                        MessageBody = lSmsMessages[i].getMessageBody();

                        Telephone_Company = lSmsMessages[i].getServiceCenterAddress();
                    }
                } catch (Exception e){}

                // Display the SMS as Toast.
                Toast.makeText(context,"Sender: " +Sender + ", MessageBody: "+ MessageBody
                        +", Telephone_company: "+ Telephone_Company , Toast.LENGTH_SHORT).show();

            }
        }
    }
}
