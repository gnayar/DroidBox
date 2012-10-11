package com.example.droidbox;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
//uses up navigation in the manifest, should be changed later so users can't go back to the queue without signing in
public class LoginActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_login, menu);
        return true;
    }
    
    public void KillApp(View view)
    {
    	//close the application...somehow
    }
    public void Authenticate(View view)
    {
    	Intent intent = new Intent(this, Main.class);
    	startActivity(intent);//goes to main screen AND users can't "back" into this login screen again
    }
    
    public void onBackPressed() {
    	//overriding back button so that user can't go back to main screen.
    }
    
}
