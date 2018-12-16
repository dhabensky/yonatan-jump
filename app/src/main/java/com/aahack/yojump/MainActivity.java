package com.aahack.yojump;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
	private static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1001;
	private Button startButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		startButton = findViewById(R.id.start);
		startButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				checkPermission();

                /*Intent intent = new Intent(MainActivity.this, DhabenskyActivity.class);
                startActivity(intent);*/
			}
		});
	}

	private void checkPermission() {
		// Here, thisActivity is the current activity
		if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
			ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.ACCESS_FINE_LOCATION },
					MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

		}
		else {
			Intent intent = new Intent(MainActivity.this, DhabenskyActivity.class);
			startActivity(intent);
		}
	}

	@Override
	public void onRequestPermissionsResult(int requestCode,
	                                       String permissions[], int[] grantResults) {
		switch (requestCode) {
			case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
				// If request is cancelled, the result arrays are empty.
				if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
					// permission was granted, yay! Do the
					// contacts-related task you need to do.
					Intent intent = new Intent(MainActivity.this, DhabenskyActivity.class);
					startActivity(intent);
				} else {
					// permission denied, boo! Disable the
					// functionality that depends on this permission.
					Intent intent = new Intent(MainActivity.this, DhabenskyActivity.class);
					startActivity(intent);
				}
				return;
			}

			// other 'case' lines to check for other
			// permissions this app might request.
		}
	}


}
