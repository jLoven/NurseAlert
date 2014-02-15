package com.jackieloven.thebasics;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class SecondScreen extends Activity implements OnCheckedChangeListener {
	// make sure you implement the OnCheckedChangeListener, do the RadioGroup
	// version
	// then the SecondScreen gets an error
	// click add unimplemented methods

	// now make some variable to use in the classes
	TextView textOut;
	EditText textIn;
	RadioGroup gravityG, styleG;

	// gravity group/ ganster
	// and now there are 2 variable (separate with comma)
	// kinda useless on their own though

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.second_screen);
		textOut = (TextView) findViewById(R.id.tvChange);
		// given the textOut a reference
		textIn = (EditText) findViewById(R.id.textEdit1);
		gravityG = (RadioGroup) findViewById(R.id.rgGravity);
		gravityG.setOnCheckedChangeListener(this);
		styleG = (RadioGroup) findViewById(R.id.rgStyle);
		styleG.setOnCheckedChangeListener(this);
		Button gen = (Button) findViewById(R.id.bGenerate);

		gen.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				textOut.setText(textIn.getText());
				// this sets the text it outputs to be the same as the input
				// text
				// use getText, you could use quotations, but you want the same
				// text directly from the input
			}
		});

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub

		switch (checkedId) {
		// set up each case (in case it is radio button left, do this
		case R.id.rbLeft:
			textOut.setGravity(Gravity.LEFT);
			break;
		case R.id.rbCenter:
			textOut.setGravity(Gravity.CENTER);
			break;
		case R.id.rbRight:
			textOut.setGravity(Gravity.RIGHT);
			break;
		case R.id.rbNormal:
			textOut.setTypeface(Typeface.defaultFromStyle(Typeface.NORMAL),
					Typeface.NORMAL);
			break;
		case R.id.rbBold:
			textOut.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD),
					Typeface.BOLD);
			break;
		case R.id.rbItalic:
			textOut.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC),
					Typeface.ITALIC);
			break;
		}

	}

}
