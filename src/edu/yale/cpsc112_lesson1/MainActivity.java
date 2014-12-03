package edu.yale.cpsc112_lesson1;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.Firebase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Firebase.setAndroidContext(this);
		setContentView(R.layout.activity_main);
		 Firebase ref = new Firebase("https://fiery-heat-5866.firebaseio.com/");
		 String itemLost = "My Head";
		 String Location = "On my body";
		 ref.child(itemLost).setValue(Location);
		 /*
		 Map<String,String> map2 = new HashMap <String, String> ();
		 String itemLost = "My Head";  
 		 String location = "On body"; 
 		 map2.put(itemLost, location); 
		 System.out.println("PrintOutput");
		 Firebase map1Ref = ref.child("map2");*/
		 
		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				try {

					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.putExtra("sms_body", "Hello, World");
					smsIntent.setType("vnd.android-dir/mms-sms");
					startActivity(smsIntent);

				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "SMS faild!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}
			}
		}); //why is this semi-colon here. What is this the syntax off?  
		
	}
}