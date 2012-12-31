package com.combinedinbox;

import com.facebook.HttpMethod;
import com.facebook.Request;
import com.facebook.Response;
import com.facebook.Session;
import com.facebook.android.FbDialog;
import com.facebook.model.GraphObject;
import android.os.Bundle;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;

public class Inbox extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_inbox);
		//get FBfeeds
		getFBFeeds(Session.getActiveSession());
		
	}
	
	
	private void loadMsgs()
	{
		ListView msgLv= (ListView)findViewById(R.id.listView1);
		Toast.makeText(getApplicationContext(), FBmsgs.getNewsFeedsList().size()+"", Toast.LENGTH_LONG).show();
		MsgListAdapter adapter = new MsgListAdapter(getApplicationContext(), FBmsgs.getNewsFeedsList());
		msgLv.setAdapter(adapter);
	}

	private void getFBFeeds(final Session session) {
	    // Make an API call to get user data and define a 
	    // new callback to handle the response.
		
		String fqlQuery = "SELECT post_id,description FROM stream WHERE filter_key in (SELECT filter_key FROM stream_filter WHERE uid = me() AND type = 'newsfeed')";
	        Bundle params = new Bundle();
	        params.putString("q", fqlQuery);
	        
	        Request request = new Request(session,
	            "/fql",                         
	            params,                         
	            HttpMethod.GET,                 
	            new Request.Callback(){       
	        		
	                public void onCompleted(Response response) {
	                	
	                	extractFeeds(response);
	                	
	                  
	                }                  
	        }); 
	        Request.executeBatchAsync(request);                
		
	} 
	
	private void extractFeeds(Response s)
	{
		 GraphObject graphObject =s.getGraphObject();
		 if (graphObject != null)
         {
             if (graphObject.getProperty("data") != null)
             {
                 
                     String json = graphObject.getProperty("data").toString();
                     FBmsgs.setnewsFeedsList(json);
                     loadMsgs();
             }
         }
	}
	

}
