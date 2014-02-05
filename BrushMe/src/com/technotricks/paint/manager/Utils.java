package com.technotricks.paint.manager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.technotricks.paint.R;
import com.technotricks.paint.constants.IDIRConstants;
import com.technotricks.paint.model.ImagesGridModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;

public class Utils implements IDIRConstants {

	private static final int LOW_DPI_STATUS_BAR_HEIGHT = 19;

	private static final int MEDIUM_DPI_STATUS_BAR_HEIGHT = 25;

	private static final int HIGH_DPI_STATUS_BAR_HEIGHT = 38;

	@SuppressLint("NewApi")
	public static int getDeviceWidth(Context context) {
		int measuredWidth = 0;
		Point size = new Point();
		WindowManager w = ((Activity) context).getWindowManager();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
			w.getDefaultDisplay().getSize(size);
			measuredWidth = size.x;
		} else {
			Display d = w.getDefaultDisplay();
			measuredWidth = d.getWidth();
		}
		return measuredWidth;
	}

	public static String getDate() {
		// TODO Auto-generated method stub

		SimpleDateFormat dateFormat = new SimpleDateFormat(
				"dd-MM-yyyy HH-mm-ss");

		return dateFormat.format(new Date());
	}

	public static String getApplicationName(Context context) {
		int stringId = context.getApplicationInfo().labelRes;
		return context.getString(stringId);
	}

	public static Bitmap getBitmapFromAsset(String strName, Context context) {
		AssetManager assetManager = context.getAssets();
		InputStream istr = null;
		try {
			istr = assetManager.open(ASSERT_FOLDER_NAME + "/" + strName);

		} catch (IOException e) {
			e.printStackTrace();
		}
		Bitmap bitmap = BitmapFactory.decodeStream(istr);
		return bitmap;
	}

	public static void getList(Context context) {
		AssetManager assetMgr = context.getAssets();
		ArrayList<ImagesGridModel> itemList = new ArrayList<ImagesGridModel>();
		try {
			String[] assetsIWant = assetMgr.list(ASSERT_FOLDER_NAME);

			System.out.println("Flags length" + assetsIWant.length);
			ImagesGridModel itemModel;
			for (int i = 0; i < assetsIWant.length; i++) {

				itemModel = new ImagesGridModel();
				itemModel.setId(String.valueOf(i + 1));
				System.out.println("file name "+ assetsIWant[i].substring(0,assetsIWant[i].lastIndexOf('.')));
				//itemModel.setImageName_OR_Path(assetsIWant[i]);
				
				itemModel.setImageName_OR_Path(ASSERT_FILE_DIR+assetsIWant[i]);
				itemList.add(itemModel);
			}

			
			AppPreferenceManager.saveImages(context, itemList);
			System.out.println("flag list size" + itemList.size());
			System.out.println("preference flag list size"+ AppPreferenceManager.getImages(context).size());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<ImagesGridModel> getSdCardFileList(Context context) {
		String filePath = Environment.getExternalStorageDirectory()+ File.separator + SDCARD_FOLDER_NAME;
		ArrayList<ImagesGridModel> imageList = new ArrayList<ImagesGridModel>();
		File dir ;
		
		System.out.println("SD CARD PATH= "+filePath);
		dir = new File(filePath);
		
	
		if(dir.isDirectory())
		{
			
		
			 File[] content = dir.listFiles(); 
			 
				 for (File file : content) {
					ImagesGridModel model=new ImagesGridModel();
					
					model.setImageName_OR_Path(SDCARD_FILE_DIR+file.getAbsolutePath());
					 
					imageList.add(model);
				}
			 
		}
		
		return imageList;
		
	}

	public static String saveImageViewToSdCard(ImageView imageView, Context context)
			throws FileNotFoundException {

		String mPath = "";
		String filePath = "";
		String appName = SDCARD_FOLDER_NAME;//getApplicationName(context);

		mPath = Environment.getExternalStorageDirectory().toString() + "/"
				+ appName;

		Drawable drawable = imageView.getDrawable();
		Rect bounds = drawable.getBounds();
		Bitmap bitmap = Bitmap.createBitmap(bounds.width(), bounds.height(),
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		drawable.draw(canvas);
		OutputStream fOut = null;
		try {
			new File(mPath).mkdir();
			filePath = mPath + "/" + appName + "_" + Utils.getDate() + ".jpg";
			fOut = new FileOutputStream(filePath);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);
			

		} finally {
			if (fOut != null) {
				try {
					fOut.close();
				} catch (IOException e) {
					// report error
				}
			}

		}
		
		AlertManager.shorttoastMessage(context, "Save Image");

		return filePath;
	}
	
	
	public static Bitmap  getfileToBitmap(String path){
		
		
		
		Bitmap myBitmap = BitmapFactory.decodeFile(path);
		return myBitmap;
		
	}
	public static String saveImageView(ImageView imageView, Context context)
			throws FileNotFoundException {

		String mPath = "";
		String filePath = "";
		String appName = "";//getApplicationName(context);

		mPath = Environment.getExternalStorageDirectory().toString() + "/"
				+ appName;

		Drawable drawable = imageView.getDrawable();
		Rect bounds = drawable.getBounds();
		Bitmap bitmap = Bitmap.createBitmap(bounds.width(), bounds.height(),
				Bitmap.Config.ARGB_8888);
		// Point p=new Point();
		//  p.set(5, 5);
		//  bitmap=waterMark(bitmap, "Kishore..", p, Color.BLUE, 90,50,true);
		Canvas canvas = new Canvas(bitmap);
		drawable.draw(canvas);
		OutputStream fOut = null;
		try {
			new File(mPath).mkdir();
			filePath = mPath + "/" + appName + "_temp.jpg";
			fOut = new FileOutputStream(filePath);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fOut);

		} finally {
			if (fOut != null) {
				try {
					fOut.close();
				} catch (IOException e) {
					// report error
				}
			}

		}

		return filePath;
	}
	
	
	

	
	
	public static Bitmap waterMark(Bitmap src, String watermark, Point location, int color, int alpha, int size, boolean underline) {
		//get source image width and height
		int w = src.getWidth();
		int h = src.getHeight();
		
		Bitmap result = Bitmap.createBitmap(w, h, src.getConfig());
		//create canvas object
		Canvas canvas = new Canvas(result);
		//draw bitmap on canvas
		canvas.drawBitmap(src, 0, 0, null);
		//create paint object
		Paint paint = new Paint();
		//apply color
		paint.setColor(color);
		//set transparency
		//paint.setAlpha(alpha);
		//set text size
		paint.setTextSize(size);
		paint.setAntiAlias(true);
		//set should be underlined or not
		paint.setUnderlineText(underline);
		//draw text on given location
		canvas.drawText(watermark, location.x, location.y, paint);
		
		return result;
		}

	public static void share(Context context, String path) {
		Intent shareCaptionIntent = new Intent(Intent.ACTION_SEND);
		shareCaptionIntent.setType("image/*");

		shareCaptionIntent.putExtra(Intent.EXTRA_STREAM,
				Uri.parse("file://" + path));
		
		//shareCaptionIntent.putExtra(Intent.EXTRA_TEXT, "Body text of the new status");

		context.startActivity(Intent.createChooser(shareCaptionIntent,
				"Share Via"));

	}

	

	public static void setAsWallpaper(Context context,String path) {
		
		//SDCARD_FILE_DIR
		
		path=path.replace(SDCARD_FILE_DIR, "");

		WindowManager wm = (WindowManager) context.getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		// Point size = new Point();
		// display.getSize(size);
		int width = display.getWidth();
		int height = display.getHeight();

		DisplayMetrics displayMetrics = new DisplayMetrics();
		((WindowManager)context. getSystemService(Context.WINDOW_SERVICE))
				.getDefaultDisplay().getMetrics(displayMetrics);

		int statusBarHeight;

		switch (displayMetrics.densityDpi) {
		case DisplayMetrics.DENSITY_HIGH:
			statusBarHeight = HIGH_DPI_STATUS_BAR_HEIGHT;
			break;
		case DisplayMetrics.DENSITY_MEDIUM:
			statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
			break;
		case DisplayMetrics.DENSITY_LOW:
			statusBarHeight = LOW_DPI_STATUS_BAR_HEIGHT;
			break;
		default:
			statusBarHeight = MEDIUM_DPI_STATUS_BAR_HEIGHT;
		}
		Bitmap bmp = Bitmap.createScaledBitmap(BitmapFactory.decodeResource(context.getResources(), R.drawable.img_1),
				2 * width, height, true);

		height = height - statusBarHeight;

		Bitmap bitmap = Bitmap.createScaledBitmap(
				BitmapFactory.decodeFile(path), width, height, false);

		Canvas canvas = new Canvas(bmp);
		canvas.drawColor(Color.RED);
		canvas.drawBitmap(bitmap, 0, statusBarHeight, new Paint(Color.WHITE));
		canvas.drawBitmap(bitmap, width, statusBarHeight,
				new Paint(Color.WHITE));
		
		try {
			context.getApplicationContext().setWallpaper(bmp);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		AlertManager.shorttoastMessage(context, "Set Wallpaper");
		
	}
	
	
	public static Bitmap createContrast(Bitmap src, double value) {
		// image size
		int width = src.getWidth();
		int height = src.getHeight();
		// create output bitmap
		Bitmap bmOut = Bitmap.createBitmap(width, height, src.getConfig());
		// color information
		int A, R, G, B;
		int pixel;
		// get contrast value
		double contrast = Math.pow((100 + value) / 100, 2);

		// scan through all pixels
		for(int x = 0; x < width; ++x) {
		    for(int y = 0; y < height; ++y) {
		        // get pixel color
		        pixel = src.getPixel(x, y);
		        A = Color.alpha(pixel);
		        // apply filter contrast for every channel R, G, B
		        R = Color.red(pixel);
		        R = (int)(((((R / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
		        if(R < 0) { R = 0; }
		        else if(R > 255) { R = 255; }

		        G = Color.red(pixel);
		        G = (int)(((((G / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
		        if(G < 0) { G = 0; }
		        else if(G > 255) { G = 255; }

		        B = Color.red(pixel);
		        B = (int)(((((B / 255.0) - 0.5) * contrast) + 0.5) * 255.0);
		        if(B < 0) { B = 0; }
		        else if(B > 255) { B = 255; }

		        // set new pixel color to output bitmap
		        bmOut.setPixel(x, y, Color.argb(A, R, G, B));
		    }
		}

		return bmOut;
		 }
	
	 public static Bitmap convertColorIntoBlackAndWhiteImage(Bitmap orginalBitmap) {
	        ColorMatrix colorMatrix = new ColorMatrix();
	        colorMatrix.setSaturation(0);

	        ColorMatrixColorFilter colorMatrixFilter = new ColorMatrixColorFilter(
	                colorMatrix);

	        Bitmap blackAndWhiteBitmap = orginalBitmap.copy(
	                Bitmap.Config.ARGB_8888, true);

	        Paint paint = new Paint();
	        paint.setColorFilter(colorMatrixFilter);

	        Canvas canvas = new Canvas(blackAndWhiteBitmap);
	        canvas.drawBitmap(blackAndWhiteBitmap, 0, 0, paint);

	        return blackAndWhiteBitmap;
	    }
	 
	 
	 public static int calculateSize(Context context)
	 {
	     // GET SCREEN SIZE
	     Display display = ((Activity) context).getWindowManager().getDefaultDisplay();

	     // HEIGHT
	     int height = display.getHeight();
	     long roundedHeightSize = Math.round((0.2132*height)+27.177);

	     //WIDTH
	     int width = display.getWidth();
	     long roundedWidthSize = Math.round((0.4264*width)-6.9355);


	     return (int)((roundedHeightSize+roundedWidthSize));
	 }

}
