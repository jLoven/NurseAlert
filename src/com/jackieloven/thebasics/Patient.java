package com.jackieloven.thebasics;

import java.net.Socket;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Patient extends Activity implements Networked {
	public static String ip;

	private Dialog dialog;
	private NetComm netComm;

	private enum PatientScreen {
		HURT, MISC
	}
	PatientScreen patientScreen;

	private void callNurse(String bodyPart) {
		HurtMsg msg = new HurtMsg();
		msg.bodyPart = bodyPart;
		netComm.write(msg);
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

	private boolean inBigCircle(MotionEvent e, double centerX, double centerY) {
		int width = getWindowManager().getDefaultDisplay().getWidth();
		int height = getWindowManager().getDefaultDisplay().getHeight();

		if ((e.getX() - centerX * width) * (e.getX() - centerX * width)
				+ (e.getY() - centerY * height) * (e.getY() - centerY * height) < (height / 4)
				* (height / 4)) {
			return true;
		}
		return false;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.patient);

		patientScreen = PatientScreen.HURT;

		ImageView imageBody = (ImageView) findViewById(R.id.imageView1);
		
		new Thread(new NetworkThread()).start();

		imageBody.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent e) {

				if (dialog == null || !dialog.isShowing()) {
					int width = getWindowManager().getDefaultDisplay().getWidth();
					int height = getWindowManager().getDefaultDisplay().getHeight();
					if (patientScreen == PatientScreen.HURT) {
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
						if (e.getX() > width * 7 / 8) {
							imageBody.setImageResource(R.drawable.background_3);
							patientScreen = PatientScreen.MISC;
							return true;
						}
					}
					else if (patientScreen == PatientScreen.MISC) {
						if (inBigCircle(e, 0.5, 0.25) {
							netComm.write(new RestroomMsg());
							dialog = new Dialog(this);
							dialog.setTitle("A nurse will come to assist you.");
							dialog.show();
						}
						if (inBigCircle(e, 0.5, 0.75) {
							netComm.write(new QuestionMsg());
							dialog = new Dialog(this);
							dialog.setTitle("A nurse will come to assist you.");
							dialog.show();
						}
						if (e.getX() < width / 8) {
							imageBody.setImageResource(R.drawable.background_2);
							patientScreen = PatientScreen.HURT;
							return true;
						}
					}
				}
				return false;
			}
		});

	}
	
	public void msgReceived(Object msgObj, NetComm sender) {
		if (msgObj instanceof CloseConnectionMsg) {
			netComm.close();
			netComm = null;
			finish();
		}
		else {
			System.out.println("Warning: received unknown message " + msgObj);
		}
	}

	private class NetworkThread implements Runnable {
		public void run() {
			try {
				netComm = new NetComm(new Socket(ip, Server.PORT), Patient.this);
			}
			catch (Exception ex) {
				netComm = null;
				finish();
			}
		}
	}
}
