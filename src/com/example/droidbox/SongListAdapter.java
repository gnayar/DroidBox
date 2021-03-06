package com.example.droidbox;
//how to get updateQueue() working
//how to get tableNumber and tablePasscode
//both issues stem from that the fact that I can't access Main
import org.json.JSONObject;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Application;


public class SongListAdapter extends ArrayAdapter<Song> {
	
	private int resource;
	private LayoutInflater inflater;
	private Context context;
	
	public SongListAdapter(Context ctx, int textViewResourceId,
			SongList objects) {
		super(ctx, textViewResourceId, objects);
		this.resource = textViewResourceId;
		this.inflater = LayoutInflater.from(ctx);
		context = ctx;
	}
	@Override
	public View getView (final int position, View convertView, ViewGroup parent){
		convertView = (RelativeLayout) inflater.inflate(resource,  null);
		final Song song = getItem(position);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		title.setText(song.getTitle());
		TextView album = (TextView) convertView.findViewById(R.id.album);
		album.setText(song.getAlbum());
		TextView artist = (TextView) convertView.findViewById(R.id.artist);
		artist.setText(song.getArtist());
		
		return convertView;
	}
		
//    public void imageButtonClick(View v) {
//    	Log.v("imagebutton", "it worked!");
//    	//this is where request to upvote should go
//
//    	
//    }
	
	
	
	public void refreshSongs(SongList songs) {
		this.clear();
		for(int i =0; i < songs.size(); i++) {
			this.add(songs.get(i));
		}
	}
	
	@Override
	public void notifyDataSetChanged() {
		super.notifyDataSetChanged();
		Log.v("Return", "notifyDataSetChanged");
	}
	
		

}
