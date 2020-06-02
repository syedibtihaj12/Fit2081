package com.example.warehouseinventoryapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    public static final String INTENT_FILTER_ID = "INTENTFILTER";
    public static final String MSG_KEY = "MSG";

    @Override
    public void onReceive(Context context, Intent intent) {
        SmsMessage[] messages = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        for (int i =0; i < messages.length; i++){
            SmsMessage currentMessage = messages[i];
            String sender_Num = currentMessage.getDisplayOriginatingAddress();
            String message_body = currentMessage.getDisplayMessageBody();
            Toast.makeText(context, "Sender: " + sender_Num + ", message" + message_body,Toast.LENGTH_SHORT);
            Intent intentMSG = new Intent();
            intentMSG.setAction(INTENT_FILTER_ID);
            intentMSG.putExtra(MSG_KEY, message_body);
            context.sendBroadcast(intentMSG);
        }

    }
}
