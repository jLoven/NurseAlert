package com.jackieloven.thebasics;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle TravisIsAwesome) {
		super.onCreate(TravisIsAwesome);
		/**
		 * on create runs when the app starts you can really change this
		 * variable input to whatever you want as long as you reference it back
		 * wherever it was originally used
		 */
		setContentView(R.layout.splash);

		Thread logoTimer = new Thread() {
			/** start a new thread and we're using the splash screen */
			public void run() {
				try {
					/**
					 * want to try something. things are running at the same
					 * time wanna only try something in case it doesn't work
					 */

					Intent menuIntent = new Intent(
							"com.jackieloven.thebasics.MENU");
					/**
					 * you can name the intent whatever, here it is just
					 * "menuIntent" make sure you import intent by hovering over
					 * the red underlined variable you want it to open the menu,
					 * so copy the name from the Android Manifest (bright blue
					 * writing)
					 */
					startActivity(menuIntent);
					/**
					 * use intent you just defined to open menu, matches
					 * Manifest
					 */
				}

				finally {
					finish();
					/** finishes current activity */
				}
			}
		};
		/** end of thread, it has a semicolon, but still within OnCreate */
		logoTimer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
