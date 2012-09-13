package com.example.droidbox;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;



public class ScanIn {
	public int currentSyncCode;
	public String artist, album, song;
	
	public ScanIn() {
		//if there is no sync code (on initialization) then just set to 0 so method will now to update it
		currentSyncCode = 0;
	}
	
	public ScanIn(int currentSyncCode) {
		currentSyncCode = this.currentSyncCode; //hopefully can pass in the currentSynccode
	}
	
	public ArrayList<Song> read() {  //TODO: return type of ArrayList<Song>
		ArrayList<Song> songs = new ArrayList<Song>();
		try { 
			File file = new File("update.txt");
			Scanner scanner = new Scanner(file);
			
			
			//check to see whether or not we should sync
			int newSyncCode = scanner.nextInt();
			
			if(currentSyncCode == newSyncCode) {
				//do not sync
				//do something else here
				System.out.println("Updated");
			}
			else {
				while(scanner.hasNextLine()) {
					if(scanner.hasNextLine()) {
						artist = scanner.nextLine();
					}
					if(scanner.hasNextLine()) {
						song = scanner.nextLine();
					}
					if(scanner.hasNextLine()) {
					album = scanner.nextLine();
					}
					System.out.println(artist + song + album); //only to check to see if this is working
					
					songs.add(new Song(song, artist, album));
					//TODO: create song objects and save the properties into Songs and then put them into an ArrayList<Song>
					//Added, test to see if it works - Gautam 
					
				}
				scanner.close();
				
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();//what happens to songs list here??
		}
		return songs;
	}
	
	
}
