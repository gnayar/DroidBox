package com.example.droidbox;
import java.io.*;
import java.util.ArrayList;

public class ReadFile {
	public int currentSyncCode;
	public String artist, album, song;
	
	public ReadFile() {
		//if there is no sync code (on initialization) then just set to 0 so method will now to update it
		currentSyncCode = 0;
	}
	
	public ReadFile(int currentSyncCode) {
		currentSyncCode = this.currentSyncCode; //hopefully can pass in the currentSynccode
	}
	
	public ArrayList<Song> read() {
		ArrayList<Song> songs = new ArrayList<Song>();
		try {
			
			FileInputStream fstream = new FileInputStream("update.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			
			int newSyncCode = Integer.parseInt(reader.readLine()); //first read new sync code and convert to int
			
			if(currentSyncCode == (int) newSyncCode) {
				System.out.println("updated");
				//exit
			}
			else {
				//library needs to be updated
				//how can I tell the file is done? there is no hasNext() ??
				while( (artist = reader.readLine()) != null) {
					album = reader.readLine();
					song = reader.readLine();
					songs.add(new Song(artist, album, song));
				}
			}
			in.close(); //close the buffer
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return songs;
	}
}
