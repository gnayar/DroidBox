package com.example.droidbox;

import java.util.ArrayList;

public class SongList extends ArrayList<Song> {

	public SongList() {
		super();
	}


	//using a bubble sort which a complexity of O(n^2) which isn't too efficient  but so far seems fast enough
	
	
	public SongList sortByArtist() {

		//ATTEMPT AT BUBBLE SORTING BUT NOT WORKING CORRECTLY
		Song item;
		int j;
		boolean flag = true;
		while(flag) {
			flag = false;
			for(j = 0; j < this.size() -1; j++) {
				
				if(this.get(j).getArtist().compareTo(this.get(j+1).getArtist()) > 0) {
					item = this.get(j);
					this.set(j, this.get(j+1));
					this.set(j+1, item);
					flag = true;
				}
			}
		}
		return this;

/*		//INSERTION SORT ---- SEEMS TO BE WORKING
		for (int i = 1; i < this.size(); i++) {
			Song item = this.get(i);
			int index = i;

			while(index > 0 && this.get(index-1).getArtist().charAt(0) > item.getArtist().charAt(0)) {
				this.set(index, this.get(index -1));
				index = index - 1;
			}
			this.set(index, item);

			//TODO:what if repeating same artist name values? like joseph and josh??? add more loops such that if they are equal it will loop until one name runs out and that will be = 0
		}
		return this; */
	}
	
	public SongList sortByTitle() {
		Song item;
		int j;
		boolean flag = true;
		while(flag) {
			flag = false;
			for(j = 0; j < this.size() -1; j++) {
				
				if(this.get(j).getTitle().compareTo(this.get(j+1).getTitle()) > 0) {
					item = this.get(j);
					this.set(j, this.get(j+1));
					this.set(j+1, item);
					flag = true;
				}
			}
		}
		return this;
	}
	
	public SongList sortByAlbum() {
		Song item;
		int j;
		boolean flag = true;
		while(flag) {
			flag = false;
			for(j = 0; j < this.size() -1; j++) {
				
				if(this.get(j).getAlbum().compareTo(this.get(j+1).getAlbum()) > 0) {
					item = this.get(j);
					this.set(j, this.get(j+1));
					this.set(j+1, item);
					flag = true;
				}
			}
		}
		return this;
	}
}

