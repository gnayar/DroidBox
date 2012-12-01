package com.example.droidbox;

public class Song {
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
	public String getReqType() {
		return req_type;
	}
	
	
	
}
