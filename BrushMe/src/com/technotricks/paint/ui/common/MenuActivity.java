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
import com.technotricks.paint.manager.Utils;

public class MenuActivity extends BaseActivity implements OnClickListener , IIntentConstants{
	
	private Context context;
	private Intent i;
	
	private Button btnStart;
	private Button btnMoreImages;
	private Button btnSavedImages;

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_menu);
		context=this;
		
		 setupAd();
		intializeUI();
		setListner();
		
	}

	

	private void intializeUI() {
		btnStart=(Button)findViewById(R.id.btnStart);
		
		btnMoreImages=(Button)findViewById(R.id.btnMoreImages);
		btnSavedImages=(Button)findViewById(R.id.btnSavedImages);
	}
	
	private void setListner() {
		btnStart.setOnClickListener(this);
		btnMoreImages.setOnClickListener(this);
		btnSavedImages.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		if(v==btnStart){
			i=new Intent(context,PaintPanalActivity.class);
			startActivity(i);
		}
		
		else if (v==btnMoreImages) {
			i=new Intent(context,ImageListActivity.class);
			i.putExtra(INTENT_IMAGE_TYPE, INTENT_IMAGE_LIST);
			startActivity(i);
		}
		
		else if (v==btnSavedImages) {
			i=new Intent(context,ImageListActivity.class);
			
			i.putExtra(INTENT_IMAGE_TYPE, INTENT_IMAGE_SAVE_LIST);
			startActivity(i);
			
			//System.out.println("SD CARD"+Utils.getSdCardFileList(context).size());
		}
		
	}

}
