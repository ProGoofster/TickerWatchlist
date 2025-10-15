package com.example.tickerwatchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.tickerwatchlist.datatypes.FixedLinkedList;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TickerListFragment extends Fragment {
    public TickerListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ticker_list_fragment, container, false);

        ListView listView = view.findViewById(R.id.ticker_list);

        FixedLinkedList<String> tickers = new FixedLinkedList<>(6);
        tickers.addAll(Arrays.asList("NEE", "AAPL", "DIS"));

        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, tickers);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener((parent, view1, position, id) -> {
            String clickedTicker = tickers.get(position);

            InfoWebFragment webFragment = (InfoWebFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.info_web_fragment_frame); //direct get fragment
            if (webFragment != null) webFragment.loadTicker(clickedTicker);
        });

        return view;
    }
}
