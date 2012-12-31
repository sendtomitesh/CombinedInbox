package com.combinedinbox;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MsgListAdapter extends BaseAdapter {
	
	
	ArrayList<FBmsgs> newsFeedList;
	private LayoutInflater mInflater;
	MsgListAdapter(Context ct, ArrayList<FBmsgs> nflist)
	{
		newsFeedList=nflist;
		mInflater = LayoutInflater.from(ct);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return newsFeedList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return newsFeedList.get(arg0);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		viewHolder vh;
		if(convertView == null)
		{
			 convertView = mInflater.inflate(R.layout.msgitem, null);
			 vh= new viewHolder();
			 vh.msgText = (TextView)convertView.findViewById(R.id.msgText);
			 convertView.setTag(vh);
			
		}
		else
		{
			vh=(viewHolder) convertView.getTag();
		}
		
		vh.msgText.setText(newsFeedList.get(position).getnewsFeed());
		return convertView;
	}
	public class viewHolder
	{
		TextView msgText;
	}

}
