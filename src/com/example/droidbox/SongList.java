package com.example.droidbox;

import java.util.ArrayList;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class SongList extends ArrayList<Song> implements Parcelable{

	public SongList() {
		super();
		
	}
	
	

	//--------------------------------------------------Parcelable Methods---------	
	public SongList(Parcel in) {
		this();
		readFromParcel(in);
	}
	
	@SuppressWarnings("unchecked")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
		public SongList createFromParcel(Parcel in) {
			return new SongList(in);
		}
		public Object[] newArray(int arg0) {
			return null;
		}
	};
	
	public void readFromParcel(Parcel in) {
		this.clear();
		int size = in.readInt();
		for(int i = 0; i < size; i++) {
			Song temp = new Song();
			temp.setArtist(in.readString());
			temp.setTitle(in.readString());
			temp.setAlbum(in.readString());
			temp.setID(in.readString());
			this.add(temp);
		}
	}
	
	public void writeToParcel(Parcel dest, int flags) {
		int size = this.size();
		dest.writeInt(size);
		
		for(int i = 0; i < size; i++) {
			Song temp = this.get(i);
			dest.writeString(temp.getArtist());
			dest.writeString(temp.getTitle());
			dest.writeString(temp.getAlbum());
			dest.writeString(temp.getID());
		}	
	}
	
	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	//-----------------------------------------------------------------------------------end of parcelable
	
	
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
	
	
	
	
	public SongList searchByTitle(String search) {
		SongList results = new SongList();
		
//		//not tested
//		
//		//midpoint method first
//		//first need the songlist in lexical order
//		SongList library = this.sortByTitle();
//		
//		//initialize bounds and find where the midpoint is
//		int a = 0;
//		int b = library.size();
//		int midpoint = (b-a)/2;
//		
//		//and now using the comparator
//		while(search.compareTo(library.get(midpoint).getTitle()) != 0) {
//			if(search.compareTo(library.get(midpoint).getTitle()) < 0) {
//				//so the search is less, need to reset b
//				b = midpoint;
//			}
//			else if(search.compareTo(library.get(midpoint).getTitle()) > 0) {
//				a = midpoint;
//			}
//			//lastly need to reset the midpoint
//			midpoint = (b-a)/2;
//		}
//		results.add(library.get(midpoint));
		
		
		//contain method...surely slower and just as accurate but more reliable
		SongList library = this.sortByTitle(); //isn't necessary for this type of search
		for(int i = 0; i < this.size(); i++) {
				if(search.contains(library.get(i).getTitle())) {
					results.add(library.get(i));
				}
		}
		return results;
		
	}

	public SongList searchByAlbum(String search) {
//		SongList results = new SongList();
//		
//		//not tested
//		
//		//midpoint method first
//		//first need the songlist in lexical order
//		SongList library = this.sortByAlbum();
//		
//		//initialize bounds and find where the midpoint is
//		int a = 0;
//		int b = library.size();
//		int midpoint = (b-a)/2;
//		
//		//and now using the comparator
//		while(search.compareTo(library.get(midpoint).getAlbum()) != 0) {
//			if(search.compareTo(library.get(midpoint).getAlbum()) < 0) {
//				//so the search is less, need to reset b
//				b = midpoint;
//			}
//			else if(search.compareTo(library.get(midpoint).getAlbum()) > 0) {
//				a = midpoint;
//			}
//			//lastly need to reset the midpoint
//			midpoint = (b-a)/2;
//		}
//		results.add(library.get(midpoint));
//		
//		return results;
		
		SongList results = new SongList();
		SongList library = this.sortByAlbum(); //isn't necessary for this type of search
		for(int i = 0; i < this.size(); i++) {
				if(search.contains(library.get(i).getAlbum())) {
					results.add(library.get(i));
				}
		}
		return results;
		
	}
	
	
	public SongList searchByArtist(String search) {
		SongList results = new SongList();
		SongList library = this.sortByArtist(); //isn't necessary for this type of search
		for(int i = 0; i < this.size(); i++) {
				if(search.contains(library.get(i).getArtist())) {
					results.add(library.get(i));
				}
		}
		return results;
	}
	
	
	//IMPPROVED SEACHING METHODS using regular expressions - as good as its gonna get
		//should be able to include artist,title,album all one in method
		
		//worked on test program and is implemented in searchable but UNTESTED (11/16)
		public SongList searchUsingRegex(String search) {
			SongList results = new SongList();
			//shouldn't need to sort it! saves time
			
			Pattern pattern = Pattern.compile(search, Pattern.CASE_INSENSITIVE);
			Matcher matcher = pattern.matcher(this.get(0).getTitle()); //just a place holder so only construct one
			
			//three loops; one for artist, one for album, one for title
			for(int i = 0; i < this.size(); i++) {
				matcher.reset(this.get(i).getArtist());
				if(matcher.find()) {
					results.add(this.get(i));
				}
			}
			
			for(int i = 0; i < this.size(); i++) {
				matcher.reset(this.get(i).getAlbum());
				if(matcher.find()) {
					results.add(this.get(i));
				}
			}
			
			for(int i = 0; i < this.size(); i++) {
				matcher.reset(this.get(i).getTitle());
				if(matcher.find()) {
					results.add(this.get(i));
				}
			}
			
			return results;
			
			
		}
	
	
	//----------------------------------------------------------------------------------
	//VOTING METHODS
	
	public void moveUp(SongList sl, int position) {
		//where sl is the currently being played SongList
		//where position is the song that is selected (so in this case position-1 is the slot its moving to)
		//just going to increase a songs position with the one before it and have them swap places
		
		Song temp = sl.get(position-1); //temporary hold for the song that will be removed
		sl.add(position-1, sl.get(position));
		sl.add(position, temp);
		
	}
	
	public void moveDown(SongList sl, int position) {
		//where sl is the currently being played SongList
		//where position is the song that is selected (so in this case position+1 is the slot its moving to)
		
		Song temp = sl.get(position+1); //temp for hold for where the song is going
		sl.add(position+1, sl.get(position));
		sl.add(position, temp);
		
		
	}


}

