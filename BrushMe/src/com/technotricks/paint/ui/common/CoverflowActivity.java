/*package com.technotricks.paint.ui.common;

import java.util.ArrayList;







import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.constants.IResultConstants;
import com.technotricks.paint.customclass.coverflow.CoverFlow;
import com.technotricks.paint.customclass.coverflow.ReflectingImageAdapter;
import com.technotricks.paint.customclass.coverflow.ResourceImageAdapterNew;
import com.technotricks.paint.customclass.coverflow3d.AppUtils;
import com.technotricks.paint.customclass.coverflow3d.CarouselDataItem;
import com.technotricks.paint.customclass.coverflow3d.CarouselView;
import com.technotricks.paint.customclass.coverflow3d.CarouselViewAdapter;
import com.technotricks.paint.customclass.coverflow3d.Singleton;
import com.technotricks.paint.model.ImagesGridModel;



public class CoverflowActivity extends BaseActivity implements IResultConstants{
	
	private Context context;
	private Intent i;
	
	String imagePath = "";
	
	private ArrayList<ImagesGridModel> sdCardImageList;
	
	private CoverFlow coverFlow;
	
	ResourceImageAdapterNew adapterNew;
	
	
	
	//
	
	Singleton 				m_Inst 					= Singleton.getInstance();
	
	CarouselViewAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_coverflow);
		
		
		
		context = this;

		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			
			sdCardImageList = (ArrayList<ImagesGridModel>)extras.getSerializable(RESULT_COVERFLOW_VALUE);
		}
		
		intializeUI();
		//setListners();
	//	setSelectionManager();
		
		
	}
	private void intializeUI() {
		coverFlow=(CoverFlow)findViewById(R.id.coverflowReflect);
		
		//adapterNew=new ResourceImageAdapterNew(context, sdCardImageList);
		ReflectingImageAdapter adapter=new ReflectingImageAdapter(new ResourceImageAdapterNew(context,sdCardImageList));
		coverFlow.setImageReflectionRatio(0.2f);
		coverFlow.setSpacing(10);
		coverFlow.setAdapter(adapter);
		
		Singleton.getInstance().InitGUIFrame(this);
		
		int padding = m_Inst.Scale(10);
		
		RelativeLayout panel  = new RelativeLayout(this);
	    panel.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT,LayoutParams.FILL_PARENT));
	//	panel.setPadding(padding, padding, padding, padding);
	    panel.setBackgroundDrawable(new GradientDrawable(GradientDrawable.Orientation.TOP_BOTTOM, 
	    		new int[]{Color.WHITE, Color.GRAY}));
	    setContentView(panel); 
	    
	    
		CarouselView carouselView=new CarouselView(this);
		
		
		  ArrayList<CarouselDataItem> Docus = new ArrayList<CarouselDataItem>();
		    for (int i=0;i<sdCardImageList.size();i++) {
		    	CarouselDataItem docu;
		    	String path=sdCardImageList.get(i).getImageName_OR_Path();
		    	
		    	path=path.replaceAll("file://", "");
		       
		        docu = new CarouselDataItem(path, 0, "4th Image "+i);
		        Docus.add(docu);
		    } 
		
		    adapter=new CarouselViewAdapter(context, Docus,m_Inst.Scale(400),m_Inst.Scale(300));
		    carouselView.setSpacing(-1*m_Inst.Scale(160));
		    carouselView.setAdapter(adapter);
		
		    
		    AppUtils.AddView(panel, carouselView, LayoutParams.FILL_PARENT,LayoutParams.WRAP_CONTENT, 
	        		new int[][]{new int[]{RelativeLayout.CENTER_IN_PARENT}},
	        		-1, -1); 
	}
	
	
	

}
*/