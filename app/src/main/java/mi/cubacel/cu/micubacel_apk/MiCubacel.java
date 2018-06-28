package mi.cubacel.cu.micubacel_apk;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import static android.view.View.VISIBLE;

public class MiCubacel extends AppCompatActivity {

    private WebView webView;
    boolean loadingFinished = true;
    boolean redirect = false;
    private String url = "https://mi.cubacel.net/primary/_-ia1Zrwwf";
    private TextView textMsg;
    ImageView btn;
    private boolean gotError = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cubacel);
        textMsg = (TextView) findViewById(R.id.msg);
        btn = (ImageView) findViewById(R.id.btn);
        webView = (WebView) findViewById(R.id.webview);
        webView.loadUrl(url);
        webView.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
        webView.getSettings().setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadingFinished = false;
                webView.setVisibility(View.GONE);
                btn.setVisibility(View.GONE);
                textMsg.setText(R.string.loading);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
//                if (!redirect) {
//                    loadingFinished = true;
//                }
//
//                if (loadingFinished && !redirect) {
                if (!gotError)
                    webView.setVisibility(View.VISIBLE);
                else{
                    webView.setVisibility(View.GONE);
                    btn.setVisibility(View.VISIBLE);
                    textMsg. setText(getString(R.string.no_se_ha_podido_conectar));
                }
//                } else {
//                    redirect = false;
//                }
                super.onPageFinished(view, url);
            }

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                super.onReceivedError(view, request, error);
                gotError = true;
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                gotError = true;
            }
        });
    }


    public void reload(View view) {
        gotError = false;
//            webView.setVisibility(View.GONE);
//            btn.setVisibility(View.GONE);
        webView.loadUrl(url);
//            textMsg.setText(getString(R.string.loading));

    }
}
