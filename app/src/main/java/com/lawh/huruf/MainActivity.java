package com.lawh.huruf;

import android.app.Activity;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/** Shows the bundled game (assets/index.html) full screen. Works fully offline. */
public class MainActivity extends Activity {
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        boolean night = (getResources().getConfiguration().uiMode
                & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        int bg = night ? Color.parseColor("#0e1124") : Color.parseColor("#eceef5");
        Window win = getWindow();
        win.setStatusBarColor(bg);
        win.setNavigationBarColor(bg);
        if (!night && Build.VERSION.SDK_INT >= 23) {
            win.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

        web = new WebView(this);
        web.setBackgroundColor(bg);
        WebSettings s = web.getSettings();
        s.setJavaScriptEnabled(true);
        s.setDomStorageEnabled(true);          // saves found words, stars and streak
        s.setMediaPlaybackRequiresUserGesture(false);
        s.setTextZoom(100);                    // keep the layout fitting one screen
        if (Build.VERSION.SDK_INT >= 29) {
            s.setForceDark(night ? WebSettings.FORCE_DARK_AUTO : WebSettings.FORCE_DARK_OFF);
        }
        web.setWebViewClient(new WebViewClient());
        web.loadUrl("file:///android_asset/index.html");
        setContentView(web);
    }

    @Override
    public void onBackPressed() {
        // Back closes an open card first; if nothing is open, it leaves the app.
        web.evaluateJavascript(
            "(function(){var a=document.getElementById('sheet'),b=document.getElementById('wordsSheet');"
            + "if(a&&!a.hidden){document.getElementById('nextBtn').click();return 1}"
            + "if(b&&!b.hidden){b.hidden=true;return 1}return 0})()",
            value -> { if (!"1".equals(value)) MainActivity.super.onBackPressed(); });
    }

    @Override
    protected void onPause() { super.onPause(); web.onPause(); }

    @Override
    protected void onResume() { super.onResume(); web.onResume(); }
}
