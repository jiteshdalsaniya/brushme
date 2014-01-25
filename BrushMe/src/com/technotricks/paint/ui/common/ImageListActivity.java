package com.technotricks.paint.ui.common;

import java.util.ArrayList;

import com.technotricks.paint.R;
import com.technotricks.paint.adapter.ImageGridAdapter;
import com.technotricks.paint.constants.IResultConstants;
import com.technotricks.paint.manager.AppPreferenceManager;
import com.technotricks.paint.model.ImagesGridModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class ImageListActivity extends Activity implements IResultConstants{
	
	private Context context;

	private Intent i;
	
	private GridView gridImages;
	
	private ImageGridAdapter gridAdapter; ;
	
	private ArrayList<ImagesGridModel> imagesGridList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.listimages);
		context=this;
		
		intializeUI();
		setListners();
		setSelectionManager();
		
		setData();
		
	}

	private void intializeUI() {
		gridImages=(GridView)findViewById(R.id.gridImages);
		
		
		
		
		
	}
	private void setListners() {
		gridImages.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				
				Intent intent=new Intent();
				intent.putExtra(RESULT_NEW_STRING, imagesGridList.get(pos).getImageName());
				setResult(RESULT_NEW_IMAGE, intent);
				finish();
			}
		});
		
	}
	private void setSelectionManager() {
		// TODO Auto-generated method stub
		
	}
	
	private void setData() {
		imagesGridList=AppPreferenceManager.getBrands(context);
		
		gridAdapter= new ImageGridAdapter(context, 0, imagesGridList, 2);
		gridImages.setNumColumns(2);
		
		gridImages.setAdapter(gridAdapter);
		
	}

}
