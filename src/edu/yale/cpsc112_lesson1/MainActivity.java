package edu.yale.cpsc112_lesson1;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.MapFragment;
import android.widget.TextView.OnEditorActionListener;


public class MainActivity extends Activity {
	static final LatLng oldCampus = new LatLng(41.3085 , -72.9285);
	String phoneNumber = "";

	//41.3085 , 72.9285);
	private GoogleMap googleMap;
	private Marker marker;
	private LatLngBounds OLDCAMPUS = new LatLngBounds( 
			//new LatLng (41.3085 , 72.9285), new LatLng (42.3085 , 73.9285));
			new LatLng (0 ,0), new LatLng (1 , 1));
	Double x = 0.0;
	Double y = 0.0;
	String LocationFinal = "SAJ";
	LatLng xyz;
	String itemLost = "";
	
	Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Firebase.setAndroidContext(this);
		Firebase ref = new Firebase("https://fiery-heat-5866.firebaseio.com/");
		//Firebase ref2 = new Firebase("https://fiery-fire-1683.firebaseio.com"); 
		
		
		 //Textbox to enter your number  
		// EditText editMessage = (EditText) findViewById(R.id.edit_message);
		/* editMessage.setOnEditorActionListener(new OnEditorActionListener() {
			 @Override
			 public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				 boolean handled = false;
		         if (actionId == EditorInfo.IME_ACTION_DONE) {

		        	 handled = true;
		         }
		     return handled;
		     }
			
		     
		 });*/
		
		 //TextView myTextView = (TextView) findViewById(R.id.textView1);
		 //myTextView.setText(phoneNumber);
			//TextView
		 
		 //End of Textbox to enter number 
		//Textbox to enter your LostItem 
		 /*EditText editMessage2 = (EditText) findViewById(R.id.edit_message2);

		 editMessage2.setOnEditorActionListener(new OnEditorActionListener() {
			 @Override
			 public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
		         boolean handled = false;
		         if (actionId == EditorInfo.IME_ACTION_DONE) {
		             handled = true;
		         }
		         
		         return handled;
		     }
		     
		 });*/
		//Textbox to end storage of LostItem 
		try { 
			if (googleMap == null) {
				googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			}

			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oldCampus, 15));
			googleMap.setOnMapClickListener(new OnMapClickListener () { 
				public void onMapClick(LatLng point) {
					Log.d("Map","Map clicked");
					LocationFinal = point.toString(); 
					//Firebase 
					Firebase ref = new Firebase("https://fiery-heat-5866.firebaseio.com/");//Since this is its own method we define the same firebase reference again
					//ref.child(itemLost).setValue(LocationFinal); //this is the main sender to Firebase 
					//Firebase 	
				}

			}

					);

		} catch (Exception e) {

			e.printStackTrace();
		}


		//Outputting to textbox 


		//end of textbox 	
		//String Location = "On my body";

		// ref.child(itemLost).setValue(LocationFinal);


		/*
		 Map<String,String> map2 = new HashMap <String, String> ();
		 String itemLost = "My Head";  
 		 String location = "On body"; 
 		 map2.put(itemLost, location); 
		 System.out.println("PrintOutput");
		 Firebase map1Ref = ref.child("map2");*/
		ref.child(itemLost).addValueEventListener(new ValueEventListener () {
			public void onDataChange(DataSnapshot snapshot) {
				System.out.println(snapshot.getValue());
				String outputLocation;
				outputLocation = snapshot.toString();
				//TextView myTextView = (TextView) findViewById(R.id.textView1);
				//myTextView.setText(outputLocation);
			}
			public void onCancelled(FirebaseError error) {

			}

		});

		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {
	
		EditText editMessage = (EditText) findViewById(R.id.edit_message);
		EditText editMessage2 = (EditText) findViewById(R.id.edit_message2);

			@Override
			public void onClick(View v) {
				String saransNumber = "9546873395";
				String phoneNumber2 =  editMessage.getText().toString();
				String itemLost2 = 	editMessage2.getText().toString();
				String keyValue = phoneNumber2+itemLost2;
				Firebase ref = new Firebase("https://fiery-heat-5866.firebaseio.com/");
				ref.child(keyValue).setValue(LocationFinal);
				TextView myTextView = (TextView) findViewById(R.id.textView1);
				myTextView.setText(phoneNumber2+itemLost2);
				
			}
		});   

	}
}