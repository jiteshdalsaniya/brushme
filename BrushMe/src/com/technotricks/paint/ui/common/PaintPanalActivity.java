package com.technotricks.paint.ui.common;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;


import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.customclass.FloodFill;


public class PaintPanalActivity extends BaseActivity implements OnClickListener ,OnTouchListener{

	private Context context;
	private Intent i;
	private Button btnRed, btnBlue, btnGrey, btnBlack;
	private ImageView imgPanel;

	 Matrix matrix = new Matrix();
	 Matrix savedMatrix = new Matrix();
	 PointF start = new PointF();
	//Bitmap Items..
	private Bitmap _alteredBitmap;
	Bitmap bitmap;
	
	
	private DrawAsync drawAsync;
	
	int  originalImageOffsetX = 0, originalImageOffsetY = 0,color = 0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_paintpanal);

		context = this;

		setupAd();
		intializeUI();
		setListner();
		
		initializeCanvas();          
	}

	

	private void intializeUI() {
		btnRed = (Button) findViewById(R.id.btnRed);
		btnBlue = (Button) findViewById(R.id.btnBlue);
		btnGrey = (Button) findViewById(R.id.btnGrey);
		btnBlack = (Button) findViewById(R.id.btnBlack);
		
		imgPanel=(ImageView)findViewById(R.id.imgPanel);
		
		drawAsync=new DrawAsync();

	}
	
	private void initializeCanvas() {
		
		BitmapDrawable drawable = (BitmapDrawable) imgPanel.getDrawable();
		Bitmap bmp = drawable.getBitmap();
		try 
		{
			_alteredBitmap = Bitmap.createBitmap(bmp.getWidth(),
					bmp.getHeight(), Bitmap.Config.ARGB_8888);
		} catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		Canvas canvas = new Canvas(_alteredBitmap);
		Paint paint = new Paint();
		Matrix matrix = new Matrix();
		canvas.drawBitmap(bmp, matrix, paint);

		imgPanel.setImageBitmap(_alteredBitmap);
		imgPanel.setOnTouchListener(this);
		
	}

	private void setListner() {
		btnRed.setOnClickListener(this);
		btnBlack.setOnClickListener(this);
		btnBlue.setOnClickListener(this);
		btnGrey.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		if (v == btnRed) {

		} else if (v == btnBlack) {

		} else if (v == btnBlue) {

		} else if (v == btnGrey) {

		}

	}



	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		ImageView view = (ImageView) imgPanel;
		
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			
			System.out.println("ACTION_DOWN");
			
			BitmapDrawable drawable = (BitmapDrawable) imgPanel.getDrawable();
			bitmap = drawable.getBitmap();
			Rect imageBounds = new Rect();
			imgPanel.getDrawingRect(imageBounds);

			// original height and width of the bitmap
			int intrinsicHeight = drawable.getIntrinsicHeight();
			int intrinsicWidth = drawable.getIntrinsicWidth();

			// height and width of the visible (scaled) image
			int scaledHeight = imageBounds.height();
			int scaledWidth = imageBounds.width();

			// Find the ratio of the original image to the scaled image
			// Should normally be equal unless a disproportionate scaling
			// (e.g. fitXY) is used.
			float heightRatio = (float) intrinsicHeight / scaledHeight;
			float widthRatio = (float) intrinsicWidth / scaledWidth;

			// do whatever magic to get your touch point
			// MotionEvent event;

			// get the distance from the left and top of the image bounds
			float scaledImageOffsetX = event.getX() - imageBounds.left;
			float scaledImageOffsetY = event.getY() - imageBounds.top;

			// scale these distances according to the ratio of your scaling
			// For example, if the original image is 1.5x the size of the scaled
			// image, and your offset is (10, 20), your original image offset
			// values should be (15, 30).
			originalImageOffsetX = Math.round(scaledImageOffsetX * widthRatio);
			originalImageOffsetY = Math.round(scaledImageOffsetY * heightRatio);

			try {
				color = bitmap.getPixel(originalImageOffsetX,originalImageOffsetY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			 savedMatrix.set(matrix);
			 start.set(event.getX(), event.getY());
			 //lmib.getStatus() == AsyncTask.Status.RUNNING
			
			 DrawAsync drawAsync=new DrawAsync();
			 
			 drawAsync.execute();
			 
			// hRefresh.sendEmptyMessage(3);
			
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			
			System.out.println("ACTION_POINTER_DOWN");
			
		    break;
		    
		  case MotionEvent.ACTION_UP:
		  case MotionEvent.ACTION_POINTER_UP:
			  
			  break;
			  
		  case MotionEvent.ACTION_MOVE:
			  
			  break;
			 
		default:
			
			
			
			
		    break;
			 
		}
		
		
		return true;
	}
	
	
	Handler hRefresh = new Handler() 
	{
		public void handleMessage(Message msg) 
		{
			switch (msg.what) 
			{
			
			case 3:
				int newcolor = getResources().getColor(R.color.rose);
				FloodFill floodfill = new FloodFill(bitmap, color,newcolor);
				floodfill.fill(originalImageOffsetX,originalImageOffsetY);
				
				imgPanel.invalidate();
				
				break;
			default:
				break;
			}
		}
	};
	private class DrawAsync extends AsyncTask<String, Integer, Boolean>{

		
		
		protected void onPostExecute(Boolean result) {
			
			super.onPostExecute(result);
			imgPanel.invalidate();
			System.out.println("Post");
		}
		
		

		



		@Override
		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			super.onProgressUpdate(values);
			imgPanel.invalidate();
			System.out.println("Update");
		}







		@Override
		protected void onPreExecute() {
			
			super.onPreExecute();
			publishProgress(1);
			
			System.out.println("Pree");
		}

		@Override
		protected Boolean doInBackground(String... params) {
			System.out.println("Doing");
			int newcolor = getResources().getColor(R.color.rose);
			FloodFill floodfill = new FloodFill(bitmap, color,newcolor);
			floodfill.fill(originalImageOffsetX,originalImageOffsetY);
			return null;
		}
		
	}

}
