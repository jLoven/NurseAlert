package com.jackieloven.thebasics;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class menu extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);

		// setting up the button sound with the MediaPlayer object, can call it
		// whatever
		// here it is called buttonSound
		final MediaPlayer buttonSound = MediaPlayer.create(menu.this,
				R.raw.button_noise);
		// make sure to import MediaPlayer
		// don't forget semicolon!
		// make it final because it complained about a non-final error down
		// below in onClick

		// Setting up the button references
		
		Button btnNurse = (Button) findViewById(R.id.btnNurse);
		Button btnPatient = (Button) findViewById(R.id.btnPatient);
		// in parentheses is what we're going to use is a button, with button
		// methods
		// everything in xml is a view (like buttons, text, pictures) and it's a
		// class
		// find view by ID of that view
		
		
		btnNurse.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonSound.start();
				startActivity(new Intent("com.jackieloven.thebasics.SERVER"));

				// remember, this takes an Intent
				// remember to import intent by hovering
				// and you can use something complex, but also you can make a
				// new intent
				// inside like we did here

			}
		});

		btnPatient.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				buttonSound.start();
				startActivity(new Intent("com.jackieloven.thebasics.CONNECT"));

				// remember, this takes an Intent
				// remember to import intent by hovering
				// and you can use something complex, but also you can make a
				// new intent
				// inside like we did here

			}
		});
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}



}
