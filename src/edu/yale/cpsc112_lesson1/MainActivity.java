package edu.yale.cpsc112_lesson1;

import java.util.HashMap;
import java.util.Map;

import com.firebase.client.ChildEventListener;
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
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
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
	
//Please see Read Me File for full description of program functionality. In this part of the program we 
//define global variables that are used throughout the program. 
//The uncommon global variables include some uncommon object types that are described below: - 
	//1. A LatLng is an object that contains 2 doubles that serve as the x, y coordinate 
	//when a point is clicked on Google Maps. It comes defined with the Google Maps imports and library. 
	//2. A button and a GoogleMap are identifiers used to call a defined button and googleMap 
	// using the name given to it in the Activity-Main.xml file. 
//Note full citations for every line of code can be found on the Developer Pages (that are linked on the ReadMe).On this file I will refer to these citations.  
	static final LatLng oldCampus = new LatLng(41.3085 , -72.9285);
	static final LatLng sterlingLatLng = new LatLng( 41.3113, -72.9291);
	static final LatLng sillimanLatLng = new LatLng(41.310864, -72.924953);
	static final double sterlingRadius = new Double(100);
	static final LatLng brLatLng = new LatLng( 41.3098, -72.9298);
	static final double brRadius = new Double(100);

	Double[] testLat = {0.00041, 0.000608};
	Double[] testLng = {0.001, 0.000413};
	static final LatLng[] circleLatLng = {sterlingLatLng,brLatLng};
	static final Double[] circleRad = {sterlingRadius, brRadius};
	LatLng trialLocClick = new LatLng(41.3085 , -72.9285);
	// LatLng trialLocClickRet = new LatLng(23, 25);
	static final String keyValue = "";
	String n0 = "";
	String phoneNumber = "asas";
	String newNumber = "9174779589";
	String itemLost2 = "";
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
	String test ="";
	Button button;
	String Locat = "";
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//These next 2 lines  set up Firebase. First line, initializes the Firebase Library. 
		//Second line creates a reference to the unique Firebase we have used. For introduction 
		//to what is Firebase and how it is used in context of our app, please see ReadMe/Firebase. 
		Firebase.setAndroidContext(this);
		Firebase ref = new Firebase("https://fiery-heat-5866.firebaseio.com/");
		
		//This chunk of code calls from Firebase all the phone numbers stored on it. 
		//The Value Event Listener 'listens' for a Data Snapshot, that is an object that detects all 
		//the data stored on the Firebase at a point in time. 
		ref.addValueEventListener(new ValueEventListener() {
		    @Override
		    public void onDataChange(DataSnapshot snapshot) {
		         Object phoneN = snapshot.getValue();
		         //Here we create an object called phoneN that gets the values of the 
		        //Data Snapshot from Firebase. In the next Line we convert this to a string in order 
		         //then parse it. We parse this string using a method defined in the Circles Class 
		         //and will call this String once a button is pressed. 
		         phoneNumber = phoneN.toString();
		        
		  
		    }
		    //The below 3 lines are a try catch for Firebase which ensures if Firebase is 
		    //unable to read, it returns an error message and does not crash. 
		    //Note, app still works if phone is not connected to the Internet. 
		    @Override
		    public void onCancelled(FirebaseError firebaseError) {
		        System.out.println("The read failed: " + firebaseError.getMessage());
		    }
		});
		//All the above Firebase Code has been retrieved from the Firebase developer pages called 
		//Retrieving Data and Saving Data. 
		
		//The code below brings up the Google Map, by calling on the ID it was given in the XML File. 
		//We defined the Google Map through the XML file, and just call it here. 
		//The try statement is to ensure that Java reads the Google Map that has been defined in the XML.
		//The If statement looks to populate the defined Google Map, if it's empty with attributes 
		//we define in the Main Acitvity. 
		
		try { 
			if (googleMap == null) {
				googleMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
			}
			Circle sterlingLibrary = googleMap.addCircle(new CircleOptions()

			.center(new LatLng( 41.3113, -72.9291))
			.radius(100));

			Circle branfordCollege = googleMap.addCircle(new CircleOptions()

			.center(new LatLng( 41.3098, -72.9298))
			.radius(100));

			LatLng[] circleLatLng = {sterlingLibrary.getCenter(), branfordCollege.getCenter()};
			Double[] circleRad = {sterlingLibrary.getRadius(), branfordCollege.getRadius()};
			//The lines below initialize the map to the view that we need.
			//Move Camera is a pre-written Google Map Method to move the camera to a defined a latlng 
			//and a given level of zoom. The SetOnMapClick listens for a click on the Map. A click 
			//is a predefined single touch of the screen, inside the google map. 
			//If you run the program, you can see a mapclick registered on the logcat. The click 
			//method takes a LatLng called point as its parameter.  
			googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(oldCampus, 15));
			googleMap.setOnMapClickListener(new OnMapClickListener () { 
				public void onMapClick(LatLng point) {
					Log.d("Map","Map clicked");
					//Below I call a global LatLng Variable and then set it equal to point. 
					//x and y are global doubles that store the x and y coordinates individually, as doubles. 
					//The .latitude and .longitude function method calls convert to doubles. 
					trialLocClick = point; 
					x = trialLocClick.latitude;
					y = trialLocClick.longitude;
	
					//All the Google map code except actually converting the clicked point 
					//to a double were taken from the Google Maps Android Page 
					//with the following particular pages: 
					// - Interacting with the Map 
					// - Setting up the Map 
				}

			}


					);

		} catch (Exception e) {//the exception occurs if any XML attributes are wrong, and prevents the 
			//program from crashing. 

			e.printStackTrace();
		}



		button = (Button) findViewById(R.id.button);
		button.setOnClickListener(new OnClickListener() {

			EditText editMessage = (EditText) findViewById(R.id.edit_message);
			EditText editMessage2 = (EditText) findViewById(R.id.edit_message2);

			@Override
			public void onClick(View v) {
				String phoneNumber2 =  editMessage.getText().toString();
				 itemLost2 = 	editMessage2.getText().toString();
				String keyValue = phoneNumber2;
				Firebase ref = new Firebase("https://fiery-heat-5866.firebaseio.com/");
				//	Firebase latRef = ref.child(keyValue).child("Lat");
				//	Firebase lngRef = ref.child(keyValue).child("Lng");
				ref.child(keyValue).setValue(itemLost2);
				//ref.child(keyValue).setValue(y);
				//latRef.child("Lat").setValue(x);
				//lngRef.child("Lng").setValue(y);
				//	TextView myTextView = (TextView) findViewById(R.id.textView1);
				//	myTextView.setText(phoneNumber2+itemLost2);


				double x = trialLocClick.latitude;
				double y = trialLocClick.longitude;
				double testLatLng;
				double testRad;
				//building off the skills we learned in CPSC 112, we developed the following 
				//for loop, which checks to see if the user has clicked within a valid circle
				for (int i= 0; i<circleLatLng.length; i++) {
					testRad = circleRad[i];
					double testX = (x - circleLatLng[i].latitude);
					double testY = (y - circleLatLng[i].longitude);

					//the following if statement checks to see which circle the user clicked inside of.
					if (Math.abs(testX) < 0.00041 && Math.abs(testY) < 0.00041) {
						if (i == 0) {
							TextView myTextView = (TextView) findViewById(R.id.textView1);
							myTextView.setText("Your have selected Sterling Library");
							Locat = "Sterling Library";
							break;
							

						}
						else if (i == 1){
							TextView myTextView = (TextView) findViewById(R.id.textView1);
							myTextView.setText("Your have selected Branford College");
							Locat = "Branford College";
							break;

						}
						 
						
					}
					//if the user did not click within a valid circle, fYndr alerts the user.
					else {
					TextView myTextView = (TextView) findViewById(R.id.textView1);
					myTextView.setText("Please select a valid location by clicking within one of the circles!");
					}
					
							}
				
				//the following try catch sends a text message with the input location, phone number and item description.
				try {
					Intent smsIntent = new Intent(Intent.ACTION_VIEW);
					//the following line defines the recipient/recipients of the text message
					smsIntent.putExtra("address", "9174779589");
					//the following line defines the body of the text message
					smsIntent.putExtra("sms_body", "A " + itemLost2 + " has been lost at " + Locat + " . Please contact " + phoneNumber2 + " to retrieve.");
					smsIntent.setType("vnd.android-dir/mms-sms");
					startActivity(smsIntent);
				} catch (Exception e) {
					Toast.makeText(getApplicationContext(), "SMS failed!",
							Toast.LENGTH_LONG).show();
					e.printStackTrace();
				}

		};   
	});
	

	}
	
}