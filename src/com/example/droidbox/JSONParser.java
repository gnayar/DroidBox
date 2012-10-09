package com.example.droidbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import android.content.Context;

//example string
//{"songs":[{"id":"1","title":"Drunkship of Lanterns","artist":"The Mars Volta","album":"Deloused In the Comatorium"},{"id":


public class JSONParser {
	SongList list;
	Song current;
	
	public JSONParser() {
		list = new SongList();
	}
	
	public SongList parse(String hugeString, Context context) {
		try {
			BufferedReader reader = new BufferedReader(new FileReader(hugeString));
		
		} catch
		
		
		
		return list;
	}
}
