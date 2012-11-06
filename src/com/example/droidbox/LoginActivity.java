package com.example.droidbox;

import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
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
    	
    	
    	EditText nick = (EditText) findViewById(R.id.editText1);//getting the table nickname
    	String nickname = nick.getText().toString();
    	
    	EditText pass = (EditText) findViewById(R.id.editText2);//getting the password
    	String tablePassword = pass.getText().toString();
    	
    	Log.v("LoginCheck", tableNumber);
    	Log.v("LoginCheck", nickname);
    	Log.v("LoginCheck", tablePassword);
    	
    	SharedPreferences loginSettings = getSharedPreferences("LoginDetails",0);
    	SharedPreferences.Editor edit = loginSettings.edit();
    	edit.putString("nickname",nickname);
    	edit.putString("tableNumber", tableNumber);
    	edit.putString("tablePassword",tablePassword);
    	edit.commit();
    	
    	JSONParser jParser = new JSONParser();
    	
        // getting JSON string from URL
    	String url = "http://9.12.10.1/db-wa/checkCredentials.php";
    	JSONObject json = new JSONObject();
        try {
			json = jParser.execute(url,"t_num",tableNumber,"t_code",tablePassword).get();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ExecutionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
      
        int success = readSuccessFromJSON(json);
    	if(success != 0){
    		Toast.makeText(this, "Wrong Table Number or Passcode", Toast.LENGTH_SHORT).show();
    		num.setText("");
    		nick.setText("");
    		pass.setText("");
    		num.requestFocus();
    	}
    	else{
    	Intent intent = new Intent(this, Main.class);
    	startActivity(intent);
    	finish();//goes to main screen AND users can't "back" into this login screen again
    	}
        
    }
   
    public int readSuccessFromJSON(JSONObject json){
    	int suc = 5;
    	try {
    		
    		suc = json.getInt("success");
    		String message = json.getString("message");
    		Log.v("LoginCheck",String.valueOf(suc));
    		Log.v("LoginCheck", message);
    	}
    	catch (JSONException e) {

    		Log.v("LoginCheck","Failed");
    		e.printStackTrace();
    		
    	}
    	return suc;
    }
    public void onBackPressed() {
    	//overriding back button so that user can't go back to main screen.
    }
    
}
