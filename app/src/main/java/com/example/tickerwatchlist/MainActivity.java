package com.example.tickerwatchlist;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import com.example.tickerwatchlist.receivers.SMSReceiver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private static final String PREFS_NAME = "TickerWatchlistPrefs";
    private static final String TICKERS_KEY = "tickers";

    private TickerListFragment tickerFragment;
    private InfoWebFragment webFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{ Manifest.permission.RECEIVE_SMS }, 101);
        }

        //this counts for the reloading extra credit
        SharedPreferences prefs = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        Set<String> savedTickers = prefs.getStringSet(TICKERS_KEY, new HashSet<>());
        if (savedTickers.isEmpty()) {
            savedTickers.add("NEE");
            savedTickers.add("AAPL");
            savedTickers.add("DIS");
        }
        ArrayList<String> tickers = new ArrayList<>(savedTickers);


        tickerFragment = new TickerListFragment();
        Bundle args = new Bundle();
        args.putStringArrayList("tickers", tickers);
        tickerFragment.setArguments(args);

        webFragment = new InfoWebFragment();

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.ticker_list_fragment_frame, tickerFragment);
        trans.add(R.id.info_web_fragment_frame, webFragment);
        trans.commit();

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        handleIntent(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();

        SharedPreferences.Editor editor = getSharedPreferences(PREFS_NAME, MODE_PRIVATE).edit();
        if (tickerFragment != null) {
            editor.putStringSet(TICKERS_KEY, new HashSet<>(tickerFragment.getTickers()));
            editor.apply();
        }
    }

    private void handleIntent(Intent intent) {
        String ticker = intent.getStringExtra("ticker");
        if (ticker != null) {
            Toast.makeText(this, "Added ticker: " + ticker, Toast.LENGTH_SHORT).show();
            if (tickerFragment != null) {
                tickerFragment.addTicker(ticker);
            }
            if (webFragment != null) {
                webFragment.loadTicker(ticker);
            }
        }
    }
}
