package com.example.droidbox;

public class Song {
	private String title;
	private String artist;
	private String album;
	
	public Song() {
		title = "";
		artist = "";
		album = "";
	}
	public Song(String title, String artist, String album) {
		super();
		this.title = title;
		this.artist = artist;
		this.album = album;
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
	
	
}
