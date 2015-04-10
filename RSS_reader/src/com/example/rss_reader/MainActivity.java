package com.example.rss_reader;
/*
 * 
 * 
 * author tormahiri
 * 2015-4-10
 * http://github.com/tormahiri/rss-okuguq
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import nl.matshofman.saxrssreader.RssFeed;
import nl.matshofman.saxrssreader.RssItem;
import nl.matshofman.saxrssreader.RssReader;

import org.xml.sax.SAXException;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class MainActivity extends ActionBarActivity {

	
	
	
   private List<String> arrayList;
   private List<String> content;
private ListView listView;
private ArrayAdapter<String> adapter;
private ProgressBar progressBar;
private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewGroup layout = (ViewGroup) findViewById(android.R.id.content).getRootView();

        progressBar = new ProgressBar(this,null,android.R.attr.progressBarStyleLarge);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        RelativeLayout.LayoutParams params = new 
                RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT);

        RelativeLayout rl = new RelativeLayout(this);

        rl.setGravity(Gravity.CENTER);
        rl.addView(progressBar);

        layout.addView(rl,params);
        setContentView(R.layout.activity_main);
        Handler handler=new Handler();
  
        arrayList=new ArrayList<String>();
        content=new ArrayList<String>();
    
        
       listView = (ListView) findViewById(R.id.country_list);
  btn=new Button(this);
  btn.setVisibility(View.GONE);
       btn.setText("تېخىمۇ كۆپ ");
       
       listView.addFooterView(btn);
       btn.setOnClickListener(new OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
		  new MyNewTask().execute();
			
		}
    	   
    	   
    	 
       });
     
       adapter = new ArrayAdapter<String>(getBaseContext(), 
      	        R.layout.acitivity_listview, arrayList);
       listView.setAdapter(adapter);
       new MyTask().execute();
       listView.setOnItemClickListener(new OnItemClickListener(){

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			// TODO Auto-generated method stub
	
			String data=content.get(position).toString();
			//Toast.makeText(getApplicationContext(), data, 0).show();
			Intent i=new Intent(MainActivity.this,Korush.class).putExtra("data",content.get(position));
		startActivity(i);
			
		}
     
    	   
       });

    }
// birinjisi 
    
    
    public class MyNewTask extends AsyncTask<Void,Void,Void> {

    	 

    	 
    	@Override
        protected void onPreExecute() {
       
        }

    	@Override
    	protected Void doInBackground(Void... params) {
    	   	 URL url = null;
     		try {
     			
     			//http://mouse.arzu3.com//?feed=rss2
     			url = new URL("http://mouse.arzu3.com//?feed=rss2&paged=2");
     		} catch (MalformedURLException e) {
     			// TODO Auto-generated	` catch block
     			e.printStackTrace();
     		}
             RssFeed feed = null;
     		try {
     			feed = RssReader.read(url);
     		} catch (SAXException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		} catch (IOException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}

             ArrayList<RssItem> rssItems = feed.getRssItems();
             for(RssItem rssItem : rssItems) {
               //  Log.i("RSS Reader", rssItem.getTitle());
               if(arrayList.contains(rssItem.getTitle()) & content.contains(rssItem.getLink())){
            	   
            	   return null;
               }else{
            	   
            	   
            	   arrayList.add(rssItem.getTitle());
                   content.add(rssItem.getLink());
               }
            
             }
    		return null;
    	}
      
    	
    	@Override
    	protected void onPostExecute(Void result) {
    		// TODO Auto-generated method stub
    		super.onPostExecute(result);
    		
    		 adapter.notifyDataSetChanged();
             listView.invalidateViews();
             progressBar.setVisibility(View.GONE);
             btn.setVisibility(View.VISIBLE);
     		
    	}

        }

// asynctask started


public class MyTask extends AsyncTask<Void,Void,Void> {

 

 
	@Override
    protected void onPreExecute() {
   
    }

	@Override
	protected Void doInBackground(Void... params) {
	   	 URL url = null;
 		try {
 			
 			//http://mouse.arzu3.com//?feed=rss2
 			url = new URL(adris.TORBIKAT_ADIRSI);
 		} catch (MalformedURLException e) {
 			// TODO Auto-generated	` catch block
 			e.printStackTrace();
 		}
         RssFeed feed = null;
 		try {
 			feed = RssReader.read(url);
 		} catch (SAXException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		} catch (IOException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}

         ArrayList<RssItem> rssItems = feed.getRssItems();
         for(RssItem rssItem : rssItems) {
           //  Log.i("RSS Reader", rssItem.getTitle());
            arrayList.add(rssItem.getTitle());
             content.add(rssItem.getLink());
        
         }
		return null;
	}
  
	
	@Override
	protected void onPostExecute(Void result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		 adapter.notifyDataSetChanged();
         listView.invalidateViews();
         progressBar.setVisibility(View.GONE);
         btn.setVisibility(View.VISIBLE);
 		
	}

    }


}
