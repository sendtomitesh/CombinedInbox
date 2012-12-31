package com.combinedinbox;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FBmsgs {

	String newsFeeds;
	static ArrayList<FBmsgs> newsFeedsList;
	FBmsgs(String msg)
	{
	newsFeeds=msg;
	
	}
	
	public void setnewsFeed(String nf)
	{
		newsFeeds=nf;
	}
	public String getnewsFeed()
	{
		return newsFeeds;
	}
	public static void setnewsFeedsList(String json)
	{
		newsFeedsList= new ArrayList<FBmsgs>();
		String feed;
		try {
            

            JSONArray jsonNArray = new JSONArray(json);
            for (int i = 0; i < jsonNArray.length(); i++) {

                JSONObject jsonObject = jsonNArray.getJSONObject(i);
                feed=jsonObject.getString("description");
                FBmsgs fb=null;
                if(!feed.equals(null))
                	fb = new FBmsgs(feed);
                newsFeedsList.add(fb);
               // Toast.makeText(getApplicationContext(), jsonObject.getString("description"), Toast.LENGTH_LONG).show();
            }
        }
        catch(JSONException e)
        {
       	 e.printStackTrace();
        }
	}
	public static ArrayList<FBmsgs> getNewsFeedsList()
	{
		return newsFeedsList;
	}
}
