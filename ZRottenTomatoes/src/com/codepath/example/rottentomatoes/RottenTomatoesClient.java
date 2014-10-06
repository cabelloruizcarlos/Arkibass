package com.codepath.example.rottentomatoes;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class RottenTomatoesClient {
	private final String API_KEY = "jwsyx56bht9fcx2nsew8gbmy";
	
	private final String API_BASE_URL = "http://api.rottentomatoes.com/api/public/v1.0/";
	private AsyncHttpClient client;

	public RottenTomatoesClient() {
		this.client = new AsyncHttpClient();
	}
	
	// http://api.rottentomatoes.com/api/public/v1.0/lists/movies/box_office.json?apikey=<key>
	public void getBoxOfficeMovies(JsonHttpResponseHandler handler) {
		String url = getApiUrl("lists/movies/box_office.json");
		RequestParams params = new RequestParams("apikey", API_KEY);
		client.get(url, params, handler);
	}

	// http://api.rottentomatoes.com/api/public/v1.0/lists/movies/in_theaters.json?apikey=[your_api_key]
	public void getInTheatersMovies(JsonHttpResponseHandler handler) {
		// TODO Auto-generated method stub
		String url = getApiUrl("lists/movies/in_theaters.json");
		RequestParams params = new RequestParams("apikey", API_KEY);
		client.get(url, params, handler);
	}
	
	private String getApiUrl(String relativeUrl) {
		return API_BASE_URL + relativeUrl;
	}

}