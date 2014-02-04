package com.technotricks.paint.ui.common;

import java.util.Random;

import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.manager.AppPreferenceManager;
import com.technotricks.paint.manager.Utils;

import android.os.Bundle;
import android.os.Handler;
import android.widget.RelativeLayout;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class SplashAnimated extends Activity {

	private Context context;
	private Intent i;

	private final int SPLASH_DISPLAY_LENGTH = 5000;
	
	private RelativeLayout bg_color;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_anim);
		
		context = this;

		
		intializeUI();
	}

	private void intializeUI() {
		
		Random rnd = new Random(); 
		int color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));   
		
		bg_color=(RelativeLayout)findViewById(R.id.bg_color);
		bg_color.setBackgroundColor(color);
		
		
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
