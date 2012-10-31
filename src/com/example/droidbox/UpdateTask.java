package com.example.droidbox;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

public class UpdateTask extends AsyncTask<Integer,Void, Integer> {
	Activity activity;
	public UpdateTask(Activity activity){
		this.activity = activity;
	}

	@Override
	protected Integer doInBackground(Integer... millis) {
        try {
            int waited = 0;
            int duration = 10000;
            while (waited < duration) {
                Thread.sleep(100);
                waited += 100;
            }
        } catch (InterruptedException e) {
            // do nothing
        }
        Log.v("Return", "Refresh");
        ((Main)activity).onResume();

        return 1;
    }

}
