package com.codepath.apps.basictwitter.fragments;

import org.apache.http.Header;
import org.json.JSONArray;

import android.os.Bundle;
import android.util.Log;

import com.codepath.apps.basictwitter.TwitterApplication;
import com.codepath.apps.basictwitter.TwitterClient;
import com.codepath.apps.basictwitter.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

public class MentionsTimelineFragment extends TweetsListFragment {
	private TwitterClient client;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		client = TwitterApplication.getRestClient();
		populateTimeline();
	}
	
	public void populateTimeline() {
		client.getMentionsTimeline(new JsonHttpResponseHandler() {
			@Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray json) {
                addAll(Tweet.fromJSONArray(json));
			}
			
			@Override
            public void onFailure(int statusCode, Header[] headers, String s, Throwable e) {
                Log.d("debug", e.toString());
      		    Log.d("debug", s.toString());
			}
		});
	}
}
