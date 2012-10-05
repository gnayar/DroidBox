package com.example.droidbox;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

public class queueListView extends ListView {
	Context ctx;
    public queueListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public void onListItemClick(ListView l, View v, int position, long id) {
        // Do something when a list item is clicked
		
		Song song = (Song) l.getItemAtPosition(position);
		CharSequence test = (CharSequence) song.getTitle();
		Toast.makeText(ctx, test, 15).show();

    }
	
	
}
