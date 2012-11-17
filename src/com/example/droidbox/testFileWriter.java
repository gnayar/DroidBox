package com.example.droidbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

public class testFileWriter {
	File myDir;
	FileWriter write;
	public SongList queue;
	
	
	public testFileWriter() {
		
	}
	public testFileWriter(Context context){
		//final String TESTSTRING = new String("1\nArtist1\nSong1\nAlbum1");
		myDir = new File(context.getFilesDir().getAbsolutePath());
		try {
			write = new FileWriter(myDir + "/update.txt");
			//write.write(TESTSTRING);
			write.close();
			//Toast.makeText(context, "file created", Toast.LENGTH_SHORT).show();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void update(Context context, SongList queue) {
		
		
		myDir = new File(context.getFilesDir().getAbsolutePath());
		try {
			write = new FileWriter(myDir + "/update.txt");
			for(int i = 0; i < queue.size(); i++ ){
				write.write(queue.get(i).getArtist() + "\n" + queue.get(i).getTitle() + "\n" + queue.get(i).getAlbum() + "\n" + queue.get(i).getID() + "\n");
				Log.v("artist", queue.get(i).getArtist());
				Log.v("title", queue.get(i).getTitle());
				Log.v("album", queue.get(i).getAlbum());

			}	
			write.close();
			//Toast.makeText(context, "file created", Toast.LENGTH_SHORT).show();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
}
