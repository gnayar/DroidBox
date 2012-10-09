package com.example.droidbox;
//simple queue. creates a new data structure that is a SongList, so it has all the same properties but is
//organized better as a queue.
public class QueuedList {
	public SongList queue;
	
	public QueuedList() {
		queue = new SongList();
	}
	
	public boolean isEmpty() {
		return(queue.size() == 0);
	}
	
	public void enqueue(Song song) {
		queue.add(song);
	}
	
	public Song pop() {
		Song top = queue.get(0);
		queue.remove(0);
		return top;
	}
	
	public Song peek() {
		return queue.get(0);
	}
}
