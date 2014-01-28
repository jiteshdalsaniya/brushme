package com.technotricks.paint.ui.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.constants.IIntentConstants;
import com.technotricks.paint.manager.SelectorManager;
import com.technotricks.paint.manager.Utils;

public class MenuActivity extends BaseActivity implements OnClickListener,
		IIntentConstants {

	private Context context;
	private Intent i;

	private Button btnStart;
	private Button btnMoreImages;
	private Button btnSavedImages;
	private Button btnMoreApps;
	private Button btnSetting;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_menu);
		context = this;

		setupAd();
		intializeUI();
		selectionManager();
		setListner();

	}

	private void selectionManager() {
		SelectorManager.setBackground(btnStart, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button1,
						R.drawable.button1_press));
		
		
		SelectorManager.setBackground(btnMoreImages, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button2,
						R.drawable.button2_press));
		
		SelectorManager.setBackground(btnSavedImages, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button3,
						R.drawable.button3_press));
		
		SelectorManager.setBackground(btnMoreApps, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button4,
						R.drawable.button4_press));
		
		SelectorManager.setBackground(btnSetting, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button5,
						R.drawable.button5_press));
		
	}

	private void intializeUI() {
		btnStart = (Button) findViewById(R.id.btnStart);

		btnMoreImages = (Button) findViewById(R.id.btnMoreImages);
		btnSavedImages = (Button) findViewById(R.id.btnSavedImages);

		btnMoreApps = (Button) findViewById(R.id.btnMoreApps);
		btnSetting = (Button) findViewById(R.id.btnSetting);
	}

	private void setListner() {
		btnStart.setOnClickListener(this);
		btnMoreImages.setOnClickListener(this);
		btnSavedImages.setOnClickListener(this);
		btnMoreApps.setOnClickListener(this);
		btnSetting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		if (v == btnStart) {
			i = new Intent(context, PaintPanalActivity.class);
			startActivity(i);
		}

		else if (v == btnMoreImages) {
			i = new Intent(context, ImageListActivity.class);
			i.putExtra(INTENT_IMAGE_TYPE, INTENT_IMAGE_LIST);
			startActivity(i);
		}

		else if (v == btnSavedImages) {
			i = new Intent(context, ImageListActivity.class);

			i.putExtra(INTENT_IMAGE_TYPE, INTENT_IMAGE_SAVE_LIST);
			startActivity(i);

			// System.out.println("SD CARD"+Utils.getSdCardFileList(context).size());
		} else if (v == btnMoreApps) {
			i = new Intent(context, ColorPick.class);

			startActivity(i);
		}
		 else if (v == btnSetting) {
				i = new Intent(context, ColorPick.class);

				startActivity(i);
			}

	}

}
