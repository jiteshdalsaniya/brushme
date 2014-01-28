package com.technotricks.paint.ui.common;

import com.larswerkman.holocolorpicker.ColorPicker;
import com.larswerkman.holocolorpicker.ColorPicker.OnColorChangedListener;
import com.larswerkman.holocolorpicker.OpacityBar;
import com.larswerkman.holocolorpicker.SVBar;
import com.larswerkman.holocolorpicker.SaturationBar;
import com.larswerkman.holocolorpicker.ValueBar;
import com.technotricks.paint.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ColorPick extends Activity {
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.color_picker_alert);
		
		final TextView textViewColor=(TextView)findViewById(R.id.textView1);
		
		ColorPicker picker = (ColorPicker) findViewById(R.id.picker);
		SVBar svBar = (SVBar) findViewById(R.id.svbar);
		OpacityBar opacityBar = (OpacityBar) findViewById(R.id.opacitybar);
		SaturationBar saturationBar = (SaturationBar) findViewById(R.id.saturationbar);
		ValueBar valueBar = (ValueBar) findViewById(R.id.valuebar);

		picker.addSVBar(svBar);
		picker.addOpacityBar(opacityBar);
		picker.addSaturationBar(saturationBar);
		picker.addValueBar(valueBar);
		
		

		//To get the color
		picker.getColor();

		//To set the old selected color u can do it like this
		picker.setOldCenterColor(picker.getColor());
		// adds listener to the colorpicker which is implemented
		//in the activity
		picker.setOnColorChangedListener(new OnColorChangedListener() {
			
			@Override
			public void onColorChanged(int color) {
				textViewColor.setBackgroundColor(color);
				
			}
		});
		
		
		
		
	}

}