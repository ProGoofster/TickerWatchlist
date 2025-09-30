package com.example.tickerwatchlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TickerListFragment tickerFragment = new TickerListFragment();
        InfoWebFragment webFragment = new InfoWebFragment();

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.ticker_list_fragment_frame, tickerFragment);
        trans.add(R.id.info_web_fragment_frame, webFragment);
        trans.commit();

    }
}