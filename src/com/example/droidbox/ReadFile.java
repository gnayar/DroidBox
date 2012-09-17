package com.example.droidbox;
import java.io.*;
import java.util.ArrayList;

import android.content.Context;
import android.content.res.AssetManager;
import android.widget.Toast;
public class ReadFile {
	public int currentSyncCode;
	private String newSyncCode1;
	public String artist, album, song;
	public boolean synced;
	
	public ReadFile() {
		//if there is no sync code (on initialization) then just set to 0 so method will now to update it
		currentSyncCode = 0;
		//newSyncCode1 = "constructed";
		synced = false;
	}
	
	public ReadFile(int currentSyncCode) {
		currentSyncCode = this.currentSyncCode; //hopefully can pass in the currentSynccode
	}
	
	public ArrayList<Song> read(String file, Context context) {
		ArrayList<Song> songs = new ArrayList<Song>();
		try {
		
			Toast.makeText(context, "scanning", Toast.LENGTH_LONG).show();
			AssetManager am = context.getAssets();
			InputStream fstream = am.open(file);
			//HAVING PROBLEM WITH THIS LINE ^^^^^^^^^
			//not seeing the file even though the File object has been created.
			
			
			//move this variable around to test toasts
			
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			//for debugging - trying to check if reading sync code correctly.
			newSyncCode1 = reader.readLine();
			int newSyncCode = Integer.parseInt(newSyncCode1); //first read new sync code and convert to int
			
			if(currentSyncCode == newSyncCode) {
				synced = true;
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
			Toast.makeText(context, "file not found", Toast.LENGTH_LONG).show();
			//newSyncCode1 = "Caught exception";
		}
		return songs;
	}
	
	public boolean isSynced() {
		return synced;
	}
	
	public String getNewSyncCode() {
		return newSyncCode1;
	}
}
