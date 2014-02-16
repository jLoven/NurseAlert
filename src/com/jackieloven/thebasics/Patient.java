package com.jackieloven.thebasics;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Patient extends Activity {

	Dialog dialog;

	private void callNurse(String bodyPart) {

		dialog = new Dialog(this);
		dialog.setTitle("Your " + bodyPart + " hurts. A nurse will come soon.");
		dialog.show();
	}

	private boolean inCircle(MotionEvent e, double centerX, double centerY) {
		int height = getWindowManager().getDefaultDisplay().getHeight();

		if ((e.getX() - centerX * height) * (e.getX() - centerX * height)
				+ (e.getY() - centerY * height) * (e.getY() - centerY * height) < (height / 16)
				* (height / 16)) {
			return true;
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient);

		ImageView imageBody = (ImageView) findViewById(R.id.imageView1);

		imageBody.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent e) {

				if (dialog == null || !dialog.isShowing()) {
					if (inCircle(e, 0.3, 0.05)) {
						callNurse("head");
						return true;
					}
					if (inCircle(e, 0.35, 0.27)) {
						callNurse("chest");
						return true;
					}
					if (inCircle(e, 0.2, 0.5)) {
						callNurse("hand");
						return true;
					}
					if (inCircle(e, 0.35, 0.4)) {
						callNurse("stomach");
						return true;
					}
					if (inCircle(e, 0.48, 0.5)) {
						callNurse("hand");
						return true;
					}
					if (inCircle(e, 0.27, 0.9)) {
						callNurse("foot");
						return true;
					}
					if (inCircle(e, 0.37, 0.9)) {
						callNurse("foot");
						return true;
					}
				}
				return false;
			}
		});

	}
}
