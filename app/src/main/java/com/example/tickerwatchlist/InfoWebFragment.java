package com.example.tickerwatchlist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InfoWebFragment extends Fragment {
    private WebView webView;

    public InfoWebFragment() {
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
        View view = inflater.inflate(R.layout.info_web_fragment, container, false);

        webView = view.findViewById(R.id.web_view);
        webView.loadUrl("https://seekingalpha.com");

        return view;
    }

    public void loadTicker(String ticker) {
        webView.loadUrl("https://seekingalpha.com/symbol/" + ticker);
    }
}
