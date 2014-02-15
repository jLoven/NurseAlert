package com.jackieloven.thebasics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class Patient extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient);
		
		ImageView imageBody = (ImageView) findViewById(R.id.imageView1);
		
		imageBody.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				startActivity(new Intent("com.jackieloven.thebasics.PATIENT_DIALOG"));
			}
		});
		
		
		
	}
}
