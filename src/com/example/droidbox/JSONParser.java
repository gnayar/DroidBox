package com.example.droidbox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

public class JSONParser extends AsyncTask<String, Integer, JSONObject>{
 
    static InputStream is = null;
    static JSONObject jObj = null;
    public JSONObject tempHolder = null;
    static String json = "";
 
    // constructor
    public JSONParser() {
 
    }
    @Override
	protected JSONObject doInBackground(String... params) {
		String url = params[0];
		List<NameValuePair> parameters = new ArrayList<NameValuePair>();
		for(int i = 1;i<params.length;i+=2){
			parameters.add(new BasicNameValuePair(params[i], params[i+1]));
		}
		try {
             DefaultHttpClient httpClient = new DefaultHttpClient();
             HttpPost httpPost = new HttpPost(url);
             httpPost.setEntity(new UrlEncodedFormEntity(parameters));
             Log.v("HTTP URL", httpPost.getURI().toString());
             HttpResponse httpResponse = httpClient.execute(httpPost);
             HttpEntity httpEntity = httpResponse.getEntity();
             is = httpEntity.getContent();

          

     } catch (UnsupportedEncodingException e) {
         e.printStackTrace();
         Log.e("http error", "1 " + e.toString());
     } catch (ClientProtocolException e) {
         e.printStackTrace();
         Log.e("http Error", "2 " + e.toString());
     } catch (IOException e) {
         e.printStackTrace();
         Log.e("http Error", "3 " + e.toString());
     } catch (Exception e) {
     	e.printStackTrace();
     	Log.e("http Error", "4" + e.toString());
     }
		try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    is, "iso-8859-1"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            json = sb.toString();
           
        } catch (Exception e) {
            Log.e("Buffer Error", "Error converting result " + e.toString());
        }
 
        // try parse the string to a JSON object
        try {
            jObj = new JSONObject(json);
            
        } catch (JSONException e) {
            Log.e("JSON Parser", "Error parsing data " + e.toString());
            Log.e("JSON Parser", "jsonstring" + json);
        }
 
        // return JSON String
        return jObj;
	}

    // function get json from url
    // by making HTTP POST or GET mehtod

    
    public SongList createList(JSONObject o) {
    	SongList creation = new SongList();
    	final String TAG_ARTIST = "artist";
    	final String TAG_ALBUM = "album";
    	final String TAG_TITLE = "title";
    	final String TAG_ID = "id";
    	final String TAG_SONGS = "songs";
    	
    	JSONArray songsJSON = null;
    	//JSONParser jParser = new JSONParser();
    	
    	
    	try {
    		songsJSON = o.getJSONArray(TAG_SONGS);
    		
    		for(int i = 0; i < songsJSON.length(); i++) {
    			JSONObject c = songsJSON.getJSONObject(i);
    			String artist = c.getString(TAG_ARTIST);
    			String album = c.getString(TAG_ALBUM);
    			String title = c.getString(TAG_TITLE);
    			String id = c.getString(TAG_ID);
    			creation.add(new Song(artist,title,album, id));
    			
    		}
    	} catch (JSONException e) {
    		e.printStackTrace();
    	}
    	return creation;
    }

	
}