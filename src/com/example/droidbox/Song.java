package com.example.droidbox;

import android.os.Parcel;
import android.os.Parcelable;

public class Song implements Parcelable{
	private String title;
	private String artist;
	private String album;
	private String ID;
	private String req_type;
	private String num_votes;
	
	public Song() {
		title = "";
		artist = "";
		album = "";
		ID = "";
		req_type = "0";
		num_votes = "0";
	}
	public Song(String artist, String title, String album, String ID) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.ID = ID;
		req_type = "0";
		num_votes = "0";
	}
	
	public Song(String artist, String title, String album, String ID, String req_types, String num_votes) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.ID = ID;
		this.req_type = req_type;
		this.num_votes = num_votes;
	}	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getArtist() {
		return artist;
	}
	public void setArtist(String artist) {
		this.artist = artist;
	}
	public String getAlbum() {
		return album;
	}
	public void setAlbum(String album) {
		this.album = album;
	}
	public String getID() {
		return ID;		
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getNumVotes() {
		return num_votes;
	}
	
	public void setNumVotes( String num_votes)
	{
		this.num_votes = num_votes;
	}
	
	public String getReqType() {
		return req_type;
	}
	
	public void setReqType(String req_type) {
		this.req_type = req_type;
	}
	
	//-------------------------------------------Parcelable Methods--------------------------------//	
	public int describeContents()
	{
        return 0;
    }

	public void writeToParcel(Parcel dest, int flags)
	{
		dest.writeString(this.getArtist());
		dest.writeString(this.getTitle());
		dest.writeString(this.getAlbum());
		dest.writeString(this.getID());
		dest.writeString(this.getReqType());
		dest.writeString(this.getNumVotes());
		
	}
	
	public static final Parcelable.Creator<Song> CREATOR = new Parcelable.Creator<Song>()
	{
		public Song createFromParcel(Parcel in)
		{
			return new Song(in);
		}
		public Song[] newArray(int size)
		{
			return new Song[size];
		}
	};
	
	 private Song(Parcel in)
	 {
		this.setArtist(in.readString());
		this.setTitle(in.readString());
		this.setAlbum(in.readString());
		this.setID(in.readString());
		this.setReqType(in.readString());
		this.setNumVotes(in.readString());
		
     }
	//-------------------------------------end of parcelable---------------------------------------------//
	
}
