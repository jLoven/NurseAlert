package com.jackieloven.thebasics;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Connect extends Activity {
	TextView txtAddress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.connect);
		txtAddress = (TextView) findViewById(R.id.txtAddress);
		Button btnConnect = (Button) findViewById(R.id.btnConnect);

		btnConnect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				Patient.ip = txtAddress.getText().toString();
				startActivity(new Intent("com.jackieloven.thebasics.PATIENT"));
			}
		});

	}
}
