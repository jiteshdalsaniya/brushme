package com.technotricks.paint.ui.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;

public class MenuActivity extends BaseActivity implements OnClickListener{
	
	private Context context;
	private Intent i;
	
	private Button btnStart;
	
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

	private void setListner() {
		btnStart.setOnClickListener(this);
		
	}

	private void intializeUI() {
		btnStart=(Button)findViewById(R.id.btnStart);
		
	}

	@Override
	public void onClick(View v) {
		if(v==btnStart){
			i=new Intent(context,PaintPanalActivity.class);
			startActivity(i);
		}
		
	}

}
