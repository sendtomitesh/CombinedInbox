package com.combinedinbox;

import java.util.ArrayList;
import java.util.List;

import com.facebook.Session;
import com.facebook.SessionState;
import com.facebook.UiLifecycleHelper;
import com.facebook.widget.LoginButton;




import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.widget.Toast;

public class Login extends Activity {

	private boolean isResumed=false;
	List<String> fbPerm = new ArrayList<String>();
	LoginButton lb;
	Context appContext=this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		
		fbPerm.add("offline_access");
		fbPerm.add("publish_stream");
		fbPerm.add("photo_upload");
		fbPerm.add("publish_actions");
		lb =(LoginButton)findViewById(R.id.login_button);
		lb.setPublishPermissions(fbPerm);
		uiHelper = new UiLifecycleHelper(this, callback);
		uiHelper.onCreate(savedInstanceState);
		initializelogin();
	}

	private void initializelogin()
	{
		Session s = Session.getActiveSession();
		if(s.isOpened())
		{
			Toast.makeText(getApplicationContext(), "You are already logged in", Toast.LENGTH_LONG).show();
			moveToInbox();
		}
		
	}
	private void moveToInbox()
	{
		Intent Inboxintent = new Intent(appContext, Inbox.class);
		startActivity(Inboxintent);
		finish();

	}
	private UiLifecycleHelper uiHelper;
	private Session.StatusCallback callback = 
	    new Session.StatusCallback() {
	    @Override
	    public void call(Session session, 
	            SessionState state, Exception exception) {
	        onSessionStateChange(session, state, exception);
	    }
	};
	
	private void onSessionStateChange(Session session, SessionState state, Exception exception) {
	    // Only make changes if the activity is visible
		
	    if (isResumed) 
	    {
	       
	        if (state.isOpened()) {
	            // If the session state is open:
	            // Show the authenticated fragment
	        	moveToInbox();
	        	
	        } else if (state.isClosed()) {
	            // If the session state is closed:
	            // Show the login fragment
	           // showFragment(SPLASH, false);
	        	
	        	
	        }
	    }
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		isResumed=true;
		uiHelper.onResume();
		super.onResume();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		isResumed=false;
		uiHelper.onPause();
		super.onPause();
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		 uiHelper.onDestroy();
		super.onDestroy();
	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
	    super.onActivityResult(requestCode, resultCode, data);
	    uiHelper.onActivityResult(requestCode, resultCode, data);
	}
	@Override
	protected void onSaveInstanceState(Bundle outState) {
	    super.onSaveInstanceState(outState);
	    uiHelper.onSaveInstanceState(outState);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}
