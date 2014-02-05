package com.technotricks.paint.ui.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.constants.IResultConstants;

public class FullScreenActivity extends BaseActivity implements IResultConstants {
	
	
	private Context context;

	private Intent i;
	
	String imagePath = "";
	
	private ImageView imageView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_fullscreen);
		context=this;
		
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
		
			imagePath = extras.getString(RESULT_NEW_STRING);
		}
		
		imageView=(ImageView)findViewById(R.id.imagePreview);
		
		imageLoader.displayImage(imagePath, imageView);
	}

}
