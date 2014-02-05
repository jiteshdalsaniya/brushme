package com.technotricks.paint.ui.common;

import java.io.FileNotFoundException;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.constants.IResultConstants;
import com.technotricks.paint.customclass.FloodFill;
import com.technotricks.paint.manager.Utils;

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
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.fullscreen_menu, menu);

		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

	
		case R.id.action_setasWall:

			hRefresh.sendEmptyMessage(1);

			break;

		case R.id.action_share:

			hRefresh.sendEmptyMessage(4);

			break;

		default:
			break;
		}

		return true;
	}
	
	Handler hRefresh = new Handler() {
		public void handleMessage(Message msg) {
			
			switch (msg.what) {

			case 1:
				
				Utils.setAsWallpaper(context, imagePath);

				break;

			

			

			case 4:
				Utils.share(context, imagePath);
				break;
			default:
				break;
			}
		}
	};

}
