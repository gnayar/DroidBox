package com.example.droidbox;

import java.util.ArrayList;

public class SongList extends ArrayList<Song> {

	public SongList() {
		super();
	}


	public SongList sortByArtist() {

		//ATTEMPT AT BUBBLE SORTING BUT NOT WORKING CORRECTLY

	/*	boolean need = true;
		for(int k = 1; k < this.size(); k++) {
			need = false;
			for(int i = 0; i < this.size() - k; i++) {
				if(this.get(i).getArtist().charAt(0) < this.get(i+1).getAlbum().charAt(0)) {
					Song temp = this.get(i);
					this.set(i, this.get(i+1));
					this.set(i+1, temp);
					need = true;
				}
			}
		}
		return this; */


		//INSERTION SORT ---- SEEMS TO BE WORKING
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
		return this;
	}
}