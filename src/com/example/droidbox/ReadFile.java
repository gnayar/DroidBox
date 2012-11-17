package com.example.droidbox;
import java.io.*;
import java.util.ArrayList;

import android.content.Context;
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
	
	public SongList read(File myDir,String file, Context context) {
		SongList songs = new SongList();
		try {
		
			//Toast.makeText(context, "scanning", Toast.LENGTH_SHORT).show();
			
			BufferedReader reader = new BufferedReader(new FileReader(myDir + file));
			//Toast.makeText(context, "file found", Toast.LENGTH_SHORT).show();

			//for debugging - trying to check if reading sync code correctly.
			//newSyncCode1 = reader.readLine();
			//Toast.makeText(context, "read int: "+newSyncCode1+"b", Toast.LENGTH_SHORT).show();
			//int newSyncCode = Integer.parseInt(newSyncCode1); //first read new sync code and convert to int
			//Toast.makeText(context, "sync check", Toast.LENGTH_SHORT).show();
			//if(currentSyncCode == newSyncCode) {
			//	synced = true;
			//	//exit
			//}
			//else {
				//library needs to be updated
				//how can I tell the file is done? there is no hasNext() ??
				//Toast.makeText(context, "updating", Toast.LENGTH_SHORT).show();
				while( (artist = reader.readLine()) != null) {
					song = reader.readLine();
					album = reader.readLine();
					String ID = reader.readLine();
					songs.add(new Song(artist, song, album, ID)); //needs to be changed to account for ID's
					//Toast.makeText(context, "added", Toast.LENGTH_SHORT).show();
				//}
			}
			//in.close(); //close the buffer
			reader.close();
		}catch (FileNotFoundException e) {
		    e.printStackTrace();
		    Toast.makeText(context, "file not found", Toast.LENGTH_SHORT).show();
		} catch (IOException e) {
		    e.printStackTrace(); 
		    Toast.makeText(context, "could not write", Toast.LENGTH_SHORT).show();
		}catch(NumberFormatException e){
			e.printStackTrace(); 
		    Toast.makeText(context, "number issue", Toast.LENGTH_SHORT).show();
		}catch (Exception e) {
		    e.printStackTrace(); 
		    Toast.makeText(context, "general exception", Toast.LENGTH_SHORT).show();
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
