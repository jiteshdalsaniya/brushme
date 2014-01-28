package com.technotricks.paint.manager;

import android.content.Context;
import android.graphics.Typeface;
import android.widget.TextView;

public class FontManager {

	
	
	public static void setHoloFont(TextView txt,Context context){
		
		Typeface externalFont=Typeface.createFromAsset(	context.getAssets(), "Fonts/HOBOSTD.OTF");
		txt.setTypeface(externalFont);
		
	}
	
}
