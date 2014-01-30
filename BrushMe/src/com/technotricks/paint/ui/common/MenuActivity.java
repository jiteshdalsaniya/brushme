package com.technotricks.paint.ui.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ToggleButton;


import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.constants.IIntentConstants;
import com.technotricks.paint.manager.AppPreferenceManager;
import com.technotricks.paint.manager.FontManager;
import com.technotricks.paint.manager.SelectorManager;
import com.technotricks.paint.manager.SoundManager;
import com.technotricks.paint.manager.Utils;
import com.technotricks.paint.ui.common.ColorPick.OnColorCodeChangedListener;

public class MenuActivity extends BaseActivity implements OnClickListener,
		IIntentConstants {

	private Context context;
	private Intent i;

	private Button btnStart;
	private Button btnMoreImages;
	private Button btnSavedImages;
	private Button btnMoreApps;
	private Button btnSetting;
	
	private ToggleButton tglSound;
	
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

		if(AppPreferenceManager.getSoundState(context)){
			
			SoundManager.instance().startBackgroundMusic();
		}
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		SoundManager.instance().stopBackgroundMusic();
		
	}

	private void selectionManager() {
		SelectorManager.setBackground(btnStart, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button5,
						R.drawable.button5_press));
		
		SelectorManager.setBackground(btnSavedImages, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button2,
						R.drawable.button2_press));
		
		
		SelectorManager.setBackground(btnMoreImages, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button1,
						R.drawable.button1_press));
		
		
		
		SelectorManager.setBackground(btnMoreApps, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button4,
						R.drawable.button4_press));
		
		SelectorManager.setBackground(btnSetting, SelectorManager
				.getButtonDrawableByScreenCathegory(context,
						R.drawable.button3,
						R.drawable.button3_press));
		
		SelectorManager.setBackground(tglSound, SelectorManager.getToggleButtonSelection(context, R.drawable.sound_on, R.drawable.sound_off));
	
		
		
		FontManager.setHoloFont(btnStart, context);
		FontManager.setHoloFont(btnSavedImages, context);
		FontManager.setHoloFont(btnMoreImages, context);
		FontManager.setHoloFont(btnMoreApps, context);
		FontManager.setHoloFont(btnSetting, context);
		
	}

	private void intializeUI() {
		btnStart = (Button) findViewById(R.id.btnStart);

		btnMoreImages = (Button) findViewById(R.id.btnMoreImages);
		btnSavedImages = (Button) findViewById(R.id.btnSavedImages);

		btnMoreApps = (Button) findViewById(R.id.btnMoreApps);
		btnSetting = (Button) findViewById(R.id.btnSetting);
		
		tglSound=(ToggleButton)findViewById(R.id.tglSound);
		
		tglSound.setChecked(AppPreferenceManager.getSoundState(context));
		
		
	}

	private void setListner() {
		btnStart.setOnClickListener(this);
		btnMoreImages.setOnClickListener(this);
		btnSavedImages.setOnClickListener(this);
		btnMoreApps.setOnClickListener(this);
		btnSetting.setOnClickListener(this);
		
		tglSound.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton arg0, boolean isChecked) {
				
				AppPreferenceManager.saveSoundState(context, isChecked);

				if(isChecked){
					SoundManager.instance().startBackgroundMusic();
				}
				else{
					SoundManager.instance().stopBackgroundMusic();
				}
				
				
			}
		});
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
			
		}
		 else if (v == btnSetting) {
			 
			
			}

	}

}
