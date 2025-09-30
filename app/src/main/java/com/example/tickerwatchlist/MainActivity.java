package com.example.tickerwatchlist;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TickerListFragment tickerFrag = new TickerListFragment();
        InfoWebFragment webFrag = new InfoWebFragment();

        FragmentTransaction trans = getSupportFragmentManager().beginTransaction();
        trans.add(R.id.ticker_list_fragment_frame, tickerFrag);
        trans.add(R.id.info_web_fragment_frame, webFrag);
        trans.commit();

    }
}