package com.example.tickerwatchlist.receivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

import com.example.tickerwatchlist.MainActivity;

public class SMSReceiver extends BroadcastReceiver {
    private static final String TAG = "SMSReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {
        if (!Telephony.Sms.Intents.SMS_RECEIVED_ACTION.equals(intent.getAction())) return;

        var msgs = Telephony.Sms.Intents.getMessagesFromIntent(intent);
        StringBuilder body = new StringBuilder();
        for (var msg : msgs) {
            body.append(msg.getMessageBody());
        }
        String message = body.toString();

        Intent launchIntent = new Intent(context, MainActivity.class);
        launchIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

        if(message.startsWith("Ticker:<<") && message.endsWith(">>")) {
            String ticker = message.substring(9, message.length() - 2).trim().toUpperCase();

            if(!ticker.matches("[A-Z]+")) return;

            Log.d(TAG, "Ticker: " + ticker);
            launchIntent.putExtra("ticker", ticker);

        } else {
            Toast.makeText(context, "No valid watchlist entry found", Toast.LENGTH_SHORT).show();
        }
        context.startActivity(launchIntent);
    }
}
