package com.example.droidbox;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import android.content.Context;
import android.widget.Toast;

public class testFileWriter {
	public testFileWriter(Context context){
		final String TESTSTRING = new String("1\nArtist: Artist1\nSong: Song1\nAlbum: Album1");
		File myDir = new File(context.getFilesDir().getAbsolutePath());
		try {
			FileWriter write = new FileWriter(myDir + "/update.txt");
			write.write(TESTSTRING);
			write.close();
			Toast.makeText(context, "file created", Toast.LENGTH_SHORT).show();
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
