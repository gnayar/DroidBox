package com.example.droidbox;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class ScanIn {
	private int currentSyncCode;
	
	public ScanIn() {
		//if there is no sync code (on initialization) then just set to 0 so method will now to update it
		currentSyncCode = 0;
	}
	
	public ScanIn(int currentSyncCode) {
		currentSyncCode = this.currentSyncCode; //hopefully can pass in the currentSynccode
	}
	
	public void read() {  //TODO: return type of ArrayList<Song>
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
					String artist = scanner.nextLine();
					String song = scanner.nextLine();
					String album = scanner.nextLine();
					System.out.println(artist + song + album); //only to check to see if this is working
				}
				scanner.close();
				
			}
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ScanIn scan = new ScanIn();
		scan.read();
	}
	
}
