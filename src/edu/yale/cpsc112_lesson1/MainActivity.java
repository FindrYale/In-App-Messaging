package edu.yale.cpsc112_lesson1;

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
		setContentView(R.layout.activity_main);

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
		});
	}
}