package com.fokakefir.spaceship.gui.fragment.commander;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.fragment.app.Fragment;

import com.fokakefir.spaceship.R;
import com.fokakefir.spaceship.gui.activity.MainActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class KnowledgeFragment extends Fragment implements View.OnClickListener {

    // region 1. Decl and Init

    private View view;

    private MainActivity activity;

    private WebView webViewDailyKnowledge;

    private FloatingActionButton fabPrevious;
    private FloatingActionButton fabNext;

    private List<String> documentNames;
    private String actualDocumentName;

    // endregion

    // region 2. Lifecycle and constructor

    public KnowledgeFragment(MainActivity activity) {
        this.activity = activity;
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_commander_knowledge, container, false);

        this.webViewDailyKnowledge = this.view.findViewById(R.id.vw_daily_knowledge);
        this.fabPrevious = this.view.findViewById(R.id.fab_previous);
        this.fabNext = this.view.findViewById(R.id.fab_next);

        this.fabPrevious.setOnClickListener(this);
        this.fabNext.setOnClickListener(this);

        this.documentNames = new ArrayList<>();
        loadDocumentNames();

        this.actualDocumentName = this.documentNames.get(0);
        this.webViewDailyKnowledge.getSettings().setJavaScriptEnabled(true);
        this.webViewDailyKnowledge.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                injectCSS();
                super.onPageFinished(view, url);
            }
        });
        this.webViewDailyKnowledge.loadUrl("file:///android_asset/" + this.actualDocumentName);

        return this.view;
    }

    // endregion

    // region 3. FloatingActionButton listener

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab_previous) {
            int index = (this.documentNames.indexOf(this.actualDocumentName) - 1 + this.documentNames.size()) % this.documentNames.size();
            this.actualDocumentName = this.documentNames.get(index);
            this.webViewDailyKnowledge.loadUrl("file:///android_asset/" + this.actualDocumentName);
        } else if (v.getId() == R.id.fab_next) {
            int index = (this.documentNames.indexOf(this.actualDocumentName) + 1) % this.documentNames.size();
            this.actualDocumentName = this.documentNames.get(index);
            this.webViewDailyKnowledge.loadUrl("file:///android_asset/" + this.actualDocumentName);
        }
    }

    // endregion

    // region 4. Load documents and CSS file

    private void loadDocumentNames() {
        AssetManager assetManager = this.activity.getAssets();
        try {
            for (String file : assetManager.list("")) {
                if (file.endsWith(".html"))
                    this.documentNames.add(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.shuffle(this.documentNames);
    }

    private void injectCSS() {
        try {
            InputStream inputStream = this.activity.getAssets().open("style.css");
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            inputStream.close();
            String encoded = Base64.encodeToString(buffer, Base64.NO_WRAP);
            this.webViewDailyKnowledge.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var style = document.createElement('style');" +
                    "style.type = 'text/css';" +
                    "style.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(style)" +
                    "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // endregion
}