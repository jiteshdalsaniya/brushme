package com.technotricks.paint.ui.common;

import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.manager.AppPreferenceManager;
import com.technotricks.paint.manager.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class Splash extends Activity {

	private Context context;
	private Intent i;

	private final int SPLASH_DISPLAY_LENGTH = 500;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		context = this;

		
		intializeUI();
	}

	private void intializeUI() {
		
		
		if (AppPreferenceManager.getImages(context).size()==0) {
			Utils.getList(context);
		}

		new Handler().postDelayed(new Runnable() {

			public void run() {

				i = new Intent(context, MenuActivity.class);
				startActivity(i);
				finish();
			}
		}, SPLASH_DISPLAY_LENGTH);

	}

}
