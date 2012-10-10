package com.example.droidbox;

public class Song {
	private String title;
	private String artist;
	private String album;
	private String ID;
	
	public Song() {
		title = "";
		artist = "";
		album = "";
		ID = "";
	}
	public Song(String artist, String title, String album, String ID) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.ID = ID;
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
	
	
}
