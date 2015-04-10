package com.example.rss_reader;
/*
 * 
 * 
 * author tormahiri
 * 2015-4-10
 * http://github.com/tormahiri/rss-okuguq
 */
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.webkit.ClientCertRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class Korush extends ActionBarActivity {

	private ProgressBar progress;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_we_view);
		progress=(ProgressBar)findViewById(R.id.progressBar1);
		WebView webView = (WebView)findViewById(R.id.webview);
             webView.getSettings().setJavaScriptEnabled(true);
	    webView.setWebViewClient(new WebViewClient(){

		
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
		
				return super.shouldOverrideUrlLoading(view, url);
			}

		
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
			
				super.onPageStarted(view, url, favicon);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
			
				super.onPageFinished(view, url);
				progress.setVisibility(View.GONE);
			}

		
		
	    	
	    	
	    	
	    });
	 
	 
	    webView.loadUrl(getIntent().getStringExtra("data"));
	 
		
	}


}
