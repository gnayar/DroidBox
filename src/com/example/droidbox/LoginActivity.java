package com.example.droidbox;

import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
//uses up navigation in the manifest, should be changed later so users can't go back to the queue without signing in
public class LoginActivity extends Activity {
	
	public static String tableNumber;
	public static String tablePasscode;
	public static String tableNickname;
	public static String requestType; // 0 paid, 1 non-paid
	
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
    	//close the application
    	finish();
    	
    }
    public void Authenticate(View view)
    {
    	EditText num = (EditText) findViewById(R.id.editText0);//getting the table number
    	String tableNumber = num.getText().toString();
    	this.tableNumber = tableNumber;
    	
    	EditText nick = (EditText) findViewById(R.id.editText1);//getting the table nickname
    	String nickname = nick.getText().toString();
    	this.tableNickname = nickname;
    	
    	EditText pass = (EditText) findViewById(R.id.editText2);//getting the password
    	String tablePassword = pass.getText().toString();
    	this.tablePasscode = tablePassword;
    	
    	communicate( tablePassword, tableNumber, nickname, "0");//passing parameters to helper method communicate
    	
    	Intent intent = new Intent(this, Main.class);
    	startActivity(intent);
    	finish();//goes to main screen AND users can't "back" into this login screen again
        SplashscreenActivity.alreadyLogged = true;//set boolean to true!
    }
    
    public void communicate( String tablePassword, String tableNumber, String nickname, String requestType) 
    {
    	
    	JSONParser jParser = new JSONParser();
    	
        // getting JSON string from URL
    	String url = "http://9.12.10.1/db-wa/getLibrary.php";
    	 JSONObject json = new JSONObject();
        try {//passing in all parameters for testing purposes.
			json = jParser.execute(url, "t_num", tableNumber, "t_code", tablePassword, "req_type", requestType).get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void onBackPressed() {
    	//overriding back button so that user can't go back to main screen.
    }
    
}
