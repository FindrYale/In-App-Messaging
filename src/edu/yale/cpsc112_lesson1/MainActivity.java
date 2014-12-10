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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.os.Bundle;
import com.google.android.gms.maps.MapFragment;


public class MainActivity extends Activity {
	 static final LatLng oldCampus = new LatLng(41.3085 , -72.9285);
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
	 String itemLost = "My Head";

	Button button;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Firebase.setAndroidContext(this);
		Firebase ref = new Firebase("https://fiery-heat-5866.firebaseio.com/");
		 
			try { 
	            if (googleMap == null) {
	               googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
	            }
	         //googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	         //Marker TP = googleMap.addMarker(new MarkerOptions().position(oldCampus).title("oldCampus"));
	        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oldCampus, 15));
	        //googleMap.moveCamera(CameraUpdateFactory.newLatLngBounds(OLDCAMPUS,14));
	        
	         googleMap.setOnMapClickListener(new OnMapClickListener () { 
	        	 public void onMapClick(LatLng point) {
	        		Log.d("Map","Map clicked");
	        		LocationFinal = point.toString(); 
	        		// x = Double.valueOf(point.latitude); //pointlatitude 
	        		// y = Double.valueOf(point.longitude); //pointlatitude
	        	//Textbox
	        		TextView myTextView = (TextView) findViewById(R.id.textView1);
	        		myTextView.setText(LocationFinal);
	        	//Textbox
	        	//Firebase 
	        		Firebase ref = new Firebase("https://fiery-heat-5866.firebaseio.com/");
	        		ref.child(itemLost).setValue(LocationFinal);
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
		 }
		 public void onCancelled(FirebaseError error) {
			 
		 }
		
		 });
		
	

		button = (Button) findViewById(R.id.button);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String saransNumber = "9546873395";

				try {

					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					smsIntent.putExtra("address", saransNumber);
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