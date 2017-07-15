package ir.adnan.lib_requirement_code.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import ir.adnan.lib_requirement_code.R;
import ir.adnan.lib_requirement_code.core.Log;

/**
 * Created by Adnan on 7/15/2017.
 */

public class LibraryWebViewFragmen extends LibraryFragment {private static final String TAG = "WebViewFragment";//.class.getSimpleName();

    protected WebView mWebView;

    protected static final String URL = "siteUrl";

    public static LibraryWebViewFragmen newInstance (String siteUrl) {
        LibraryWebViewFragmen libraryWebViewFragmen = new LibraryWebViewFragmen();

        Bundle bundle = new Bundle();
        bundle.putString(URL , siteUrl);

        libraryWebViewFragmen.setArguments(bundle);

        return libraryWebViewFragmen;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_webview, container, false);

        String siteUrl = getArguments().getString(URL);

        if ( siteUrl != null && siteUrl.length() > 0) {

            Log.e(TAG, "siteUrl : " + siteUrl);

            mWebView = (WebView) view.findViewById(R.id.webview);
            mWebView.loadUrl(siteUrl);

            // Enable Javascript
            WebSettings webSettings = mWebView.getSettings();
            webSettings.setJavaScriptEnabled(true);

            // Force links and redirects to open in the WebView instead of in a browser
            mWebView.setWebViewClient(new WebViewClient());
        }

        return view;
    }
}
