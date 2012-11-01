package com.example.droidbox;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

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
	public View getView (int position, View convertView, ViewGroup parent){
		convertView = (RelativeLayout) inflater.inflate(resource,  null);
		Song song = getItem(position);
		TextView title = (TextView) convertView.findViewById(R.id.title);
		title.setText(song.getTitle());
		TextView album = (TextView) convertView.findViewById(R.id.album);
		album.setText(song.getAlbum());
		TextView artist = (TextView) convertView.findViewById(R.id.artist);
		artist.setText(song.getArtist());
		return convertView;
		
	}
	
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
