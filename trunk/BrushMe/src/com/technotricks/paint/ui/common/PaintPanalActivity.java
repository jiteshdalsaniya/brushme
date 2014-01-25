package com.technotricks.paint.ui.common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

import com.squareup.picasso.Picasso;
import com.technotricks.paint.R;
import com.technotricks.paint.baseactivity.BaseActivity;
import com.technotricks.paint.constants.IResultConstants;
import com.technotricks.paint.customclass.FloodFill;
import com.technotricks.paint.manager.AppPreferenceManager;
import com.technotricks.paint.manager.ColorPickerDialog;
import com.technotricks.paint.manager.ColorPickerDialog.OnColorChangedListener;
import com.technotricks.paint.manager.Utils;

public class PaintPanalActivity extends BaseActivity implements
		OnClickListener, OnTouchListener ,ColorPickerDialog.OnColorChangedListener, IResultConstants{

	private Context context;
	private Intent i;
	private Button btnColorPicker, btnBlue, btnGrey, btnRose;
	private ImageView imgPanel;

	private Matrix matrix = new Matrix();
	private Matrix savedMatrix = new Matrix();
	private PointF start = new PointF();
	private Paint mPaint;
	// Bitmap Items..
	private Bitmap _alteredBitmap;
	private Bitmap bitmap;

	int originalImageOffsetX = 0, originalImageOffsetY = 0, color = 0,newColor = 0;
	
	
	
	//Save...
	
	 File imageFile, directory;
	 String mPath;
	 
		String dt;

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
		btnColorPicker = (Button) findViewById(R.id.btnColorPicker);
		btnBlue = (Button) findViewById(R.id.btnBlue);
		btnGrey = (Button) findViewById(R.id.btnGrey);
		btnRose = (Button) findViewById(R.id.btnRose);

		imgPanel = (ImageView) findViewById(R.id.imgPanel);

		 newColor = getResources().getColor(R.color.rose);
		 mPaint=new Paint();
		 
		 
		 imgPanel.setImageBitmap(Utils.getBitmapFromAsset(AppPreferenceManager.getBrand(context, 0).getImageName(),context));
		 
		
	}

	private void initializeCanvas() {

		BitmapDrawable drawable = (BitmapDrawable) imgPanel.getDrawable();
		Bitmap bmp = drawable.getBitmap();
		try {
			_alteredBitmap = Bitmap.createBitmap(bmp.getWidth(),
					bmp.getHeight(), Bitmap.Config.ARGB_8888);
		} catch (Exception e) {
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
		btnColorPicker.setOnClickListener(this);
		btnRose.setOnClickListener(this);
		btnBlue.setOnClickListener(this);
		btnGrey.setOnClickListener(this);

	}
	
	 @Override
	    public boolean onCreateOptionsMenu(Menu menu) {
	        MenuInflater inflater = getMenuInflater();
	        inflater.inflate(R.menu.paint, menu);
	 
	        return super.onCreateOptionsMenu(menu);
	    }
	 
	 @Override
	  public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	    
	    case R.id.action_new:
	    
	    	i=new Intent(context,ImageListActivity.class);
	    	startActivityForResult(i, RESULT_NEW_IMAGE);
	    	
	    	  break;
	    case R.id.action_save:
	    	
	    	//hRefresh.sendEmptyMessage(2);
	    	
	    	new Thread() 
			{
				public void run() 
				{
					try 
					{
					// This is just a tmp sleep so that we can emulate
					// something loading
					Thread.sleep(1000);
					// Use this handler so than you can update the UI from a
					// thread
					hRefresh.sendEmptyMessage(2);
					} catch (Exception e) {
										}
				}
			}.start();
	     
	      break;
	    case R.id.action_setasWall:
	    	
	    	Bitmap bm;
	        Boolean result;
	        FileOutputStream fos;
	    	 Intent i=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
             startActivityForResult(i, 100);
             imgPanel.setDrawingCacheEnabled(true);
             bm = imgPanel.getDrawingCache();

             try {
                 fos = new FileOutputStream("sdcard/image.jpg");
                 result=bm.compress(CompressFormat.JPEG, 75, fos);

                 fos.flush();
                 fos.close();
             } catch (FileNotFoundException e) {
                 // TODO Auto-generated catch block

                 e.printStackTrace();
             } catch (IOException e) {
                 // TODO Auto-generated catch block

                 e.printStackTrace();
             }

	     
	      break;
	      
	    case R.id.action_share:
	    	
	    	hRefresh.sendEmptyMessage(2);
		     
		      break;


	    default:
	      break;
	    }

	    return true;
	  } 

	@Override
	public void onClick(View v) {
		if (v == btnColorPicker) {
			
			//newColor=getResources().getColor(R.color.red);
			
			//new ColorPicker(context, this, "", Color.BLACK, Color.WHITE).show();
		//	new ColorPickerDialog(context, this, "", Color.BLACK, Color.WHITE).show();
			
			mPaint.setColor(Color.BLUE);
			new ColorPickerDialog(context, this, mPaint.getColor()).show();
			

		} else if (v == btnRose) {
			
			newColor=getResources().getColor(R.color.rose);

		} else if (v == btnBlue) {
			newColor=getResources().getColor(R.color.blue);

		} else if (v == btnGrey) {
			newColor=getResources().getColor(R.color.grey);

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
				color = bitmap.getPixel(originalImageOffsetX,
						originalImageOffsetY);
			} catch (Exception e) {
				e.printStackTrace();
			}
			savedMatrix.set(matrix);
			start.set(event.getX(), event.getY());
			hRefresh.sendEmptyMessage(3);

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

	Handler hRefresh = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			
			case 2:
				try {
					Utils.saveDrawable(imgPanel, context);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;

			case 3:
				
				FloodFill floodfill = new FloodFill(bitmap, color, newColor);
				floodfill.fill(originalImageOffsetX, originalImageOffsetY);

				imgPanel.invalidate();

				break;
			default:
				break;
			}
		}
	};

	@Override
	public void colorChanged(int color) {
		// TODO Auto-generated method stub
		
		newColor=color;
		
	}
	
	public void taptoshare(ImageView imageView)
	{  
	    View content = imageView;
	    content.setDrawingCacheEnabled(true);
	        Bitmap bitmap = content.getDrawingCache();
	        
	        File root = Environment.getExternalStorageDirectory();
	        File file = new File(root.getAbsolutePath() + "/BBB/image.jpg");
	     //   File file = new File("/BRESH/Camera/image.jpg");
	        try 
	        {
	            file.createNewFile();
	            FileOutputStream ostream = new FileOutputStream(file);
	            bitmap.compress(CompressFormat.JPEG, 100, ostream);
	            ostream.close();
	        } 
	        catch (Exception e) 
	        {
	            e.printStackTrace();
	        }


	    Intent shareIntent = new Intent(Intent.ACTION_SEND);
	    Uri phototUri = Uri.parse("/DCIM/Camera/image.jpg");
	    shareIntent.setData(phototUri);
	    shareIntent.setType("image/*");
	    shareIntent.putExtra(Intent.EXTRA_STREAM, phototUri);
	    startActivity(Intent.createChooser(shareIntent, "Share Via"));

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		
		
		if ((requestCode == RESULT_NEW_IMAGE)&& (resultCode == RESULT_NEW_IMAGE)) {
			
			
			String imageName = data.getStringExtra(RESULT_NEW_STRING);
			
			
			 imgPanel.setImageBitmap(Utils.getBitmapFromAsset(imageName,context));
			
			 initializeCanvas();
		}
	}   

	
	
	
	

}
