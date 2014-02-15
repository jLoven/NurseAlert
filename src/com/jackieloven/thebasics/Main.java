package com.jackieloven.thebasics;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;



public class Main extends Activity {

	MediaPlayer logoMusic;

	/**
	 * logoMusic can now be used everywhere, not just where it's defined in
	 * onCreate
	 * 
	 * @param TravisIsAwesome
	 *            and don't redefine it down there now
	 */

	@Override
	protected void onCreate(Bundle TravisIsAwesome) {
		super.onCreate(TravisIsAwesome);
		/**
		 * on create runs when the app starts you can really change this
		 * variable input to whatever you want as long as you reference it back
		 * wherever it was originally used
		 */
		setContentView(R.layout.splash);

		logoMusic = MediaPlayer.create(Main.this, R.raw.cant_hold_us);
		logoMusic.start();

		Thread logoTimer = new Thread() {
			/** start a new thread and we're using the splash screen */
			public void run() {
				try {
					/**
					 * want to try something. things are running at the same
					 * time wanna only try something in case it doesn't work
					 */
					sleep(3500);
					/** sleep for 3.5 seconds */
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
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					/**
					 * since "sleep" has an error, hover over it and insert
					 * catch
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
		logoMusic.release();
		/**
		 * LOL release: "kicks the sound down that hole in 300" just going to
		 * stop the sound, too, if the sound is more than 5 seconds
		 */
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
