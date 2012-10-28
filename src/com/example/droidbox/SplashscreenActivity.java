package com.example.droidbox;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class SplashscreenActivity extends Activity {
	private long splashDelay = 10;
	static boolean alreadyLogged = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        TimerTask task = new TimerTask(){
        	@Override
        	public void run(){
        		if( alreadyLogged == true){
	        		Intent intent = new Intent().setClass(SplashscreenActivity.this, Main.class);
	            	startActivity(intent);
	            	finish();
        		}
        		else{
        			Intent intent = new Intent().setClass(SplashscreenActivity.this, LoginActivity.class);
                	startActivity(intent);
                	finish();
        		}
        	}
        };
        Timer timer = new Timer();
        timer.schedule(task, splashDelay);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.splashscreen, menu);
        return true;
    }
}
