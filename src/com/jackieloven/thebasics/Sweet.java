package com.jackieloven.thebasics;

import android.app.Activity;
import android.os.Bundle;

public class Sweet extends Activity {
	//remember to override the method, add onCreate Bundle
	//and extend the activity too, yo

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		
	}

}
