package com.example.dam2m8_exemplewebview;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

public class WebViewFragment extends Fragment {

    private WebViewViewModel webViewViewModel;

    public static WebViewFragment newInstance() {
        return new WebViewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.web_view_fragment, container, false);

        webViewViewModel =
                ViewModelProviders.of(this).get(WebViewViewModel.class);
        final WebView webView = root.findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);

        MiHilo miHilo = new MiHilo();
        miHilo.execute("http://www.google.com");


        webViewViewModel.getWeb().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String html) {
                webView.loadData(html,"text/html", "utf-8");
            }
        });

        return root;
    }


public class MiHilo extends AsyncTask<String,Void,String> {

    @Override
    protected String doInBackground(String... strings) {
        webViewViewModel.downloadURL(strings[0]);
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        //Aqui no hago nada porque utilizo LiveData
        //Sin usar LiveData aquí podría definir que hago con la respuesta del Thread
    }
}

}
